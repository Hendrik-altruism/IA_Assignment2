package de.hbrs.ia.superhighperformance.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "salesmen")
public class SalesMen {
	@Id
	private int sid;
	private String firstName;
	private String lastName;

	SalesMen() { }

	public SalesMen(int sid, String firstName, String lastName) {
		this.sid = sid;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
}
