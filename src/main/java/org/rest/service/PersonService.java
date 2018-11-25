package org.rest.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.rest.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class PersonService {

	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	

	public PersonService() {
		/*ApplicationContext ctx = new ClassPathXmlApplicationContext("hibernate.xml");
		sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");*/
	}
	
	
	public List<Person> getAllPersons(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<Person> query = session.createQuery("from Person", Person.class);
		List<Person> persons = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return persons;
	}
}
