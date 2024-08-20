package ca.mgisinc.tms2.controller;

import ca.mgisinc.tms2.form.BiometricCollectionForm;
import ca.mgisinc.tms2.model.Privilege;
import ca.mgisinc.tms2.model.Role;
import ca.mgisinc.tms2.model.User;
import ca.mgisinc.tms2.repository.UserRepository;
import ca.mgisinc.tms2.tableview.UserTableView;
import ca.mgisinc.tms2.util.DataView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor

@RestController
@RequestMapping("/api")
public class ApplicationApiController {
	
	private final Logger log = LoggerFactory.getLogger(ApplicationApiController.class);
	private final RestTemplate restTemplate = new RestTemplate();
	
	private UserRepository userRepository;
	
	private FlowableApiControllerConfig conf;
	
	@Autowired
	public ApplicationApiController(FlowableApiControllerConfig conf, UserRepository userRepository) {
		this.conf = conf;
		this.userRepository = userRepository;
	}
	
	/**
	 * Returns an list of all users from the database.
	 */
	@PostMapping(value = "/get_users", produces = MediaType.APPLICATION_JSON_VALUE)
	public DataView getUser() {
		
		log.info("ApplicationController root");
		
		DataView dataView = new DataView(new ArrayList<>());
		
		for (User u : userRepository.findAll()) {
			
			UserTableView userTableView = new UserTableView();
			BeanUtils.copyProperties(u, userTableView);
			
			Set<String> roles = new HashSet<>();
			Set<String> privileges = new HashSet<>();
			
			for (Role r : u.getRoles()) {
				roles.add(r.getName());
				
				for (Privilege p : r.getPrivileges()) {
					privileges.add(p.getName());
				}
			}
			
			userTableView.setRoles(roles);
			userTableView.setPrivileges(privileges);
			
			dataView.getData().add(userTableView);
			
		}
		
		return dataView;
		
	}
	
}
