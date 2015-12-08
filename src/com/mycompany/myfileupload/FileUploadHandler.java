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
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.mindrot.jbcrypt.BCrypt;

import com.mycompany.myfileupload.Properties;
import com.mycompany.myfileupload.SendFileEmail;

/**
 * Servlet to handle File upload request from Client
 * @author Javin Paul
 */
public class FileUploadHandler extends HttpServlet {
    private final String UPLOAD_DIRECTORY = com.mycompany.myfileupload.Properties.documentRoot;
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        boolean testUser = false;
        boolean creditUser = false;
        
        String myMessage="";
        
        String destinationCompany = "";
        String destinationLastName = "";
        String destinationFirstName = "";
        String destinationStreetName = "";
        String destinationStreetNumber = "";
        String destinationZipCode = "";
        String destinationCity = "";
        String destinationEmail = "";
        
        String senderCompany = "";
        String senderVAT = "";
        String senderLastName = "";
        String senderFirstName = "DhrMevr";
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

                        //get form parameters
                        if(fieldName.equals("destinationcompany"))
                            destinationCompany = fieldValue;
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
                        if(fieldName.equals("sendercompany"))
                            senderCompany = fieldValue;
                        if(fieldName.equals("sendervat"))
                            senderVAT = fieldValue;
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
                
                //if email empty: retrieve userid from session
                if(senderEmail.equals("")){
                    HttpSession session = request.getSession();
                    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
                        request.setAttribute("message","no user session - please login first");
                        request.getRequestDispatcher("/index.html").forward(request, response);
                    }else{
                        senderEmail = (String)session.getAttribute("userid");
                    }
                }else{
                    testUser = true;
                }

                try{
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/c9", Properties.username, Properties.password);
                    
                    String SQLfind = "SELECT id, first_name, last_name from Members where email = ?";
                    PreparedStatement statementFind = con.prepareStatement(SQLfind);
                    statementFind.setString(1,senderEmail);
                    ResultSet rsFind = statementFind.executeQuery();
                    
                    if(rsFind.next()){
                        if(testUser){
                            request.setAttribute("message"," 1 gratis verzending reeds opgebruikt - please login first");
                            request.getRequestDispatcher("/aangetekende-brief.jsp").forward(request, response);
                        }else{
                            idMembers = rsFind.getInt(1);
                            //senderFirstName = rsFind.getString(2);
                            //senderLastName = rsFind.getString(3);
                            
                            
                        }
                    }else{
                
                        String SQLmembers = "INSERT INTO Members(first_name,last_name,email,pass,streetname,streetnumber,zipcode,city,company,vat_number) VALUES (?,?,?,?,?,?,?,?,?,?)";
                        
                        PreparedStatement statementMembers = con.prepareStatement(SQLmembers,Statement.RETURN_GENERATED_KEYS);
                        statementMembers.setString(1,senderFirstName);
                        statementMembers.setString(2,senderLastName);
                        statementMembers.setString(3,senderEmail);
                        
                        String hashed = BCrypt.hashpw(senderPassword, BCrypt.gensalt());
                        statementMembers.setString(4,hashed);
                        statementMembers.setString(5,senderStreetName);
                        statementMembers.setString(6,senderStreetNumber);
                        statementMembers.setString(7,senderZipCode);
                        statementMembers.setString(8,senderCity);
                        statementMembers.setString(9,senderCompany);
                        statementMembers.setString(10,senderVAT);
                        
                        statementMembers.execute();
                        
                        ResultSet rsMembers = statementMembers.getGeneratedKeys();
                        
                        if(rsMembers.next()){
                            idMembers = rsMembers.getInt(1);
                        }
                    }
                    
                    String SQLbrieven = "INSERT INTO Brieven (destinationLastName,destinationFirstName,destinationStreetName," +
                    "destinationStreetNumber,destinationZipCode,destinationCity,destinationEmail,senderLastName,"+
                    "senderFirstName,senderStreetName,senderStreetNumber,senderZipCode,senderCity,senderEmail,member_id,status,destinationCompany,senderCompany)"+
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    
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
                    
                    if (testUser){
                        statement.setString(16,"paid");
                    }else{
                        statement.setString(16,"stored");
                    }
                    
                    statement.setString(17,destinationCompany);
                    statement.setString(18,senderCompany);
                    
                    statement.execute();
                    
                    ResultSet rs = statement.getGeneratedKeys();
                    if (rs.next()){
                        id=rs.getInt(1);
                    }
                    
                    //check credits
                    SQLfind = "SELECT SUM(amount) as huidigTotaal FROM CreditLog where member_id = ?;";
                    statementFind = con.prepareStatement(SQLfind);
                    statementFind.setInt(1,idMembers);
                    rsFind = statementFind.executeQuery();
                    
                    //if credits - deduct 1
                    if(rsFind.next()){
                        int huidigTotaal = rsFind.getInt("huidigTotaal");
                        if(huidigTotaal>0){
                            creditUser = true;
                            
                            String SQLCredits = "INSERT INTO CreditLog (member_id,amount,brief_id) VALUES(?,-1,?);";
                            statement = con.prepareStatement(SQLCredits,Statement.RETURN_GENERATED_KEYS);
                            statement.setInt(1,idMembers);
                            statement.setInt(2,id);
                            statement.execute();
                            
                            //set brief paid
                            SQLCredits = "UPDATE Brieven SET status='paid' WHERE id=?";
                            statement = con.prepareStatement(SQLCredits,Statement.RETURN_GENERATED_KEYS);
                            statement.setInt(1,id);
                            statement.execute();
                            
                        }
                    }
                              

                }catch(SQLException e){
                    System.err.println(e);
                }

                //upload file, using row id as filename
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + "brieven" + id + ".pdf"));
                    }
                }
           
               //File uploaded successfully
               request.setAttribute("message", "File Uploaded Successfully ");
               request.setAttribute("orderid", Integer.toString(id));
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
         
        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("userid", senderEmail);
        session.setAttribute("naam", senderFirstName + " " + senderLastName);

        //if first time or credits: dispatch to user home page
        //if second time and no credits: dispatch to payment page        
        if (testUser){
            SendFileEmail mailer = new SendFileEmail();
            mailer.setMailTo(senderEmail);
            mailer.setMessage("We verzenden de brief zo snel mogelijk aangetekend. U vindt de door u opgeladen brief als bijlage bij deze e-mail.");
            mailer.setSubject("Uw brief werd succesvol opgeladen op Zendu.be");
            mailer.setAttachmentName(UPLOAD_DIRECTORY + "brieven"+id+".pdf");
            mailer.getMessage();
            
            //send email
/*          AmazonSESAttachment mailer = new AmazonSESAttachment();
            mailer.setTO(senderEmail);
            mailer.setBODY("We verzenden de brief zo snel mogelijk aangetekend. U vindt de door u opgeladen brief als bijlage bij deze e-mail.");
            mailer.setSUBJECT("Uw brief werd succesvol opgeladen op Zendu.be");
            mailer.setFileName("brieven"+id+".pdf");
            mailer.sendMessage();*/
            
            //notification
            AmazonSES mailer2 = new AmazonSES();
            mailer2.setTO("jan.blonde@icloud.com");
            mailer2.setBODY("NOTIFICATION: nieuwe Zendu brief!");
            mailer2.setSUBJECT("NOTIFICATION: nieuwe Zendu brief!");
            mailer2.sendMessage();
            
            session.setAttribute("origin","testuser");
            response.sendRedirect("https://www.zendu.be/success.jsp");
            //request.getRequestDispatcher("/success.jsp").forward(request, response);
        }else{
            if(creditUser){
                //send email
                SendFileEmail myMail = new SendFileEmail();
                myMail.setMailTo(senderEmail);
                myMail.setAttachmentName(UPLOAD_DIRECTORY+File.separator + "brieven" + id +".pdf");
                myMail.setSubject("Uw brief werd succesvol opgeladen op zendu.be");
                myMail.setMessage("We verzenden de brief zo snel mogelijk aangetekend. U vindt de door u opgeladen brief als bijlage bij deze e-mail.");
                String returnMessage = myMail.getMessage();
                
                session.setAttribute("origin","testuser");
                response.sendRedirect("https://www.zendu.be/success.jsp");
                        
            }else{
                //goto payment page
                session.setAttribute("orderref",id);
                session.setAttribute("clientid",idMembers);
                session.setAttribute("first",senderFirstName);
                session.setAttribute("last",senderLastName);
                session.setAttribute("email",senderEmail);
                session.setAttribute("street",senderStreetName);
                session.setAttribute("housenumber",senderStreetNumber);
                session.setAttribute("postalcode",senderZipCode);
                session.setAttribute("city",senderCity);
                session.setAttribute("returnpage","letters");
                response.sendRedirect("payment.jsp");
            }
        }
        
    }
}