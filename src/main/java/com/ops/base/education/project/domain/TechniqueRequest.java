package com.ops.base.education.project.domain;
import javax.persistence.*;
import java.io.Serializable;
@Entity
public class TechniqueRequest implements Serializable {
  @Id
  @GeneratedValue
  private  Long id;
  String sampleName;
  String poisonName;
  String resultFileName;
  @OneToOne
  private Technique technique;
  private long time;
  public TechniqueRequest(){}
  public Technique getTechnique() {
    return technique;
  }
  public void setTechnique(Technique technique) {
    this.technique = technique;
  }
  public long getTime() {
    return time;
  }
  public void setTime(long time) {
    this.time = time;
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getSampleName() {
    return sampleName;
  }
  public void setSampleName(String sampleName) {
    this.sampleName = sampleName;
  }
  public String getPoisonName() {
    return poisonName;
  }
  public void setPoisonName(String poisonName) {
    this.poisonName = poisonName;
  }
  public String getResultFileName() {
    return resultFileName;
  }
  public void setResultFileName(String resultFileName) {
    this.resultFileName = resultFileName;
  }
}
