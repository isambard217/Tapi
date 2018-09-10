package com.ops.base.education.project.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
@Entity
public class Role implements Serializable {
  private static final long serialVersionUID = 3503779351274594615L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String name;
  @ManyToMany(mappedBy = "roles")
  private Collection<ApiUser> apiUsers;
  @ManyToMany
  @JoinTable(
    name = "roles_privileges",
    joinColumns = @JoinColumn(
      name = "role_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(
      name = "privilege_id", referencedColumnName = "id"))
  private Collection<Privilege> privileges;
  public Role() {
  }
  public Role(String roleName) {
    this.name = roleName;
  }
  public Role(String roleName, List<Privilege> privileges) {
    setName(roleName);
    setPrivileges(privileges);
  }
  public static long getSerialVersionUID() {
    return serialVersionUID;
  }
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Collection<ApiUser> getApiUsers() {
    return apiUsers;
  }
  public void setApiUsers(Collection<ApiUser> apiUsers) {
    this.apiUsers = apiUsers;
  }
  public Collection<Privilege> getPrivileges() {
    return privileges;
  }
  public void setPrivileges(Collection<Privilege> privileges) {
    this.privileges = privileges;
  }
}
