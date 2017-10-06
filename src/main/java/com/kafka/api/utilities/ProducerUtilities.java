package com.kafka.api.utilities;

import com.kafka.api.models.Person;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerUtilities {

	public static org.apache.kafka.clients.producer.Producer<String, Person> getProducer() {
		Properties configProperties = new Properties();
		configProperties.put(ProducerConfig.CLIENT_ID_CONFIG,
				"kafka json producer");
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"localhost:9092");
		configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.ByteArraySerializer");
		configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"com.kafka.api.serdes.JsonSerializer");

		org.apache.kafka.clients.producer.Producer<String, Person> producer = new KafkaProducer<String, Person>(
				configProperties);
		return producer;
	}

	public ProducerRecord<String, Person> createRecord(Person person) {
		ProducerRecord<String, Person> record = new ProducerRecord<String, Person>(
				"test", person);
		return record;
	}

}
