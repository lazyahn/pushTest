import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.config.SslConfigs;

import java.util.Collections;
import java.util.Properties;


public class KafkaCon {
	
//	private static final String KAFKA_SINGLE_IP = "ServerIp:KafkaPort";
//	private static final String TOPIC_NAME = "tnmtech1";
	private static final String KAFKA_SINGLE_IP = "smarthousing.tnmiot.co.kr:9092";
    private static final String TOPIC_NAME = "jmeter_kafka_test";

    public static void main(String[] args) {
        Properties configs = new Properties();

        // kafka server host 및 port 설정
        configs.put("bootstrap.servers", KAFKA_SINGLE_IP);
        configs.put("group.id", "karim-group-id-1"); // group-id 설정
        configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // key deserializer
        configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // value deserializer

        /*
		configs.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");
		configs.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "/Users/mac/AHN/TNMTECH" + "/kafka.client.truststore.jks");
		configs.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "iwaz123");
		configs.put(SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, "pkcs12");
		
		// TLS 인증서 추가
		configs.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, "/Users/mac/AHN/TNMTECH" + "/kafka.client.keystore.jks");
		configs.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, "iwaz123");
		configs.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, "iwaz123");
		configs.put(SslConfigs.SSL_KEYSTORE_TYPE_CONFIG, "pkcs12");
		*/
        
        
        System.out.println(configs);
        
        // consumer 생성
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configs);
        
        // topic 설정
        consumer.subscribe(Collections.singletonList(TOPIC_NAME));

        try {
            while (true) {
                // 계속 loop를 돌면서 producer의 message를 띄운다.
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records)
                {
                	String message = record.value();
                	System.out.println( message );
                }
            }
        } catch (Exception e) {
        } finally {
            consumer.close();
        }
    }
    
}
