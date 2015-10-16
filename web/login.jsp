<%@ page import ="java.sql.*" %>
<%@ page import ="org.mindrot.jbcrypt.BCrypt" %>
<%
    String email = request.getParameter("email");    
    String pwd = request.getParameter("pass");
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/c9", "janblonde", "");
    Statement st = con.createStatement();
    ResultSet rs;
    rs = st.executeQuery("select * from Members where email='" + email + "';");
    
    if (rs.next()){
        String hashed = rs.getString("pass");
    
        if (BCrypt.checkpw(pwd, hashed)){
	        out.println("It matches");
	        session.setAttribute("userid",email);
	        response.sendRedirect("success.jsp");
        }else{
	        out.println("Invalid password <a href='index.jsp'>try again</a>");
	    }

    } else {
        out.println("Invalid userid <a href='index.jsp'>try again</a>");
    }
%>