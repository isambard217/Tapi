package com.ops.base.education.project.api;
import com.ops.base.education.project.Service.TechniqueRequestsService;
import com.ops.base.education.project.domain.TechniqueRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/techniqueRequests")
public class TechniqueRequests {
  private TechniqueRequestsService techniqueRequestsService;
  private final Logger logger;
  @Autowired
  public TechniqueRequests(TechniqueRequestsService techniqueRequestsService) {
    this.techniqueRequestsService = techniqueRequestsService;
    this.logger = LoggerFactory.getLogger(this.getClass());
  }
  @PostMapping
  public TechniqueRequest requestTechnique(@RequestHeader String auth,
                                           @RequestBody TechniqueRequest techniqueRequest,
                                           @RequestParam long projectId){
    try {
      return this.techniqueRequestsService.requestAnalysis(techniqueRequest, projectId);
    } catch (Exception e) {
      logger.error("Sorry request fail because: " + e.getMessage());
    }
    return techniqueRequest;
  }
  @DeleteMapping
  public String deleteTechniqueRequest(@RequestHeader String auth, @RequestParam long requestId){
    return this.techniqueRequestsService.deleteTechniqueRequest(requestId);
  }
}
