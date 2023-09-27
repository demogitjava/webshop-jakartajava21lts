package de.jgsoftware.webshop.config;


import com.zaxxer.hikari.HikariConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
//@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "de.jgsoftware.webshop.dao.interfaces.shopdb",
        entityManagerFactoryRef = "shopEntityManagerFactory",
        transactionManagerRef = "shopTransactionManager")
public class ShopDBConfig extends HikariConfig
{
    //@Autowired
    //@Qualifier(value = "shopJdbcTemplate")
    //JdbcTemplate jtm2;



    @Autowired
    DataSource dataSource2;



    public ShopDBConfig()
    {

    }


    @Bean("ds3")
    @Qualifier("shopdb")
    @ConfigurationProperties(prefix="app.datasource3")
    public DataSource secondDS()
    {
        return DataSourceBuilder.create().build();
    }



    @Bean(name = "shopEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean shopEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("shopdb") DataSource dataSource2) {
        HashMap<String, Object> properties = new HashMap<>();

        properties.put("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
        return builder.dataSource(dataSource2).properties(properties)
                .packages("de.jgsoftware.webshop.model.shopdb").persistenceUnit("derbyshopdb").build();
    }

    @Bean(name = "shopTransactionManager")
    public PlatformTransactionManager shopTransactionManager(
            @Qualifier("shopEntityManagerFactory") EntityManagerFactory shopEntityManagerFactory) {
        return new JpaTransactionManager(shopEntityManagerFactory);
    }

    @Bean(name = "shopJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("ds3") DataSource dataSource2)
    {

        return new JdbcTemplate(dataSource2);
    }

    public DataSource getDataSource2() {
        return dataSource2;
    }

    public void setDataSource2(DataSource dataSource2) {
        this.dataSource2 = dataSource2;
    }
    
    



}