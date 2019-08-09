package org.mycompany.RouteHandling.Processor;

import org.apache.camel.CamelException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mycompany.RouteHandling.Pojos.Employee;


public class ExceptionProcessor implements Processor {

	 public void process(Exchange exchange) throws Exception {
	        System.out.println("Exception Thrown");
	        throw new CamelException();
	    }

}
