package org.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Person {

	@Id
	@GeneratedValue
	private long Id;
	private String forename;
	private String surname;
	private Gender gender;
	
	@Transient
	private List<Link> links= new ArrayList<Link>();
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	@ManyToOne
	private Adress adress;

	@JsonbTransient
	@XmlTransient
	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public Person(String forename, String surname, Gender gender, Adress adress) {
		super();
		this.forename = forename;
		this.surname = surname;
		this.gender = gender;
		this.adress = adress;
	}
	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

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

	public Person(String forename, String surname, Gender gender) {
		super();
		this.forename = forename;
		this.surname = surname;
		this.gender = gender;
	}
	
	public Person() {
	}

}
