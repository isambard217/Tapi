package com.ops.base.education.project.dto;
import java.io.Serializable;
/**
 * @author alhaytham
 * At the moment we have two types of system user:
 *  - Admin Module Cordinator/ Lecturer (i.e. At the moment Dr C.J. Evans)
 *  - Students
 */
public class ApiUserToAddDto implements Serializable {
  private static final long serialVersionUID = 3966661617346711721L;
	private Long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
  public static long getSerialVersionUID() {
    return serialVersionUID;
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getUserName() {
    return userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
}
