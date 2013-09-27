package gawekar.test.restclient;

import gawekar.test.util.ClasspathFileReader;

import java.util.List;

import javax.ws.rs.client.Client;

public class Main {
	
	public static void main(String args[]){
		final Client client = JerseyClientToUntrustedServer.getClient();
		final RESTService service = new RESTService(client,"https://localhost:9091/0/transformations/");
		boolean single = false;
		final String fileName = single?"single_stroke_point.json":"multiple_stroke_point.json";
		final ClasspathFileReader fileReader = new ClasspathFileReader(fileName);
		final List<String> data = fileReader.readLines();
		
		boolean finalResult = service.sendJson("enetpulse.tennis.live", data);
		System.out.println("All data posted? >>> " + finalResult );
	}
}
