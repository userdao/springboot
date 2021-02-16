package de.bips.bootweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import de.bips.bootweb.service.RestClientService;

@Controller
public class LoginController {

  @Autowired
  private RestClientService restClient;


  @RequestMapping("/")
  public String index() {
    return "index";
  }

  @RequestMapping("/login")
  public String login(Model model) {

    restClient.exchange("http://localhost:8080/webmodys/api/probands");

    return "login";
  }

  @RequestMapping("/noSecurity")
  public String noSecurity() {
    return "noSecurity";
  }

  @RequestMapping("/Access_Denied")
  public String accessDeniedPage() {
    return "accessdenied";
  }
}
