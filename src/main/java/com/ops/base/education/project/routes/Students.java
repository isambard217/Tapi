package com.ops.base.education.project.routes;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ops.base.education.project.domain.Student;
import com.ops.base.education.project.Service.StudentsService;

@RestController
@RequestMapping("api/students")
public class Students {
	
	@Autowired
	StudentsService us;
	
	@GetMapping
	public ArrayList<Student> getStudents() {
		return us.getStudnets();
	}
	
	@PostMapping
	public String addStudents() {
		
		us.save(new Student("bob", "wills"));
		
		return "Students added";
	}
}
