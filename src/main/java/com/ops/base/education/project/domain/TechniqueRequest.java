package com.ops.base.education.project.domain;
import javax.persistence.*;
import java.io.Serializable;
@Entity
public class TechniqueRequest implements Serializable {
  @Id
  @GeneratedValue
  private  Long id;
  @OneToOne
  private Sample sample;
  @OneToOne
  private Specie specie;
  private String resultFileName;
  @OneToOne
  private Technique technique;
  @ManyToOne
  private Project project;
  private long time;
  private String specieName;
  public TechniqueRequest(){}
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public Sample getSample() {
    return sample;
  }
  public void setSample(Sample sample) {
    this.sample = sample;
  }
  public Specie getSpecie() {
    return specie;
  }
  public void setSpecie(Specie specie) {
    this.specie = specie;
  }
  public String getResultFileName() {
    return resultFileName;
  }
  public void setResultFileName(String resultFileName) {
    this.resultFileName = resultFileName;
  }
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
  public Project getProject() {
    return project;
  }
  public void setProject(Project project) {
    this.project = project;
  }
  public String getSpecieName() {
    return specieName;
  }
  public void setSpecieName(String specieName) {
    this.specieName = specieName;
  }
}
