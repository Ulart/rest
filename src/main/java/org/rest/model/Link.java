package org.rest.model;

import java.net.URI;

import javax.persistence.Column;

public class Link {

	@Column(name="URI")
	private URI uri;
	private String rel;
	public URI getUri() {
		return uri;
	}
	public void setUri(URI uri) {
		this.uri = uri;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public Link(URI uri, String rel) {
		super();
		this.uri = uri;
		this.rel = rel;
	}
	public Link() {
		super();
		// TODO Auto-generated constructor stub
	}
}
