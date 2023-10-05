package de.jgsoftware.webshop;

import de.jgsoftware.webshop.config.Derbydb_Hiberante;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;



/**
 *
 * @author hoscho
 */


@SpringBootApplication
@EnableAutoConfiguration(
exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})

public class DemoWebshop {

    public static void main(String[] args) 
    {
     
    	/*
    	 *  derby db
    	 */
        Derbydb_Hiberante derbydb = new Derbydb_Hiberante();
        derbydb.openSession();
        
        SpringApplication.run(DemoWebshop.class, args);

    }
}
