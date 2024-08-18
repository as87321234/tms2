package ca.mgisinc.tms2.controller;

import ca.mgisinc.tms2.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@SuppressWarnings("SameReturnValue")
@RestController
@RequestMapping("/flowable/proxy")


public class FlowableProxyController {
	
	private final Logger log = LoggerFactory.getLogger(FlowableProxyController.class);
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	public FlowableProxyController(JwtUtil jwtUti) {
	}
	
	@GetMapping(value = "/management/engine", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getManagementEngine(HttpServletRequest request, HttpServletResponse response, Model model) throws URISyntaxException {
		
		log.info("FlowableProxyController: /management/engine");
		String protocol = "http";
		HttpMethod method = HttpMethod.GET;
		String userinfo = null;
		String fragment = null;
		int port = 9090;
		String host = "localhost";
		String url = "/process-api/management/engine";
		
		URI thirdPartyApi = new URI(protocol, userinfo, host, port, url, request.getQueryString(), fragment);
		
		ResponseEntity<String> resp =
				restTemplate.exchange(thirdPartyApi, method, null, String.class);
		
		return resp.getBody();
	}
	
	@GetMapping(value = "/management/properties", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getManagementProperties(HttpServletRequest request, HttpServletResponse response, Model model) throws URISyntaxException {
		
		log.info("FlowableProxyController: /management/properties");
		String protocol = "http";
		HttpMethod method = HttpMethod.GET;
		String userinfo = null;
		String fragment = null;
		int port = 9090;
		String host = "localhost";
		String url = "/process-api/management/properties";
		
		URI thirdPartyApi = new URI(protocol, userinfo, host, port, url, request.getQueryString(), fragment);
		
		ResponseEntity<String> resp =
				restTemplate.exchange(thirdPartyApi, method, null, String.class);
		
		return resp.getBody();
	}
	
	
	@GetMapping(value = "/repository/deployments", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getRepositoryDeployments(HttpServletRequest request, HttpServletResponse response, Model model) throws URISyntaxException {
		
		log.info("FlowableProxyController: /repository/deployments");
		String protocol = "http";
		HttpMethod method = HttpMethod.GET;
		String userinfo = null;
		String fragment = null;
		int port = 9090;
		String host = "localhost";
		String url = "/process-api/repository/deployments";
		
		URI thirdPartyApi = new URI(protocol, userinfo, host, port, url, request.getQueryString(), fragment);
		
		ResponseEntity<String> resp =
				restTemplate.exchange(thirdPartyApi, method, null, String.class);
		
		return resp.getBody();
	}
	
	@GetMapping(value = "/repository/definitions", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getRepositoryDefinitions(HttpServletRequest request, HttpServletResponse response, Model model) throws URISyntaxException {
		
		log.info("FlowableProxyController: /repository/definitions");
		String protocol = "http";
		HttpMethod method = HttpMethod.GET;
		String userinfo = null;
		String fragment = null;
		int port = 9090;
		String host = "localhost";
		String url = "/process-api/repository/deployments";
		
		URI thirdPartyApi = new URI(protocol, userinfo, host, port, url, request.getQueryString(), fragment);
		
		ResponseEntity<String> resp =
				restTemplate.exchange(thirdPartyApi, method, null, String.class);
		
		return resp.getBody();
	}
	
}
