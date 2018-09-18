package com.ops.base.education.project.loader;
import com.ops.base.education.project.Repository.ApiUsersRepository;
import com.ops.base.education.project.Repository.PrivilegesRepository;
import com.ops.base.education.project.Repository.RolesRepository;
import com.ops.base.education.project.Service.ApiUsersService;
import com.ops.base.education.project.domain.ApiUser;
import com.ops.base.education.project.domain.Privilege;
import com.ops.base.education.project.domain.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ops.base.education.project.security.PublicSecurityConstants.API_PASSWORD;
import static com.ops.base.education.project.security.PublicSecurityConstants.API_USER_NAME;

@Component
@Transactional
public class LoadFirstApiUser implements CommandLineRunner {
  private final Logger logger;
  private final ApiUsersService apiUsersService;
  private final PrivilegesRepository privilegesRepository;
  private final RolesRepository rolesRepository;
  private final ApiUsersRepository apiUsersRepository;
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired
  public LoadFirstApiUser(ApiUsersService apiUsersService,
                          PrivilegesRepository privilegesRepository, RolesRepository rolesRepository,
                          ApiUsersRepository apiUsersRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
    this.apiUsersService = apiUsersService;
    this.privilegesRepository = privilegesRepository;
    logger = LoggerFactory.getLogger(getClass());
    this.rolesRepository = rolesRepository;
    this.apiUsersRepository = apiUsersRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }
  /**
   * Callback used to run the bean.
   * @param args incoming main method arguments
   * @throws Exception on error
   */
  @Override
  public void run(String... args) throws Exception {
    logger.debug("Creating a read and a write privileges");
    final Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
    logger.debug("A read privilege is created successfully ..." + readPrivilege.getName());
    final Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
    logger.debug("A write privilege is created successfully ..." + writePrivilege.getName());
    logger.debug("creating a List of admin privileges");
    List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
    logger.debug("A List of admin privileges is created ...\n" +
      "can read: "+ adminPrivileges.contains(readPrivilege) +
      ", can write: " + adminPrivileges.contains(writePrivilege));
    logger.debug("Create two initial roles ROLE_ADMIN (with READ_PRIVILEGE and WRITE_PRIVILEGE)," +
    "and ROLE_USER with READ_PRIVILEGE aka ReadOnly");
    Role roleUser = createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
    logger.debug("ROLE_USER is created ... " + roleUser.getName());
    Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
    logger.debug("Role admin created ... " + roleAdmin.getName());
    logger.debug("creating first apiUser with ROLE_ADMIN aka first api user that can create other users");
    ApiUser admin = createUserIfNotFound(API_USER_NAME, API_PASSWORD, "Alhaytham as an admin",
      "Elhassan as an admin", "admin@student.le.ac.uk", new ArrayList<Role>(Arrays.asList(roleAdmin)));
    logger.debug("Admin user created successfully ... "+ admin.getFirstName()+ " isEnabled=" + admin.isEnabled());
    ApiUser ate5 = createUserIfNotFound("ate5",API_PASSWORD,"Alhaytham as student",
      " Elhassan as studnet", "ate5@student.le.ac.uk", new ArrayList<>(Arrays.asList(roleUser)));
    logger.debug("A user with ROLE_USER is created successfully ... " + ate5.getFirstName() + " isEnabled="+ ate5.isEnabled());
    ApiUser cje8 = createUserIfNotFound("cje8", API_PASSWORD, "Corey",
      "Evans", "cje8@leicester.ac.uk", new ArrayList<Role>(Arrays.asList(roleAdmin)));
    logger.debug("Admin user created successfully ... " + cje8.getFirstName() + " isEnabled=" + admin.isEnabled());
  }
  /**
   * A method to create an api user with a given roles
   * @param userName the username of the api user aka login user name
   * @param password the password of the api user aka login password
   * @param firstName first name of the api user
   * @param lastName last name of the api user
   * @param email email address of the api user
   * @param roles the collection of roles the api user has
   * @return the newly created api user
   */
  @Transactional
  ApiUser createUserIfNotFound(String userName, String password, String firstName, String lastName,
                                       String email, ArrayList<Role> roles) {
    ApiUser apiUser = this.apiUsersRepository.findByUserName(userName);
    if (apiUser == null ) {
      String encodedPassword = this.bCryptPasswordEncoder.encode(password);
      apiUser = this.apiUsersRepository.save(new ApiUser(userName, encodedPassword,firstName, lastName, email,
        roles, true));
    }
    return apiUser;
  }
  /**
   * Create role even if not found in the Role repo
   * @param roleName the role name the invoker want
   * @param privileges privileges associated with newly created role
   * @return domain Role entity
   */
  @Transactional
  Role createRoleIfNotFound(String roleName, List<Privilege> privileges) {
    Role role = this.rolesRepository.findByName(roleName);
    if (role == null){
      role = this.rolesRepository.save(new Role(roleName, privileges));
    }
    return role;
  }
  /**
   * Create a Privilege with a given name (instance is also persisted in the database)
   * @param privilegeName the privilege name the invoker want
   * @return an instance of domain entity Privilege
   */
  @Transactional
  Privilege createPrivilegeIfNotFound(String privilegeName) {
    Privilege privilege = this.privilegesRepository.findByName(privilegeName);
    if(privilege == null){
      privilege = this.privilegesRepository.save(new Privilege(privilegeName));
    }
    return privilege;
  }
}
