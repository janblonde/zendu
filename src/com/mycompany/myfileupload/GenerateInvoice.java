package com.mycompany.myfileupload;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.*;
import org.apache.pdfbox.pdmodel.edit.*;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;

import com.mycompany.myfileupload.Properties;

public class GenerateInvoice{
    
    String mailTo = "";
    
    public static void main(String args[]) {
        try {
            // Create a new empty document
            PDDocument document = new PDDocument();
            
            // Create a new blank page and add it to the document
            PDPage blankPage = new PDPage();
            document.addPage( blankPage );
            
            // Save the newly created document
            document.save("BlankPage2.pdf");
            
            // finally make sure that the document is properly
            // closed.
            document.close();
            
            GenerateInvoice thisInvoice = new GenerateInvoice();
            thisInvoice.generateForLetter("55");

        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void generateForLetter (String briefID){

        String destinationLastName = "";        

        String firstName = "Jan";
        String lastName = "Blonde";
        String streetName = "Huybrechtsstraat";
        String streetNumber = "76";
        String zipCode = "2140";
        String city = "Borgerhout";
        String company = "BVBA CEEJAY";
        String vat = "BE0865267120";
        int invoiceID = 0;
        
        try {
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/c9", Properties.username, Properties.password);
            
            String SQL = "SELECT * from Brieven where id = ?";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setString(1,briefID);
            ResultSet rs = statement.executeQuery();
            
            if(rs.next()){
                destinationLastName = rs.getString("destinationLastName");
                String memberID = rs.getString("member_id");
                System.err.println(memberID);
                
                SQL = "SELECT * from Members where id = ?";
                statement = con.prepareStatement(SQL);
                statement.setString(1,memberID);
                ResultSet rs2 = statement.executeQuery();
                
                if(rs2.next()){
                    firstName = rs2.getString("first_name");
                    lastName = rs2.getString("last_name");
                    streetName = rs2.getString("streetname");
                    streetNumber = rs2.getString("streetnumber");
                    zipCode = rs2.getString("zipcode");
                    city = rs2.getString("city");
                    vat = rs2.getString("vat_number");
                    company = rs2.getString("company");
                }

                SQL = "INSERT INTO Invoices(member_id,title,amount) VALUES(?,?,?)";
                statement = con.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
                statement.setString(1,memberID);
                statement.setString(2,"Verzending 1 aangetekende brief");
                statement.setString(3,"8.50 &euro;");

                statement.execute();                        
                ResultSet rs3 = statement.getGeneratedKeys();
                if (rs3.next()){
                  invoiceID = rs3.getInt(1);
                }
            }
        
            // Create a new empty document
            PDDocument document = new PDDocument();
            
            // Create a new blank page and add it to the document
            PDPage page = new PDPage();
            document.addPage( page );
            
            PDFont font = PDType1Font.HELVETICA_BOLD;
            
            //create an image
            InputStream in = new FileInputStream(new File(com.mycompany.myfileupload.Properties.documentRoot + "zendu2.jpg"));
            PDJpeg img = new PDJpeg(document, in);
            
            // Start a new content stream which will "hold" the to be created content
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            
            //add logo
            contentStream.drawImage(img, 100, 730);
            
            // Our address
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 100, 700 );
            contentStream.drawString( "BVBA CEEJAY" );
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 100, 690 );
            contentStream.drawString( "Huybrechtsstraat 76" );
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 100, 680 );
            contentStream.drawString( "2140 Borgerhout" );
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 100, 670 );
            contentStream.drawString( "BTW: BE0865267120" );
            contentStream.endText();

            // Invoice number and date
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 100, 640 );
            contentStream.drawString("Factuurnummer: F" + String.format("%05d",invoiceID));
            contentStream.endText();

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dateNow = sdf.format(date);

            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 100, 630 );
            contentStream.drawString("Factuurdatum: " + dateNow);
            contentStream.endText();
            
            // Client address
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 300, 700 );
            contentStream.drawString(company);
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 300, 690 );
            contentStream.drawString(streetName + " " + streetNumber);
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 300, 680 );
            contentStream.drawString(zipCode + " " + city);
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 300, 670 );
            contentStream.drawString( "BTW: " + vat );
            contentStream.endText();

            //Content : title + amount + VAT amounts
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 100, 500 );
            contentStream.drawString( "Omschrijving ");
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 400, 500 );
            contentStream.drawString("Bedrag");
            contentStream.endText();
            
            contentStream.setStrokingColor(Color.RED);
            contentStream.setLineWidth(1);
            contentStream.addLine(100, 495, 525, 495);
            contentStream.closeAndStroke();

            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 100, 480 );
            contentStream.drawString( "Verzending 1 aangetekende brief");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 400 , 480 );
            contentStream.drawString( "Excl. BTW:");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 400, 470 );
            contentStream.drawString("BTW (21%):");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 400 , 455 );
            contentStream.drawString( "Inc. BTW:");
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 470 , 480 );
            contentStream.drawString( "7.02 EUR");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 470, 470 );
            contentStream.drawString("1.48 EUR");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 470 , 455 );
            contentStream.drawString( "8.50 EUR");
            contentStream.endText();


            //Mention of "Betaald"
            contentStream.beginText();
            contentStream.setFont( font, 12 );
            contentStream.moveTextPositionByAmount( 100 , 400 );
            contentStream.drawString( "Deze factuur werd reeds betaald");
            contentStream.endText();
            
            // Make sure that the content stream is closed:
            contentStream.close();
            
            // Save the results and ensure that the document is properly closed:
            document.save( com.mycompany.myfileupload.Properties.documentRoot + "factuur" + Integer.toString(invoiceID) + ".pdf");
            document.close();
        
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void generateForCredits (String orderRef) {
        String destinationLastName = "";        

        String firstName = "Jan";
        String lastName = "Blonde";
        String streetName = "Huybrechtsstraat";
        String streetNumber = "76";
        String zipCode = "2140";
        String city = "Borgerhout";
        String company = "BVBA CEEJAY";
        String vat = "BE0865267120";
        int invoiceID = 0;
        String amount = "0";
        
        try {
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/c9", Properties.username, Properties.password);
            
            String SQL = "SELECT * from CreditLog where id = ?";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setString(1,orderRef);
            ResultSet rs = statement.executeQuery();
            
            if(rs.next()){
                String memberID = rs.getString("member_id");
                amount = rs.getString("amount");
            
                SQL = "SELECT * from Members where id = ?";
                statement = con.prepareStatement(SQL);
                statement.setString(1,memberID);
                ResultSet rs2 = statement.executeQuery();
                
                if(rs2.next()){
                    firstName = rs2.getString("first_name");
                    lastName = rs2.getString("last_name");
                    streetName = rs2.getString("streetname");
                    streetNumber = rs2.getString("streetnumber");
                    zipCode = rs2.getString("zipcode");
                    city = rs2.getString("city");
                    vat = rs2.getString("vat_number");
                    company = rs2.getString("company");
                }
    
                SQL = "INSERT INTO Invoices(member_id,title,amount) VALUES(?,?,?)";
                statement = con.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
                statement.setString(1,memberID);
                statement.setString(2,"Aankoop credits voor het verzenden van " + amount + " aangetekende brieven");
                if(amount.equals("10"))
                    statement.setString(3,"75 &euro;");
                if(amount.equals("50"))
                    statement.setString(3,"360 &euro;");
                if(amount.equals("100"))
                    statement.setString(3,"820 &euro;");
    
                statement.execute();                        
                ResultSet rs3 = statement.getGeneratedKeys();
                if (rs3.next()){
                  invoiceID = rs3.getInt(1);
                }
            }
        
            // Create a new empty document
            PDDocument document = new PDDocument();
            
            // Create a new blank page and add it to the document
            PDPage page = new PDPage();
            document.addPage( page );
            
            PDFont font = PDType1Font.HELVETICA_BOLD;
            
            //create an image
            InputStream in = new FileInputStream(new File(com.mycompany.myfileupload.Properties.documentRoot + "zendu2.jpg"));
            PDJpeg img = new PDJpeg(document, in);
            
            // Start a new content stream which will "hold" the to be created content
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            
            //add logo
            contentStream.drawImage(img, 100, 730);
            
            // Our address
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 100, 700 );
            contentStream.drawString( "BVBA CEEJAY" );
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 100, 690 );
            contentStream.drawString( "Huybrechtsstraat 76" );
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 100, 680 );
            contentStream.drawString( "2140 Borgerhout" );
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 100, 670 );
            contentStream.drawString( "BTW: BE0865267120" );
            contentStream.endText();

            // Invoice number and date
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 100, 640 );
            contentStream.drawString("Factuurnummer: F" + String.format("%05d",invoiceID));
            contentStream.endText();

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dateNow = sdf.format(date);

            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 100, 630 );
            contentStream.drawString("Factuurdatum: " + dateNow);
            contentStream.endText();
            
            // Client address
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 300, 700 );
            contentStream.drawString(company);
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 300, 690 );
            contentStream.drawString(streetName + " " + streetNumber);
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 300, 680 );
            contentStream.drawString(zipCode + " " + city);
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 300, 670 );
            contentStream.drawString( "BTW: " + vat );
            contentStream.endText();

            //Content : title + amount + VAT amounts
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 100, 500 );
            contentStream.drawString( "Omschrijving ");
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 400, 500 );
            contentStream.drawString("Bedrag");
            contentStream.endText();
            
            contentStream.setStrokingColor(Color.RED);
            contentStream.setLineWidth(1);
            contentStream.addLine(100, 495, 525, 495);
            contentStream.closeAndStroke();

            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 100, 480 );
            contentStream.drawString( "Aankoop credits voor het verzenden van " + amount);
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 100, 470 );
            contentStream.drawString( "aangetekende brieven");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 400 , 480 );
            contentStream.drawString( "Excl. BTW:");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 400, 470 );
            contentStream.drawString("BTW (21%):");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 400 , 455 );
            contentStream.drawString( "Inc. BTW:");
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 470 , 480 );
            if(amount.equals("10"))
                contentStream.drawString( " 61.98 EUR");
            if(amount.equals("50"))
                contentStream.drawString( "351.24 EUR");
            if(amount.equals("100"))
                contentStream.drawString( "677.31 EUR");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 470, 470 );
            if(amount.equals("10"))
                contentStream.drawString( " 13.02 EUR");
            if(amount.equals("50"))
                contentStream.drawString( "  73.76 EUR");
            if(amount.equals("100"))
                contentStream.drawString( "142.31 EUR");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont( font, 10 );
            contentStream.moveTextPositionByAmount( 470 , 455 );
            if(amount.equals("10"))
                contentStream.drawString( " 88.00 EUR");
            if(amount.equals("50"))
                contentStream.drawString( "425.00 EUR");
            if(amount.equals("100"))
                contentStream.drawString( "820.00 EUR");            
            contentStream.endText();


            //Mention of "Betaald"
            contentStream.beginText();
            contentStream.setFont( font, 12 );
            contentStream.moveTextPositionByAmount( 100 , 400 );
            contentStream.drawString( "Deze factuur werd reeds betaald");
            contentStream.endText();
            
            // Make sure that the content stream is closed:
            contentStream.close();
            
            // Save the results and ensure that the document is properly closed:
            document.save( com.mycompany.myfileupload.Properties.documentRoot + "factuur" + Integer.toString(invoiceID) + ".pdf");
            document.close();
        
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}