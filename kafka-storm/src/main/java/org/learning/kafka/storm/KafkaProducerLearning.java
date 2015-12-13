package org.learning.kafka.storm;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Random;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducerLearning {

	public static void main(String[] args) throws UnknownHostException{
		Properties props = new Properties();
		props.put("metadata.broker.list", "flo-oms-app1.stage.ch.flipkart.com:9092");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("partitioner.class", "example.producer.SimplePartitioner");
		props.put("request.required.acks", "1");
		
		ProducerConfig config = new ProducerConfig(props);
		
		Producer<String,String> producer = new Producer<>(config);
		Random rnd = new Random(Inet4Address.getLocalHost().hashCode());
		for(int i=0; i<10; i++){
			long timestamp = System.currentTimeMillis();
			String ip = "102.13.12"+rnd.nextInt(255);
			String msg ="test";
			KeyedMessage<String, String> data = new KeyedMessage<String, String>("test", key, partKey, message)
		}
	}
}
