package org.mycompany.RouteHandling.Filter;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class JMSMessaging {

	// TODO Auto-generated method stub

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		// Note we can explicit name of the component
		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				from("seda:start").log("Jms messages transferred").to("jms:Queue1");

			}

		});

		ProducerTemplate produce = context.createProducerTemplate();
		produce.sendBody("seda:start", "testing body");

		context.start();
		System.in.read();
	}

}