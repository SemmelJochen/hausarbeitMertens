package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PeerReviewer extends Person implements Serializable {

	private static final long serialVersionUID = 0xAFFEL;
	private int capacity;
	private List<Student> firstReviewerRoles;
	private List<Student> secondReviewerRoles;
	private String title;

	public PeerReviewer(String title, String name, String firstName, String email, Integer capacity) {
		super(name, firstName, email);
		this.title = title;
		this.capacity = (capacity != null) ? capacity : 10; // default capacity
		this.firstReviewerRoles = new ArrayList<>();
		this.secondReviewerRoles = new ArrayList<>();
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void addBachelorThesisAsFirstReviewer(Student student) {
		this.firstReviewerRoles.add(student);
	}

	public void addBachelorThesesAsFirstReviewer(List<Student> students) {
		for (Student s : students) {
			this.firstReviewerRoles.add(s);
		}
	}

	public void addBachelorThesisAsSecondReviewer(Student student) {
		this.secondReviewerRoles.add(student);
	}

	public void addBachelorThesesAsSecondReviewer(List<Student> students) {
		for (Student s : students) {
			this.secondReviewerRoles.add(s);
		}
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public List<Student> getFirstBachelorThesis() {
		return this.firstReviewerRoles;
	}

	public List<Student> getSecondBachelorThesis() {
		return this.secondReviewerRoles;
	}

	public int getCountFirstBachelorThesises() {
		return this.firstReviewerRoles.size();
	}

	@Override
	public String toString() {
		return super.toString() + "\ntitle: \t\t\t" + this.title + "\ncapacity: \t\t" + this.capacity;

	}

	public void printStudents() {
		System.out.println("firstBachelorThesises:\n");
		for (Student student : this.firstReviewerRoles) {
			System.out.println(student);
			System.out.println("");
		}
		this.printSecondStudents();
		System.out.println("-----------------------------------");
	}

	private void printSecondStudents() {
		System.out.println("secondBachelorThesises:\n");
		for (Student student : this.secondReviewerRoles) {
			System.out.println(student);
			System.out.println("");
		}
	}
}
