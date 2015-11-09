<%@ page import ="java.sql.*" %>
<%@ page import ="java.security.*" %>
<%@ page import ="java.math.*" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Zendu | Aangetekende brieven</title>
    <!-- Bootstrap Core CSS -->
    <link href="assets/css/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!-- Retina.js - Load first for faster HQ mobile images. -->
    <script src="assets/js/plugins/retina/retina.min.js"></script>
    <!-- Font Awesome -->
    <link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Default Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Roboto:900,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Ubuntu' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,100,200,300,600,500,700,800,900' rel='stylesheet' type='text/css'>
    <!-- Modern Style Fonts (Include these is you are using body.modern!) -->
    <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Cardo:400,400italic,700' rel='stylesheet' type='text/css'>
    <!-- Vintage Style Fonts (Include these if you are using body.vintage!) -->
    <link href='http://fonts.googleapis.com/css?family=Sanchez:400italic,400' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Cardo:400,400italic,700' rel='stylesheet' type='text/css'>
    <!-- Plugin CSS -->
    <link href="assets/css/plugins/owl-carousel/owl.carousel.css" rel="stylesheet" type="text/css">
    <link href="assets/css/plugins/owl-carousel/owl.theme.css" rel="stylesheet" type="text/css">
    <link href="assets/css/plugins/owl-carousel/owl.transitions.css" rel="stylesheet" type="text/css">
    <link href="assets/css/plugins/magnific-popup.css" rel="stylesheet" type="text/css">
    <link href="assets/css/plugins/background.css" rel="stylesheet" type="text/css">
    <link href="assets/css/plugins/animate.css" rel="stylesheet" type="text/css">
    <!-- Vitality Theme CSS -->
    <!-- Uncomment the color scheme you want to use. -->
    <link href="assets/css/vitality-red.css" rel="stylesheet" type="text/css">
    <!-- <link href="assets/css/vitality-aqua.css" rel="stylesheet" type="text/css"> -->
    <!-- <link href="assets/css/vitality-blue.css" rel="stylesheet" type="text/css"> -->
    <!-- <link href="assets/css/vitality-green.css" rel="stylesheet" type="text/css"> -->
    <!-- <link href="assets/css/vitality-orange.css" rel="stylesheet" type="text/css"> -->
    <!-- <link href="assets/css/vitality-pink.css" rel="stylesheet" type="text/css"> -->
    <!-- <link href="assets/css/vitality-purple.css" rel="stylesheet" type="text/css"> -->
    <!-- <link href="assets/css/vitality-tan.css" rel="stylesheet" type="text/css"> -->
    <!-- <link href="assets/css/vitality-turquoise.css" rel="stylesheet" type="text/css"> -->
    <!-- <link href="assets/css/vitality-yellow.css" rel="stylesheet" type="text/css"> -->
    <!-- IE8 support for HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<!-- Alternate Body Classes: .modern and .vintage -->

<body id="page-top">
<%
    ResultSet rs2 = null;
    String naam = "";

if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
%>
    You are not logged in<br/>
    <a href="agency.html">Log on</a>
<%}
else
{
    String userid = (String)session.getAttribute("userid");
    naam = (String)session.getAttribute("naam");
}%>

    <!-- Navigation -->
    <!-- Note: navbar-default and navbar-inverse are both supported with this theme. -->
    <!--<nav class="navbar navbar-inverse navbar-fixed">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display 
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">
                    Zen<span style="color:firebrick">du</span>
                </a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling 
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a class="page-scroll" href="#page-top"></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#about">Welkom <%=naam%></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#process">Profiel</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#work">Credits</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#pricing">Facturen</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="index.html">Uitloggen</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse 
        </div>
        <!-- /.container 
    </nav>-->
    <header>
        <div class="intro-content" style="top:150px;">
        
<%String orderID = (String)request.getAttribute("orderid");

String text = "ACCEPTURL=https://java-tomcat-janblonde.c9.io/zendu/success.jspweliveinnumber76AMOUNT=980weliveinnumber76CURRENCY=EURweliveinnumber76LANGUAGE=en_USweliveinnumber76ORDERID="+ orderID +"weliveinnumber76PSPID=zenduweliveinnumber76";

text = text + "TITLE=PAYMENTweliveinnumber76";

MessageDigest crypt = MessageDigest.getInstance("SHA-1");
crypt.reset();
crypt.update(text.getBytes("UTF-8"));

String result = new BigInteger(1, crypt.digest()).toString(16);
%>

<FORM id="paymentform" METHOD="post" ACTION="https://secure.ogone.com/ncol/test/orderstandard.asp" id=form1 name=form1>
<INPUT type="hidden" NAME="PSPID" value="zendu">
<INPUT type="hidden" NAME="orderID" style="color:black" VALUE="<%=orderID%>">
<INPUT type="hidden" NAME="amount" style="color:black" VALUE="980">
<INPUT type="hidden" NAME="currency" style="color:black" VALUE="EUR">
<INPUT type="hidden" NAME="language" VALUE="en_US">
<!-- lay out information -->

<!--<INPUT type="hidden" NAME="MANDATEID" VALUE="">
<INPUT type="hidden" NAME="SIGNDATE" VALUE="">
<INPUT type="hidden" NAME="SEQUENCETYPE" VALUE="">-->

<INPUT type="hidden" NAME="TITLE" VALUE="PAYMENT">
<!--<INPUT type="hidden" NAME="TXTCOLOR" VALUE="#FFFFFF">
<INPUT type="hidden" NAME="TBLBGCOLOR" VALUE="#FFFFFF">
<INPUT type="hidden" NAME="TBLTXTCOLOR" VALUE="#000000">
<INPUT type="hidden" NAME="BUTTONBGCOLOR" VALUE="#00467F">
<INPUT type="hidden" NAME="BUTTONTXTCOLOR" VALUE="#FFFFFF">
<INPUT type="hidden" NAME="LOGO" VALUE="<fill here your logo file name>">
<INPUT type="hidden" NAME="FONTTYPE" VALUE="Verdana">-->

<INPUT type="hidden" NAME="SHASIGN" VALUE="<%= result %>">
<INPUT type="hidden" NAME="ACCEPTURL" VALUE="https://java-tomcat-janblonde.c9.io/zendu/success.jsp">

<!--<INPUT type="hidden" NAME="TP" VALUE="<fill here your template page>">


<INPUT type="hidden" NAME="declineurl" VALUE="">
<INPUT type="hidden" NAME="exceptionurl" VALUE="">
<INPUT type="hidden" NAME="cancelurl" VALUE="">

<INPUT type="hidden" NAME="COM" VALUE="<fill here your order description>">
<INPUT type="hidden" NAME="CN" VALUE="<fill here your Client name>">
<INPUT type="hidden" name="EMAIL" value="<fill here your Client email>">
<INPUT type="hidden" NAME="PM" VALUE="">
<INPUT type="hidden" NAME="BRAND" VALUE="">
<INPUT type="hidden" NAME="ownerZIP" VALUE="">
<INPUT type="hidden" NAME="owneraddress" VALUE="">
<INPUT type="hidden" NAME="owneraddress2" VALUE="">
<INPUT type="hidden" NAME="owneraddress3" VALUE="">-->
<input type="submit" value="SUBMIT" id=submit2 name=submit2 hidden>
</form>
        
            
        </div>
        
        
    </header>

    <!-- Core Scripts -->
    <script src="assets/js/jquery.js"></script>
    <!--
    <script src="assets/js/bootstrap/bootstrap.min.js"></script>
     Plugin Scripts 
    <script src="assets/js/plugins/jquery.easing.min.js"></script>
    <script src="assets/js/plugins/classie.js"></script>
    <script src="assets/js/plugins/cbpAnimatedHeader.js"></script>
    <script src="assets/js/plugins/owl-carousel/owl.carousel.js"></script>
    <script src="assets/js/plugins/jquery.magnific-popup/jquery.magnific-popup.min.js"></script>
    <script src="assets/js/plugins/background/core.js"></script>
    <script src="assets/js/plugins/background/transition.js"></script>
    <script src="assets/js/plugins/background/background.js"></script>
    <script src="assets/js/plugins/jquery.mixitup.js"></script>
    <script src="assets/js/plugins/wow/wow.min.js"></script>
    <script src="assets/js/contact_me.js"></script>
    <script src="assets/js/plugins/jqBootstrapValidation.js"></script>
    <!-- Vitality Theme Scripts -->
    <script>
    $(document).ready(function(){
      $("#paymentform").submit();
    });
    </script>
</body>

</html>
