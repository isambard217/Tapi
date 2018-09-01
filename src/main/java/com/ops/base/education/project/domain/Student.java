package com.ops.base.education.project.domain;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Student implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String userName;
	private String password;
	private String role;
	@OneToOne
  private Project project;
	public Student(){super();}
	public Student(String firstName, String surname) {
		super();
		this.firstName = firstName;
		this.lastName = surname;
	}
	public Student(String userName, String password, String role){
	  setUserName(userName);
	  setPassword(password);
	  setRole(role);
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
  public Project getProject() {
    return project;
  }
  public String getRole() {
    return role;
  }
  public void setRole(String role) {
    this.role = role;
  }
  public void setProject(Project project) {
    this.project = project;
  }
}
