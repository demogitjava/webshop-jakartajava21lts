package de.jgsoftware.webshop.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration 
{

	
	    

	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        UserDetails user = User.withDefaultPasswordEncoder()
	            .username("root	")
	            .password("jj78mvpr52k1")
	            .roles("USER")
	            .build();
	        auth.jdbcAuthentication()
	            .withDefaultSchema()
	            //.dataSource(dataSource())
	            .withUser(user);
	    }
}