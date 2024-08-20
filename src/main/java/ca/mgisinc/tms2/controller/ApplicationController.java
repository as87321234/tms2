package ca.mgisinc.tms2.controller;

import ca.mgisinc.tms2.form.BiometricCollectionForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SameReturnValue")
@Controller
public class ApplicationController {
	
	private final Logger log = LoggerFactory.getLogger(ApplicationController.class);
	private FlowableApiControllerConfig conf;
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	public ApplicationController(FlowableApiControllerConfig conf) {
		this.conf = conf;
	}
	
	
	@GetMapping("/")
	public String root(HttpServletRequest request, HttpServletResponse response, Model model) {
		log.info("ApplicationController root");
		
		return index(request, response, model);
	}
	
	@GetMapping("/index")
	public String index(HttpServletRequest ignoredRequest, HttpServletResponse ignoredResponse, Model ignoredModel) {
		
		log.info("ApplicationController index");
		return "/public/index";
	}
	
	@GetMapping("/private/index")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String privateIndex(HttpServletRequest ignoredRequest, HttpServletResponse ignoredResponse, Model ignoredModel) {
		
		log.info("ApplicationController privateIndex");
		
		return "/private/index";
		
	}
	
	@GetMapping("/private/user/index")
	public String privateUserIndex(HttpServletRequest ignoredRequest, HttpServletResponse ignoredResponse, Model ignoredModel) {
		
		log.info("ApplicationController privateUser");
		return "/private/user/index";
	}
	
	@GetMapping("/private/management/index")
	public String flowableConfig(HttpServletRequest ignoredRequest, HttpServletResponse ignoredResponse, Model ignoredModel) {
		
		log.info("ApplicationController flowableConfig");
		return "/private/management/index";
	}
	
	@GetMapping("/private/management/engine")
	public String getEngineInfo(HttpServletRequest ignoredRequest, HttpServletResponse ignoredResponse, Model ignoredModel) {
		
		log.info("ApplicationController engine");
		return "/private/management/engine";
	}
	
	@GetMapping("/private/management/properties")
	public String getEngineProperties(HttpServletRequest ignoredRequest, HttpServletResponse ignoredResponse, Model ignoredModel) {
		
		log.info("ApplicationController properties");
		return "/private/management/properties";
	}
	
	@GetMapping("/private/processflow/index")
	public String getProcessFlowIndex(HttpServletRequest ignoredRequest, HttpServletResponse ignoredResponse, Model ignoredModel) {
		
		log.info("ApplicationController /private/processflow/index");
		
		return "/private/processflow/index";
	}
	
	
	@GetMapping("/private/repository/deployment")
	public String getRepositoryDeployment(HttpServletRequest ignoredRequest, HttpServletResponse ignoredResponse, Model ignoredModel) {
		
		log.info("ApplicationController /private/repository/deployment");
		
		return "/private/repository/deployment";
	}
	
	@GetMapping("/private/repository/process-definitions")
	public String getRepositoryProcessDefinitions(HttpServletRequest ignoredRequest, HttpServletResponse ignoredResponse, Model ignoredModel) {
		
		log.info("ApplicationController /private/repository/process-definitions");
		
		return "/private/repository/process-definitions";
	}
	
	@GetMapping("/private/runtime/process-instances")
	public String getRuntimeProcessInstance(HttpServletRequest ignoredRequest, HttpServletResponse ignoredResponse, Model ignoredModel) {
		
		log.info("ApplicationController /private/runtime/process-instances");
		
		return "/private/runtime/process-instances";
	}
	
	@GetMapping("/private/runtime/executions")
	public String getRuntimeExecution(HttpServletRequest ignoredRequest, HttpServletResponse ignoredResponse, Model ignoredModel) {
		
		log.info("ApplicationController /private/runtime/executions");
		
		return "/private/runtime/executions";
	}
	
	@GetMapping("/private/developer/index")
	public String getDeveloperIndex(HttpServletRequest ignoredRequest, HttpServletResponse ignoredResponse, Model ignoredModel) {
		
		log.info("ApplicationController /private/developer/index");
		
		return "/private/developer/index";
	}
	
	@RequestMapping(value = "/private/developer/submit-enrolment", method = RequestMethod.GET)
	public String loadPostBiometricTestTransaction(Model model) {
		log.info("ApplicationController /private/developer/submit-enrolment");

		List<BiometricCollectionForm> users = new ArrayList<>();
		model.addAttribute("users", users);
		model.addAttribute("userInfo", new BiometricCollectionForm());
		return "/private/developer/submit-enrolment";
	}
	
	@RequestMapping(value = "/private/developer/submit-enrolment", method = RequestMethod.POST)
	public String postBiometricTestTransaction(HttpServletRequest request,
											   HttpServletResponse response,
											   Model model,
											   @ModelAttribute BiometricCollectionForm biometricCollectionForm) throws URISyntaxException {

		log.info("ApplicationController /private/developer/submit-enrolment");
		log.info(biometricCollectionForm.toString());
		
		String protocol = conf.protocol;
		String userinfo = conf.userinfo;
		String fragment = conf.fragment;
		String host = conf.host;
		int port = conf.port;
		
		HttpMethod method = HttpMethod.POST;
		String url = FlowableApiControllerConfig.FLOW_API_CONTEXT_BIOMETRIC_POST_BIOMETRIC_COLLECTION;
		
		log.info("ApplicationController: {}", url);
		
		URI thirdPartyApi = new URI(protocol, userinfo, host, port, url, request.getQueryString(), fragment);
		
		ResponseEntity<BiometricCollectionForm> resp =
				restTemplate.exchange(thirdPartyApi, method,
						new HttpEntity<>(biometricCollectionForm), BiometricCollectionForm.class);

//		return resp.getBody();
		
		return "redirect:/private/developer/submit-enrolment";
	}
	
}
