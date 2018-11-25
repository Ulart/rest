package org.rest.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.rest.model.Person;
import org.rest.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Path("persons")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

	PersonService personService;	
	@GET
	
	public List<Person> getAllPersons() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("services.xml");
		personService = (PersonService) ctx.getBean("personService");
		return personService.getAllPersons();
	}
}
