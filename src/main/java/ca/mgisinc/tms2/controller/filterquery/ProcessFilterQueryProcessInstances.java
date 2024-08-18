package ca.mgisinc.tms2.controller.filterquery;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProcessFilterQueryProcessInstances {
	
	@Value("${app.environment.execution-maximum}")
	String size;
	
}
