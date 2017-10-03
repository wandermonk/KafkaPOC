package com.kafka.api.kafkastreaming;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;

public class MainApp {
	
	public static void main(String[] args) {
		
		Properties configs = new Properties();
		configs.put(StreamsConfig.APPLICATION_ID_CONFIG, "Kafka test application");
		configs.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		configs.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		
		KStreamBuilder builder = new KStreamBuilder();
		KStream<String,String> kStream = builder.stream("test");
		kStream.foreach(new ForeachAction<String, String>() {
			@Override
			public void apply(String key, String value) {
				System.out.println(key + " : "+ value);				
			}
		});
		
		KafkaStreams kafkaStreams = new KafkaStreams(builder,configs);
		kafkaStreams.start();

	}

}
