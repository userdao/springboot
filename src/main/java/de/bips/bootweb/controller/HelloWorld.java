package de.bips.bootweb.controller;

import de.bips.bootweb.models.User;
import de.bips.bootweb.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/demo")
public class HelloWorld {

  @Autowired
  private UserRepository userRepository;

  @GetMapping(path = "/add") // Map ONLY GET Requests
  public String addNewUser(@RequestParam String name, @RequestParam String email) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request



    return "Saved";
  }


  @GetMapping(path = "/all")
  public Iterable<User> getAllUsers() {
    // This returns a JSON or XML with the users
    return userRepository.findAll();
  }

  @GetMapping(path = "/mail")
  public Iterable<User> getAllUsersByEmail(@RequestParam String email) {
    return userRepository.findByEmail(email);
  }


  @GetMapping(path = "/maildelete")
  public Integer deleteByEmail(@RequestParam String email) {
    return userRepository.deleteByEmail(email);
  }


}
