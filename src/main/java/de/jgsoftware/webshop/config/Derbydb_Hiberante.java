package de.jgsoftware.webshop.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
//import org.reflections.Reflections;
import org.springframework.stereotype.Component;


import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.Properties;


@Component
public class Derbydb_Hiberante {

    SessionFactory sessionFactory;
    Session session;
    
    Transaction tx;

    public Session getSession() {
        this.session = createAndGetLocalSessionFactoryBean().getCurrentSession();
        return createAndGetLocalSessionFactoryBean().openSession();
    }

    SessionFactory createAndGetLocalSessionFactoryBean() {
        if (this.sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
               // Properties settings = getBuiltProperties("spring.demodb.datasource");

                Properties settings = new Properties();
                settings.put("spring.demodb.datasource.jdbcUrl", "jdbc:derby://172.17.0.2:1527/root/derbydemodb");
                
                settings.put("spring.demodb.datasource.username", "root");
                settings.put("spring.demodb.datasource.password", "jj78mvpr52k1");
                settings.put("spring.demodb.datasource.driver-class-name", "org.apache.derby.jdbc.ClientDriver");
                
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
