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

  public List<Achievable> getProjects(Long stuentId){
    List<Achievable> projects = new ArrayList<>();
    Student student;
    logger.debug("checking if student with id: " + stuentId + " exists?");
    if(this.studentsRepository.findById(stuentId).isPresent()) {
      student = this.studentsRepository.findById(stuentId).get();
      logger.debug("getting project object from student" + student.getFirstName() + " " + student.getLastName());
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
