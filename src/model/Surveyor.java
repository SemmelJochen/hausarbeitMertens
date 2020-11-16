package model;

import java.util.LinkedList;

public class Surveyor extends Person {
	
	private int capacity;
	private LinkedList<Student> surveys;
	
	public Surveyor(String name, String firstName, String email, int capacity){
		super(name, firstName, email);
		this.capacity = capacity;
		this.surveys = new LinkedList<>();
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public void addSurvey(Student student) {
		this.surveys.add(student);
	}
}
