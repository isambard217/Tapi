package com.ops.base.education.project.Service;
import com.ops.base.education.project.Repository.TechniquesRepository;
import com.ops.base.education.project.domain.Technique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TechniquesService {
  private final TechniquesRepository techniquesRepository;
  @Autowired
  public TechniquesService(TechniquesRepository techniquesRepository) {
    this.techniquesRepository = techniquesRepository;
  }
  public Technique updateTechnique(Technique technique) throws Exception {
    //find technique to update from the repo
    Optional<Technique> optionalTechnique = this.techniquesRepository.findById(technique.getId());
    if (optionalTechnique.isPresent()){
      Technique techniqueToUpdate = optionalTechnique.get();
      techniqueToUpdate.setBriefDescription(technique.getBriefDescription());
      techniqueToUpdate.setName(technique.getName());
      techniqueToUpdate.setCost(technique.getCost());
      return this.techniquesRepository.save(techniqueToUpdate);
    }
    else {
      throw new Exception("There is no Technique with id: " + technique.getId());
    }
  }
}
