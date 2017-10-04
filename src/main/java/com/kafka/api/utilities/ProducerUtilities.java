package com.kafka.api.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.api.models.Person;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerUtilities {
	
	private static ObjectMapper om = new ObjectMapper();
	

	public static org.apache.kafka.clients.producer.Producer<String, JsonNode> getProducer() {
		Properties configProperties = new Properties();
		configProperties.put(ProducerConfig.CLIENT_ID_CONFIG,
				"kafka json producer");
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"localhost:9092");
		configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.ByteArraySerializer");
		configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.connect.json.JsonSerializer");

		org.apache.kafka.clients.producer.Producer<String, JsonNode> producer = new KafkaProducer<String, JsonNode>(
				configProperties);
		return producer;
	}
	
	public ProducerRecord<String,JsonNode> createRecord(Person person){
		JsonNode jsonNode = om.valueToTree(person);
		ProducerRecord<String,JsonNode> record = new ProducerRecord<String,JsonNode>("test",jsonNode);
		return record;
	}

}
