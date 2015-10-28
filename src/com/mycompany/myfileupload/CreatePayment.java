import javax.xml.soap.*;
//import javax.xml.namsespace.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class CreatePayment {

    /**
     * Starting point for the SAAJ - SOAP Client Testing
     */
    public static void main(String args[]) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            String url = "https://test.docdatapayments.com/ps/services/paymentservice/1_3";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);

            // Process the SOAP Response
            printSOAPResponse(soapResponse);

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
    }

    private static SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "https://test.docdatapayments.com/ps/services/paymentservice/1_3";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("ns1", "http://www.docdatapayments.com/services/paymentservice/1_3/");
        //envelope.addNamespaceDeclaration("ns1", "https://secure.docdatapayments.com/ps/services/paymentservice/1_3");

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
        //Name bodyName = envelope.createName("createRequest", "ns1","https://secure.docdatapayments.com/ps/services/paymentservice/1_3");
        SOAPBodyElement createRequest = soapBody.addBodyElement(bodyName);
        
        //SOAPBody soapBody = envelope.getBody();
        //SOAPElement createRequest = soapBody.addChildElement("createRequest");
        
        Name version = envelope.createName("version");
        createRequest.addAttribute(version, "1.3");
        //Name xmlns = envelope.createName("xmlns");
        //createRequest.addAttribute(xmlns,"xyz");
        
        //merchant
        SOAPElement merchant = createRequest.addChildElement("merchant","ns1");
        
        Name merchantName = envelope.createName("name");
        merchant.addAttribute(merchantName,"zendu_be");
        
        Name password = envelope.createName("password");
        merchant.addAttribute(password,"Quyazu3e");
        
        //merchantorderreference
        SOAPElement orderRef = createRequest.addChildElement("merchantOrderReference","ns1");
        orderRef.addTextNode("123456");
        
        //paymentpreferences
        SOAPElement paymentPreferences = createRequest.addChildElement("paymentPreferences","ns1");
        SOAPElement profile = paymentPreferences.addChildElement("profile","ns1");
        profile.addTextNode("mytestprofile");
        SOAPElement nrOfDays = paymentPreferences.addChildElement("numberOfDaysToPay","ns1");
        nrOfDays.addTextNode("14");
        
        //menupreferences - TODO at a later stage
        
        //shopper
        SOAPElement shopper = createRequest.addChildElement("shopper","ns1");
        Name clientID = envelope.createName("id");
        shopper.addAttribute(clientID,"1234");
        SOAPElement name = shopper.addChildElement("name","ns1");
        SOAPElement first = name.addChildElement("first","ns1");
        first.addTextNode("Jan");
        SOAPElement last = name.addChildElement("last","ns1");
        last.addTextNode("Blonde");
        SOAPElement email = shopper.addChildElement("email","ns1");
        email.addTextNode("jan.blonde@icloud.com");
        SOAPElement language = shopper.addChildElement("language","ns1");
        Name languageCode = envelope.createName("code");
        language.addAttribute(languageCode,"nl");
        SOAPElement gender = shopper.addChildElement("gender","ns1");
        gender.addTextNode("M");
        
        //totalgrossamount
        SOAPElement totalGrossAmount = createRequest.addChildElement("totalGrossAmount","ns1");
        Name currency = envelope.createName("currency");
        totalGrossAmount.addAttribute(currency,"EUR");
        totalGrossAmount.addTextNode("980");
        
        //billTo
        SOAPElement billTo = createRequest.addChildElement("billTo","ns1");
        SOAPElement billName = billTo.addChildElement("name","ns1");
        SOAPElement billFirst = billName.addChildElement("first","ns1");
        billFirst.addTextNode("Jan");
        SOAPElement billLast = billName.addChildElement("last","ns1");
        billLast.addTextNode("Blonde");
        
        SOAPElement billAddress = billTo.addChildElement("address","ns1");
        SOAPElement street = billAddress.addChildElement("street","ns1");
        street.addTextNode("Huybrechtsstraat");
        SOAPElement houseNumber = billAddress.addChildElement("houseNumber","ns1");
        houseNumber.addTextNode("76");
        SOAPElement postalCode = billAddress.addChildElement("postalCode","ns1");
        postalCode.addTextNode("2140");
        SOAPElement city = billAddress.addChildElement("city","ns1");
        city.addTextNode("Borgerhout");
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