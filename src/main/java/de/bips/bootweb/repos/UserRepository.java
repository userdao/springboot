package de.bips.bootweb.repos;

import de.bips.bootweb.models.User;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

  List<User> findByEmail(String email);

  @Transactional
  Integer deleteByEmail(String email);

  User findByUsername(String username);

}
