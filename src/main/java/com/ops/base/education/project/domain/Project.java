package com.ops.base.education.project.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "projects")
public class Project extends AuditEntity implements Achievable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private  Long id;

  @NotNull
  @Size(max = 50)
  private String sampleDataFileName;

  @ManyToOne
  @JoinColumn(name = "template_id") // foreign key column
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Template template;

  public Project(String sampleDataFileName, Template template) {
    super();
    setSampleDataFileName(sampleDataFileName);
    this.template = template;
  }
  public String getSampleDataFileName() {
    return sampleDataFileName;
  }

  public void setSampleDataFileName(String sampleDataFileName) {
    this.sampleDataFileName = sampleDataFileName;
  }

  public Long getId() {
    return id;
  }

  public Project setId(Long id) {
    this.id = id;
    return this;
  }

  public Template getTemplate() {
    return template;
  }

  public Project setTemplate(Template template) {
    this.template = template;
    return this;
  }
  public Project(){super();}

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
