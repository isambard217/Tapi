package com.ops.base.education.project.Service;

import com.google.common.collect.Lists;
import com.ops.base.education.project.Repository.ProjectRepository;
import com.ops.base.education.project.Repository.StudentsRepository;
import com.ops.base.education.project.Repository.TemplatesRepository;
import com.ops.base.education.project.domain.Achievable;
import com.ops.base.education.project.domain.Project;
import com.ops.base.education.project.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectsService {
  private ProjectRepository projectRepository;
  private TemplatesRepository templatesRepository;
  private StudentsRepository studentsRepository;
  private static Logger logger = LoggerFactory.getLogger(ProjectsService.class);

  @Autowired
  public ProjectsService(ProjectRepository projectRepository,
                         TemplatesRepository templatesRepository,
                         StudentsRepository studentsRepository) {
    this.projectRepository = projectRepository;
    this.templatesRepository = templatesRepository;
    this.studentsRepository = studentsRepository;
  }

  public List<Achievable> getProjects(Long studentId){
    List<Achievable> projects = new ArrayList<>();
    Student student;
    Optional<Student> returnEntity = this.studentsRepository.findById(studentId);
    logger.debug("checking if student with id: " + studentId + " exists?");
    if(returnEntity.isPresent()) {
      student = returnEntity.get();
      logger.debug("getting project object from student" + student.getFirstName() + " " + student.getLastName() + "Entity");
      Project project = student.getProject();
      if (project != null) {
        projects.add(project);
      } else {
        projects = Lists.newArrayList(this.templatesRepository.findAll());
      }
    }
    return projects;
  }
}
