package com.ops.base.education.project.Service;

import com.ops.base.education.project.domain.Student;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ops.base.education.project.Repository.StudentsRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class StudentsService {
	
	private final StudentsRepository studentsRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public StudentsService(StudentsRepository studentsRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.studentsRepository = studentsRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public void save(Student student) {
	  student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
		studentsRepository.save(student);
	}
	
	public ArrayList<Student> getStudnets(){
		return (ArrayList<Student>) studentsRepository.findAll();
	}

	public ArrayList<Student> saveAll(ArrayList<Student> students) {
	  for (Student student: students){
	    student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
    }
		studentsRepository.saveAll(students);
		return students;
	}

}
