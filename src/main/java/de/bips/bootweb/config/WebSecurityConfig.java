package de.bips.bootweb.config;

import de.bips.bootweb.service.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = AuthenticatedUserService.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;


  private static final String[] AUTH_WHITELIST = {
      // -- swagger ui
      "/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**"};

  @Override
  protected void configure(HttpSecurity http) throws Exception {


    // (1)
    // http.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll().antMatchers("/**/*").denyAll();

    // (2) http.authorizeRequests().antMatchers("/noSecurity").hasRole("ADMIN").anyRequest()
    // .authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
    // .permitAll().and().exceptionHandling().accessDeniedPage("/Access_Denied");

    // (3) http.authorizeRequests().antMatchers(HttpMethod.POST, "/account").permitAll()
    // .antMatchers(HttpMethod.GET, "/account").hasAuthority("ROLE_ADMIN").anyRequest()
    // .fullyAuthenticated().and().httpBasic().and().csrf().disable();
  }

  @Autowired
  public void globalSecurityConfiguration(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

}
