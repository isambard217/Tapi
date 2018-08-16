package com.ops.base.education.project.routes;

import com.ops.base.education.project.Repository.ProjectRepository;
import com.ops.base.education.project.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/projects")
public class Projects {

  private ProjectRepository projectRepository;

  @Autowired
  public Projects(ProjectRepository projectRepository){
    this.projectRepository = projectRepository;
  }

  @GetMapping
  public List<Project> getProjects(){
    return (ArrayList<Project>) this.projectRepository.findAll();
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
