package ca.mgisinc.tms2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories

public class TMS2 {
	
	public static void main(String[] args) {
		SpringApplication.run(TMS2.class, args);
	}
	
}
