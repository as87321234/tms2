package ca.mgisinc.tms2.config.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.environment")
@Getter
@Setter
@ToString
public class AppEnvironment {
	
	private int queryMaxRecords;
	
}