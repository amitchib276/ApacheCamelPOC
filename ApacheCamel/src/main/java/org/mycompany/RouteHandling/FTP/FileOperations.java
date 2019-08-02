package org.mycompany.RouteHandling.FTP;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileOperations extends DefaultCamelContext {

	// private String ftpUrl= "ftp://speedtest.tele2.net";

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				// Move feature

				from("file:target/ftp/input?noop=true").log("File Backup Created")
				.choice()
				.when().simple("${file:name} in 'Order.xml'").to("file:target/ftp/output/order")				
				.when().simple("${file:ext} in 'txt'").to("file:target/ftp/output/ext");
				
                 // Sort by File Name
				/*from("file:target/ftp/input?sortBy=file:name&recursive=false").log("File Backup Created")
						.to("file:target/ftp/output");*/
//				// Sort by Modified
//				from("file:target/ftp/input?sortBy=file:modified&recursive=false&noop=true").log("File Backup Created")
//				.to("file:target/ftp/output");
//				// Sort by reverse modified
//				from("file:target/ftp/input?sortBy=reverse:file:modified&recursive=false&noop=true").log("File Backup Created")
//				.to("file:target/ftp/output");
				
				
				
				
				

			}

		});

		context.start();
		System.in.read();
	}

}
