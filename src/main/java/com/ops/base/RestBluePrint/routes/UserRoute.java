package com.ops.base.RestBluePrint.routes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserRoute {
	
	@GetMapping
	public String getUsers() {
		return "0 users found";
	}
	
	@PostMapping("/addUsers")
	public String addUsers() {
		return "users added";
	}
}
