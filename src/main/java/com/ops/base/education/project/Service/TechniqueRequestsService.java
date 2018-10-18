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
  public TechniqueRequest requestAnalysis(TechniqueRequest techniqueRequest){
    return this.techniqueRequestsRepository.save(techniqueRequest);
  }
  public String deleteTechniqueRequest(long requestId){
    this.techniqueRequestsRepository.deleteById(requestId);
    return "Request Id :" + requestId + " deleted ...";
  }
}
