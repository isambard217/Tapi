package com.ops.base.education.project.routes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ops.base.education.project.domain.Student;
import com.ops.base.education.project.Service.StudentsService;

@RestController
@RequestMapping("api/students")
public class Students {
	
	@Autowired
	StudentsService studentsService;
	
	@GetMapping
	public ArrayList<Student> getStudents() {
		return studentsService.getStudnets();
	}
	
	@PostMapping
	public List<Student> addStudents(@RequestBody ArrayList<Student> students) {
		return this.studentsService.saveAll(students);
	}
}
