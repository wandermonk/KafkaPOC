package com.kafka.api.utilities;

import java.util.Properties;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;

import com.kafka.api.models.Person;
import com.kafka.api.serdes.JsonDeserializer;
import com.kafka.api.serdes.JsonSerializer;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serde;

public class ConsumerUtilities {

	public static Properties getProperties() {

		Properties configs = new Properties();
		configs.put(StreamsConfig.APPLICATION_ID_CONFIG,
				"Kafka test application");
		configs.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		return configs;
	}

	public static KStreamBuilder getStreamingConsumer() {
		KStreamBuilder builder = new KStreamBuilder();
		return builder;
	}

	public static void printStreamData() {
		JsonSerializer<Person> personJsonSerializer = new JsonSerializer<>();
		JsonDeserializer<Person> personJsonDeserializer = new JsonDeserializer<>(
				Person.class);
		Serde<Person> personSerde = Serdes.serdeFrom(personJsonSerializer,
				personJsonDeserializer);
		KStreamBuilder builder = getStreamingConsumer();

		try {

			KStream<String, Person> kStream = builder.stream(Serdes.String(),
					personSerde, "test");
			kStream.print();
		} catch (Exception s) {
			s.printStackTrace();
		}
		KafkaStreams kafkaStreams = new KafkaStreams(builder, getProperties());
		kafkaStreams.start();
	}

}
