package com.ops.base.education.project.security;
import com.ops.base.education.project.Repository.StudentsRepository;
import com.ops.base.education.project.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
@Service
public class StudentDetailService implements UserDetailsService {
  private final StudentsRepository studentsRepository;
  @Autowired
  public StudentDetailService(StudentsRepository studentsRepository){
    this.studentsRepository = studentsRepository;
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
   *                                   GrantedAuthority
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Student student = this.studentsRepository.findByUserName(username);
    if(student == null){
      throw new UsernameNotFoundException(username);
    }
    return new User(student.getUserName(), student.getPassword(), new ArrayList<>());
  }
}