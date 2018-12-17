# hl7-camel-rest

HL7 testing utility :

It is a generic Hl7 Testing utility using spring boot . It posts HL7 message to Camel queue and waits for message response for configured time or parameter timeout.


    
Example Camel client code to test HL7 camel application using below parameters 
        hostname : Camel queue host name
        port : Camel queue port
        sync : true or false for synchronous request or asynchronous request
        timeout : Waiting time for exchange 
   

      String camelUrl = "mina2:tcp://" + host + ":" + port + "?sync=" + sync + "&codec=#hl7codec&timeout="
                + timeout;

      System.out.println("Input HL7 : \n" + message);
      String out = (String) template.requestBody(camelUrl, message.toString().trim());

      System.out.println("Output HL7:\n " + out);
