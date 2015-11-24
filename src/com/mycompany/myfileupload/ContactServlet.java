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

import java.util.*;

public class ContactServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
	    performTask(request, response);
	}

	private void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException {
			    
		Map<String, String[]> parameterInfo = request.getParameterMap();
        
        // In this case here we are not using the data sent to just do different things.
        // Instead we are using them as information to make changes to the server,
        // in this case, adding more bands and albums.
        String name = Arrays.asList(parameterInfo.get("name")).get(0);
        String phone = Arrays.asList(parameterInfo.get("phone")).get(0);
        String email = Arrays.asList(parameterInfo.get("email")).get(0);
        String message = Arrays.asList(parameterInfo.get("message")).get(0);    

        System.err.println(name);
        
        AmazonSES ses = new AmazonSES();
        
        ses.setTO("jan.blonde@icloud.com");
        ses.setSUBJECT("New message from Zendu.be");
        ses.setBODY(name + " | " + phone + " | " + email + " | " + message);
        
        try{
            ses.sendMessage();
        }catch(Exception e){
            System.err.println("PROBLEM" + e);
            response.setStatus(500);
        }

        // return success
        response.setStatus(200);
	}
}