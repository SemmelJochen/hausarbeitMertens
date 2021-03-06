package model;

import java.io.Serializable;
/**
 * 
 * @author josua
 *
 */
public class Person implements Serializable {

	private static final long serialVersionUID = 0xDADAL;
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

	@Override
	public String toString() {
		return "name: \t\t\t" + this.name + "\nfirstname \t\t" + this.firstName + "\nemail: \t\t\t" + this.email;
	}

	@Override
	public boolean equals(Object person) {
		if (person == null) {
			return false;
		}
		Person pToCheck = (Person) person;
		return this.getName().equals(pToCheck.getName()) && this.getFirstName().equals(pToCheck.getFirstName())
				&& this.getEmail().equals(pToCheck.getEmail());
	}

	public boolean isDummy() {
		return this.name.equals("") && this.firstName.equals("") && this.email.equals("");
	}
}
