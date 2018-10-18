package com.ops.base.education.project.api;
import com.ops.base.education.project.Repository.SamplesRepository;
import com.ops.base.education.project.domain.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("api/samples")
public class Samples {
  private final SamplesRepository samplesRepository;
  @Autowired
  public Samples(SamplesRepository samplesRepository) {
    this.samplesRepository = samplesRepository;
  }
  @GetMapping
  @ResponseBody
  public List<Sample> get(@RequestHeader String auth, @RequestHeader Long projectId){
    Iterable<Sample> samples = this.samplesRepository.findAll();
    List<Sample> samplesToRet = new ArrayList<>();
    samples.forEach(sample -> {
      if(sample.getProject().getId() == projectId.longValue())
        samplesToRet.add(sample);
    });
    return samplesToRet;
  }
}
