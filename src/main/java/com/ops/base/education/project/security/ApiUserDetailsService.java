package com.ops.base.education.project.security;
import com.ops.base.education.project.Repository.ApiUsersRepository;
import com.ops.base.education.project.domain.ApiUser;
import com.ops.base.education.project.domain.Privilege;
import com.ops.base.education.project.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service("userDetailsService")
@Transactional(readOnly = true)
public class ApiUserDetailsService implements UserDetailsService {
  private final ApiUsersRepository apiUsersRepository;
  @Autowired
  public ApiUserDetailsService(ApiUsersRepository apiUsersRepository){
    this.apiUsersRepository = apiUsersRepository;
  }
  /**
   * Locates the user based on the username. In the actual implementation, the search
   * may possibly be case sensitive, or case insensitive depending on how the
   * implementation instance is configured. In this case, the <code>UserDetails</code>
   * object that comes back may have a username that is of a different case than what
   * was actually requested..
   *
   * @param username the username identifying the user whose data is required.
   * @return a fully populated user record (never <code>null</code>)
   * @throws UsernameNotFoundException if the user could not be found or the user has no
   *                                   Privilege
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    ApiUser apiUser = this.apiUsersRepository.findByUserName(username);
    if(apiUser == null){
      throw new UsernameNotFoundException(username);
    }
    return new User(apiUser.getUserName(), apiUser.getPassword(), getAuthorities(apiUser.getRoles()));
  }
  private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
    return getGrantedAuthorities(getPrivileges(roles));
  }
  private Collection<? extends GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(privileges.toArray().length);
    for (String privilege: privileges){
      grantedAuthorities.add(new SimpleGrantedAuthority(privilege));
    }
    return grantedAuthorities;
  }
  /**
   * Given a Collection roles where each Role in roles has 0 to many Privilege
   * We want to return a List of String that contains all granted authorities of Privileges
   * ultimately the apiUser has on the api
   * @param roles Collection of Role
   * @return List of String that tell the security framework all the granted authorities or privileges the user has
   * @see org.springframework.security.access.vote.RoleVoter is configured with a prefix i.e. ROLE that is why this
   * method is returning ROLE as a prefix and the rest of the name just to make scense.
   */
  private List<String> getPrivileges(Collection<Role> roles) {
    List<String> grantedAuthorities;
    List<Privilege> privileges = new ArrayList<>();
    for (Role role: roles){
      privileges.addAll(role.getPrivileges());
    }
    grantedAuthorities = new ArrayList<>(privileges.toArray().length);
    for (Privilege privilege: privileges){
      if(privilege.getName().equals("WRITE_PRIVILEGE"))
        grantedAuthorities.add("ROLE_ADMIN_PRIVILEGE");
      if(privilege.getName().equals("READ_PRIVILEGE"))
        grantedAuthorities.add("ROLE_USER_PRIVILEGE");
    }
    return grantedAuthorities;
  }
}
