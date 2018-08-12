package com.ops.base.EducationProject.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ops.base.EducationProject.Domains.Student;

@Repository
public interface StudentsRepository extends CrudRepository <Student, Long> {
}
