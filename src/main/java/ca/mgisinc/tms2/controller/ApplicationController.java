package ca.mgisinc.tms2.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SuppressWarnings("SameReturnValue")
@Controller
public class ApplicationController {
	
	private final Logger log = LoggerFactory.getLogger(ApplicationController.class);
	
	public ApplicationController() {
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
	
}
