package org.mycompany.RouteHandling.Filter;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.bean.MethodBean;
import org.apache.camel.impl.DefaultCamelContext;


public class BeanFiltering extends DefaultCamelContext {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				from("seda:start").filter().method(MethodBean.class, "processBean")
						.log("Filter the gold members").to("activemq:queue:MyQueue");

			}

		});

		ProducerTemplate produce = context.createProducerTemplate();
		produce.sendBodyAndHeader("seda:start",  "<hello>world!</hello>","level", "Gold");

		context.start();
		System.in.read();
	}

}