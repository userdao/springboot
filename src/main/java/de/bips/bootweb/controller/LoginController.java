package de.bips.bootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

  @RequestMapping("/")
  public String index() {
    return "index";
  }

  @RequestMapping("/login")
  public String login(Model model) {
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
