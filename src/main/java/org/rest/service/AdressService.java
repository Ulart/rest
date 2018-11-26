package org.rest.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.rest.model.Adress;
import org.springframework.stereotype.Service;

@Service
public class AdressService {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	public List<Adress> getAllAdresses() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<Adress> query = session.createQuery("from Adress", Adress.class);
		List<Adress> adresses = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return adresses;
	}
	
	public Adress getAdress(long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Adress adress = session.get(Adress.class, id);
		session.getTransaction().commit();
		session.close();
		return adress;
	}
	
}
