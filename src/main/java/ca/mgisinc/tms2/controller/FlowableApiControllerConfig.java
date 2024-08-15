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

    String REST_API_CONTEXT = "/process-api";

    // Repository
    String REPOSITORY = "/repository";
    String DEPLOYMENT = "/deployments";
    String DEFINITION = "/definitions";
    String PROCESS_DEFINITION = "/process-definitions";

    // Runtime
    String RUNTIME = "/runtime";
    String PROCESS_INSTANCE = "/process-instances";
    String EXECUTIONS = "/executions";

    // URL Services - Repository
    String REPOSITORY_DEPLOYMENTS = REST_API_CONTEXT + REPOSITORY + DEPLOYMENT;
    String REPOSITORY_DEFINITIONS = REST_API_CONTEXT + REPOSITORY + DEFINITION;
    String REPOSITORY_PROCESS_DEFINITIONS = REST_API_CONTEXT + REPOSITORY + PROCESS_DEFINITION;

    // URL Services - Runtime
    String RUNTIME_PROCESS_INSTANCE = REST_API_CONTEXT + RUNTIME + PROCESS_INSTANCE;
    String RUNTIME_EXECUTIONS = REST_API_CONTEXT + RUNTIME + EXECUTIONS;

}
