package org.mycompany.RouteHandling;

import javax.xml.bind.JAXBContext;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.mycompany.RouteHandling.Pojos.Employee;
import org.mycompany.RouteHandling.Processor.MyProcessor;

public class XmlToJson extends DefaultCamelContext {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();

		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {

				JaxbDataFormat xmlDataFormat = new JaxbDataFormat();
				JAXBContext con = JAXBContext.newInstance(Employee.class);
				xmlDataFormat.setContext(con);
				// JSON Data Format
				JacksonDataFormat jsonDataFormat = new JacksonDataFormat(Employee.class);

				
				from("file:target/xmlToJson?noop=true")
						.unmarshal(xmlDataFormat).log("{$body}").process(new MyProcessor()).marshal(jsonDataFormat)
						.log("{$body}").to("file:target/cbr/output");

			}

		});
		context.start();
		  System.in.read();
		

	}

}
