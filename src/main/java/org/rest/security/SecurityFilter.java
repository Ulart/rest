package org.rest.security;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.rest.model.Account;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private SessionFactory sessionFactory = (SessionFactory) new ClassPathXmlApplicationContext("hibernate.xml")
			.getBean("sessionFactory");

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void filter(ContainerRequestContext requestContext) throws IOException {
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER);
		if (authHeader != null && authHeader.size() > 0) {
			String authToken = authHeader.get(0);
			authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
			String decodedString = Base64.decodeAsString(authToken);
			StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
			String username = tokenizer.nextToken();
			String password = tokenizer.nextToken();
			if (!username.equals("")) {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				Account account = session.get(Account.class, username);
				session.getTransaction().commit();
				session.close();
				if (account != null) {
					String aPassword = account.getPassword();
					if (aPassword != null && aPassword.equals(password)) {
						return;
					}
				}
			}
		}

		Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
				.entity("User cannot access the resource.").build();
		requestContext.abortWith(unauthorizedStatus);

	}

}
