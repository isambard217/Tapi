package com.ops.base.education.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Technique extends AuditEntity {
  @javax.persistence.Id
  @GeneratedValue
  private Long Id;
  private String name;
  @Lob
  private String briefDescription;

  public Technique(){}

  public Technique(String name, String briefDescription){
    setName(name);
    setBriefDescription(briefDescription);
  }
  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBriefDescription() {
    return briefDescription;
  }

  public void setBriefDescription(String briefDescription) {
    this.briefDescription = briefDescription;
  }
}
