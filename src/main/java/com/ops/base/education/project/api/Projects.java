package com.ops.base.education.project.api;

import com.ops.base.education.project.Repository.ProjectRepository;
import com.ops.base.education.project.Service.ProjectsService;
import com.ops.base.education.project.domain.Achievable;
import com.ops.base.education.project.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/projects")
public class Projects {

  private ProjectRepository projectRepository;
  private ProjectsService projectsService;

  @Autowired
  public Projects(ProjectRepository projectRepository, ProjectsService projectsService){
    this.projectRepository = projectRepository;
    this.projectsService = projectsService;
  }

  @GetMapping
  public List<Achievable> getProjects(@RequestParam Long studentId){
    return this.projectsService.getProjects(studentId);
  }

  @PostMapping
  public List<Project> addProjects(@RequestBody List<Project> projects){
    return (ArrayList<Project>) this.projectRepository.saveAll(projects);
  }

  @PutMapping
  public List<Project> updateProjects(@RequestBody List<Project> projects) {
    return (ArrayList<Project>) this.projectRepository.saveAll(projects);
  }

  @DeleteMapping
  String deleteProject(@RequestParam Long id) {
    this.projectRepository.deleteById(id);
    return "Project deleted successfully";
  }
}
