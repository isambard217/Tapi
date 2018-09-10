package com.ops.base.education.project.security;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import static com.ops.base.education.project.security.SecurityConstants.HEADER_STRING;
import static com.ops.base.education.project.security.SecurityConstants.SECRET;
import static com.ops.base.education.project.security.SecurityConstants.TOKEN_PREFIX;
import static java.util.Collections.emptyList;
class AuthoriseFilter extends BasicAuthenticationFilter {
  private final ApiUserDetailsService apiUserDetailsService;
  AuthoriseFilter(AuthenticationManager authenticationManager, ApiUserDetailsService apiUserDetailsService) {
    super(authenticationManager);
    this.apiUserDetailsService = apiUserDetailsService;
  }
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    // get the header for the request
    String header = request.getHeader(HEADER_STRING);
    if (header == null || !header.startsWith(TOKEN_PREFIX)){
      chain.doFilter(request, response);
      return;
    }
    UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    chain.doFilter(request, response);
  }
  private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);
    if (token != null) {
      // parse the token to get the User (i.e. User )
      String user = Jwts.parser()
        .setSigningKey(SECRET.getBytes(StandardCharsets.UTF_8))
        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
        .getBody()
        .getSubject();
      if (user != null) {
        return new UsernamePasswordAuthenticationToken(user, null,
          this.apiUserDetailsService.loadUserByUsername(user).getAuthorities());
      }
      return null;
    }
    return null;
  }
}
