package com.ops.base.education.project.domain;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
/**
 * @author alhaytham
 * At the moment we have two types of system user:
 *  - Admin Module Cordinator/ Lecturer (i.e. At the moment Dr C.J. Evans)
 *  - Students
 */
@Entity
public class ApiUser implements Serializable {
  private static final long serialVersionUID = 3966661617346711721L;
  @Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String userName;
	private String password;
	@ManyToMany
  @JoinTable(
    name = "api_users_roles",
    joinColumns = @JoinColumn(
      name = "api_user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(
      name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;
	@OneToMany(targetEntity = Event.class, cascade = CascadeType.ALL, mappedBy = "apiUser")
  private Collection<Event> events;
	private boolean enabled;
	@OneToOne
  private Project project;
	@NotNull
  @Size(min = 5)
  @Column(unique = true)
	private String email;
	private int credit;
	public ApiUser(){super();}
	public ApiUser(String firstName, String surname) {
		super();
		this.firstName = firstName;
		this.lastName = surname;
	}
	public ApiUser(String userName, String password, Collection<Role> roles){
	  setUserName(userName);
	  setPassword(password);
	  setRoles(roles);
	  this.credit = 3000;
  }
  public ApiUser(String userName, String password, String firstName, String lastName, String email,
                 ArrayList<Role> roles, boolean enabled) {
    setUserName(userName);
    setPassword(password);
    setFirstName(firstName);
    setLastName(lastName);
    setEmail(email);
    setRoles(roles);
    setEnabled(enabled);
    setCredit(3000);
  }
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
  public Collection<Role> getRoles() {
    return roles;
  }
  public void setRoles(Collection<Role> roles) {
    this.roles = roles;
  }
  public Collection<Event> getEvents() {
    return events;
  }
  public void setEvents(Collection<Event> events) {
    this.events = events;
  }
  public boolean isEnabled() {
    return enabled;
  }
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
  public Project getProject() {
    return project;
  }
  public void setProject(Project project) {
    this.project = project;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public int getCredit() {
    return credit;
  }
  public void setCredit(int credit) {
    this.credit = credit;
  }
}
