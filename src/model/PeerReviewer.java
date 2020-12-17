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
	private List<Student> requested;

	public PeerReviewer(String title, String name, String firstName, String email, int capacity) {
		super(name, firstName, email);
		this.title = title;
		this.capacity = capacity;
		this.firstReviewerRoles = new ArrayList<>();
		this.secondReviewerRoles = new ArrayList<>();
		this.requested = new ArrayList<>();
	}

	private PeerReviewer(PeerReviewer p) {
		this(p.getTitle(), p.getName(), p.getFirstName(), p.getEmail(), p.getCapacity());
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
	
	public void requestAsSecondPeerReviewer(Student s) {
		this.requested.add(s);
	}
	
	public void acceptRequest(Student s) {
		this.secondReviewerRoles.add(s);
		this.requested.remove(s);
	}
	
	public List<Student> getFirstPeerReviewerRoles() {
		return this.firstReviewerRoles;
	}

	public List<Student> getSecondPeerReviewerRoles() {
		return this.secondReviewerRoles;
	}

	public int getBachelorThesisesCount() {
		return this.firstReviewerRoles.size() + this.secondReviewerRoles.size();
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

	public PeerReviewer clone() {
		return new PeerReviewer(this);
	}
	
	@Override
	public boolean equals(Object pObject) {
		PeerReviewer peerReviewer = (PeerReviewer) pObject;
		return super.equals(peerReviewer) &&
				this.capacity == peerReviewer.getCapacity() &&
				this.title.equals(peerReviewer.getTitle()) &&
				this.firstReviewerRoles.equals(peerReviewer.getFirstPeerReviewerRoles()) &&
				this.secondReviewerRoles.equals(peerReviewer.getSecondPeerReviewerRoles());
	}
	
	public int getLoad() {
		return this.getBachelorThesisesCount() / this.capacity;
	}
	
	public boolean isDummy() {
		return super.isDummy() &&
				this.firstReviewerRoles.isEmpty() &&
				this.secondReviewerRoles.isEmpty() &&
				this.requested.isEmpty() &&
				this.capacity == -1 &&
				this.title.equals("");
				
	}
	
	public int getFreeReviews() {
		if(this.capacity == -1) {
			return 0;
		}
		return this.capacity - this.getBachelorThesisesCount();
	}
}
