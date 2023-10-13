package de.jgsoftware.webshop.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Component

@EnableJpaRepositories(basePackages = "de.jgsoftware.webshop.dao.interfaces.demodb",
entityManagerFactoryRef = "entityManagerFactory",
transactionManagerRef = "transactionManager")
public class Derbydb_Hiberante 
{

	
    
	private static SessionFactory sessionFactory;
	

	
	
	public Derbydb_Hiberante()
	{
       
	}
	
	

	
	
	private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	Configuration configuration = new Configuration();
                
                // /root/git/webshop-jakartajava21lts/src/main/resources/hibernate.cfg.xml
        	configuration.configure("hibernate.cfg.xml");
        	System.out.println("Hibernate Configuration loaded");
        	
        	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        	System.out.println("Hibernate serviceRegistry created");
        	
        	sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        	
            return sessionFactory;
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
        
  
    
    public Session openSession()
    {
        Session session = de.jgsoftware.webshop.config.Derbydb_Hiberante.getSessionFactory().openSession();
        session.beginTransaction();
        return session;
    }
}