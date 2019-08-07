package org.mycompany.ExchangeHeaders;

import org.apache.camel.builder.RouteBuilder;
import org.mycompany.RouteHandling.Processor.HeadersHandling;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HandleHeaders extends RouteBuilder {

	@Value("${name.header1}")
	String propertyVal;
	
	@Override
	public void configure() throws Exception {	

		from("seda:startHeader").log("Starting Exchange Headers - {{name.header}}").process(new HeadersHandling())
				.to("activemq:queue:MyQueue");

	}

}
