package de.bips.bootweb;

import de.bips.bootweb.repos.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootwebApplicationTests {

  private MockMvc mvc;
  @Autowired
  private UserRepository userrepository;

  @Test
  public void contextLoads() {}

  @Test
  public void createUser_thenSave() {


  }

  @Test
  public void deleteUser_thenInteger() {


  }

}
