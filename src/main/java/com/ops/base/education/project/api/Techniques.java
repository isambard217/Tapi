package com.ops.base.education.project.api;
import com.ops.base.education.project.Repository.TechniquesRepository;
import com.ops.base.education.project.Service.TechniquesService;
import com.ops.base.education.project.domain.Technique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@RequestMapping("/api/techniques")
@RestController
public class Techniques {
	private TechniquesRepository techniquesRepository;
	private final TechniquesService techniquesService;
  @Autowired
  public Techniques(TechniquesRepository techniquesRepository, TechniquesService techniquesService){
    this.techniquesRepository = techniquesRepository;
    this.techniquesService = techniquesService;
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
  public Technique update(@RequestBody Technique technique, @RequestHeader String auth){
    try {
      this.techniquesService.updateTechnique(technique);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return technique;
  }
  @DeleteMapping
  public String remove(@RequestParam Long id, @RequestHeader String auth){
    this.techniquesRepository.deleteById(id);
    return "technique deleted successfully";
  }
}
