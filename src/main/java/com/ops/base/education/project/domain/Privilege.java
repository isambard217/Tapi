package com.ops.base.education.project.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
@Entity
public class Privilege implements Serializable {
  private static final long serialVersionUID = -7738221954087576995L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  @ManyToMany(mappedBy = "privileges")
  private Collection<Role> roles;
  public Privilege(){
  }
  public Privilege(String name) {
    setName(name);
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  @JsonIgnore
  public Collection<Role> getRoles() {
    return roles;
  }
  public void setRoles(Collection<Role> roles) {
    this.roles = roles;
  }
}
