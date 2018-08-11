package com.ops.base.EducationProject.routes;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ops.base.EducationProject.Domains.User;
import com.ops.base.EducationProject.Service.UserService;

@RestController
@RequestMapping("api/user")
public class Users {
	
	@Autowired
	UserService us;
	
	@GetMapping
	public ArrayList<User> getUsers() {
		return us.getUsers();
	}
	
	@PostMapping
	public String addUsers() {
		
		us.save(new User("bob", "wills"));
		
		return "users added";
	}
}
