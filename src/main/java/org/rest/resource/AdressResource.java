package org.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.rest.model.Adress;
import org.rest.service.AdressService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Path("/")
@Produces(value= {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Consumes(value= {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class AdressResource {

	AdressService adressService = (AdressService)new ClassPathXmlApplicationContext("services.xml").getBean("adressService");
	
	@GET
	public Adress getAdress(@PathParam("personId") long id){
		return adressService.getAdress(id);
	}
}
