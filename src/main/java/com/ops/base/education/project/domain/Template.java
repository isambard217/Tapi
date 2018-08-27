package com.ops.base.education.project.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "templates")
public class Template extends AuditEntity implements Achievable {
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

  public Template(){super();}

  public Template(String name, String description){
    super();
    this.name = name;
    this.description = description;
  }
  public void setId(Long id){ this.id = id;}
  public String getName() {
    return this.name;
  }

  public String getDescription(){ return this.description; }

  /**
   * The dream is to make this method get final project result for the lecturer
   *
   * @return The ratio of student project achievement to ideal achievement to solve project puzzle
   */
  @Override
  public double getAchievingPercent() {
    return 0;
  }
}
