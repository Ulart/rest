package org.rest.resource.beans;

import javax.ws.rs.QueryParam;

import org.rest.model.Gender;

public class PersonFilterBean {

	private @QueryParam("forename") String forename;
	private @QueryParam("surname") String surname;
	private @QueryParam("gender") Gender gender;
	public String getForename() {
		return forename;
	}
	public void setForename(String forename) {
		this.forename = forename;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
