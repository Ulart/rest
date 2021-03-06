package org.rest.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.rest.model.Adress;
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
		/*
		 * ApplicationContext ctx = new ClassPathXmlApplicationContext("hibernate.xml");
		 * sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		 */
	}

	public List<Person> getAllPersonsByForename(String forename) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<Person> query = session.createQuery("from Person where forename= :forename", Person.class);
		query.setParameter("forename", forename);
		List<Person> persons = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return persons;
	}

	public List<Person> getAllPersonsBySurname(String surname) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<Person> query = session.createQuery("from Person where surname= :surname", Person.class);
		query.setParameter("surname", surname);
		List<Person> persons = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return persons;
	}

	public List<Person> getAllPersonsByForenameAndSurname(String forename, String surname) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<Person> query = session.createQuery("from Person where forename= :forename and surname= :surname",
				Person.class);
		query.setParameter("forename", forename);
		query.setParameter("surname", surname);
		List<Person> persons = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return persons;
	}

	public List<Person> getAllPersons() {
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

	public Person addPerson(Person newPerson) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Adress adress = newPerson.getAdress();
		if (adress != null)
			session.save(adress);
		session.save(newPerson);
		session.getTransaction().commit();
		session.close();
		return newPerson;
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

	public void updatePerson(Person person) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.update(person.getAdress());
		session.update(person);

		session.getTransaction().commit();
		session.close();
		return;
	}

}
