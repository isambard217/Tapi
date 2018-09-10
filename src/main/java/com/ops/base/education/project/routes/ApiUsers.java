package com.ops.base.education.project.routes;
import java.util.ArrayList;
import java.util.List;
import com.ops.base.education.project.domain.ApiUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ops.base.education.project.Service.ApiUsersService;
@RestController
@RequestMapping("api/apiUsers")
public class ApiUsers {
	private final ApiUsersService apiUsersService;
	@Autowired
	public ApiUsers(ApiUsersService apiUsersService) {
		this.apiUsersService = apiUsersService;
	}
	@GetMapping
	public ArrayList<ApiUser> getStudents() {
		return apiUsersService.getStudnets();
	}
	@PostMapping
	public List<ApiUser> addStudents(@RequestBody ArrayList<ApiUser> apiUsers) {
		return this.apiUsersService.saveAll(apiUsers);
	}
}
