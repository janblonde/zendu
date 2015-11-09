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

import com.mycompany.myfileupload.Properties;

import java.util.*;

public class CheckUsername extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //doGet
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
			    
        Map<String, String[]> parameterInfo = request.getParameterMap();
        
        // In this case here we are not using the data sent to just do different things.
        // Instead we are using them as information to make changes to the server,
        // in this case, adding more bands and albums.
        String userName = Arrays.asList(parameterInfo.get("username")).get(0);

        System.err.println(userName);
        
        try{ 
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.err.println(e);
            response.setStatus(500);
        }
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/c9", Properties.username, Properties.password);
            
            int count = 0;
            String SQLfind = "SELECT COUNT(*) FROM Members WHERE email = ?";
            PreparedStatement statementFind = con.prepareStatement(SQLfind);
            statementFind.setString(1,userName);
            ResultSet rsFind = statementFind.executeQuery();
            if(rsFind.next()){
                count = rsFind.getInt(1);
            }
            
            System.err.println(count);
            
            if (count>0){
                response.setStatus(500);
            }else{
                response.setStatus(200);
            }

        }catch(SQLException s) {
            System.err.println(s);
            response.setStatus(500);
        }

        
	}

}