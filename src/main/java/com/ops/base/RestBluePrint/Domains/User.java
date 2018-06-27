package com.ops.base.RestBluePrint.Domains;

import javax.persistence.*;

@Entity
@Table
public class User {
	
	@Id
	@GeneratedValue
	private Long Id;
	
	private String firstname;
	
	private String lastname;
	
	public User(String firstname, String surname) {
		
		this.firstname = firstname;
		this.lastname = surname;
		
	}
	
}
