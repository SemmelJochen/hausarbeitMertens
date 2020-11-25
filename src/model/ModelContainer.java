package model;

import java.util.ArrayList;

public class ModelContainer {

	// think about it -> maybe Hashset?
	private ArrayList<Person> persons;
	
	public ModelContainer() {
		this.persons = new ArrayList<Person>();
	}
	
	public ArrayList<Person> getPersons(){
		return this.persons;
	}
}
