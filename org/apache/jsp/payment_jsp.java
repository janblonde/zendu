/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.23
 * Generated at: 2015-10-20 19:08:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.security.*;
import java.math.*;

public final class payment_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("java.security");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.math");
    _jspx_imports_packages.add("java.sql");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_classes = null;
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=iso-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<html lang=\"en\">\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"utf-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\">\n");
      out.write("    <meta name=\"description\" content=\"\">\n");
      out.write("    <meta name=\"author\" content=\"\">\n");
      out.write("    <title>Zendu | Aangetekende brieven</title>\n");
      out.write("    <!-- Bootstrap Core CSS -->\n");
      out.write("    <link href=\"assets/css/bootstrap/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("    <!-- Retina.js - Load first for faster HQ mobile images. -->\n");
      out.write("    <script src=\"assets/js/plugins/retina/retina.min.js\"></script>\n");
      out.write("    <!-- Font Awesome -->\n");
      out.write("    <link href=\"assets/font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("    <!-- Default Fonts -->\n");
      out.write("    <link href='http://fonts.googleapis.com/css?family=Roboto:900,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>\n");
      out.write("    <link href='https://fonts.googleapis.com/css?family=Ubuntu' rel='stylesheet' type='text/css'>\n");
      out.write("    <link href='http://fonts.googleapis.com/css?family=Raleway:400,100,200,300,600,500,700,800,900' rel='stylesheet' type='text/css'>\n");
      out.write("    <!-- Modern Style Fonts (Include these is you are using body.modern!) -->\n");
      out.write("    <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>\n");
      out.write("    <link href='http://fonts.googleapis.com/css?family=Cardo:400,400italic,700' rel='stylesheet' type='text/css'>\n");
      out.write("    <!-- Vintage Style Fonts (Include these if you are using body.vintage!) -->\n");
      out.write("    <link href='http://fonts.googleapis.com/css?family=Sanchez:400italic,400' rel='stylesheet' type='text/css'>\n");
      out.write("    <link href='http://fonts.googleapis.com/css?family=Cardo:400,400italic,700' rel='stylesheet' type='text/css'>\n");
      out.write("    <!-- Plugin CSS -->\n");
      out.write("    <link href=\"assets/css/plugins/owl-carousel/owl.carousel.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("    <link href=\"assets/css/plugins/owl-carousel/owl.theme.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("    <link href=\"assets/css/plugins/owl-carousel/owl.transitions.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("    <link href=\"assets/css/plugins/magnific-popup.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("    <link href=\"assets/css/plugins/background.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("    <link href=\"assets/css/plugins/animate.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("    <!-- Vitality Theme CSS -->\n");
      out.write("    <!-- Uncomment the color scheme you want to use. -->\n");
      out.write("    <link href=\"assets/css/vitality-red.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("    <!-- <link href=\"assets/css/vitality-aqua.css\" rel=\"stylesheet\" type=\"text/css\"> -->\n");
      out.write("    <!-- <link href=\"assets/css/vitality-blue.css\" rel=\"stylesheet\" type=\"text/css\"> -->\n");
      out.write("    <!-- <link href=\"assets/css/vitality-green.css\" rel=\"stylesheet\" type=\"text/css\"> -->\n");
      out.write("    <!-- <link href=\"assets/css/vitality-orange.css\" rel=\"stylesheet\" type=\"text/css\"> -->\n");
      out.write("    <!-- <link href=\"assets/css/vitality-pink.css\" rel=\"stylesheet\" type=\"text/css\"> -->\n");
      out.write("    <!-- <link href=\"assets/css/vitality-purple.css\" rel=\"stylesheet\" type=\"text/css\"> -->\n");
      out.write("    <!-- <link href=\"assets/css/vitality-tan.css\" rel=\"stylesheet\" type=\"text/css\"> -->\n");
      out.write("    <!-- <link href=\"assets/css/vitality-turquoise.css\" rel=\"stylesheet\" type=\"text/css\"> -->\n");
      out.write("    <!-- <link href=\"assets/css/vitality-yellow.css\" rel=\"stylesheet\" type=\"text/css\"> -->\n");
      out.write("    <!-- IE8 support for HTML5 elements and media queries -->\n");
      out.write("    <!--[if lt IE 9]>\n");
      out.write("      <script src=\"https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js\"></script>\n");
      out.write("      <script src=\"https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js\"></script>\n");
      out.write("    <![endif]-->\n");
      out.write("</head>\n");
      out.write("<!-- Alternate Body Classes: .modern and .vintage -->\n");
      out.write("\n");
      out.write("<body id=\"page-top\">\n");

    ResultSet rs2 = null;
    String naam = "";

if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {

      out.write("\n");
      out.write("    You are not logged in<br/>\n");
      out.write("    <a href=\"agency.html\">Log on</a>\n");
}
else
{
    String userid = (String)session.getAttribute("userid");
    naam = (String)session.getAttribute("naam");
}
      out.write("\n");
      out.write("\n");
      out.write("    <!-- Navigation -->\n");
      out.write("    <!-- Note: navbar-default and navbar-inverse are both supported with this theme. -->\n");
      out.write("    <nav class=\"navbar navbar-inverse navbar-fixed\">\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <!-- Brand and toggle get grouped for better mobile display -->\n");
      out.write("            <div class=\"navbar-header\">\n");
      out.write("                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\">\n");
      out.write("                    <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                </button>\n");
      out.write("                <a class=\"navbar-brand page-scroll\" href=\"#page-top\">\n");
      out.write("                    Zen<span style=\"color:firebrick\">du</span>\n");
      out.write("                </a>\n");
      out.write("            </div>\n");
      out.write("            <!-- Collect the nav links, forms, and other content for toggling -->\n");
      out.write("            <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n");
      out.write("                <ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("                    <li class=\"hidden\">\n");
      out.write("                        <a class=\"page-scroll\" href=\"#page-top\"></a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a class=\"page-scroll\" href=\"#about\">Welkom ");
      out.print(naam);
      out.write("</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a class=\"page-scroll\" href=\"#process\">Profiel</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a class=\"page-scroll\" href=\"#work\">Credits</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a class=\"page-scroll\" href=\"#pricing\">Facturen</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a class=\"page-scroll\" href=\"index.html\">Uitloggen</a>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("            <!-- /.navbar-collapse -->\n");
      out.write("        </div>\n");
      out.write("        <!-- /.container -->\n");
      out.write("    </nav>\n");
      out.write("    <header>\n");
      out.write("        <div class=\"intro-content\" style=\"top:150px;\">\n");
      out.write("        \n");
String orderID = (String)request.getAttribute("orderid");

String text = "ACCEPTURL=https://java-tomcat-janblonde.c9.io/zendu/accept.jspweliveinnumber76AMOUNT=980weliveinnumber76CURRENCY=EURweliveinnumber76LANGUAGE=en_USweliveinnumber76ORDERID="+ orderID +"weliveinnumber76PSPID=zenduweliveinnumber76";

text = text + "TITLE=PAYMENTweliveinnumber76";

MessageDigest crypt = MessageDigest.getInstance("SHA-1");
crypt.reset();
crypt.update(text.getBytes("UTF-8"));

String result = new BigInteger(1, crypt.digest()).toString(16);

      out.write("\n");
      out.write("\n");
      out.write("<h1>Kies een betaalmethode</h1>\n");
      out.write("\n");
      out.write("<FORM METHOD=\"post\" ACTION=\"https://secure.ogone.com/ncol/test/orderstandard.asp\" id=form1 name=form1>\n");
      out.write("<INPUT type=\"hidden\" NAME=\"PSPID\" value=\"zendu\">\n");
      out.write("<INPUT NAME=\"orderID\" style=\"color:black\" VALUE=\"");
      out.print(orderID);
      out.write("\">\n");
      out.write("<INPUT NAME=\"amount\" style=\"color:black\" VALUE=\"980\">\n");
      out.write("<INPUT NAME=\"currency\" style=\"color:black\" VALUE=\"EUR\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"language\" VALUE=\"en_US\">\n");
      out.write("<!-- lay out information -->\n");
      out.write("\n");
      out.write("<!--<INPUT type=\"hidden\" NAME=\"MANDATEID\" VALUE=\"\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"SIGNDATE\" VALUE=\"\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"SEQUENCETYPE\" VALUE=\"\">-->\n");
      out.write("\n");
      out.write("<INPUT type=\"hidden\" NAME=\"TITLE\" VALUE=\"PAYMENT\">\n");
      out.write("<!--<INPUT type=\"hidden\" NAME=\"TXTCOLOR\" VALUE=\"#FFFFFF\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"TBLBGCOLOR\" VALUE=\"#FFFFFF\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"TBLTXTCOLOR\" VALUE=\"#000000\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"BUTTONBGCOLOR\" VALUE=\"#00467F\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"BUTTONTXTCOLOR\" VALUE=\"#FFFFFF\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"LOGO\" VALUE=\"<fill here your logo file name>\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"FONTTYPE\" VALUE=\"Verdana\">-->\n");
      out.write("\n");
      out.write("<INPUT type=\"hidden\" NAME=\"SHASIGN\" VALUE=\"");
      out.print( result );
      out.write("\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"ACCEPTURL\" VALUE=\"https://java-tomcat-janblonde.c9.io/zendu/accept.jsp\">\n");
      out.write("\n");
      out.write("<!--<INPUT type=\"hidden\" NAME=\"TP\" VALUE=\"<fill here your template page>\">\n");
      out.write("\n");
      out.write("\n");
      out.write("<INPUT type=\"hidden\" NAME=\"declineurl\" VALUE=\"\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"exceptionurl\" VALUE=\"\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"cancelurl\" VALUE=\"\">\n");
      out.write("\n");
      out.write("<INPUT type=\"hidden\" NAME=\"COM\" VALUE=\"<fill here your order description>\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"CN\" VALUE=\"<fill here your Client name>\">\n");
      out.write("<INPUT type=\"hidden\" name=\"EMAIL\" value=\"<fill here your Client email>\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"PM\" VALUE=\"\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"BRAND\" VALUE=\"\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"ownerZIP\" VALUE=\"\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"owneraddress\" VALUE=\"\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"owneraddress2\" VALUE=\"\">\n");
      out.write("<INPUT type=\"hidden\" NAME=\"owneraddress3\" VALUE=\"\">-->\n");
      out.write("<input type=\"submit\" value=\"SUBMIT\" id=submit2 name=submit2>\n");
      out.write("</form>\n");
      out.write("        \n");
      out.write("            \n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"login-form\">\n");
      out.write("        </div>\n");
      out.write("    \n");
      out.write("        <div class=\"getstarted-form\">\n");
      out.write("        </div>\n");
      out.write("    \n");
      out.write("    \n");
      out.write("        \n");
      out.write("        <div class=\"scroll-down\">\n");
      out.write("            <a class=\"btn page-scroll\" href=\"#about\"><i class=\"fa fa-angle-down fa-fw\"></i></a>\n");
      out.write("        </div>\n");
      out.write("    </header>\n");
      out.write("\n");
      out.write("    <footer class=\"footer\" style=\"background-image: url('assets/img/bg-footer.jpg')\">\n");
      out.write("        <div class=\"container text-center\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"col-md-4 contact-details\">\n");
      out.write("                    <h4><i class=\"fa fa-phone\"></i> Call</h4>\n");
      out.write("                    <p>555-213-4567</p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-md-4 contact-details\">\n");
      out.write("                    <h4><i class=\"fa fa-map-marker\"></i> Visit</h4>\n");
      out.write("                    <p>3481 Melrose Place\n");
      out.write("                        <br>Beverly Hills, CA 90210</p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-md-4 contact-details\">\n");
      out.write("                    <h4><i class=\"fa fa-envelope\"></i> Email</h4>\n");
      out.write("                    <p><a href=\"mailto:mail@example.com\">mail@example.com</a>\n");
      out.write("                    </p>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"row social\">\n");
      out.write("                <div class=\"col-lg-12\">\n");
      out.write("                    <ul class=\"list-inline\">\n");
      out.write("                        <li><a href=\"#\"><i class=\"fa fa-facebook fa-fw fa-2x\"></i></a>\n");
      out.write("                        </li>\n");
      out.write("                        <li><a href=\"#\"><i class=\"fa fa-twitter fa-fw fa-2x\"></i></a>\n");
      out.write("                        </li>\n");
      out.write("                        <li><a href=\"#\"><i class=\"fa fa-linkedin fa-fw fa-2x\"></i></a>\n");
      out.write("                        </li>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"row copyright\">\n");
      out.write("                <div class=\"col-lg-12\">\n");
      out.write("                    <p class=\"small\">&copy; 2015 Start Bootstrap Themes</p>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </footer>\n");
      out.write("\n");
      out.write("    <!-- Core Scripts -->\n");
      out.write("    <script src=\"assets/js/jquery.js\"></script>\n");
      out.write("    <script src=\"assets/js/bootstrap/bootstrap.min.js\"></script>\n");
      out.write("    <!-- Plugin Scripts -->\n");
      out.write("    <script src=\"assets/js/plugins/jquery.easing.min.js\"></script>\n");
      out.write("    <script src=\"assets/js/plugins/classie.js\"></script>\n");
      out.write("    <script src=\"assets/js/plugins/cbpAnimatedHeader.js\"></script>\n");
      out.write("    <script src=\"assets/js/plugins/owl-carousel/owl.carousel.js\"></script>\n");
      out.write("    <script src=\"assets/js/plugins/jquery.magnific-popup/jquery.magnific-popup.min.js\"></script>\n");
      out.write("    <script src=\"assets/js/plugins/background/core.js\"></script>\n");
      out.write("    <script src=\"assets/js/plugins/background/transition.js\"></script>\n");
      out.write("    <script src=\"assets/js/plugins/background/background.js\"></script>\n");
      out.write("    <script src=\"assets/js/plugins/jquery.mixitup.js\"></script>\n");
      out.write("    <script src=\"assets/js/plugins/wow/wow.min.js\"></script>\n");
      out.write("    <script src=\"assets/js/contact_me.js\"></script>\n");
      out.write("    <script src=\"assets/js/plugins/jqBootstrapValidation.js\"></script>\n");
      out.write("    <!-- Vitality Theme Scripts -->\n");
      out.write("    <script src=\"assets/js/vitality.js\"></script>\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
