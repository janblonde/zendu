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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet to handle File upload request from Client
 * @author Javin Paul
 */
public class FileUploadLeaflet extends HttpServlet {
    private final String UPLOAD_DIRECTORY = "/home/ubuntu/workspace/";
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String myMessage="";
        
        String destinationLastName = "";
        String destinationFirstName = "";
        String destinationStreet = "";
        String destinationStreetNumber = "";
        String destinationZipCode = "";
        String destinationCity = "";
        String destinationEmail = "";
        
        String senderLastName = "";
        String senderFirstName = "";
        String senderEmail = "";
        
        String id = "";
      
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

                        //get form parameters
                        if(fieldName.equals("id"))
                            id = fieldValue;
                    }
                }

                //upload file, using row id as filename
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + "LEAFLET" + id + ".pdf"));
                    }
                }

                //send e-mail
                SendFileEmail myMail = new SendFileEmail();
                myMail.setMailTo("jan.blonde@icloud.com");
                myMail.setAttachmentName(UPLOAD_DIRECTORY+File.separator + id +".pdf");
                String returnMessage = myMail.getMessage();
                
                //update database
                try{ 
                    Class.forName("com.mysql.jdbc.Driver");
                }catch(ClassNotFoundException e){
                    System.out.println(e);
                }

                try{
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/c9", "janblonde", "");
                    Statement stmt = con.createStatement();                   
                   

                    String SQL = "UPDATE Brieven SET leaflet=1 where id=" + id;
                    
                    stmt.executeUpdate(SQL);

                }catch(SQLException e){
                    System.err.println(e);
                }
           
               //File uploaded successfully
               request.setAttribute("message", "File Uploaded Successfully " + returnMessage);
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
         
        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
    
        request.getRequestDispatcher("/admin.jsp").forward(request, response);
     
    }
  
}