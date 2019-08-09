package org.mycompany.errorHandling;

import org.apache.camel.CamelException;
import org.apache.camel.ValidationException;
import org.apache.camel.builder.RouteBuilder;
import org.mycompany.RouteHandling.Processor.ExceptionProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyExceptionHandling extends RouteBuilder {
	
	

	public void configure() {
		System.out.println("test here");
		

		onException(ValidationException.class)
		.maximumRedeliveries(6)	;
		
		from("direct:inputA").doTry().process(new ExceptionProcessor()).doCatch(CamelException.class)
				.log("in Exception 1st Route").to("direct:inputB");
		from("direct:inputB").log("in Exception  2nd Route");
	}

}
