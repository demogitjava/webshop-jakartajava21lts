package de.jgsoftware.webshop.config;



import java.util.HashMap;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Persistence;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
//@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "de.jgsoftware.webshop.dao.interfaces.demodb",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
@ConfigurationProperties(prefix = "spring.demodb.datasource")

public class DemoDBConfig extends HikariConfig
{

    //@Autowired
    //@Qualifier(value = "defaultJdbcTemplate")
    //JdbcTemplate jtm;
	

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager em;

    public DemoDBConfig()
    {
        //startH2Server();
    	
    	  if (entityManagerFactory == null) {
    		  entityManagerFactory = Persistence.createEntityManagerFactory("derbydemodb");
          }

          if (em == null || !em.isOpen()) {
              em = entityManagerFactory.createEntityManager();
          }

         
    }

 

    @Primary
    @Bean(name = "dataSource")
    @Qualifier("demodb")
    @ConfigurationProperties(prefix = "spring.demodb.datasource")
    public DataSource dataSource() 
    {
        return DataSourceBuilder.create().build();
    }


    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory
    (
    		EntityManagerFactoryBuilder builder,
    		@Qualifier("demodb") DataSource dataSource
    ) 
    {
        HashMap<String, Object> properties = new HashMap<>();

        properties.put("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
        
        return builder.dataSource(dataSource)
        		.properties(properties)
                .packages("de.jgsoftware.webshop.demodb").persistenceUnit("derbydemodb").build();

    }


    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    //@Primary
    //@Bean(name = "defaultJdbcTemplate")
    //public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource demodb)
    //{
      //  jtm = new JdbcTemplate();
      //  jtm.setDataSource(demodb);
      //  return jtm;
    //}
}