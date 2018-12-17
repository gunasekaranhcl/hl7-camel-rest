package com.camel.hl7.rest;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.hl7.HL7MLLPCodec;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Service;

@Service
public class CamelHL7TestService {

	public String postHL7Message(String host, String port, String timeout, String message, String sync) {

		String inMessage = "";
		String outMessage = "";

		if (sync == null || sync.equals("")) {
			sync = "true";
		}
		if (message == null || message.equals("")) {

			message = "";
		}
		if (timeout == null || timeout.equals("")) {

			timeout = "6000";
		}

		try {

			CamelContext context = new DefaultCamelContext();

			final org.apache.camel.impl.SimpleRegistry registry = new org.apache.camel.impl.SimpleRegistry();
			final org.apache.camel.impl.CompositeRegistry compositeRegistry = new org.apache.camel.impl.CompositeRegistry();
			compositeRegistry.addRegistry(context.getRegistry());
			compositeRegistry.addRegistry(registry);
			((org.apache.camel.impl.DefaultCamelContext) context).setRegistry(compositeRegistry);

			HL7MLLPCodec hl7codec = new HL7MLLPCodec();
			hl7codec.setCharset("iso-8859-1");

			registry.put("hl7codec", hl7codec);

			ProducerTemplate template = context.createProducerTemplate();
			template.start();

			String camelUrl = "mina2:tcp://" + host + ":" + port + "?sync=" + sync + "&codec=#hl7codec&timeout="
					+ timeout;

			System.out.println("Input HL7 : \n" + inMessage);
			String out = (String) template.requestBody(camelUrl, inMessage.toString());

			System.out.println("Output HL7:\n " + out);
			outMessage = out;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return outMessage;

	}

}
