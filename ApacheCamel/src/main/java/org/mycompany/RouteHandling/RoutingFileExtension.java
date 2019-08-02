package org.mycompany.RouteHandling;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class RoutingFileExtension extends DefaultCamelContext{

	public static void main(String[] args) throws Exception {
		
		CamelContext context = new DefaultCamelContext();
		 context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				
				from("file:target/cbr/input?noop=true").log("File Routing on extensions by Order  Element")
				.choice()
				.when().simple("${file:ext} in 'xml'").to("file:target/cbr/output/xml")				
				.when().simple("${file:ext} in 'txt'").to("file:target/cbr/output/txt");
				
			}
			 
		 });
		 
		 context.start();
		 System.in.read();

	
		 
		
		
		// TODO Auto-generated method stub

	}

}
