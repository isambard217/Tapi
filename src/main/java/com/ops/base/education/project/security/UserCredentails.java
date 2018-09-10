package com.ops.base.education.project.security;
import java.io.Serializable;
public class UserCredentails implements Serializable {
  private String userName;
  private String password;
  UserCredentails(){
  }
  UserCredentails(String userName, String password) {
    this.userName = userName;
    this.password = password;
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
}
