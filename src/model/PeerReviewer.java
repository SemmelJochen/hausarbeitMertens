package model;

import java.util.LinkedList;

public class PeerReviewer extends Person {
	
	private int capacity;
	private LinkedList<Student> bachelorThesises;
	private String title;
	
	public PeerReviewer(String title, String name, String firstName, String email, int capacity){
		super(name, firstName, email);
		this.title = title;
		this.capacity = capacity;
		this.bachelorThesises = new LinkedList<>();
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public void addSurvey(Student student) {
		this.bachelorThesises.add(student);
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
}
