package de.bips.bootweb.service;

import static de.bips.bootweb.models.generated.Tables.T_APP_USER;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import de.bips.bootweb.models.AppUser;
import de.bips.bootweb.models.generated.tables.pojos.TAppUser;

@Service
public class AuthenticatedUserService implements UserDetailsService {

  @Autowired
  private DSLContext create;

  @Override
  public UserDetails loadUserByUsername(String username) {

    // USER:swagger PASSWORD:test bycrpyt Problem in Spring Boot, please change $2y$... to $2a$...


    TAppUser user = create.select().from(T_APP_USER).where(T_APP_USER.USER_LOGIN.eq(username))
        .fetchOneInto(TAppUser.class);
    if (null == user) {
      throw new UsernameNotFoundException("The user " + username + " does not exist");
    }
    return new AppUser(user, AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
  }

}
