package com.ops.base.education.project.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
@Entity
public class Sample implements Serializable {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private int sampleNumber;
  @ManyToOne
  private Project project;
  @OneToMany
  private Collection<Specie> species;
  private String fspeiceName;
  public Sample() {}
  public Sample(String name, int sampleNumber, Project project) {
    this.name = name;
    this.sampleNumber = sampleNumber;
    this.project = project;
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
  public int getSampleNumber() {
    return sampleNumber;
  }
  public void setSampleNumber(int sampleNumber) {
    this.sampleNumber = sampleNumber;
  }
  public Collection<Specie> getSpecies() {
    return species;
  }
  public void setSpecies(Collection<Specie> species) {
    this.species = species;
  }
  public Project getProject() {
    return project;
  }
  public void setProject(Project project) {
    this.project = project;
  }
}
