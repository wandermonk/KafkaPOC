package com.kafka.api.models;

public class Person {

	private String name;
	private String personalID;
	private String country;
	private String occupation;
	
	public Person(){
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPersonalID() {
		return personalID;
	}
	public void setPersonalID(String personalID) {
		this.personalID = personalID;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	@Override
	public String toString(){
		return "{" + " "+"Name :" + " " + name
				   + " "+"ID :" + " " + personalID
				   + " "+"Country :" + " " + country
				   + " "+"Occupation :" + " " + occupation
				   +
				"}";
	}
}
