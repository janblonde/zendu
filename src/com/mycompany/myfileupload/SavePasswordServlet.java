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
import org.mindrot.jbcrypt.BCrypt;

import com.mycompany.myfileupload.Properties;

import java.util.*;

public class SavePasswordServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

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
			    
		//Map<String, String[]> parameterInfo = request.getParameterMap();
        String password = request.getParameter("senderpassword");
        String random = request.getParameter("random");
        
        System.err.println(password);
        System.err.println(random);
        
        //check if random is available in database
        try{ 
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/c9", Properties.username, Properties.password);
            
            String SQLfind = "SELECT userid from PasswordReset where random = ?";
            PreparedStatement statementFind = con.prepareStatement(SQLfind);
            statementFind.setString(1,random);
            ResultSet rsFind = statementFind.executeQuery();
            
            if(rsFind.next()){
                System.err.println("found random");
                System.err.println(Integer.toString(rsFind.getInt("userid")));
                
                //update password
                String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
                String SQL = "UPDATE Members SET pass=? where id=?";
                PreparedStatement statement = con.prepareStatement(SQL);
                statement.setString(1,hashed);
                statement.setInt(2,rsFind.getInt("userid"));
                statement.execute();
                
                //delete row in passwordreset table
                SQL = "DELETE FROM PasswordReset where random=?";
                statement = con.prepareStatement(SQL);
                statement.setString(1,random);
                statement.execute();
                
                //get the userid and set the session
                SQL = "SELECT email, first_name, last_name FROM Members where id=?";
                statement = con.prepareStatement(SQL);
                statement.setInt(1,rsFind.getInt("userid"));
                ResultSet rs = statement.executeQuery();
                
                if(rs.next()){
                    HttpSession session = request.getSession();
                    session.setAttribute("userid",rs.getString("email"));
	                session.setAttribute("naam", rs.getString("first_name") + " " + rs.getString("last_name"));
                }
                
                response.sendRedirect("success.jsp");
                //request.getRequestDispatcher("/success.jsp").forward(request, response);
            }else{
                response.setStatus(500);
            }
            
        }catch(SQLException se){
            System.err.println(se);
            response.setStatus(500);
        }
	}
}