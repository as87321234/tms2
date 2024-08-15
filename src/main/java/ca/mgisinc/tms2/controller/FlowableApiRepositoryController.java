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
@RequestMapping("/flow-api/repository")

public class FlowableApiRepositoryController {

    Logger log = LoggerFactory.getLogger(FlowableApiRepositoryController.class);

    @Autowired
    FlowableApiControllerConfig conf;

    @GetMapping(value = "/deployments", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDeployments(HttpServletRequest request, HttpServletResponse response, Model model) throws URISyntaxException {

        String protocol = conf.protocol;
        String userinfo = conf.userinfo;
        String fragment = conf.fragment;
        String host = conf.host;
        int port = conf.port;

        HttpMethod method = HttpMethod.GET;
        String url = conf.REPOSITORY_DEPLOYMENTS;
        String body = null;

        log.info("FlowableApiRepositoryController: " + url);

        URI thirdPartyApi = new URI(protocol, userinfo, host, port, url, request.getQueryString(), fragment);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp =
                restTemplate.exchange(thirdPartyApi, method, new HttpEntity<String>(body), String.class);

        return resp.getBody();
    }

    @GetMapping(value = "/process-definitions", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getProcessDefinitions(HttpServletRequest request, HttpServletResponse response, Model model) throws URISyntaxException {

        String protocol = conf.protocol;
        String userinfo = conf.userinfo;
        String fragment = conf.fragment;
        String host = conf.host;
        int port = conf.port;

        HttpMethod method = HttpMethod.GET;
        String url = conf.REPOSITORY_PROCESS_DEFINITIONS;
        String body = null;

        log.info("FlowableApiRepositoryController: " + url);

        URI thirdPartyApi = new URI(protocol, userinfo, host, port, url, request.getQueryString(), fragment);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp =
                restTemplate.exchange(thirdPartyApi, method, new HttpEntity<String>(body), String.class);

        return resp.getBody();
    }

}
