package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PeerReviewer extends Person implements Serializable, PropertyChangeListener {

	private static final long serialVersionUID = 0xAFFEL;

	private int capacity;
	private List<Student> firstReviewerRoles;
	private List<Student> secondReviewerRoles;
	private String title;
	private String subjects;

	/*
	 * The List requested is used to save Students, whose state is
	 * "waiting for response" in secondPeerReviewerInformation if a student gets
	 * accepted by a PeerReviewer the entry will be deleted in the list and then
	 * appended in secondReviewerRoles.
	 */
	private List<Student> requested;

	public PeerReviewer(String title, String name, String firstName, String email, int capacity, String subjects) {
		super(name, firstName, email);
		this.title = title;
		this.capacity = capacity;
		this.firstReviewerRoles = new ArrayList<>();
		this.secondReviewerRoles = new ArrayList<>();
		this.requested = new ArrayList<>();
		this.subjects = subjects;
	}

	private PeerReviewer(PeerReviewer p) {
		this(p.getTitle(), p.getName(), p.getFirstName(), p.getEmail(), p.getCapacity(), p.getSubjects());
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

	public void removeBachelorThesisAsFirstReviewer(Student student) {
		this.firstReviewerRoles.remove(student);
	}

	public void removeBachelorThesisAsSecondReviewer(Student student) {
		this.secondReviewerRoles.remove(student);
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

	public List<Student> getRequested() {
		return requested;
	}

	public void addRequest(Student request) {
		this.requested.add(request);
	}

	public String getSubjects() {
		return subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return super.toString() + "\ntitle: \t\t\t" + this.title + "\ncapacity: \t\t" + this.capacity;
	}

	public PeerReviewer clone() {
		return new PeerReviewer(this);
	}

	@Override
	public boolean equals(Object pObject) {
		PeerReviewer peerReviewer = (PeerReviewer) pObject;
		return super.equals(peerReviewer) && this.getCapacity() == peerReviewer.getCapacity()
				&& this.getTitle().equals(peerReviewer.getTitle())
				&& this.getFirstPeerReviewerRoles().equals(peerReviewer.getFirstPeerReviewerRoles())
				&& this.getSecondPeerReviewerRoles().equals(peerReviewer.getSecondPeerReviewerRoles());
	}

	public static PeerReviewer createDummy() {
		return new PeerReviewer("", "", "", "", -1, "");
	}

	public int getLoad() {
		if (this.capacity < 0) {
			return 100;
		}
		return (int) (100d * this.getBachelorThesisesCount() / this.capacity);
	}

	public boolean isDummy() {
		return super.isDummy() && this.firstReviewerRoles.isEmpty() && this.secondReviewerRoles.isEmpty()
				&& this.requested.isEmpty() && this.capacity == -1 && this.title.equals("");

	}

	public int getFreeReviews() {
		if (this.capacity < 0) {
			return 0;
		}
		return this.capacity - this.getBachelorThesisesCount();
	}

	public void removeRequest(Student student) {
		this.requested.remove(student);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("firstReviewer")) {
			this.removeBachelorThesisAsFirstReviewer((Student) evt.getOldValue());
			this.addBachelorThesisAsFirstReviewer((Student) evt.getNewValue());
		}
	}

	public void declineRequest(Student student) {
		this.requested.remove(student);
	}
}
