package com.ops.base.EducationProject.Domains;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class ProjectBase implements Serializable {
  @Setter(AccessLevel.PACKAGE)
  @Id @GeneratedValue private Long id;
  private String name;
  private String description;
  public ProjectBase(String name, String description){
    this.name = name;
    this.description = description;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Object getName() {
    return this.name;
  }
}
