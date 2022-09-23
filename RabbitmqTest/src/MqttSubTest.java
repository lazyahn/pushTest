import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttSubTest implements MqttCallback {
	
	String broker       = "tcp://smarthousing.tnmiot.co.kr:1883";
	String clientId     = "";
	String userId       = "smart";
	char[] password     = "smart1234".toCharArray();
	
	String topic        = "test";
	int qos             = 0;
	
	MqttClient client;
	

    public MqttSubTest() 
    {
    	try
    	{
	        MqttConnectOptions conOpts = new MqttConnectOptions();
	        conOpts.setCleanSession(true);
	        conOpts.setUserName(userId);
	        conOpts.setPassword(password);
	
	        client = new MqttClient(broker, clientId, new MemoryPersistence());
	        client.setCallback(this);
	        client.connect(conOpts);
	
	        client.subscribe(this.topic, qos);
    	}
        catch(MqttException me) 
	    {
	    	System.out.println();
	    	System.out.println();
	    	System.out.println();
	        System.out.println("reason "+me.getReasonCode());
	        System.out.println("msg "+me.getMessage());
	        System.out.println("loc "+me.getLocalizedMessage());
	        System.out.println("cause "+me.getCause());
	        System.out.println("excep "+me);
            
	        me.printStackTrace();
        }
        
    }

	

	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub
		System.out.println("Connection lost because: " + arg0);
        System.exit(1);
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(String.format("[%s] %s", topic, new String(message.getPayload())));
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		new MqttSubTest();
	}
}