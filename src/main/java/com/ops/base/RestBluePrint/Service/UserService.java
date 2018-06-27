package com.ops.base.RestBluePrint.Service;

import org.springframework.stereotype.Component;

import com.ops.base.RestBluePrint.Domains.User;
import com.ops.base.RestBluePrint.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void save(User u) {
		userRepository.save(u);
	}

}
