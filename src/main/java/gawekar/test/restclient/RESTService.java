package gawekar.test.restclient;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;

public class RESTService {
	final WebTarget webTarget;
	public RESTService(Client client, String endPoint) {
		this.webTarget = client.target(endPoint);
	}
	
	/**
	 * Returns true only if all data is send successfully.
	 * @param path
	 * @param data
	 * @return
	 */
	public boolean sendJson(String path,List<String> data){
		boolean returnValue = true;
		final WebTarget resourceWebTarget = webTarget.path(path);
		for (String json : data) {
			if (StringUtils.isNotBlank(json)){
				if (json.startsWith("#")){
					continue;
				}
				Response postResponse = resourceWebTarget.request().post(
						Entity.entity(json, MediaType.APPLICATION_JSON));
				if (postResponse.getStatus() == 200){
					System.out.print(".");
					
				}else{
					System.out.println(String.format("\nFailed: %s \n", json));
					returnValue = false;
				}
			}
		}
		return returnValue;
	}
	
}
