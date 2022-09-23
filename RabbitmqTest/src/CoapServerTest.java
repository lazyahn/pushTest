import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.server.resources.CoapExchange;


public class CoapServerTest {

	
	public static void main(String[] args) {
		
		CoapServer server = new CoapServer(8888);
		
		server.add(new MyResource("getPath"));
		server.add(new MyResource("postPath"));
		server.add(new MyResource("putPath"));
		server.add(new MyResource("deletePath"));
		
		server.start();
		System.out.println(server);
	}
	
	/*
	public void CoapServer()
	{
		CoapServer server = new CoapServer(8888);
		
		server.add(new MyResource("getPath"));
		server.add(new MyResource("postPath"));
		server.add(new MyResource("putPath"));
		server.add(new MyResource("deletePath"));
		
		server.start();
	}
	*/
	
	
	public static class MyResource extends CoapResource 
	{
		public MyResource(String name) {
			super(name);
		}
		
		
		// GET
		@Override
		public void handleGET(CoapExchange exchange) 
		{
			String path = exchange.getRequestOptions().getUriPathString();
			if(path.equals("getPath"))
			{
				System.out.println("=== handle GET ===");
				System.out.println(exchange.getRequestOptions());
				
				exchange.respond("get respond");
			}
		}
		
		
		// POST
		@Override
		public void handlePOST(CoapExchange exchange) 
		{
			String path = exchange.getRequestOptions().getUriPathString();
			if(path.equals("postPath"))
			{
				System.out.println("=== handle POST ===");
				System.out.println(exchange.getRequestOptions());
				
				byte[] payload = exchange.getRequestPayload();
				String payloadStr = new String(payload);

				System.out.println(payloadStr);
				
				
				exchange.respond("post respond");
			}

		}
		
		
		// PUT
		@Override
		public void handlePUT(CoapExchange exchange) 
		{
			String path = exchange.getRequestOptions().getUriPathString();
			if(path.equals("putPath"))
			{
				System.out.println("=== handle PUT ===");
				System.out.println(exchange.getRequestOptions());
				
				exchange.respond("put respond");
			}
		}
		
		
		// DELETE
		@Override
		public void handleDELETE(CoapExchange exchange) 
		{
			String path = exchange.getRequestOptions().getUriPathString();
			if(path.equals("deletePath"))
			{
				System.out.println("=== handle DELETE ===");
				System.out.println(exchange.getRequestOptions());
				
				exchange.respond("delete respond");
			}
		}
	}
	
}
