package ca.mgisinc.tms2.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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

@RestController
@RequestMapping("/flow-api/runtime")

public class FlowableApiRuntimeController {

    Logger log = LoggerFactory.getLogger(FlowableApiRuntimeController.class);

    @Autowired
    FlowableApiControllerConfig conf;

    @GetMapping(value = "/process-instances", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getProcessInstances(HttpServletRequest request, HttpServletResponse response, Model model) throws URISyntaxException {

        String protocol = conf.protocol;
        String userinfo = conf.userinfo;
        String fragment = conf.fragment;
        String host = conf.host;
        int port = conf.port;

        HttpMethod method = HttpMethod.GET;
        String url = conf.RUNTIME_PROCESS_INSTANCE;
        String body = null;

        log.info("FlowableApiRuntimeController: " + url);

        URI thirdPartyApi = new URI(protocol, userinfo, host, port, url, request.getQueryString(), fragment);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp =
                restTemplate.exchange(thirdPartyApi, method, new HttpEntity<String>(body), String.class);

        return resp.getBody();
    }
    @GetMapping(value = "/executions", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getExecutions(HttpServletRequest request, HttpServletResponse response, Model model) throws URISyntaxException {

        String protocol = conf.protocol;
        String userinfo = conf.userinfo;
        String fragment = conf.fragment;
        String host = conf.host;
        int port = conf.port;

        HttpMethod method = HttpMethod.GET;
        String url = conf.RUNTIME_EXECUTIONS;
        String body = null;

        log.info("FlowableApiRuntimeController: " + url);

        URI thirdPartyApi = new URI(protocol, userinfo, host, port, url, request.getQueryString(), fragment);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp =
                restTemplate.exchange(thirdPartyApi, method, new HttpEntity<String>(body), String.class);

        return resp.getBody();
    }


}
