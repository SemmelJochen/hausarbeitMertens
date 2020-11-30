package model;

public class Student extends Person {

	private String studentGroup, praxisPartner, subject;
	
	public Student(String name, String firstName, String email, String studentGroup, String praxisPartner, String subject) {
		super(name, firstName, email);
		this.studentGroup = studentGroup;
		this.praxisPartner = praxisPartner;
		this.subject= subject;
	}

	public String getStudentGroup() {
		return this.studentGroup;
	}

	public void setStudentGroup(String studentGroup) {
		this.studentGroup = studentGroup;
	}

	public String getPraxisPartner() {
		return this.praxisPartner;
	}

	public void setPraxisPartner(String praxisPartner) {
		this.praxisPartner = praxisPartner;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nstudent group: \t\t" + this.studentGroup + "\npraxis partner: \t" + this.praxisPartner + "\nsubject: \t\t" + this.subject;
	}
}
