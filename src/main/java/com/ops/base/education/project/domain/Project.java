package com.ops.base.education.project.domain;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.io.Serializable;
/**
 * @author alhaytham
 * As this entity changing in its way to take its last release form
 * It came to mind  that each project should have a seperate budget
 * that make sence even for future verstions of this Entity of even the
 * Entier Application.
 */
@Entity
public class Project implements Achievable, Serializable {
  @Id
  @GeneratedValue
  private  Long id;
  private String fileName;
  @ManyToOne
  @JoinColumn(name = "template_id") // foreign key column
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Template template;
  private Long startTime;
  private double budget = 0.0;
  public Project(String fileName, Template template) {
    super();
    setFileName(fileName);
    this.template = template;
  }
  public Project(Template template, Long startTime, double budget) {
    setTemplate(template);
    setStartTime(startTime);
    setBudget(budget);
  }
  public String getFileName() {
    return fileName;
  }
  public void setFileName(String fileName) {
    this.fileName = fileName;
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
  public void setTemplate(Template template) {
    this.template = template;
  }
  public Project(){super();}
  /**
   * The dream is to make this method get final project result for the lecturer
   * @return The ratio of student project achievement to ideal achievement to solve project puzzle
   */
  @Override
  public double getAchievingPercent() {
    return 0;
  }
  public Long getStartTime() {
    return startTime;
  }
  public void setStartTime(Long startTime) {
    this.startTime = startTime;
  }
  public double getBudget() {
    return budget;
  }
  public void setBudget(double budget) {
    this.budget = budget;
  }
}
