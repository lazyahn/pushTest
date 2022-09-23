import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.SslConfigs;



public class KafkaProd {
	
//    private static final String KAFKA_SINGLE_IP = "dev.iwaz.co.kr:9097";
//    private static final String TOPIC_NAME = "molit";
//    private static final String KAFKA_SINGLE_IP = "172.30.1.10:9092";
    private static final String KAFKA_SINGLE_IP = "smarthousing.tnmiot.co.kr:9092";
//    private static final String KAFKA_SINGLE_IP = "121.138.67.115:9092";
    private static final String TOPIC_NAME = "tnmtech";

    public static void main(String[] args) {

    	try 
    	{
    		String sendMessage = new Date() + "";
    		
    		Properties configs = new Properties();
    		configs.put("bootstrap.servers", KAFKA_SINGLE_IP); 	// kafka host 및 server 설정
    		configs.put("acks", "1"); 							// 자신이 보낸 메시지에 대해 카프카로부터 확인을 기다리지 않는다.
    		configs.put("block.on.buffer.full", "true"); 		// 서버로 보낼 레코드를 버퍼링 할 때 사용할 수 있는 전체 메모리의 바이트수
    		configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");   // serialize 설정
    		configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // serialize 설정
    		
    		
//    		configs.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");
//    		configs.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "/Users/mac/AHN/TNMTECH" + "/kafka.client.truststore.jks");
//    		configs.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "iwaz123");
//    		configs.put(SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, "pkcs12");
//    		
//    		// TLS 인증서 추가
//    		configs.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, "/Users/mac/AHN/TNMTECH" + "/kafka.client.keystore.jks");
//    		configs.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, "iwaz123");
//    		configs.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, "iwaz123");
//    		configs.put(SslConfigs.SSL_KEYSTORE_TYPE_CONFIG, "pkcs12");
    		
    		
    		System.out.println(configs);
    		
    		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(configs);
    		
    		System.out.println(sendMessage);
    		
    		// kafka로 메세지 개시
    		producer.send(new ProducerRecord<String, String>(TOPIC_NAME, sendMessage));
    		
    		
    		System.out.println(producer);
    		
    		producer.flush();
    		
    		System.out.println(producer);
    		
    		// producer 닫기
    		producer.close();
			
		} catch (Exception e) {
			
			
			// TODO: handle exception
			System.out.println(e);
		}
    }
}
