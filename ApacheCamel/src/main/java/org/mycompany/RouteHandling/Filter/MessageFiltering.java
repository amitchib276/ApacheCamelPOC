package org.mycompany.RouteHandling.Filter;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class MessageFiltering extends DefaultCamelContext {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				from("file:target/filerting").filter(header("foo").isEqualTo("test")).to("activemq:Queue1");

			}

		});

		context.start();;
		System.in.read();
	}

}