package com.ops.base.EducationProject.Service;

import org.springframework.stereotype.Component;

import com.ops.base.EducationProject.Domains.User;
import com.ops.base.EducationProject.Repository.UserRepository;

import antlr.collections.List;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void save(User u) {
		userRepository.save(u);
	}
	
	public ArrayList<User> getUsers(){
		return (ArrayList<User>) userRepository.findAll();
	}

	public ArrayList<User> saveAll(ArrayList<User> users) {
		userRepository.saveAll(users);
		return users;
	}

}
