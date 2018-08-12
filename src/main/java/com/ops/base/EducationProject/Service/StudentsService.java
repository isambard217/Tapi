package com.ops.base.EducationProject.Service;

import org.springframework.stereotype.Component;

import com.ops.base.EducationProject.Domains.Student;
import com.ops.base.EducationProject.Repository.StudentsRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class StudentsService {
	
	private final StudentsRepository studentsRepository;

	@Autowired
	public StudentsService(StudentsRepository studentsRepository) {
		this.studentsRepository = studentsRepository;
	}

	public void save(Student u) {
		studentsRepository.save(u);
	}
	
	public ArrayList<Student> getStudnets(){
		return (ArrayList<Student>) studentsRepository.findAll();
	}

	public ArrayList<Student> saveAll(ArrayList<Student> students) {
		studentsRepository.saveAll(students);
		return students;
	}

}
