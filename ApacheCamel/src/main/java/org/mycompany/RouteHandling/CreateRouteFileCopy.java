package org.mycompany.RouteHandling;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CreateRouteFileCopy extends DefaultCamelContext {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();

		
		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("file:target/input?noop=true&charset=utf-8").process(
						new Processor() {
							
							@Override
							public void process(Exchange arg0) throws Exception {
								System.out.println("Put logic in processor ");
								
							}
						}).to("file:target/output?charset=utf-8");
			}

		});
		context.start();
		  System.in.read();
		

	}

}
