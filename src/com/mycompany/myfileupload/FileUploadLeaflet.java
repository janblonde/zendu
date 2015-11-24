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
    private final String UPLOAD_DIRECTORY = com.mycompany.myfileupload.Properties.documentRoot;
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + "bewijs" + id + ".pdf"));
                    }
                }
                
                //update database + send email
                try{ 
                    Class.forName("com.mysql.jdbc.Driver");
                }catch(ClassNotFoundException e){
                    System.out.println(e);
                }

                try{
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/c9", "janblonde", "");
                    Statement stmt = con.createStatement();                   
                   

                    String SQL = "UPDATE Brieven SET status='sent' where id=" + id;
                    
                    stmt.executeUpdate(SQL);
                    
                    SQL = "UPDATE Brieven SET sent_date = NOW() where id=" + id;
                    
                    stmt.executeUpdate(SQL);
                    
                    SQL = "SELECT member_id from Brieven where id=" + id;
                    ResultSet rs = stmt.executeQuery(SQL);
                    while(rs.next()){
                        int memberID = rs.getInt("member_id");
                        
                        SQL = "SELECT email from Members where id=" + Integer.toString(memberID);
                        ResultSet rs2 = stmt.executeQuery(SQL);
                        while(rs2.next()){
                            String senderEmail = rs2.getString("email");
                            
                            //send e-mail
                            AmazonSESAttachment mailer = new AmazonSESAttachment();
                            mailer.setTO(senderEmail);
                            mailer.setBODY("Uw brief werd zojuist aangetekend verzonden via de reguliere post. U vindt een scan van het bewijsstrookje als bijlage bij deze e-mail.");
                            mailer.setSUBJECT("Uw brief werd aangetekend verzonden door Zendu.be");
                            mailer.setFileName("bewijs"+id+".pdf");
                            mailer.sendMessage();
                            
                        }
                    }
                    
                    

                }catch(SQLException e){
                    System.err.println(e);
                }
           
               //File uploaded successfully
               request.setAttribute("message", "File Uploaded Successfully ");
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
         
        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
    
        //request.getRequestDispatcher("/admin.jsp").forward(request, response);
        response.sendRedirect("admin.jsp");
     
    }
  
}