package de.jgsoftware.webshop.config;


import java.util.HashMap;
import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;



@Configuration
@EnableJpaRepositories(basePackages = "de.jgsoftware.webshop.dao.interfaces.demodb.*",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
public class DemoDBConfig extends HikariConfig
{

    //@Autowired
    //@Qualifier(value = "defaultJdbcTemplate")
    //org.springframework.jdbc.core.JdbcTemplate jtm;

    public DemoDBConfig()
    {
        //startH2Server();
    }

  

    @Primary
    @Bean(name = "dataSource")
    @Qualifier("demodb")
    @ConfigurationProperties(prefix = "spring.demodb.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }


    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("demodb") DataSource dataSource) {
        HashMap<String, Object> properties = new HashMap<>();

        properties.put("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
        return builder.dataSource(dataSource).properties(properties)
                .packages("de.jgsoftware.webshop.model.demodb").persistenceUnit("derbydemodb").build();

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
    	
    	/*
    	 *  run manuel query 
    	 *  JDBC Template - jtm
    	 */
      //  jtm = new org.springframework.jdbc.core.JdbcTemplate();
       // jtm.setDataSource(demodb);
      //  return jtm;
   // }
}