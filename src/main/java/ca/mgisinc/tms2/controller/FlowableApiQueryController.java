package ca.mgisinc.tms2.controller;

import ca.mgisinc.tms2.controller.filterquery.ProcessFilterQueryDefault;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/flow-api/query")

public class FlowableApiQueryController {
	
	private final Logger log = LoggerFactory.getLogger(FlowableApiQueryController.class);
	private final RestTemplate restTemplate = new RestTemplate();
	final
	FlowableApiControllerConfig conf;
	
	@Autowired
	ProcessFilterQueryDefault query ;
	
	public FlowableApiQueryController(FlowableApiControllerConfig conf) {
		this.conf = conf;
	}
	
	@GetMapping(value = "/process-instances", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String queryProcessInstances(HttpServletRequest request, HttpServletResponse response, Model model) throws URISyntaxException {
		
		String protocol = conf.protocol;
		String userinfo = conf.userinfo;
		String fragment = conf.fragment;
		String host = conf.host;
		int port = conf.port;
		
		HttpMethod method = HttpMethod.POST;
		String url = FlowableApiControllerConfig.QUERY_PROCESS_INSTANCE;
		
		log.info("FlowableApiQueryController: {}", url);
		
		URI thirdPartyApi = new URI(protocol, userinfo, host, port, url, request.getQueryString(), fragment);
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<ProcessFilterQueryDefault> entity = new HttpEntity<>(query, headers);
		
		ResponseEntity<String> resp =
				restTemplate.exchange(thirdPartyApi, method, entity, String.class);
		
		return resp.getBody();
		
	}
	
	@GetMapping(value = "/executions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String queryExecution(HttpServletRequest request, HttpServletResponse response, Model model) throws URISyntaxException {
		
		String protocol = conf.protocol;
		String userinfo = conf.userinfo;
		String fragment = conf.fragment;
		String host = conf.host;
		int port = conf.port;
		
		HttpMethod method = HttpMethod.POST;
		String url = FlowableApiControllerConfig.QUERY_EXECUTION;
		
		log.info("FlowableApiQueryController: {}", url);
		
		URI thirdPartyApi = new URI(protocol, userinfo, host, port, url, request.getQueryString(), fragment);
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<ProcessFilterQueryDefault> entity = new HttpEntity<>(query, headers);
		
		ResponseEntity<String> resp =
				restTemplate.exchange(thirdPartyApi, method, entity, String.class);
		
		return resp.getBody();
		
	}
	
}




