package com.ops.base.education.project.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "project_bases")
public class ProjectBase extends AuditEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Size(max = 100)
  @Column(unique = true)
  private String name;

  @NotNull
  @Lob
  private String description;

  public ProjectBase(){super();}

  public ProjectBase(String name, String description){
    super();
    this.name = name;
    this.description = description;
  }
  public void setId(Long id){ this.id = id;}
  public String getName() {
    return this.name;
  }

  public String getDescription(){ return this.description; }
}
