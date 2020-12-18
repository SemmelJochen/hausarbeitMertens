package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/*
 * TODO: add remark
 */
public class Student extends Person implements Serializable {

	private static final long serialVersionUID = 0xBAFFL;
	private String studentGroup, practicePartner, subject;
	private String firstPeerReviewerKey;
	private Pair<String, Boolean> secondPeerReviewerInformation;
	private String remark;
	
//	private PropertyChangeSupport propertyChangeSupport;

	public Student(String name, String firstName, String email, String studentGroup, String practicePartner,
			String subject, String firstPeerReviewerKey, String secondPeerReviewerKey, boolean state, String remark) {
		super(name, firstName, email);
		this.studentGroup = studentGroup;
		this.practicePartner = practicePartner;
		this.subject = subject;
		this.firstPeerReviewerKey = firstPeerReviewerKey;
		this.secondPeerReviewerInformation = new Pair<>(secondPeerReviewerKey, state);
		this.remark = remark;
		
//		this.propertyChangeSupport = new PropertyChangeSupport(this);
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
		
//		PeerReviewer tmp = ModelContainer.getInstance().getPeerReviewer(firstPeerReviewerKey);
//		if(tmp != null)
//		System.out.println("## " + tmp.getFirstName() + " ##");
//		
//		Student oldStudent = this.clone();
//		
//		this.propertyChangeSupport.addPropertyChangeListener(ModelContainer.getInstance().getPeerReviewer(firstPeerReviewerKey));
		this.firstPeerReviewerKey = firstPeerReviewerKey;
//		this.propertyChangeSupport.firePropertyChange("firstReviewer", oldStudent, this);
//		
//		System.out.println("new Value:" + this.firstPeerReviewerKey);
	}

	public String getSecondPeerReviewerKey() {
		return this.secondPeerReviewerInformation.getKey();
	}

	public void setSecondPeerReviewerKey(String secondPeerReviewerKey) {
		if(this.secondPeerReviewerInformation.getKey() != null) {
//			this.propertyChangeSupport.firePropertyChange("secondReviewer", this, null);
//			this.propertyChangeSupport.removePropertyChangeListener(ModelContainer.getInstance().getPeerReviewer(this.secondPeerReviewerKey.getKey()));
		}
//		this.propertyChangeSupport.addPropertyChangeListener(ModelContainer.getInstance().getPeerReviewer(secondPeerReviewerKey));
		this.secondPeerReviewerInformation.setKey(secondPeerReviewerKey);
//		this.propertyChangeSupport.firePropertyChange("secondReviewer", null, this);
	}
	
	public boolean getSecondPeerReviewerState() {
		return this.secondPeerReviewerInformation.getValue();
	}
	
	public String getStudentGroup() {
		return this.studentGroup;
	}

	public void setStudentGroup(String studentGroup) {
		this.studentGroup = studentGroup;
	}
	
	public void setSecondPeerReviewerState(boolean state) {
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
				+ this.practicePartner + "\nsubject: \t\t" + this.subject + "\nGutachter: \t\t" + this.getFirstPeerReviewerKey();
	}

	public Student clone() {
		return new Student(this);
	}   
	
	@Override
	public boolean equals(Object student) {
		Student studentToCheck = (Student) student;
		boolean tmp = super.equals(studentToCheck) &&
				this.getStudentGroup().equals(studentToCheck.getStudentGroup()) &&
				this.getPracticePartner().equals(studentToCheck.getPracticePartner()) &&
				this.getSubject().equals(studentToCheck.getSubject()) &&
				this.firstPeerReviewerKey.equals(studentToCheck.getFirstPeerReviewerKey()) &&
				this.getSecondPeerReviewerKey().equals(studentToCheck.getSecondPeerReviewerKey()) &&
				this.getSecondPeerReviewerState() == studentToCheck.getSecondPeerReviewerState();
		System.out.println( tmp);
		return tmp;
	}
	
	public static Student createDummy() {
		return new Student("", "", "", "", "", "", "");
	}
}
