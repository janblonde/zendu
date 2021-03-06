package com.mycompany.myfileupload;

import javax.xml.soap.*;
//import javax.xml.namsespace.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.util.*;
import org.w3c.dom.*;

public class CreatePaymentRequest {
    
    public static String myOrderRef = "O005";
    public static String myClientID = "1234";
    public static String myFirst = "Jan";
    public static String myLast = "Blonde";
    public static String myEmail = "jan.blonde@icloud.com";
    public static String myAmount = "850";
    public static String myStreet = "Huybrechtsstraat";
    public static String myHouseNumber = "76";
    public static String myPostalCode = "2140";
    public static String myCity = "Borgerhout";

    /**
     * Starting point for the SAAJ - SOAP Client Testing
     */
    public static void main(String args[]) {
        String response = "";
        
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            //REMOVE String url = "https://test.docdatapayments.com/ps/services/paymentservice/1_3";
            String url = "https://secure.docdatapayments.com/ps/services/paymentservice/1_3";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            Source sourceContent = soapResponse.getSOAPPart().getContent();
            System.out.print("\nResponse SOAP Message = ");
            StreamResult result = new StreamResult(System.out);
            transformer.transform(sourceContent, result);

            // Process the SOAP Response
            //printSOAPResponse(soapResponse);
            NodeList responseElement = soapResponse.getSOAPBody().getElementsByTagName("key");
            
            if(null!=responseElement){
                response = responseElement.item(0).getFirstChild().getNodeValue(); 
            }else{
                response="ERROR";
            }  

            soapConnection.close();
            
            //return response;
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
            //return "ERROR";
        }        
    }
    
    public static String makeCall() {
        String response = "";
        
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            //REMOVE String url = "https://test.docdatapayments.com/ps/services/paymentservice/1_3";
            String url = "https://secure.docdatapayments.com:443/ps/services/paymentservice/1_3";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            Source sourceContent = soapResponse.getSOAPPart().getContent();
            System.out.print("\nResponse SOAP Message = ");
            StreamResult result = new StreamResult(System.out);
            transformer.transform(sourceContent, result);

            // Process the SOAP Response
            //printSOAPResponse(soapResponse);
            NodeList responseElement = soapResponse.getSOAPBody().getElementsByTagName("key");
            
            if(null!=responseElement){
                response = responseElement.item(0).getFirstChild().getNodeValue(); 
            }else{
                response="ERROR";
            }  

            soapConnection.close();
            
            return response;
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
            return "ERROR";
        }
        
    }

    private static SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        //REMOVE String serverURI = "https://www.docdatapayments.com/services/paymentservice/1_3/";
        String serverURI = "https://secure.docdatapayments.com/ps/services/paymentservice/1_3";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("ns1", "http://www.docdatapayments.com/services/paymentservice/1_3/");

        /*
        Constructed SOAP Request Message:
        <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:example="http://ws.cdyne.com/">
            <SOAP-ENV:Header/>
            <SOAP-ENV:Body>
                <example:VerifyEmail>
                    <example:email>mutantninja@gmail.com</example:email>
                    <example:LicenseKey>123</example:LicenseKey>
                </example:VerifyEmail>
            </SOAP-ENV:Body>
        </SOAP-ENV:Envelope>
         */

        // SOAP Body
        SOAPBody soapBody = soapMessage.getSOAPBody();
        Name bodyName = envelope.createName("createRequest", "ns1","http://www.docdatapayments.com/services/paymentservice/1_3/");
        SOAPBodyElement createRequest = soapBody.addBodyElement(bodyName);
        
        Name version = envelope.createName("version");
        createRequest.addAttribute(version, "1.3");
        
        //merchant
        SOAPElement merchant = createRequest.addChildElement("merchant","ns1");
        
        Name merchantName = envelope.createName("name");
        merchant.addAttribute(merchantName,"zendu_be");
        
        Name password = envelope.createName("password");
        //REMOVE merchant.addAttribute(password,"Quyazu3e");
        merchant.addAttribute(password,"StED6xun");
        
        //merchantorderreference
        SOAPElement orderRef = createRequest.addChildElement("merchantOrderReference","ns1");
        orderRef.addTextNode(myOrderRef);
        //REMOVE orderRef.addTextNode("X0612" + myOrderRef);
        
        //paymentpreferences
        SOAPElement paymentPreferences = createRequest.addChildElement("paymentPreferences","ns1");
        SOAPElement profile = paymentPreferences.addChildElement("profile","ns1");
        profile.addTextNode("mytestprofile");
        SOAPElement nrOfDays = paymentPreferences.addChildElement("numberOfDaysToPay","ns1");
        nrOfDays.addTextNode("14");
        
        //menupreferences
        SOAPElement menuPreferences = createRequest.addChildElement("menuPreferences","ns1");
        SOAPElement css = menuPreferences.addChildElement("css","ns1");
        Name cssID = envelope.createName("id");
        css.addAttribute(cssID,"1");
        
        //shopper
        SOAPElement shopper = createRequest.addChildElement("shopper","ns1");
        Name clientID = envelope.createName("id");
        shopper.addAttribute(clientID,myClientID);
        SOAPElement name = shopper.addChildElement("name","ns1");
        SOAPElement first = name.addChildElement("first","ns1");
        first.addTextNode(myFirst);
        SOAPElement last = name.addChildElement("last","ns1");
        last.addTextNode(myLast);
        SOAPElement email = shopper.addChildElement("email","ns1");
        email.addTextNode(myEmail);
        SOAPElement language = shopper.addChildElement("language","ns1");
        Name languageCode = envelope.createName("code");
        language.addAttribute(languageCode,"nl");
        SOAPElement gender = shopper.addChildElement("gender","ns1");
        gender.addTextNode("M");
        
        //totalgrossamount
        SOAPElement totalGrossAmount = createRequest.addChildElement("totalGrossAmount","ns1");
        Name currency = envelope.createName("currency");
        totalGrossAmount.addAttribute(currency,"EUR");
        totalGrossAmount.addTextNode(myAmount);
        
        //billTo
        SOAPElement billTo = createRequest.addChildElement("billTo","ns1");
        SOAPElement billName = billTo.addChildElement("name","ns1");
        SOAPElement billFirst = billName.addChildElement("first","ns1");
        billFirst.addTextNode(myFirst);
        SOAPElement billLast = billName.addChildElement("last","ns1");
        billLast.addTextNode(myLast);
        
        SOAPElement billAddress = billTo.addChildElement("address","ns1");
        SOAPElement street = billAddress.addChildElement("street","ns1");
        street.addTextNode(myStreet);
        SOAPElement houseNumber = billAddress.addChildElement("houseNumber","ns1");
        houseNumber.addTextNode(myHouseNumber);
        SOAPElement postalCode = billAddress.addChildElement("postalCode","ns1");
        postalCode.addTextNode(myPostalCode);
        SOAPElement city = billAddress.addChildElement("city","ns1");
        city.addTextNode(myCity);
        SOAPElement country = billAddress.addChildElement("country","ns1");
        Name countryCode = envelope.createName("code");
        country.addAttribute(countryCode, "BE");
        
        //SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("email", "example");
        //soapBodyElem1.addTextNode("mutantninja@gmail.com");
        //SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("LicenseKey", "example");
        //soapBodyElem2.addTextNode("123");

        //MimeHeaders headers = soapMessage.getMimeHeaders();
        //headers.addHeader("SOAPAction", serverURI  + "/createRequest");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }

    /**
     * Method used to print the SOAP Response
     */
    private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);

    }

}