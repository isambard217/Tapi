package com.ops.base.education.project.Repository;

import com.ops.base.education.project.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends CrudRepository<Student, Long> {
  public Student findByUserName(String username);
}
