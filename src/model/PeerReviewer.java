package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class PeerReviewer extends Person implements Serializable {
	
	private static final long serialVersionUID = 0xAFFEL;
	private int capacity;
	private List<Student> firstBachelorThesises;
	private List<Student> secondBachelorThesises;
	private String title;
	
	public PeerReviewer(String title, String name, String firstName, String email, int capacity){
		super(name, firstName, email);
		this.title = title;
		this.capacity = capacity;
		this.firstBachelorThesises = new ArrayList<>();
		this.secondBachelorThesises = new ArrayList<>();
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public void addFirstBachelorThesis(Student student) {
		this.firstBachelorThesises.add(student);
	}
	
	public void addFirstBachelorThesis(List<Student> students) {
		for(Student s: students) {
			this.firstBachelorThesises.add(s);
		}
	}

	public void addSecondBachelorThesis(List<Student> students) {
		for(Student s: students) {
			this.secondBachelorThesises.add(s);
		}
	}
	
	public void addSecondBachelorThesis(Student student) {
		this.secondBachelorThesises.add(student);
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public List<Student> getFirstBachelorThesis(){
		return this.firstBachelorThesises;
	}
	
	public List<Student> getSecondBachelorThesis(){
		return this.secondBachelorThesises;
	}
	
	public int getCountFirstBachelorThesises() {
		return this.firstBachelorThesises.size();
	}
	
	@Override
	public String toString() {
		return super.toString() + "\ntitle: \t\t\t" + this.title + "\ncapacity: \t\t" + this.capacity;
		
	}
	
	public void printStudents() {
		System.out.println("firstBachelorThesises:\n");
		for(Student student: this.firstBachelorThesises) {
			System.out.println(student);
			System.out.println("");
		}
		this.printSecondStudents();
		System.out.println("-----------------------------------");
	}
	
	private void printSecondStudents() {
		System.out.println("secondBachelorThesises:\n");
		for(Student student: this.secondBachelorThesises) {
			System.out.println(student);
			System.out.println("");
		}
	}
}
