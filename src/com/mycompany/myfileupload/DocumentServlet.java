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

public class DocumentServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

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
			    
	    System.err.println("performTask");
	    
	    HttpSession session = request.getSession();
	    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
	        request.getRequestDispatcher("/error.jsp").forward(request, response);
	    }else{
            
            String email = (String)session.getAttribute("userid");
            
            try{ 
                Class.forName("com.mysql.jdbc.Driver");
            }catch(ClassNotFoundException e){
                System.out.println(e);
            }
            
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/c9", Properties.username, Properties.password);
                Statement st = con.createStatement();
                ResultSet rs;
                rs = st.executeQuery("select * from Members where email='" + email + "';");
                
                if (rs.next()){
                    
                    String docid = request.getParameter("docid");
                    String doctype = request.getParameter("doctype");
                    
                    ResultSet rs2 = null;
                    
                    if(doctype.equals("factuur")){
                        rs2 = st.executeQuery("select * from Invoices where member_id=" + rs.getInt("id")+";");
                    }else{
                        rs2 = st.executeQuery("select * from Brieven where member_id=" + rs.getInt("id")+";");
                    }
                    
                    while (rs2.next()){
                        if (Integer.toString(rs2.getInt("id")).equals(docid)){
                            System.err.println("match");
                    		String pdfFileName = doctype + docid + ".pdf"; 
                    		System.err.println(pdfFileName);
                    		//String contextPath = getServletContext().getRealPath(File.separator);
                    		String contextPath = com.mycompany.myfileupload.Properties.documentRoot;
                    		File pdfFile = new File(contextPath + pdfFileName);
                    
                    		response.setContentType("application/pdf");
                    		response.addHeader("Content-Disposition", "inline; filename=" + pdfFileName);
                    		response.setContentLength((int) pdfFile.length());
                    
                    		FileInputStream fileInputStream = new FileInputStream(pdfFile);
                    		OutputStream responseOutputStream = response.getOutputStream();
                    		int bytes;
                    		while ((bytes = fileInputStream.read()) != -1) {
                    			responseOutputStream.write(bytes);
                    		}
                        }
                    }
                }
            }catch(SQLException s) {
                System.out.println(s);
            }
	    }
	}
}