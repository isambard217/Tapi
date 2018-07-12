package com.ops.base.RestBluePrint.routes;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ops.base.RestBluePrint.Domains.User;
import com.ops.base.RestBluePrint.Service.UserService;

@CrossOrigin(origins = "*")
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
