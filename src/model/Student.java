package model;

import java.io.Serializable;

/*
 * TODO: add remark
 */
public class Student extends Person implements Serializable {

	private static final long serialVersionUID = 0xBAFFL;
	private String studentGroup, practicePartner, subject;
	private String firstPeerReviewerKey;
	private String secondPeerReviewerKey;
	private String remark;

	public Student(String name, String firstName, String email, String studentGroup, String practicePartner,
			String subject, String firstPeerReviewerKey, String secondPeerReviewerKey, String remark) {
		super(name, firstName, email);
		this.studentGroup = studentGroup;
		this.practicePartner = practicePartner;
		this.subject = subject;
		this.firstPeerReviewerKey = firstPeerReviewerKey;
		this.secondPeerReviewerKey = secondPeerReviewerKey;
		this.remark = remark;
	}

	public Student(String name, String firstName, String email, String studentGroup, String practicePartner,
			String subject, String remark) {
		this(name, firstName, email, studentGroup, practicePartner, subject, null, null, remark);
	}

	private Student(Student s) {
		this(s.getName(), s.getFirstName(), s.getEmail(), s.getStudentGroup(), s.getPracticePartner(), s.getSubject(),
				s.getFirstPeerReviewer(), s.getSecondPeerReviewer(), s.getRemark());
	}

	public String getFirstPeerReviewer() {
		return this.firstPeerReviewerKey;
	}

	public void setFirstPeerReviewer(String firstPeerReviewerKey) {
		this.firstPeerReviewerKey = firstPeerReviewerKey;
	}

	public String getSecondPeerReviewer() {
		return this.secondPeerReviewerKey;
	}

	public void setSecondPeerReviewer(String secondPeerReviewerKey) {
		this.secondPeerReviewerKey = secondPeerReviewerKey;
	}

	public String getStudentGroup() {
		return this.studentGroup;
	}

	public void setStudentGroup(String studentGroup) {
		this.studentGroup = studentGroup;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Student clone() {
		return new Student(this);
	}   
	
	@Override
	public boolean equals(Object student) {
		Student studentToCheck = (Student) student;
		return super.equals(studentToCheck) &&
				this.getStudentGroup().equals(studentToCheck.getStudentGroup()) &&
				this.getPracticePartner().equals(studentToCheck.getPracticePartner()) &&
				this.getSubject().equals(studentToCheck.getSubject()) &&
				this.firstPeerReviewerKey.equals(studentToCheck.getFirstPeerReviewer()) &&
				this.secondPeerReviewerKey.equals(studentToCheck.getSecondPeerReviewer());
	}
	
	public static Student createDummy() {
		return new Student("", "", "", "", "", "", "");
	}
}
