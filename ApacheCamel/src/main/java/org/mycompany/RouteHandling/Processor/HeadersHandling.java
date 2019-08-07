package org.mycompany.RouteHandling.Processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class HeadersHandling implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// Process the headers	
	
		if (exchange.getIn().getHeader("user").equals("Amit") && exchange.getIn().getHeader("role").equals("Admin")) {

			exchange.getIn().setHeader("secure", "true");
			exchange.getIn().removeHeader("user");

		}

	}

}
