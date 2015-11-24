package com.mycompany.myfileupload;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendFileEmail{
    
    String mailTo = "";
    String attachmentName = "";
    String textMessage = "This is message body";
    String subject = "";
    
    public void setMailTo(String mailTo){
        this.mailTo = mailTo;
    }
    
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
    
    public void setSubject(String message) {
        this.subject = message;
    }
    
    public void setMessage(String message) {
        this.textMessage = message;
    }

    public String getMessage() {
      // Recipient's email ID needs to be mentioned.
      String to = mailTo;

      // Sender's email ID needs to be mentioned
      String from = "info@zendu.be";

      // Get system properties
      java.util.Properties properties = System.getProperties();

      // Setup mail server
      //properties.setProperty("mail.smtp.host", host);
      properties.setProperty("mail.transport.protocol", "smtp");
      properties.setProperty("mail.host", "smtp.gmail.com");
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.port", "465");
      properties.put("mail.debug", "false");
      properties.put("mail.smtp.socketFactory.port", "465");
      properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      properties.put("mail.smtp.socketFactory.fallback", "false");
      properties.setProperty("mail.user", "jan@strategydesigners.be");
      properties.setProperty("mail.password", "ciFEja51");

      // Get the default Session object.
      //Session session = Session.getDefaultInstance(properties);
      
      Session emailSession = Session.getInstance(properties,
           new javax.mail.Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication("jan@strategydesigners.be","ciFEja51");
           }
       });

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(emailSession);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject(subject);

         // Create the message part 
         BodyPart messageBodyPart = new MimeBodyPart();

         // Fill the message
         messageBodyPart.setText(textMessage);
         
         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
        if(!attachmentName.equals("")){
             messageBodyPart = new MimeBodyPart();
             String filename = attachmentName;
             DataSource source = new FileDataSource(filename);
             messageBodyPart.setDataHandler(new DataHandler(source));
             messageBodyPart.setFileName(filename);
             multipart.addBodyPart(messageBodyPart);
        }

         // Send the complete message parts
         message.setContent(multipart );

         // Send message
         Transport.send(message);
         return "Sent message successfully....";
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
      return "Sent message";
   }
}