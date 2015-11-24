/*
 * Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
 
package com.mycompany.myfileupload;

import java.io.IOException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import com.amazonaws.services.simpleemail.model.RawMessage;

import javax.mail.Session;
import javax.mail.internet.*;
import javax.mail.*;
import javax.activation.*;
import javax.mail.util.*;
import java.io.*;
import java.util.Arrays;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.Properties;


public class AmazonSESAttachment {

    private String FROM = "info@zendu.be";  // Replace with your "From" address. This address must be verified.
    private String TO = "jan.blonde@icloud.com"; // Replace with a "To" address. If you have not yet requested
                                                      // production access, this address must be verified.
    private String BODY = "This is the body";
    private String SUBJECT = "This is the subject";
    
    private String fileName = "";
    
    public void setFROM(String FROM) {
        this.FROM = FROM;
    }
    
    public void setTO(String TO) {
        this.TO = TO;
    }
    
    public void setBODY(String BODY) {
        this.BODY = BODY;
    }
    
    public void setSUBJECT(String SUBJECT) {
        this.SUBJECT = SUBJECT;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /*
     * Before running the code:
     *      Fill in your AWS access credentials in the provided credentials
     *      file template, and be sure to move the file to the default location
     *      (~/.aws/credentials) where the sample code will load the
     *      credentials from.
     *      https://console.aws.amazon.com/iam/home?#security_credential
     *
     * WARNING:
     *      To avoid accidental leakage of your credentials, DO NOT keep
     *      the credentials file in your source directory.
     */

    public void sendMessage() {

        try {
            /*
             * The ProfileCredentialsProvider will return your [default]
             * credential profile by reading from the credentials file located at
             * (~/.aws/credentials).
             *
             * TransferManager manages a pool of threads, so we create a
             * single instance and share it throughout our application.
             */
            AWSCredentials credentials = null;
            try {
                credentials = new ProfileCredentialsProvider().getCredentials();
            } catch (Exception e) {
                throw new AmazonClientException(
                        "Cannot load the credentials from the credential profiles file. " +
                        "Please make sure that your credentials file is at the correct " +
                        "location (~/.aws/credentials), and is in valid format.",
                        e);
            }

            // Instantiate an Amazon SES client, which will make the service call with the supplied AWS credentials.
            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);

            // Choose the AWS region of the Amazon SES endpoint you want to connect to. Note that your production
            // access status, sending limits, and Amazon SES identity-related settings are specific to a given
            // AWS region, so be sure to select an AWS region in which you set up Amazon SES. Here, we are using
            // the US East (N. Virginia) region. Examples of other regions that Amazon SES supports are US_WEST_2
            // and EU_WEST_1. For a complete list, see http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html
            Region REGION = Region.getRegion(Regions.EU_WEST_1);
            client.setRegion(REGION);
            
            Session session = Session.getInstance(new Properties()); 
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSubject(SUBJECT); 
            MimeMultipart mimeMultipart = new MimeMultipart(); 
            BodyPart p = new MimeBodyPart(); 
            p.setContent(BODY, "text/plain"); 
            mimeMultipart.addBodyPart(p);
            
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setFileName(fileName);
            mimeBodyPart.setDescription("Factuur", "UTF-8");
            DataSource ds = new ByteArrayDataSource(new FileInputStream(new File(com.mycompany.myfileupload.Properties.documentRoot + fileName)), "application/pdf");
            mimeBodyPart.setDataHandler(new DataHandler(ds));
            mimeMultipart.addBodyPart(mimeBodyPart);
            
            mimeMessage.setContent(mimeMultipart);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            mimeMessage.writeTo(outputStream);
            RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));
            
            String[] recipient = {TO};
            
            SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(rawMessage);
            rawEmailRequest.setDestinations(Arrays.asList(recipient));
            rawEmailRequest.setSource(FROM);

            // Send the email.
            client.sendRawEmail(rawEmailRequest);

        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
    }
}
