package ca.mgisinc.tms2.controller;

import ca.mgisinc.tms2.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SuppressWarnings("SameReturnValue")
@Controller
public class ApplicationController {

    final Logger log = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    JwtUtil jwtUti;

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

}
