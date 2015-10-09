/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myfileupload;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet to handle File upload request from Client
 * @author Javin Paul
 */
public class FileUploadHandler extends HttpServlet {
    private final String UPLOAD_DIRECTORY = "/home/ubuntu/workspace/";
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String myMessage="";
        
        String destinationLastName = "";
        String destinationFirstName = "";
        String destinationStreetName = "";
        String destinationStreetNumber = "";
        String destinationZipCode = "";
        String destinationCity = "";
        String destinationEmail = "";
        
        String senderLastName = "";
        String senderFirstName = "";
        String senderStreetName = "";
        String senderStreetNumber = "";
        String senderZipCode = "";
        String senderCity = "";        
        String senderEmail = "";
        String senderPassword = "";
        
        int id = 0;
        int idMembers = 0;
      
        //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        //String name = new File(item.getName()).getName();
                        //item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                    }else{
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString();
                        
                        System.err.println(fieldName);
                        System.err.println(fieldValue);
                        //get form parameters
                        if(fieldName.equals("destinationlastname"))
                            destinationLastName = fieldValue;
                        if(fieldName.equals("destinationfirstname"))
                            destinationFirstName = fieldValue;
                        if(fieldName.equals("destinationstreetname"))
                            destinationStreetName = fieldValue;
                        if(fieldName.equals("destinationstreetnumber"))
                            destinationStreetNumber = fieldValue;
                        if(fieldName.equals("destinationzipcode"))
                            destinationZipCode = fieldValue;
                        if(fieldName.equals("destinationcity"))
                            destinationCity = fieldValue;
                        if(fieldName.equals("destinationemail"))
                            destinationEmail = fieldValue;
                        if(fieldName.equals("senderlastname"))
                            senderLastName = fieldValue;
                        if(fieldName.equals("senderfirstname"))
                            senderFirstName = fieldValue;
                        if(fieldName.equals("senderstreetname"))
                            senderStreetName = fieldValue;
                        if(fieldName.equals("senderstreetnumber"))
                            senderStreetNumber = fieldValue;
                        if(fieldName.equals("senderzipcode"))
                            senderZipCode = fieldValue;
                        if(fieldName.equals("sendercity"))
                            senderCity = fieldValue;
                        if(fieldName.equals("senderemail"))
                            senderEmail = fieldValue;
                        if(fieldName.equals("senderpassword"))
                            senderPassword = fieldValue;
                                                
                        
                    }
                }
                
                //write to database
                try{ 
                    Class.forName("com.mysql.jdbc.Driver");
                }catch(ClassNotFoundException e){
                    System.out.println(e);
                }

                try{
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/c9", "janblonde", "");
                    
                    String SQLfind = "SELECT id from Members where email = ?";
                    PreparedStatement statementFind = con.prepareStatement(SQLfind);
                    statementFind.setString(1,senderEmail);
                    ResultSet rsFind = statementFind.executeQuery();
                    
                    if(rsFind.next()){
                        idMembers = rsFind.getInt(1);
                    }else{
                
                        String SQLmembers = "INSERT INTO Members(first_name,last_name,email,pass) VALUES (?,?,?,?)";
                        
                        PreparedStatement statementMembers = con.prepareStatement(SQLmembers,Statement.RETURN_GENERATED_KEYS);
                        statementMembers.setString(1,senderFirstName);
                        statementMembers.setString(2,senderLastName);
                        statementMembers.setString(3,senderEmail);
                        
                        String hashed = BCrypt.hashpw(senderPassword, BCrypt.gensalt());
                        statementMembers.setString(4,hashed);
                        
                        statementMembers.execute();
                        
                        ResultSet rsMembers = statementMembers.getGeneratedKeys();
                        
                        if(rsMembers.next()){
                            idMembers = rsMembers.getInt(1);
                        }
                    }
                    
                    String SQLbrieven = "INSERT INTO Brieven (destinationLastName,destinationFirstName,destinationStreetName," +
                    "destinationStreetNumber,destinationZipCode,destinationCity,destinationEmail,senderLastName,"+
                    "senderFirstName,senderStreetName,senderStreetNumber,senderZipCode,senderCity,senderEmail,member_id)"+
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    
                    PreparedStatement statement = con.prepareStatement(SQLbrieven,Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1,destinationLastName);
                    statement.setString(2,destinationFirstName);
                    statement.setString(3,destinationStreetName);
                    statement.setString(4,destinationStreetNumber);
                    statement.setString(5,destinationZipCode);
                    statement.setString(6,destinationCity);
                    statement.setString(7,destinationEmail);
                    statement.setString(8,senderLastName);
                    statement.setString(9,senderFirstName);
                    statement.setString(10,senderStreetName);
                    statement.setString(11,senderStreetNumber);
                    statement.setString(12,senderZipCode);
                    statement.setString(13,senderCity);
                    statement.setString(14,senderEmail);
                    statement.setInt(15,idMembers);
                    
                    statement.execute();
                    
                    ResultSet rs = statement.getGeneratedKeys();
                    if (rs.next()){
                        id=rs.getInt(1);
                    }
                              

                }catch(SQLException e){
                    System.err.println(e);
                }

                //upload file, using row id as filename
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + id + ".pdf"));
                    }
                }

                //send e-mail
                SendFileEmail myMail = new SendFileEmail();
                myMail.setMailTo("jan.blonde@icloud.com");
                myMail.setAttachmentName(UPLOAD_DIRECTORY+File.separator + id +".pdf");
                String returnMessage = myMail.getMessage();
           
               //File uploaded successfully
               request.setAttribute("message", "File Uploaded Successfully " + returnMessage);
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
         
        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
    
        request.getRequestDispatcher("/result.jsp").forward(request, response);
     
    }
  
}