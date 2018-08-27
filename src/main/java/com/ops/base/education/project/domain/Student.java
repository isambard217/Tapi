package com.ops.base.education.project.domain;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student extends AuditEntity{
	
	@Id
	@GeneratedValue
	private Long Id;
	private String firstName;
	private String lastName;
	@OneToOne
  private Project project;

	public Student(){super();}

	public Student(String firstName, String surname) {
		super();
		this.firstName = firstName;
		this.lastName = surname;
	}

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }
}
