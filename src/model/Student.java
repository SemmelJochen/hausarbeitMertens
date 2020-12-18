package model;

import java.io.Serializable;

/*
 * TODO: add remark
 */
public class Student extends Person implements Serializable {

	private static final long serialVersionUID = 0xBAFFL;
	private String studentGroup, practicePartner, subject;
	private String firstPeerReviewerKey;
	private Pair<String, Boolean> secondPeerReviewerKey;
	private String remark;

	public Student(String name, String firstName, String email, String studentGroup, String practicePartner,
			String subject, String firstPeerReviewerKey, String secondPeerReviewerKey, boolean state, String remark) {
		super(name, firstName, email);
		this.studentGroup = studentGroup;
		this.practicePartner = practicePartner;
		this.subject = subject;
		this.firstPeerReviewerKey = firstPeerReviewerKey;
		this.secondPeerReviewerKey = new Pair<>(secondPeerReviewerKey, state);
		this.remark = remark;
	}

	public Student(String name, String firstName, String email, String studentGroup, String practicePartner,
			String subject, String remark) {
		this(name, firstName, email, studentGroup, practicePartner, subject, "", "", false, remark);
	}

	private Student(Student s) {
		this(s.getName(), s.getFirstName(), s.getEmail(), s.getStudentGroup(), s.getPracticePartner(), s.getSubject(),
				s.getFirstPeerReviewerKey(), s.getSecondPeerReviewerKey(), s.getSecondPeerReviewerState(), s.getRemark());
	}

	public String getFirstPeerReviewerKey() {
		return this.firstPeerReviewerKey;
	}

	public void setFirstPeerReviewerKey(String firstPeerReviewerKey) {
		this.firstPeerReviewerKey = firstPeerReviewerKey;
	}

	public String getSecondPeerReviewerKey() {
		return this.secondPeerReviewerKey.getKey();
	}

	public void setSecondPeerReviewerKey(String secondPeerReviewerKey) {
		this.secondPeerReviewerKey = new Pair<String, Boolean>(secondPeerReviewerKey, false);
	}
	
	public boolean getSecondPeerReviewerState() {
		return this.secondPeerReviewerKey.getValue();
	}
	
	public String getStudentGroup() {
		return this.studentGroup;
	}

	public void setStudentGroup(String studentGroup) {
		this.studentGroup = studentGroup;
	}
	
	public void setSecondPeerReviewerState(Boolean state) {
		this.secondPeerReviewerKey.setValue(state);
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
				this.firstPeerReviewerKey.equals(studentToCheck.getFirstPeerReviewerKey()) &&
				this.secondPeerReviewerKey.equals(studentToCheck.getSecondPeerReviewerKey());
	}
	
	public static Student createDummy() {
		return new Student("", "", "", "", "", "", "");
	}
}
