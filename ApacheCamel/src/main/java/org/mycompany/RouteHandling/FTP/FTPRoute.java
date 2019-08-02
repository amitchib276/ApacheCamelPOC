package org.mycompany.RouteHandling.FTP;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FTPRoute extends DefaultCamelContext {
	
	//private String ftpUrl= "ftp://speedtest.tele2.net"; 

	public static void main(String[] args) throws Exception {
		
		CamelContext context = new DefaultCamelContext();
		 context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				
				from("ftp://test.rebex.net?Password=password&Username=demo").to("file:target/ftp");
				
			}
			 
		 });
		 
		 context.start();
		 System.in.read();
}

}
