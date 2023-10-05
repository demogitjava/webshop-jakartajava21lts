package de.jgsoftware.webshop.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
//import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.util.Properties;


@Component
public class Derbydb_Hiberante {

    SessionFactory sessionFactory = null;
    Session session;
    
    Transaction tx;

    public Session getSession() {
        this.session = createAndGetLocalSessionFactoryBean().getCurrentSession();
        return session != null ? this.session : createAndGetLocalSessionFactoryBean().openSession();
    }

    SessionFactory createAndGetLocalSessionFactoryBean() {
        if (this.sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = getBuiltProperties("spring.demodb.datasource");

                configuration.setProperties(settings);
                configuration.addPackage("de.jgsoftware.webshop.model.demodb");
              
                StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(settings);
                sessionFactory = configuration.buildSessionFactory(serviceRegistry.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    private Properties getBuiltProperties(String propertyFileName) {
        Properties properties = new Properties();
        InputStream input = Derbydb_Hiberante.class
                .getClassLoader().getResourceAsStream(propertyFileName);
        try {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    CriteriaBuilder getCriteriaBuilder() {
        Session session = getSession();
        tx = session.getTransaction();
        if (!tx.isActive()) {
            tx = session.beginTransaction();
        }
        return session.getCriteriaBuilder();
    }

    public <T> TypedQuery<T> query(CriteriaQuery<T> query) {
        session = getSession();
        tx = session.getTransaction();
        if (!tx.isActive()) {
            tx = session.beginTransaction();
        }
        var result = session.getEntityManagerFactory().createEntityManager().createQuery(query);
        tx.commit();
        return result;
    }
}
