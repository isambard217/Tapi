package com.ops.base.EducationProject.routes;

import com.ops.base.EducationProject.Domains.ProjectBase;
import com.ops.base.EducationProject.Repository.ProjectBasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/projectBases")
public class ProjectBases {
  private ProjectBasesRepository projectBasesRepository;

  @Autowired
  public ProjectBases(ProjectBasesRepository projectBasesRepository){
    this.projectBasesRepository = projectBasesRepository;
  }

  @PostMapping
  ArrayList<ProjectBase> addProjectBases(@RequestBody List<ProjectBase> projectBases){
    return (ArrayList<ProjectBase>)this.projectBasesRepository.saveAll(projectBases);
  }

  @GetMapping
  List<ProjectBase> getProjectBases(){
    return (ArrayList<ProjectBase>) this.projectBasesRepository.findAll();
  }

  @PutMapping
  List<ProjectBase> updateProjectBases(@RequestBody List<ProjectBase> projectBases) {
    return (ArrayList<ProjectBase>) this.projectBasesRepository.saveAll(projectBases);
  }

  @DeleteMapping
  String deleteProjectBase(@PathVariable Long id) {
    this.projectBasesRepository.deleteById(id);
    return "ProjectBase deleted successfully";
  }
}
