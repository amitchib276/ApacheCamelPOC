package org.mycompany.RouteHandling;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ContentBasedRouting extends DefaultCamelContext{

	public static void main(String[] args) throws Exception {
		
		CamelContext context = new DefaultCamelContext();
		 context.addRoutes(new RouteBuilder() {

			/*@Override
			public void configure() throws Exception {
				
				from("file:target/cbr/input?noop=true&fileName=Order.xml").log("Split by Order  Element").split(xpath("Orders/Order"))
				.choice()
				.when(xpath("Order/Country='USA'")).to("file:target/cbr/output/US")				
				.when(xpath("Order/Country='UK'")).to("file:target/cbr/output/UK");
				
			}*/
			 
			 
			 // Header based routing
			 @Override
			public void configure() throws Exception {

				from("file:target/cbr/input?noop=true&fileName=Order.xml").log("Split by Order  Element").choice()
						.when(header("foo").isEqualTo("test")).to("file:target/cbr/output/HeaderFoo").otherwise().to("seda:xyz");

			}
			 
			 
			 
		 });
		 
		 context.start();
		 System.in.read();

	
		 
		
		
		// TODO Auto-generated method stub

	}

}
