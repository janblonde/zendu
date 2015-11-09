package com.mycompany.myfileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.math.BigInteger;
import java.security.SecureRandom;

import com.mycompany.myfileupload.Properties;

import java.util.*;

public class RequestPasswordServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
	    performTask(request, response);
	}

	private void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
			    
		Map<String, String[]> parameterInfo = request.getParameterMap();
        String email = Arrays.asList(parameterInfo.get("email")).get(0);
        
        //check if email is available as username in database
        try{ 
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/c9", Properties.username, Properties.password);
            
            String SQLfind = "SELECT id from Members where email = ?";
            PreparedStatement statementFind = con.prepareStatement(SQLfind);
            statementFind.setString(1,email);
            ResultSet rsFind = statementFind.executeQuery();
            
            if(rsFind.next()){
                //generate random number
                SecureRandom random = new SecureRandom();
                String myRandom = new BigInteger(130, random).toString(32);
        
                //store random and userid in database
                String SQLInsert = "INSERT INTO PasswordReset(random,userid) VALUES(?,?);";
                PreparedStatement statement = con.prepareStatement(SQLInsert);
                statement.setString(1,myRandom);
                statement.setInt(2,rsFind.getInt("id"));
                statement.execute();
        
                //send mail including url referencing the hash
                SendFileEmail myMail = new SendFileEmail();
                //myMail.setMailTo(email);
                myMail.setMailTo("jan.blonde@icloud.com");
                myMail.setSubject("Uw aanvraag om uw paswoord te resetten op Zendu.be");
                myMail.setMessage("Klik op volgende link om u een nieuw paswoord in te geven: http://www.zendu.be/resetpassword.jsp?id="+myRandom);
                String returnMessage = myMail.getMessage();
                
                response.setStatus(200);
            
            }else{
                response.setStatus(500);
            }
            
        }catch(SQLException se){
            System.err.println(se);
            response.setStatus(500);
        }
	}
}