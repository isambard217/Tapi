package com.ops.base.education.project.security;
import com.ops.base.education.project.configuration.EduProjectManagerCORSConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static com.ops.base.education.project.security.SecurityConstants.LOG_IN_URL;
import static com.ops.base.education.project.security.SecurityConstants.SIGN_UP_URL;
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  private UserDetailsService userDetailsService;
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  private EduProjectManagerCORSConfiguration corsConfiguration;
  @Autowired
  public SecurityConfiguration(StudentDetailService userDetailsService,
                               BCryptPasswordEncoder bCryptPasswordEncoder,
                               EduProjectManagerCORSConfiguration corsConfiguration){
    setUserDetailsService(userDetailsService);
    setBCryptPasswordEncoder(bCryptPasswordEncoder);
    setCorsConfiguration(corsConfiguration);
  }
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.cors().and().csrf().disable().authorizeRequests()
      .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
      .anyRequest().authenticated()
      .and()
      .addFilter(getJwtAuthenticationFilter(authenticationManager()))
      .addFilter(new AuthoriseFilter(authenticationManager()))
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }
  private AuthFilter getJwtAuthenticationFilter(AuthenticationManager authenticationManager) {
    AuthFilter authFilter = new AuthFilter(authenticationManager);
    authFilter.setFilterProcessesUrl(LOG_IN_URL);
    return authFilter;
  }
  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder
      .userDetailsService(this.getUserDetailsService())
      .passwordEncoder(this.getBCryptPasswordEncoder());
  }
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
  }
  private void setUserDetailsService(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }
  private UserDetailsService getUserDetailsService(){
    return this.userDetailsService;
  }
  public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }
  private BCryptPasswordEncoder getBCryptPasswordEncoder(){
    return this.bCryptPasswordEncoder;
  }
  public BCryptPasswordEncoder getbCryptPasswordEncoder() {
    return bCryptPasswordEncoder;
  }
  public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }
  public EduProjectManagerCORSConfiguration getCorsConfiguration() {
    return corsConfiguration;
  }
  public void setCorsConfiguration(EduProjectManagerCORSConfiguration corsConfiguration) {
    this.corsConfiguration = corsConfiguration;
  }
}
