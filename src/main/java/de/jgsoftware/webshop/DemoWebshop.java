package de.jgsoftware.webshop;

import de.jgsoftware.webshop.config.Derbydb_Hiberante;
import de.jgsoftware.webshop.config.Mawi_Hiberante;
import de.jgsoftware.webshop.config.Shop_Hiberante;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import java.sql.Timestamp;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;


/**
 *
 * @author hoscho
 */


@SpringBootApplication
@EnableAutoConfiguration(
exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class DemoWebshop 
{
	 public static String st_timezones = "Europe/Berlin";
	 public static String operationsytem = null;
	

    public static void main(String[] args) 
    {

    	/*
    	 *  demo db
    	 */
        Derbydb_Hiberante derbydb = new Derbydb_Hiberante();
        derbydb.openSession();
        
        /*
    	 *  mawi db
    	 */     
        Mawi_Hiberante mawidb = new Mawi_Hiberante();
        mawidb.openSession();
        
        Shop_Hiberante shopdb = new Shop_Hiberante();
        shopdb.openSession();
        
        
        //SpringApplication.run(DemoWebshop.class, args);
        
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(st_timezones));
        //ZonedDateTime now = ZonedDateTime.now();

        // set up to GMT
        now.withZoneSameInstant( ZoneId.of("GMT") )
                .format( DateTimeFormatter.ISO_OFFSET_DATE_TIME );
        // 1. ZonedDateTime to TimeStamp
        Timestamp timestamp = Timestamp.valueOf(now.toLocalDateTime());

        // 2. ZonedDateTime to TimeStamp , no different
        Timestamp timestamp2 = Timestamp.from(now.toInstant());

        System.out.println(now);
        System.out.println(timestamp);
        System.out.println(timestamp2);


        String operationsytem = System.getProperty("os.name").toLowerCase();
        if (operationsytem.contains("win")){
            //Betriebssystem = Windows
            operationsytem = "Windows";

        }
        else if (operationsytem.contains("osx")){
            //Betriebssystem = OSX von Apple
            operationsytem = "OSX Apple";
        }
        else if (operationsytem.contains("nix") || operationsytem.contains("aix") || operationsytem.contains("nux")){
            //Betriebssystem = Unix bzw. Linux basiert
            operationsytem = "unix-linux";
        }
        System.out.print(operationsytem + "\n");

        SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoWebshop.class);
        builder.web(WebApplicationType.SERVLET).headless(true).run();

    }
}
