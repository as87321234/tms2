package ca.mgisinc.tms2.controller;

import ca.mgisinc.tms2.form.BiometricCollectionForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/private/submit-enrolment")
public class BiometricCollectionController {
  
  Logger log = LoggerFactory.getLogger(BiometricCollectionController.class);
 
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String loadPostBiometricTestTransaction(Model model) {
      List<BiometricCollectionForm> users = new ArrayList<>();
    model.addAttribute("users", users);
    model.addAttribute("userInfo", new BiometricCollectionForm());
    return "/private/submit-enrolment";
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public String postBiometricTestTransaction(Model model, @ModelAttribute BiometricCollectionForm biometricCollectionForm) {
//    UserInfo user = userService.createUser(userInfo);
    log.info(biometricCollectionForm.toString());
    
    return "redirect:/private/submit-enrolment/";
  }
}
