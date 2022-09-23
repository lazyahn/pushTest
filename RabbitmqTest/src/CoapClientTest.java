import java.io.IOException;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.elements.exception.ConnectorException;

public class CoapClientTest {


	public static void main(String[] args) {
		
		try {
			getClient();
			postClient();
			putClient();
			deleteClient();
			
			
		} catch (ConnectorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static String URI_HOST = "localhost:8888";
	
	// GET, DELETE
	// uri-query : param1=value1
	static String uriQuery = "?" + "param1" + "=" + "value1";;
	
	// POST, PUT 
	// payload : {"key1":"value1"}
	static String payloadJson = "{\"key1\":\"value1\"}";		
	
	
	
	
	public static void getClient() throws ConnectorException, IOException
	{
		String uriPath = "/getPath";
		
		CoapClient client = new CoapClient(URI_HOST + uriPath + uriQuery);
		
		String responseMsg = client.get().getResponseText();
		System.out.println("Response : " + responseMsg);
	}
	
	
	public static void postClient() throws ConnectorException, IOException
	{
		String uriPath = "/postPath";
		
		CoapClient client = new CoapClient(URI_HOST + uriPath);
		
		String responseMsg = client.post(payloadJson, MediaTypeRegistry.APPLICATION_JSON).getResponseText();
		System.out.println("Response : " + responseMsg);
	}
	
	
	public static void putClient() throws ConnectorException, IOException
	{
		String uriPath = "/putPath";
		
		CoapClient client = new CoapClient(URI_HOST + uriPath);
		
		String responseMsg = client.put(payloadJson, MediaTypeRegistry.APPLICATION_JSON).getResponseText();
		System.out.println("Response : " + responseMsg);
	}
	
	
	public static void deleteClient() throws ConnectorException, IOException
	{
		String uriPath = "/deletePath";
		
		CoapClient client = new CoapClient(URI_HOST + uriPath + uriQuery);
		
		String responseMsg = client.delete().getResponseText();
		System.out.println("Response : " + responseMsg);
	}
	
	
	
	
}
