package ca.mgisinc.tms2.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "flowable-rest-api")
@Setter
@Getter
@Data
public class FlowableApiControllerConfig {


//	/flow-api/biometric/post-biometric-collection
	
	
	public static final String REST_API_CONTEXT = "/process-api";
	public static final String FLOW_API_CONTEXT = "/flow-api";
	// Repository
	public static final String REPOSITORY = "/repository";
	public static final String DEPLOYMENT = "/deployments";
	public static final String DEFINITION = "/definitions";
	public static final String MANAGEMENT = "/management";
	public static final String RUNTIME = "/runtime";
	// Runtime
	public static final String PROCESS_INSTANCE = "/process-instances";
	public static final String PROCESS_DEFINITION = "/process-definitions";
	public static final String EXECUTIONS = "/executions";
	public static final String QUERY = "/query";
	public static final String IMAGE = "/image";
	public static final String ENGINE = "/engine";
	public static final String BIOMETRIC = "/biometric";
	public static final String PROPERTIES = "/properties";
	public static final String POST_BIOMETRIC_COLLECTION = "/post-biometric-collection";
	// URL Services - Repository
	public static final String REPOSITORY_DEPLOYMENTS = REST_API_CONTEXT + REPOSITORY + DEPLOYMENT;
	public static final String REPOSITORY_DEFINITIONS = REST_API_CONTEXT + REPOSITORY + DEFINITION;
	public static final String REPOSITORY_PROCESS_DEFINITIONS = REST_API_CONTEXT + REPOSITORY + PROCESS_DEFINITION;
	// URL Services - Runtime
	public static final String RUNTIME_PROCESS_INSTANCE = REST_API_CONTEXT + RUNTIME + PROCESS_INSTANCE;
	public static final String RUNTIME_EXECUTIONS = REST_API_CONTEXT + RUNTIME + EXECUTIONS;
	public static final String QUERY_PROCESS_INSTANCE = REST_API_CONTEXT + QUERY + PROCESS_INSTANCE;
	public static final String QUERY_EXECUTION = REST_API_CONTEXT + QUERY + EXECUTIONS;
	// URL Flow API - MGIS Rest call
	public static final String FLOW_API_CONTEXT_BIOMETRIC_POST_BIOMETRIC_COLLECTION = FLOW_API_CONTEXT + BIOMETRIC + POST_BIOMETRIC_COLLECTION;;
	public static final String MANAGEMENT_ENGINE = REST_API_CONTEXT + MANAGEMENT + ENGINE;
	public static final String MANAGEMENT_PROPERTIES = REST_API_CONTEXT + MANAGEMENT + PROPERTIES;
	
	@Value("${flowable.api.controller.host}")
	public  String host = "localhost";
	@Value("${flowable.api.controller.protocol}")
	public  String protocol = "http";
	@Value("${flowable.api.controller.userinfo}")
	public  String userinfo = null;
	@Value("${flowable.api.controller.fragment}")
	public  String fragment = null;
	@Value("${flowable.api.controller.port}")
	public  int port = 9999;
	
}
