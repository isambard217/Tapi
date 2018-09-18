package com.ops.base.education.project.Service;
import com.ops.base.education.project.domain.ApiUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.ops.base.education.project.Repository.ApiUsersRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
@Component
public class ApiUsersService {
	private final ApiUsersRepository apiUsersRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	public ApiUsersService(ApiUsersRepository apiUsersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.apiUsersRepository = apiUsersRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	public ApiUser add(ApiUser apiUser) {
	  apiUser.setPassword(bCryptPasswordEncoder.encode(apiUser.getPassword()));
		return apiUsersRepository.save(apiUser);
	}
	public ArrayList<ApiUser> getApiUsers(){
		return (ArrayList<ApiUser>) apiUsersRepository.findAll();
	}
	public ArrayList<ApiUser> addApiUsers(ArrayList<ApiUser> apiUsers) {
	  for (ApiUser apiUser : apiUsers){
	    apiUser.setPassword(bCryptPasswordEncoder.encode(apiUser.getPassword()));
    }
		apiUsersRepository.saveAll(apiUsers);
		return apiUsers;
	}
}
