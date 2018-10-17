package com.ops.base.education.project.api;
import com.ops.base.education.project.Repository.ProjectsRepository;
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
  private ProjectsRepository projectsRepository;
  private ProjectsService projectsService;
  @Autowired
  public Projects(ProjectsRepository projectsRepository, ProjectsService projectsService){
    this.projectsRepository = projectsRepository;
    this.projectsService = projectsService;
  }
  @GetMapping
  public List<Achievable> getProjects(@RequestParam Long apiUserId, @RequestHeader String auth){
    return this.projectsService.getProjects(apiUserId);
  }
  @PostMapping
  public Project createProject(@RequestBody Long apiUserId, @RequestParam Long templateId, @RequestHeader String auth){
    return this.projectsService.selectProject(apiUserId, templateId);
  }
  @PutMapping
  public List<Project> updateProjects(@RequestBody List<Project> projects, String auth) {
    return (ArrayList<Project>) this.projectsRepository.saveAll(projects);
  }
  @DeleteMapping
  String deleteProject(@RequestParam Long id, String auth) {
    this.projectsRepository.deleteById(id);
    return "Project deleted successfully";
  }
}
