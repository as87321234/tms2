package ca.mgisinc.tms2.controller;

import ca.mgisinc.tms2.util.DataView;
import ca.mgisinc.tms2.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@SuppressWarnings("SameReturnValue")
@RestController
@RequestMapping("/flowable/proxy")


public class FlowableProxyController {

    final Logger log = LoggerFactory.getLogger(FlowableProxyController.class);

    @Autowired
    JwtUtil jwtUti;

    @GetMapping(value = "/management/engine", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getManagementEngine(HttpServletRequest request, HttpServletResponse response, Model model) throws URISyntaxException {

        log.info("FlowableProxyController: /management/engine");
        String protocol = "http";
        HttpMethod method = HttpMethod.GET;
        String userinfo = null;
        String fragment = null;
        String body = null;
        int port = 9090;
        String host = "localhost";
        String url = "/process-api/management/engine";

        URI thirdPartyApi = new URI(protocol, userinfo, host, port, url, request.getQueryString(), fragment);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp =
                restTemplate.exchange(thirdPartyApi, method, new HttpEntity<String>(body), String.class);

        return resp.getBody();
    }

    @GetMapping(value = "/management/properties", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getManagementProperties(HttpServletRequest request, HttpServletResponse response, Model model) throws URISyntaxException {

        log.info("FlowableProxyController: /management/engine/properties");
        String protocol = "http";
        HttpMethod method = HttpMethod.GET;
        String userinfo = null;
        String fragment = null;
        String body = null;
        int port = 9090;
        String host = "localhost";
        String url = "/process-api/management/properties";

        URI thirdPartyApi = new URI(protocol, userinfo, host, port, url, request.getQueryString(), fragment);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp =
                restTemplate.exchange(thirdPartyApi, method, new HttpEntity<String>(body), String.class);

        return resp.getBody();
    }


}
