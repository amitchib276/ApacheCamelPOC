package org.mycompany.BeanProcessing;


import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.mycompany.RouteHandling.Processor.MyBean;

public class BeanProcessing extends DefaultCamelContext {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			MyBean beanObj = new MyBean();
			
			@Override
			public void configure() throws Exception {

				from("timer://tickTock?period=2000").log("Starting route")
				.bean(beanObj, "processBody").log("Come out of Bean ")
				.to("file:data/outbound");
			}

		});

		context.start();;
		System.in.read();
	}

}