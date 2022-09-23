import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPubTest {

	public static void main(String[] args) {

		String serverUrl	= "tcp://smarthousing.tnmiot.co.kr:1883";   // your IP
		String clientId     = "";
		String userId       = "smart";
		char[] password     = "smart1234".toCharArray();
		String topic        = "smart";
	    int qos             = 0;
	    
	    try 
	    {
	        MqttConnectOptions connOpts = new MqttConnectOptions();
	        connOpts.setCleanSession(true);
	        connOpts.setUserName(userId);
	        connOpts.setPassword(password);
	        
	        String content = "pub test msg";
	        MqttClient client = new MqttClient(serverUrl, clientId, new MemoryPersistence());
	        
	        // connect
	        client.connect(connOpts);
	        
	        MqttMessage message = new MqttMessage(content.getBytes());
	        message.setQos(qos);
	        
	        // publish
	        client.publish(topic, message);

	        
	        System.out.println("Publishing message: "+content);
	        
	        // disconnect
	        client.disconnect();
	    } 
	    catch(MqttException me) 
	    {
	        me.printStackTrace();
        }
    }
}