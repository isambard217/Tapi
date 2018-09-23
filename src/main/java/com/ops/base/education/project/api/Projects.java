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
  public List<Achievable> getProjects(@RequestParam Long studentId, @RequestHeader String auth){
    return this.projectsService.getProjects(studentId);
  }
  @PostMapping
  public String createProject(@RequestBody Long apiUserId, @RequestParam Long templateId, @RequestHeader String auth){
    return this.projectsService.selectProject(apiUserId, templateId);
  }
  @PutMapping
  public List<Project> updateProjects(@RequestBody List<Project> projects, String auth) {
    return (ArrayList<Project>) this.projectRepository.saveAll(projects);
  }
  @DeleteMapping
  String deleteProject(@RequestParam Long id, String auth) {
    this.projectRepository.deleteById(id);
    return "Project deleted successfully";
  }
}
