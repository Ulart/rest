package org.rest.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.rest.model.Person;
import org.springframework.stereotype.Service;

@Service
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
	
	public Person getPerson(long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Person person = session.get(Person.class, id);
		session.getTransaction().commit();
		session.close();
		return person;
	}
	
	public void addPerson(Person newPerson) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(newPerson.getAdress());
		session.save(newPerson);
		session.getTransaction().commit();
		session.close();
		return;
	}
	
	public void deletePerson(long personId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Person person = getPerson(personId);
		session.delete(person.getAdress());
		session.delete(person);
		
		session.getTransaction().commit();
		session.close();
		
		return;
	}
	
}
