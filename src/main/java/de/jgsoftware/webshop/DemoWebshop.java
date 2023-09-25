package de.jgsoftware.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 *
 * @author hoscho
 */


@SpringBootApplication
//@EnableJpaRepositories(basePackages = "de.jgsoftware.landingpage.dao.interfaces.*") 
@EnableJpaRepositories
public class DemoWebshop {

    public static void main(String[] args) {
     
       SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoWebshop.class);
		builder
				// none
				// reactive
				// servlet
				.web(WebApplicationType.SERVLET).headless(true).run();

    }
}
