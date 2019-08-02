package org.mycompany.RouteHandling.Processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mycompany.RouteHandling.Pojos.Employee;


public class MyProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		Employee employee = exchange.getIn().getBody(Employee.class);
		//employee.setEmpName("JavaInUse Rocks");
		 System.out.println(" Received Exchange: " + exchange.getIn().getBody(Employee.class) + ", MIP: " + exchange.getPattern());
		exchange.getIn().setBody(employee);
	}

}
