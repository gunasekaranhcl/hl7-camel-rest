package com.camel.hl7.rest;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.camel.hl7.rest.CamelHL7TestService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class MainController {
	
	@Autowired
	CamelHL7TestService camelHL7TestService;
	
	@CrossOrigin
	@RequestMapping(value = "/hl7/message")
	@ResponseBody
	@PostMapping
	public String getBarBySimplePathWithRequestParam( @RequestParam("host") String host,@RequestParam("port") String port,@RequestParam("timeout") String timeout,@RequestParam("sync") String sync,@RequestBody String hl7message) {
	    
		System.out.println("host:"+host);
		System.out.println("port:"+port);
		System.out.println("timeout:"+timeout);
		System.out.println("hl7message:"+hl7message);
		System.out.println("sync:"+sync);		
		
		String outHL7=camelHL7TestService.postHL7Message(host, port, timeout, hl7message,sync);
				
		return outHL7;
	}
		
}