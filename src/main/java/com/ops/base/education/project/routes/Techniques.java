package com.ops.base.education.project.routes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/Techniques")
@RestController
public class Techniques {
	@GetMapping
	public String getTech() {
		return "tech not availble";
	}
}
