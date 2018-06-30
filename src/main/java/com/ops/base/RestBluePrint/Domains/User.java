package com.ops.base.RestBluePrint.Domains;

import javax.persistence.*;

@Entity
@Table
public class User {
	
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
	public User() {
		
	}
	public User(String firstname, String surname) {
		
		this.firstname = firstname;
		this.lastname = surname;
		
	}
	
}
