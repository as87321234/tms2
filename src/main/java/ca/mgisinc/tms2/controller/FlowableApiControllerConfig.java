package ca.mgisinc.tms2.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="flowable-rest-api")
@Setter
@Getter
public class FlowableApiControllerConfig {

    String host = "localhost";
    String protocol = "http";
    String userinfo = null;
    String fragment = null;
    int port = 9999;

    static final String REST_API_CONTEXT = "/process-api";

    // Repository
    static final String REPOSITORY = "/repository";
    static final String DEPLOYMENT = "/deployments";
    static final String DEFINITION = "/definitions";
    static final String PROCESS_DEFINITION = "/process-definitions";
    static final String IMAGE = "/image";


    // Runtime
    static final String RUNTIME = "/runtime";
    static final String PROCESS_INSTANCE = "/process-instances";
    static final String EXECUTIONS = "/executions";
    static final String QUERY = "/query";

    // URL Services - Repository
    static final String REPOSITORY_DEPLOYMENTS = REST_API_CONTEXT + REPOSITORY + DEPLOYMENT;
    static final String REPOSITORY_DEFINITIONS = REST_API_CONTEXT + REPOSITORY + DEFINITION;
    static final String REPOSITORY_PROCESS_DEFINITIONS = REST_API_CONTEXT + REPOSITORY + PROCESS_DEFINITION;

    // URL Services - Runtime
    static final String RUNTIME_PROCESS_INSTANCE = REST_API_CONTEXT + RUNTIME + PROCESS_INSTANCE;
    static final String RUNTIME_EXECUTIONS = REST_API_CONTEXT + RUNTIME + EXECUTIONS;
    static final String QUERY_PROCESS_INSTANCE = REST_API_CONTEXT + QUERY + PROCESS_INSTANCE;

}
