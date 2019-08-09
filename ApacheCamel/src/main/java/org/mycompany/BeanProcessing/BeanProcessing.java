package org.mycompany.BeanProcessing;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.mycompany.RouteHandling.Processor.MyBean;

public class BeanProcessing extends DefaultCamelContext {
	
	//Routes have been called for n times
	static int  counter = 0;


	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			MyBean beanObj = new MyBean();
			
			@Override
			public void configure() throws Exception {

				/*
				 * from("timer://tickTock?period=2000").log("Starting route") .bean(beanObj,
				 * "processBody").log("Come out of Bean ") .to("file:data/outbound");
				 * 
				 */

				// Calling overloaded method

				/*
				 * from("timer://tickTock?period=2000").log("Starting route") .bean(beanObj,
				 * "processBody(*,overloaded)").log("Come out of Bean ")
				 * .to("file:data/outbound");
				 */
				
				// Passing the body and header
				from("timer://tickTock?period=2000").setBody(simple("Hello World, " + counter++)).log("Starting route")
						.bean(beanObj, "handleBodyHeaders(${body},${header)").log("Come out of Bean ")
						.to("file:data/outbound");

			}

		});

		/*
		 * ProducerTemplate produce = context.createProducerTemplate();
		 * produce.sendBody("direct:start", "testing body");
		 */
		context.start();
		;
		System.in.read();
	}

}