<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload Example in JSP and Servlet - Java web application</title>
    </head>
 
    <body> 
        <div>
            <h3> Choose File to Upload in Server </h3>
            <form action="upload" method="post" enctype="multipart/form-data">
                Last Name: <input type="text" name="destinationlastname"/><br>
                First Name: <input type="text" name="destinationfirstname"/><br>
                Street: <input type="text" name="destinationstreet"/><br>
                Number: <input type="text" name="destinationstreetnumber"/><br>
                <input type="file" name="file" />
                <input type="submit" value="upload" />
            </form>          
        </div>
      
    </body>
</html>