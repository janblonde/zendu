<%@ page import ="java.sql.*" %>
<%@ page import ="com.mycompany.myfileupload.Properties" %>
<!DOCTYPE html>
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
    String firstName = "";
    String lastName = "";
    String streetName = "";
    String streetNumber = "";
    String zipCode = "";
    String city = "";
    String company = "";
    boolean credits = false; 
    
if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
    response.sendRedirect("aangetekende-brief.jsp");
}else{

    String userid = (String)session.getAttribute("userid");
    naam = (String)session.getAttribute("naam");

    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/c9", Properties.username, Properties.password);
    Statement st = con.createStatement();
    ResultSet rs1;
    rs1 = st.executeQuery("select * from Members where email='" + userid + "';");
    
    if (rs1.next()){
        String memberID = rs1.getString("id");
        firstName = rs1.getString("first_name");
        lastName = rs1.getString("last_name");
        streetName = rs1.getString("streetname");
        streetNumber = rs1.getString("streetnumber");
        zipCode = rs1.getString("zipcode");
        city = rs1.getString("city");
        company = rs1.getString("company");
        
        ResultSet rs3 = st.executeQuery("select SUM(amount) as huidigTotaal from CreditLog where member_id=" + memberID +";");
        if (rs3.next()){
            if(rs3.getInt("huidigTotaal")>0) credits = true;
        }
        
        rs2 = st.executeQuery("select * from Brieven where member_id=" + memberID + ";");
    }%>




    <!-- Navigation -->
    <!-- Note: navbar-default and navbar-inverse are both supported with this theme. -->
    <nav class="navbar navbar-inverse navbar-fixed">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand page-scroll" href="success.jsp">
                    Zen<span style="color:firebrick">du</span>
                </a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a class="page-scroll" href="#page-top"></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="profile.jsp">Welkom <%=naam%></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="credits.jsp">Credits</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="invoices.jsp">Facturen</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="aangetekende-brief.jsp">Uitloggen</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
    <header style="height:1800px;">
        <div>
            <button type="submit" class="btn btn-outline-dark" onclick="window.location.href='success.jsp'" style="position:relative;top:80px;left:80px">Terug naar overzicht</button><br><br>
        </div>

    <section id="process" class="services">
        <div class="container">        
            <div class="row content-row">
                
              <form id="upload" action="upload" method="post" enctype="multipart/form-data">
                <legend>Gegevens bestemmeling</legend>
                <div class="form-group">
                  <label for="destinationfirstname">Voornaam:</label>
                  <input id="destinationfirstname" type="text" class="form-control valid" name="destinationfirstname" style="width:40%">
                </div>
                <div class="form-group">
                  <label for="destinationlastname">Naam: <span style="color:red">*</span></label>
                  <input id="destinationlastname" type="text" class="form-control" name="destinationlastname" style="width:40%">
                  <span class="error">Dit is een verplicht veld</span>
                </div>
                <div class="form-group">
                  <label for="destinationlastname">Bedrijf: </span></label>
                  <input id="destinationcompany" type="text" class="form-control valid" name="destinationcompany" style="width:40%">
                </div>
                <div class="form-group">
                  <label for="destinationstreetname">Straatnaam: <span style="color:red">*</span></label>
                  <input id="destinationstreetname" type="text" class="form-control" name="destinationstreetname" style="width:70%">
                  <span class="error">Dit is een verplicht veld</span>
                </div>
                <div class="form-group">
                  <label for="destinationstreetnumber">Straatnummer: <span style="color:red">*</span></label>
                  <input id="destinationstreetnumber" type="text" class="form-control" name="destinationstreetnumber" style="width:10%">
                  <span class="error">Dit is een verplicht veld</span>
                </div>
                <div class="form-group">
                  <label for="destinationzipcode">Postcode: <span style="color:red">*</span></label>
                  <input id="destinationzipcode" type="text" class="form-control" name="destinationzipcode" style="width:20%">
                  <span class="error">Dit is een verplicht veld</span>
                </div>
                <div class="form-group">
                  <label for="destinationcity">Stad: <span style="color:red">*</span></label>
                  <input id="destinationcity" type="text" class="form-control" name="destinationcity" style="width:40%">
                  <span class="error">Dit is een verplicht veld</span>
                </div>
                <div class="form-group">
                  <label for="destinationemail">E-mail adres:</label>
                  <input id="destinationemail" type="email" class="form-control valid" name="destinationemail" style="width:40%">
                  <span class="error">Gelieve een geldig email adres in te geven</span>
                </div>
                <legend>Te verzenden document <span style="color:red">*</span></legend>
                <div class="form-group">
                  <input id="file" name="file" type="file" class="file" accept="application/pdf">
                  <span class="error"><br>We aanvaarden enkel PDF documenten, omdat we enkel zo kunnen garanderen dat uw opmaak exact behouden blijft.<br>Dus graag uw document eerst opslaan in PDF formaat voor u het oplaadt.</span>
                </div>
                <legend>Uw gegevens</legend>
                <div class="form-group">
                  <label for="senderfirstname">Voornaam:</label>
                  <input id="senderfirstname" type="text" class="form-control valid" name="senderfirstname" value="<%=firstName%>">
                </div>
                <div class="form-group">
                  <label for="senderlastname">Naam: <span style="color:red">*</span></label>
                  <input id="senderlastname" type="text" class="form-control valid" name="senderlastname" value="<%=lastName%>">
                  <span class="error">Dit is een verplicht veld</span>
                </div>
                <div class="form-group">
                  <label for="sendercompany">Vennootschap:</label>
                  <input id="sendercompany" type="text" class="form-control valid" name="sendercompany" value="<%=company%>">
                </div>
                <div class="form-group">
                  <label for="senderstreetname">Straatnaam: <span style="color:red">*</span></label>
                  <input id="senderstreetname" type="text" class="form-control valid" name="senderstreetname" value="<%=streetName%>">
                  <span class="error">Dit is een verplicht veld</span>
                </div>
                <div class="form-group">
                  <label for="senderstreetnumber">Straatnummer: <span style="color:red">*</span></label>
                  <input id="senderstreetnumber" type="text" class="form-control valid" name="senderstreetnumber" value="<%=streetNumber%>">
                  <span class="error">Dit is een verplicht veld</span>
                </div>
                <div class="form-group">
                  <label for="senderzipcode">Postcode: <span style="color:red">*</span></label>
                  <input id="senderzipcode" type="text" class="form-control valid" name="senderzipcode" value="<%=zipCode%>">
                  <span class="error">Dit is een verplicht veld</span>
                </div>
                <div class="form-group">
                  <label for="sendercity">Stad: <span style="color:red">*</span></label>
                  <input id="sendercity" type="text" class="form-control valid" name="sendercity" value="<%=city%>">
                </div>
                <div id="success_upload"></div>
                <div class="form-group"><% if (credits){%>
                  <input id="upload_new" type="submit" class="btn btn-lg btn-default" name="upload_new" value="verzenden"/>
                                        <% }else{ %>
                  <input id="payment_new" type="submit" class="btn btn-lg btn-default" name="payment_new" value="betalen en verzenden"/>
                                        <% } %>
                  <a id="closebutton" name="closebutton" class="btn btn-lg btn-default">Cancel</a><br>
                </div>
              </form>
            </div>
        </div>
    </section>
        
        <div class="login-form">
        </div>
    
        <div class="getstarted-form">
        </div>
    
    
        
        <div class="scroll-down">
            <a class="btn page-scroll" href="#about"><i class="fa fa-angle-down fa-fw"></i></a>
        </div>
    </header>

    <footer class="footer" style="background-image: url('assets/img/bg-footer.jpg')">
        <div class="container text-center">
            <div class="row">
                <div class="col-md-4 contact-details">
                    <h4><i class="fa fa-phone"></i> Call</h4>
                    <p>0489 62 19 67</p>
                </div>
                <div class="col-md-4 contact-details">
                    <h4><i class="fa fa-map-marker"></i> Visit</h4>
                    <p>Huybrechtsstraat 76
                        <br>2140 Borgerhout</p>
                </div>
                <div class="col-md-4 contact-details">
                    <h4><i class="fa fa-envelope"></i> Email</h4>
                    <p><a href="mailto:mail@example.com">jan@zendu.be</a>
                    </p>
                </div>
            </div>
            <div class="row social">
                <div class="col-lg-12">
                    <ul class="list-inline">
                        <li><a href="#"><i class="fa fa-facebook fa-fw fa-2x"></i></a>
                        </li>
                        <li><a href="#"><i class="fa fa-twitter fa-fw fa-2x"></i></a>
                        </li>
                        <li><a href="#"><i class="fa fa-linkedin fa-fw fa-2x"></i></a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row copyright">
                <div class="col-lg-12">
                    <p class="small">&copy; 2015 Zendu.be Aangetekende brieven</p>
                    <p class="small"><a href="disclaimer.html">Disclaimer</a> &nbsp &nbsp <a href="cookies.html">Cookies Policy</a></p>
                </div>
            </div>
        </div>
    </footer>

    <!-- Core Scripts -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/bootstrap/bootstrap.min.js"></script>
    <!-- Plugin Scripts -->
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
    <script src="assets/js/vitality.js"></script>
<%}%>
</body>

</html>
