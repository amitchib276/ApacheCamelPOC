package org.mycompany.RouteHandling.Processor;

import org.apache.camel.Exchange;
import org.apache.camel.Headers;
import org.mycompany.RouteHandling.Pojos.Employee;

public class MyBean {

	public void processBody(Exchange exchange) throws Exception {
		
		System.out.println("Printing the bean here");
		
		Employee employee = new Employee();
		employee.setEmpId(1);
		// employee.setEmpName("JavaInUse Rocks");
		System.out.println(
				" Received Exchange: "+  " MIP: " + exchange.getPattern());
		exchange.getIn().setBody(employee);
	}
	
	
public void processBody(Exchange exchange,String name) throws Exception {
		
		System.out.println("processBody overloaded method " +name);
		
		Employee employee = new Employee();
		employee.setEmpId(1);
		// employee.setEmpName("JavaInUse Rocks");
		System.out.println(
				" Received Exchange: "+  " MIP: " + exchange.getPattern());
		exchange.getIn().setBody(employee);
	}



public void handleBodyHeaders(String body) throws Exception {
	
	System.out.println("processBody  method " +body);
	
}


}
