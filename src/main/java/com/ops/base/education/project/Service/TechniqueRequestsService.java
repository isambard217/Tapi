package com.ops.base.education.project.Service;
import com.ops.base.education.project.Repository.ProjectsRepository;
import com.ops.base.education.project.Repository.TechniqueRequestsRepository;
import com.ops.base.education.project.Repository.TechniquesRepository;
import com.ops.base.education.project.domain.Project;
import com.ops.base.education.project.domain.TechniqueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TechniqueRequestsService {
  private final TechniquesRepository techniquesRepository;
  private final ProjectsRepository projectsRepository;
  private final TechniqueRequestsRepository techniqueRequestsRepository;
  @Autowired
  public TechniqueRequestsService(TechniquesRepository techniquesRepository,
                                  ProjectsRepository projectsRepository,
                                  TechniqueRequestsRepository techniqueRequestsRepository) {
    this.techniquesRepository = techniquesRepository;
    this.projectsRepository = projectsRepository;
    this.techniqueRequestsRepository = techniqueRequestsRepository;
  }
  public TechniqueRequest requestAnalysis(TechniqueRequest techniqueRequest, long projectId) throws Exception {
    Optional<Project> optionalProject = this.projectsRepository.findById(projectId);
    if(optionalProject.isPresent()){
      Project project = optionalProject.get();
      if(project.getTechniqueRequests() == null){
        project.setTechniqueRequests(new ArrayList<>());
      }
      TechniqueRequest savedTechRequest = this.techniqueRequestsRepository.save(techniqueRequest);
      project.getTechniqueRequests().add(savedTechRequest);
      double projectBudget = project.getBudget();
      project.setBudget(projectBudget - techniqueRequest.getTechnique().getCost());
      this.projectsRepository.save(project);
      return savedTechRequest;
    }
    else {
      throw new Exception("Sorry! .. Project with id: " + projectId + " is not found");
    }
  }
  public String deleteTechniqueRequest(long requestId){
    this.techniqueRequestsRepository.deleteById(requestId);
    return "Request Id :" + requestId + " deleted ...";
  }
}
