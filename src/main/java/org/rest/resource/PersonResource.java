package org.rest.resource;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.rest.model.Person;
import org.rest.service.PersonService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Resource
@Path("persons")
@Consumes(value= {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces(value= {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class PersonResource {

	PersonService personService =(PersonService) new ClassPathXmlApplicationContext("services.xml").getBean("personService");	
	
	/*@Autowired
	private PersonService personService;
	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}*/

	
	
	@GET
	public List<Person> getAllPersons() {
		return personService.getAllPersons();
	}
	
	@GET
	@Path("/{personId}")
	public Person getPerson(@PathParam("personId") long personId) {
		return personService.getPerson(personId);
	}
	
	@POST
	public void addPerson(Person person) {
		personService.addPerson(person);
	}
	
	@DELETE
	@Path("/{personId}")
	public void deletePerson(@PathParam("personId") long personId) {
		personService.deletePerson(personId);
	}
}