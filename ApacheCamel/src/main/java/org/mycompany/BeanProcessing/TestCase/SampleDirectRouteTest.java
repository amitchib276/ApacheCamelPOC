package org.mycompany.BeanProcessing.TestCase;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Ignore;
import org.junit.Test;

public class SampleDirectRouteTest extends CamelTestSupport {

	@Test
	public void sampleMockTest() throws InterruptedException {
		String expected = "Hello";
		/**
		 * Producer Template.
		 */
		MockEndpoint mock = getMockEndpoint("mock:output");
		mock.expectedBodiesReceived(expected);
		String input = "Hello";
		template.sendBody("direct:sampleInput", input);
		assertMockEndpointsSatisfied();

	}

	@Ignore
	public void SampleTestRoute() throws InterruptedException {
		template.sendBody("direct:sampleInput", "Hello");

		File file = new File("target/sampleOutput");
		assertTrue(file.isDirectory());
		Exchange exchange = consumer.receive("file:target/sampleOutput");
		System.out.println("Received body is :" + exchange.getIn().getBody());
		System.out.println("File Name is :" + exchange.getIn().getHeader("CamelFileName"));
		assertEquals("output.txt", exchange.getIn().getHeader("CamelFileName"));

		Thread.sleep(50000);
	}
}