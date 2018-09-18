package com.ops.base.education.project.api;
import com.ops.base.education.project.domain.Template;
import com.ops.base.education.project.Repository.TemplatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("api/templates")
public class Templates {
  private TemplatesRepository templatesRepository;
  @Autowired
  public Templates(TemplatesRepository templatesRepository){
    this.templatesRepository = templatesRepository;
  }
  @PostMapping
  ArrayList<Template> addTemplates(@RequestBody List<Template> templates){
    return (ArrayList<Template>)this.templatesRepository.saveAll(templates);
  }
  @GetMapping
  List<Template> getTemplates(@RequestHeader String auth){
    return (ArrayList<Template>) this.templatesRepository.findAll();
  }
  @PutMapping
  List<Template> updateTemplates(@RequestBody List<Template> templates) {
    return (ArrayList<Template>) this.templatesRepository.saveAll(templates);
  }
  @DeleteMapping
  String deleteTemplate(@RequestParam Long id) {
    this.templatesRepository.deleteById(id);
    return "Template deleted successfully";
  }
}
