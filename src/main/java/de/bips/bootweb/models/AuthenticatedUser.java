package de.bips.bootweb.models;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class AuthenticatedUser extends User implements UserDetails {
  /**
   * 
   */
  private static final long serialVersionUID = -7784124351438836957L;

  private List<GrantedAuthority> authorities;

  public AuthenticatedUser(User user, List<GrantedAuthority> list) {
    super(user.getUsername(), user.getPassword());
    authorities = list;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}

