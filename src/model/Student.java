package model;

import java.io.Serializable;

/*
 * TODO: add remark
 */
public class Student extends Person implements Serializable {

	public static final String ACCEPTED_STATE = "angenommen";
	public static final String REJECTED_STATE = "abgelehnt";
	public static final String REQUESTED_STATE = "ausstehend";
	private static final long serialVersionUID = 0xBAFFL;
	private String studentGroup, practicePartner, subject;
	private String firstPeerReviewerKey, remark;

	/*
	 * the String represents the key to find the PeerReviewer in the HashMap
	 * peerReviewers the Boolean represents the current state of the Requesting.
	 * True = accepted, false = waiting for response
	 */
	private Pair<String, String> secondPeerReviewerInformation = new Pair<String, String>();

	public Student(String name, String firstName, String email, String studentGroup, String practicePartner,
			String subject, String firstPeerReviewerKey, String secondPeerReviewerKey, String secondPeerReviewerState,
			String remark) {
		super(name, firstName, email);
		this.studentGroup = studentGroup;
		this.practicePartner = practicePartner;
		this.subject = subject;
		this.firstPeerReviewerKey = firstPeerReviewerKey;
		this.secondPeerReviewerInformation.setKey(secondPeerReviewerKey);
		this.secondPeerReviewerInformation.setValue(secondPeerReviewerState);
		this.remark = remark;

	}

	public Student(String name, String firstName, String email, String studentGroup, String practicePartner,
			String subject, String remark) {
		this(name, firstName, email, studentGroup, practicePartner, subject, "", "", REQUESTED_STATE, remark);
	}

	/**
	 * Constructor used for cloning the class
	 * 
	 * @param s Student to clone
	 */
	private Student(Student s) {
		this(s.getName(), s.getFirstName(), s.getEmail(), s.getStudentGroup(), s.getPracticePartner(), s.getSubject(),
				s.getFirstPeerReviewerKey(), s.getSecondPeerReviewerKey(), s.getSecondPeerReviewerState(),
				s.getRemark());
	}

	public String getFirstPeerReviewerKey() {
		return this.firstPeerReviewerKey;
	}

	public void setFirstPeerReviewerKey(String firstPeerReviewerKey) {
		this.firstPeerReviewerKey = firstPeerReviewerKey;
	}

	public String getSecondPeerReviewerKey() {
		if (this.secondPeerReviewerInformation != null) {
			return this.secondPeerReviewerInformation.getKey();
		} else {
			return null;
		}
	}

	public void setSecondPeerReviewerKey(String secondPeerReviewerKey) {
		if (this.secondPeerReviewerInformation.getKey() != null) {
		}
		this.secondPeerReviewerInformation.setKey(secondPeerReviewerKey);
	}

	public String getSecondPeerReviewerState() {
		if (this.secondPeerReviewerInformation != null) {
			return this.secondPeerReviewerInformation.getValue();
		} else {
			return null;
		}
	}

	public String getStudentGroup() {
		return this.studentGroup;
	}

	public void setStudentGroup(String studentGroup) {
		this.studentGroup = studentGroup;
	}

	public void setSecondPeerReviewerState(String state) {
		this.secondPeerReviewerInformation.setValue(state);
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
				+ this.practicePartner + "\nsubject: \t\t" + this.subject + "\nGutachter: \t\t"
				+ this.getFirstPeerReviewerKey();
	}

	public Student clone() {
		return new Student(this);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof Student) {
			Student studentToCheck = (Student) o;
			boolean tmp = super.equals(studentToCheck) && this.studentGroup.equals(studentToCheck.studentGroup)
					&& this.practicePartner.equals(studentToCheck.practicePartner)
					&& this.subject.equals(studentToCheck.subject)
					&& this.firstPeerReviewerKey.equals(studentToCheck.firstPeerReviewerKey)
					&& this.secondPeerReviewerInformation.getKey()
							.equals(studentToCheck.secondPeerReviewerInformation.getKey())
					&& this.secondPeerReviewerInformation.getValue()
							.equals(studentToCheck.secondPeerReviewerInformation.getValue());
			return tmp;
		} else {
			return false;
		}

	}

	public static Student createDummy() {
		return new Student("", "", "", "", "", "", "");
	}
}
