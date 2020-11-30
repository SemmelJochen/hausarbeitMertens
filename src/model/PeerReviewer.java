package model;

import java.util.LinkedList;

public class PeerReviewer extends Person {
	
	private int capacity;
	private LinkedList<Student> firstBachelorThesises;
	private LinkedList<Student> secondBachelorThesises;
	private String title, remark;
	
	public PeerReviewer(String title, String name, String firstName, String email, int capacity){
		super(name, firstName, email);
		this.title = title;
		this.capacity = capacity;
		this.firstBachelorThesises = new LinkedList<>();
		this.secondBachelorThesises = new LinkedList<>();
		this.remark = remark;
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
	
	public void addSecondBachelorThesis(Student student) {
		this.secondBachelorThesises.add(student);
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
