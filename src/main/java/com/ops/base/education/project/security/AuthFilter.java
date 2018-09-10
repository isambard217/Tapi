package com.ops.base.education.project.security;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ops.base.education.project.domain.ApiUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import static com.ops.base.education.project.security.SecurityConstants.*;
class AuthFilter extends UsernamePasswordAuthenticationFilter {
  private AuthenticationManager authenticationManager;
  private static Logger authLogger = LoggerFactory.getLogger(AuthFilter.class);
  AuthFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
    throws AuthenticationException {
    authLogger.debug("Attempt to authenticate a web service user ...");
    try {
      UserCredentails userCredentails = new ObjectMapper()
        .readValue(request.getInputStream(), UserCredentails.class);
      authLogger.debug("User mapped from request input stream is with:\n userName:"
        + userCredentails.getUserName() + "\n password: " + "are you kidding ...\n" +
        "will try to return authentication object to be ultimately stored in Security context holder" +
        "or otherwise will fire IOException and throwing a run time exception for spring app to handle");
      Authentication authentication =  authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(userCredentails.getUserName(), userCredentails.getPassword(),
          new ArrayList<>()));
      authLogger.debug("... \n returning authentication object to the caller \n ...");
      return authentication;
    } catch (IOException e){
      authLogger.debug("... \n bad new exception ...\n");
      authLogger.error(e.getMessage());
      throw new RuntimeException(e);
    }
  }
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                          Authentication authResult) throws IOException, ServletException {
    String jwtToken = Jwts.builder()
      .setSubject(((User) authResult.getPrincipal()).getUsername())
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(SignatureAlgorithm.HS512, SECRET.getBytes(StandardCharsets.UTF_8))
      .compact();
    response.setHeader(HEADER_STRING, TOKEN_PREFIX + jwtToken);
    try {
      response.getWriter().write(TOKEN_PREFIX + jwtToken);
    } catch (Exception e) {
      authLogger.warn(e.getMessage());
      throw (e);
    }
    response.setStatus(HttpServletResponse.SC_ACCEPTED);
  }
}
