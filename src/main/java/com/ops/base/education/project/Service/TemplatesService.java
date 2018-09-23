package com.ops.base.education.project.Service;
import com.ops.base.education.project.Repository.TemplatesRepository;
import com.ops.base.education.project.domain.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TemplatesService {
  private TemplatesRepository templatesRepository;
  @Autowired
  public TemplatesService(TemplatesRepository templatesRepository){
    this.templatesRepository = templatesRepository;
  }
  public Template getTemplate(Long templateId) {
    return this.templatesRepository.findById(templateId).get();
  }
}
