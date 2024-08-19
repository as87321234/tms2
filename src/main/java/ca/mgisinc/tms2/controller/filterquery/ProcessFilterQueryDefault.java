package ca.mgisinc.tms2.controller.filterquery;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProcessFilterQueryDefault {
	
	@Value("${app.environment.query-max-records}")
	String size;
	
}
