// AMQP Consumer 예제 

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
		

public class RabbitmqRec{

	static String RABBITMQ_HOST = "localhost";
	static int RABBITMQ_PORT = 5672;
	static String RABBITMQ_USERNAME = "smpg";
	static String RABBITMQ_PASSWORD = "smpg";

	static String EXCHANGE_NAME = "amq.direct";
	static String QUEUE_NAME = "queue_test";
	static String RAUTING_KEY = "tesKey";
	
	static int count = 1;
	
	static int total = 0;
	static String recTimeStr = "";
	
	static int oldTime = 0;
	
	
	public static void client1() throws IOException, TimeoutException
	{
		ConnectionFactory factory = new ConnectionFactory();
		
		factory.setHost(RABBITMQ_HOST);
		factory.setPort(RABBITMQ_PORT);
		factory.setUsername(RABBITMQ_USERNAME);
		factory.setPassword(RABBITMQ_PASSWORD);
	    
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		
//		/*
		for (int i = 0; i < count; i++) 
		{
			String message = "client1" + ":" + (i+1) + ":" + System.currentTimeMillis();
			
			byte[] body = message.getBytes();
			channel.basicPublish(EXCHANGE_NAME, RAUTING_KEY, null, body);
			System.out.println(" [pub] Sent '" + message + "'");
		}
		
		channel.close();
		connection.close();
	}
	
	
	public static Long getTime() throws IOException, TimeoutException
	{
		NTPUDPClient client = new NTPUDPClient();
//		InetAddress inetAddr = InetAddress.getByName("time.bora.net");
		InetAddress inetAddr = InetAddress.getByName("time.windows.com");
		TimeInfo timeInfo = client.getTime(inetAddr);
		long returnTime = timeInfo.getReturnTime();
		
		return returnTime;
	}
	
	public static void main(String[] args) throws IOException, TimeoutException
	{
		
//		client1();
		
		
//		System.out.println(getTime());
//		System.out.println(System.currentTimeMillis());
//		System.out.println(System.currentTimeMillis() - getTime());
		
		
//		/*
		ConnectionFactory factory = new ConnectionFactory();
		
		factory.setHost(RABBITMQ_HOST);
		factory.setPort(RABBITMQ_PORT);
		factory.setUsername(RABBITMQ_USERNAME);
		factory.setPassword(RABBITMQ_PASSWORD);
	    
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true);
	    
	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, RAUTING_KEY);
	    
	    
	    System.out.println(" [*] Waiting for messages... smpg");
	    
	    Consumer consumer = new DefaultConsumer(channel) {
	    	@Override
	    	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException 
	    	{
	    		String message = new String(body, "UTF-8");
	    		
	    		String path = "/home/smpg/test";
	    		File file = new File(path);
	    		
	    		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
	    		String recTime = sdf.format(new Date());
	    		
	    		if(!message.equals("getData"))
	    		{
	    			total++;
	    			recTimeStr += recTime+"\n";
	    		}
	    		
	    		
	    		if(message.equals("getData"))
	    		{
	    			
	    			System.out.println("getData : count.txt, recTime.txt");
	    			
	    			// 경로가 없다면 생성 (디렉토리)
	    			if(!file.exists())
	    			{
	    				try 
	    				{
	    					file.mkdirs();
	    				} 
	    				catch (Exception e) {
	    				}
	    			}
	    			
	    			FileWriter writer1 = null;
	    			FileWriter writer2 = null;
	    			try 
	    			{
	    				// 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
//	                writer = new FileWriter(file+"/"+msgArray[0] + ".txt", true);
	    				writer1 = new FileWriter(file+"/"+ "count" + ".txt", false);
	    				writer1.write("total " + total + "  max " + oldTime);
	    				writer1.flush();
	    				
	    				
	    				writer2 = new FileWriter(file+"/"+ "recTime" + ".txt", false);
	    				writer2.write(recTimeStr);
	    				writer2.flush();
	    			} 
	    			catch(IOException e) 
	    			{ 
	    				e.printStackTrace();
	    			}
	    			finally { try {if(writer1 != null) writer1.close();} catch(IOException e) {e.printStackTrace();} }
	    			
	    		}
	            
	            
	            
	    		
	    	}
	    };
	    
	    channel.basicConsume(QUEUE_NAME, true, consumer);
//		 */
		
		
	}
		
	
}