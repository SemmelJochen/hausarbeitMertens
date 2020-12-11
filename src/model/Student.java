package model;

import java.io.Serializable;

/*
 * TODO: add remark
 */
public class Student extends Person implements Serializable {

	private static final long serialVersionUID = 0xBAFFL;
	private String studentGroup, practicePartner, subject;
	private PeerReviewer firstPeerReviewer;
	private PeerReviewer secondPeerReviewer;

	public Student(String name, String firstName, String email, String studentGroup, String practicePartner,
			String subject, PeerReviewer firstPeerReviewer, PeerReviewer secondPeerReviewer) {
		super(name, firstName, email);
		this.studentGroup = studentGroup;
		this.practicePartner = practicePartner;
		this.subject = subject;
		this.firstPeerReviewer = firstPeerReviewer;
		this.secondPeerReviewer = secondPeerReviewer;
	}
	public Student(String name, String firstName, String email, String studentGroup, String practicePartner,
			String subject) {
		super(name, firstName, email);
		this.studentGroup = studentGroup;
		this.practicePartner = practicePartner;
		this.subject = subject;
	}

	public PeerReviewer getFirstPeerReviewer() {
		return this.firstPeerReviewer;
	}

	public void setFirstPeerReviewer(PeerReviewer firstPeerReviewer) {
		this.firstPeerReviewer = firstPeerReviewer;
	}

	public PeerReviewer getSecondPeerReviewer() {
		return this.secondPeerReviewer;
	}

	public void setSecondPeerReviewer(PeerReviewer secondPeerReviewer) {
		this.secondPeerReviewer = secondPeerReviewer;
	}

	public String getStudentGroup() {
		return this.studentGroup;
	}

	public void setStudentGroup(String studentGroup) {
		this.studentGroup = studentGroup;
	}

	public String getPracticePartner() {
		return this.practicePartner;
	}

	public void setPracticePartner(String praxisPartner) {
		this.practicePartner = praxisPartner;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return super.toString() + "\nstudent group: \t\t" + this.studentGroup + "\npraxis partner: \t"
				+ this.practicePartner + "\nsubject: \t\t" + this.subject;
	}
}
