package com.ops.base.education.project.api;
import java.util.ArrayList;
import java.util.List;
import com.ops.base.education.project.domain.ApiUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ops.base.education.project.Service.ApiUsersService;
@RestController
@RequestMapping("/api/api_users")
public class ApiUsers {
	private final ApiUsersService apiUsersService;
	@Autowired
	public ApiUsers(ApiUsersService apiUsersService) {
		this.apiUsersService = apiUsersService;
	}
	@GetMapping
	public @ResponseBody List<ApiUser> list(@RequestHeader String auth) {
		return apiUsersService.getApiUsers();
	}
	@PostMapping
	public @ResponseBody List<ApiUser> add(@RequestBody ArrayList<ApiUser> apiUsers, @RequestHeader String auth) {
		return this.apiUsersService.addApiUsers(apiUsers);
	}
}
