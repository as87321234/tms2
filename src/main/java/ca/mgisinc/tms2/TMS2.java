package ca.mgisinc.tms2;

import ca.mgisinc.tms2.config.config.AppEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories

public class TMS2 {
	
	final static Logger log = LoggerFactory.getLogger(TMS2.class);
	
	public static AppEnvironment env;
	
	public static void main(String[] args) {
		
		SpringApplication.run(TMS2.class, args);
		
		log.info("Application {} started", TMS2.class.getSimpleName());
		log.info("Application AppEnvironment {}.", TMS2.env);
		
	}
	
	@Autowired
	public AppEnvironment loadEnvironment(AppEnvironment env) {
		
		TMS2.env = env;
		
		return env;
		
	}
	
}
