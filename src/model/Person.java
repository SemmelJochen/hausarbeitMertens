package model;

/*
 * Felix was here 
 */
public abstract class Person {

	private String name, firstName, email;
	
	public Person(String name, String firstName, String email) {
		this.name = name;
		this.firstName = firstName;
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
