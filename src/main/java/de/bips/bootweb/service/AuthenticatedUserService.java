package de.bips.bootweb.service;

import static de.bips.bootweb.models.generated.Tables.T_APP_USER;
import de.bips.bootweb.models.AppUser;
import de.bips.bootweb.models.generated.tables.pojos.TAppUser;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserService implements UserDetailsService {

  // @Autowired
  // private UserRepository userRepository;

  @Autowired
  private DSLContext create;

  // @Override
  // public UserDetails loadUserByUsername(String username) {
  // User user = userRepository.findByUsername(username);
  // if (user == null) {
  // throw new UsernameNotFoundException("The user " + username + " does not exist");
  // }
  // return new AuthenticatedUser(user, AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
  // }


  @Override
  public UserDetails loadUserByUsername(String username) {
    TAppUser user = create.select().from(T_APP_USER).where(T_APP_USER.USER_NAME.eq(username))
        .fetchOneInto(TAppUser.class);
    if (null == user) {
      throw new UsernameNotFoundException("The user " + username + " does not exist");
    }
    return new AppUser(user, AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
  }


  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
