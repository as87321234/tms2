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
@RequestMapping("/flow-api/management")

public class FlowableApiManagementController {
	
	private final Logger log = LoggerFactory.getLogger(FlowableApiManagementController.class);
	private final RestTemplate restTemplate = new RestTemplate();
	private final FlowableApiControllerConfig conf;
	
	public FlowableApiManagementController(FlowableApiControllerConfig conf) {
		this.conf = conf;
	}
	
	@GetMapping(value = "/engine", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getManagementEngine(HttpServletRequest request, HttpServletResponse response, Model model) throws URISyntaxException {
		
		log.info("FlowableProxyController: /management/engine");
		
		String protocol = conf.protocol;
		String userinfo = conf.userinfo;
		String fragment = conf.fragment;
		String host = conf.host;
		int port = conf.port;
		
		HttpMethod method = HttpMethod.GET;
		
		
		String url = FlowableApiControllerConfig.MANAGEMENT_ENGINE;
		
		
		URI thirdPartyApi = new URI(protocol, userinfo, host, port, url, request.getQueryString(), fragment);
		
		ResponseEntity<String> resp =
				restTemplate.exchange(thirdPartyApi, method, null, String.class);
		
		return resp.getBody();
	}
	
	
}




