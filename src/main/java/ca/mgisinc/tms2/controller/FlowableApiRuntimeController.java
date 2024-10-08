package ca.mgisinc.tms2.controller;

import ca.mgisinc.tms2.controller.pojo.BiometricEnrolment;
import ca.mgisinc.tms2.form.BiometricCollectionForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/flow-api/runtime")

public class FlowableApiRuntimeController {
	
	private final Logger log = LoggerFactory.getLogger(FlowableApiRuntimeController.class);
	private final RestTemplate restTemplate = new RestTemplate();
	private final FlowableApiControllerConfig conf;
	
	public FlowableApiRuntimeController(FlowableApiControllerConfig conf) {
		this.conf = conf;
	}
	
	@GetMapping(value = "/process-instances/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getProcessInstanceDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") final String id) throws URISyntaxException {
		
		
		String protocol = conf.protocol;
		String userinfo = conf.userinfo;
		String fragment = conf.fragment;
		String host = conf.host;
		int port = conf.port;
		
		HttpMethod method = HttpMethod.DELETE;
		String url = FlowableApiControllerConfig.RUNTIME_PROCESS_INSTANCE + "/" + id;
		
		log.info("FlowableApiRuntimeController: {}", url);
		
		
		URI thirdPartyApi = new URI(protocol, userinfo, host, port, url, request.getQueryString(), fragment);
		
		ResponseEntity<String> resp =
				restTemplate.exchange(thirdPartyApi, method, null, String.class);
		
		return resp.getBody();
		
	}
	
	@GetMapping(value = "/process-instances/{id}/variables/{name}/data",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public BiometricEnrolment getProcessInstanceDelete(HttpServletRequest request, HttpServletResponse response,
													   @PathVariable(value = "id") final String id,
													   @PathVariable(value = "name") final String name) throws URISyntaxException {
		
		
		String protocol = conf.protocol;
		String userinfo = conf.userinfo;
		String fragment = conf.fragment;
		String host = conf.host;
		int port = conf.port;
		
		HttpMethod method = HttpMethod.GET;
		String url = FlowableApiControllerConfig.FLOW_API_CONTEXT_BIOMETRIC_GET_PROCESS_INSTANCE_VARIABLE
				+ "/" + id
				+ "/" + name;
		
		log.info("FlowableApiRuntimeController: {}", url);
		
		URI thirdPartyApi = new URI(protocol, userinfo, host, port, url, request.getQueryString(), fragment);
		
		ResponseEntity<BiometricEnrolment> resp =
				restTemplate.exchange(thirdPartyApi, method, null, BiometricEnrolment.class);
		
		return resp.getBody();
		
	}
	
}

