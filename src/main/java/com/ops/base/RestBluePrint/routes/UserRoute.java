package com.ops.base.RestBluePrint.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ops.base.RestBluePrint.Domains.User;
import com.ops.base.RestBluePrint.Service.UserService;

@RestController
@RequestMapping("api/user")
public class UserRoute {
	
	@Autowired
	UserService us;
	
	@GetMapping
	public Iterable<User> getUsers() {
		return us.getUsers();
	}
	
	@PostMapping
	public String addUsers() {
		
		us.save(new User("bob", "wills"));
		
		return "users added";
	}
}
