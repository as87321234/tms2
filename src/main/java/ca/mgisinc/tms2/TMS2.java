package ca.mgisinc.tms2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories

public class TMS2 {
	
	final static Logger log = LoggerFactory.getLogger(TMS2.class);
	
	public static void main(String[] args) {
		
		SpringApplication.run(TMS2.class, args);
		
		log.info("Application started");
		
	}
}
