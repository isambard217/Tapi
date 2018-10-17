package com.ops.base.education.project.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.Serializable;

@Entity
public class Technique implements Serializable {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  @Lob
  private String briefDescription;
  private double cost;
  private int techniqueNumber;
  public Technique(){}
  public Technique(String name, String briefDescription){
    setName(name);
    setBriefDescription(briefDescription);
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
  public String getBriefDescription() {
    return briefDescription;
  }
  public void setBriefDescription(String briefDescription) {
    this.briefDescription = briefDescription;
  }
  public double getCost() {
    return cost;
  }
  public void setCost(double cost) {
    this.cost = cost;
  }
  public int getTechniqueNumber() {
    return techniqueNumber;
  }
  public void setTechniqueNumber(int techniqueNumber) {
    this.techniqueNumber = techniqueNumber;
  }
}
