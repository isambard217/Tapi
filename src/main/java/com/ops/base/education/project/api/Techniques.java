package com.ops.base.education.project.api;

import com.ops.base.education.project.Repository.TechniquesRepository;
import com.ops.base.education.project.domain.Technique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/techniques")
@RestController
public class Techniques {
	private TechniquesRepository techniquesRepository;
  @Autowired
  public Techniques(TechniquesRepository techniquesRepository){
    this.techniquesRepository = techniquesRepository;
  }
	@GetMapping
	public List<Technique> list(@RequestHeader String auth) {
		return (ArrayList<Technique>)this.techniquesRepository.findAll();
	}
	@PostMapping
  public List<Technique> add(@RequestBody List<Technique> techniques, @RequestHeader String auth){
    return (ArrayList<Technique>) this.techniquesRepository.saveAll(techniques);
  }
  @PutMapping
  public List<Technique> update(@RequestBody List<Technique> techniques, @RequestHeader String auth){
    return (ArrayList<Technique>) this.techniquesRepository.saveAll(techniques);
  }
  @DeleteMapping
  public String remove(@RequestParam Long id, @RequestHeader String auth){
    this.techniquesRepository.deleteById(id);
    return "technique deleted successfully";
  }
}
