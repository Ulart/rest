package org.rest.resource.beans;

import javax.ws.rs.QueryParam;

public class PersonFilterBean {

	private @QueryParam("forename") String forename;
	private @QueryParam("surname") String surname;

	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getForename() {
		return forename;
	}
	public void setForename(String forename) {
		this.forename = forename;
	}

}
