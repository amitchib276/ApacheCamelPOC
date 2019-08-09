package org.mycompany.BeanProcessing.TestCase;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.mycompany.RouteHandling.Processor.MyBean;

public class TestRoute extends DefaultCamelContext {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			MyBean beanObj = new MyBean();

			// Handling headers and body
			@Override
			public void configure() throws Exception {

				/*
				 * from("timer://tickTock?period=2000").setBody(simple("Hello World")).
				 * log("Starting route") .bean(beanObj,
				 * "handleBodyHeaders(${body},${header)").log("Come out of Bean ")
				 * .to("file:data/outbound");
				 */

				from("direct:sampleInput").log("Received Message is ${body} and Headers are ${headers}")
						.to("mock:output");

				
					from("direct:target/sampleInput").log("Received Message is ${body} and Headers are ${headers}")
							.to("file:target/sampleOutput?fileName=output.txt");
				

			}

			/*
			 * ProducerTemplate produce = context.createProducerTemplate();
			 * produce.sendBody("direct:start", "testing body");
			 */
		});

		context.start();

		System.in.read();

	}
}
