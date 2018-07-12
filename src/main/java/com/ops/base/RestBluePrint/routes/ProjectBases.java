package com.ops.base.RestBluePrint.routes;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/projectBase")
public class ProjectBases {

   @GetMapping
    public List<String> getProjectBase(){
        return  new ArrayList<>();
    }

    @PostMapping
    public List<String> addProjectBases(){
        List<String> projectBases = new ArrayList<String>();
        projectBases.add("base1");
        return projectBases;
    }
}
