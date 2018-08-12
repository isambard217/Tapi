package com.ops.base.EducationProject.Domains;

import javax.persistence.*;

@Entity
@Table
public class Student {
	
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
	public Student() {
		
	}
	public Student(String firstname, String surname) {
		
		this.firstname = firstname;
		this.lastname = surname;
		
	}
	
}
