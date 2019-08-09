/**
 *  Copyright 2005-2018 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package org.mycompany;

import java.util.HashMap;
import java.util.Map;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.mycompany.ExchangeHeaders.HandleHeaders;
import org.mycompany.errorHandling.MyExceptionHandling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * A spring-boot application that includes a Camel route builder to setup the
 * Camel routes
 */
@SpringBootApplication
@PropertySource("classpath:myProperties.properties")
public class Application {

	// must have a main method spring-boot can run
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);

		CamelContext context = new DefaultCamelContext();

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		context.addComponent("activemq", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

		PropertiesComponent pc = new PropertiesComponent();
		pc.setLocation("classpath:myProperties.properties");	
		
		context.addComponent("properties", pc);
		
		
		//Add here the routes you want to run
		context.addRoutes(new HandleHeaders());
		context.addRoutes(new MyExceptionHandling());
		
		
		context.start();
		Thread.sleep(3000);
		ProducerTemplate producer = context.createProducerTemplate();
		Map<String, Object> headers = new HashMap();
		headers.put("user", "Amit");
		headers.put("role", "Admin");

		producer.sendBodyAndHeaders("direct:inputA", "Welcome processing for Headers", headers);

	}

}
