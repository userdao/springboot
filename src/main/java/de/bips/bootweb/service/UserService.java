package de.bips.bootweb.service;

import de.bips.bootweb.models.User;
import de.bips.bootweb.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userrepository;

  public Iterable<User> getAllUsers() {
    return userrepository.findAll();
  }


}
