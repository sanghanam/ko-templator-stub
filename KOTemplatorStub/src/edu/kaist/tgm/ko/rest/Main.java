package edu.kaist.tgm.ko.rest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;


public class Main {

	private static URI getBaseURI() {
		
		InetAddress Address;
		String IP = null;
		try {
			Address = InetAddress.getLocalHost();
			IP = Address.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return UriBuilder.fromUri("http://" + IP + "/").port(2360).build();
	}

	public static final URI BASE_URI = getBaseURI();

	protected static HttpServer startServer() throws IOException {
		System.out.println("Starting grizzly...");
		ResourceConfig rc = new PackagesResourceConfig(
				"edu.kaist.tgm.ko.rest");
		return GrizzlyServerFactory.createHttpServer(BASE_URI, rc);
	}

	public static void main(String[] args) throws IOException {
		HttpServer httpServer = startServer();
		
		System.out
				.println(String
						.format("Jersey app started with WADL available at "
								+ "%sapplication.wadl\nTry out %s KO TGM Stub service\nHit enter to stop it...",
								BASE_URI, BASE_URI));
		System.in.read();
		httpServer.stop();
	}
}
