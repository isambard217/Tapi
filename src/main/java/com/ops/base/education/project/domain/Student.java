package com.ops.base.education.project.domain;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student extends AuditEntity{
	
	@Id
	@GeneratedValue
	private Long Id;
	
	private String firstname;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	private String lastname;

	public Student(){super();}

	public Student(String firstname, String surname) {
		super();
		this.firstname = firstname;
		this.lastname = surname;
		
	}
	
}
