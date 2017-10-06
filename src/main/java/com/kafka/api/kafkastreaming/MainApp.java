package com.kafka.api.kafkastreaming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;

import com.kafka.api.models.Person;
import com.kafka.api.utilities.ConsumerUtilities;
import com.kafka.api.utilities.ProducerUtilities;

public class MainApp {

	private static ProducerUtilities producerutility = new ProducerUtilities();

	public static void main(String[] args) throws IOException {

		final Thread producer = new Thread(new Runnable() {

			org.apache.kafka.clients.producer.Producer<String, Person> producer = ProducerUtilities
					.getProducer();

			@Override
			public void run() {
				List<Person> listofpersons = new ArrayList<Person>();
				ClassLoader classloader = new MainApp().getClass()
						.getClassLoader();
				File file = new File(classloader.getResource("MOCK_DATA.csv")
						.getFile());
				try {
					@SuppressWarnings("resource")
					BufferedReader br = new BufferedReader(new FileReader(file));
					String line = " ";
					while ((line = br.readLine()) != null) {
						String[] persons = line.split(",");
						Person person = new Person();
						person.setName(persons[0]);
						person.setPersonalID(persons[1]);
						person.setCountry(persons[2]);
						person.setOccupation(persons[3]);
						listofpersons.add(person);
					}

					for (Person person : listofpersons) {
						System.out
								.println("pushing record to the kafka producer");
						ProducerRecord<String, Person> record = producerutility
								.createRecord(person);
						producer.send(record);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		producer.start();

		Thread consumer = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ConsumerUtilities.printStreamData();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		consumer.start();

	}

}
