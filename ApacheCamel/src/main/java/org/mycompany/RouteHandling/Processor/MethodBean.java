package org.mycompany.RouteHandling.Processor;

import org.apache.camel.Exchange;
import org.mycompany.RouteHandling.Pojos.Employee;

public class MethodBean {

	public boolean processBean() throws Exception {
		
		System.out.println("Printing the bean here");		
	
		return true;
	}
}
	