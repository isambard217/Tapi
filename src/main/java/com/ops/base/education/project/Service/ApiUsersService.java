package com.ops.base.education.project.Service;
import com.ops.base.education.project.Repository.EventsRepository;
import com.ops.base.education.project.Repository.RolesRepository;
import com.ops.base.education.project.domain.Event;
import com.ops.base.education.project.domain.ApiUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.ops.base.education.project.Repository.ApiUsersRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import static com.ops.base.education.project.security.PublicSecurityConstants.API_PASSWORD;
/**
 * @author alhaytham
 * Service layer component should not depend on each other
 * they should only depend on the lower layer i.e. Repositoires
 */
@Component
public class ApiUsersService {
	private final ApiUsersRepository apiUsersRepository;
	private final RolesRepository rolesRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final EventsRepository eventsRepository;
	private final Logger logger;
	@Autowired
	public ApiUsersService(ApiUsersRepository apiUsersRepository,
												 BCryptPasswordEncoder bCryptPasswordEncoder,
												 EventsRepository eventsRepository,
												 RolesRepository rolesRepository) {
		this.apiUsersRepository = apiUsersRepository;
		this.rolesRepository = rolesRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.eventsRepository = eventsRepository;
		this.logger = LoggerFactory.getLogger(this.getClass());
	}
	public ApiUser add(ApiUser apiUser) {
		logger.debug("Creating new user...");
		apiUser.setPassword(bCryptPasswordEncoder.encode(apiUser.getPassword()));
		ApiUser createdApiUser =  apiUsersRepository.save(apiUser);
		this.eventsRepository.save(new Event("User created",
			createdApiUser, new Date(System.currentTimeMillis()).getTime(), false));
		logger.debug("User created successfully ...");
		return createdApiUser;
	}
	public ArrayList<ApiUser> getApiUsers(){
		return (ArrayList<ApiUser>) apiUsersRepository.findAll();
	}
	public List<ApiUser> addApiUsers(Collection<ApiUser> apiUsers) {
		List<ApiUser> createdApiUsers = new ArrayList<>(apiUsers.toArray().length);
		apiUsers.forEach(apiUser -> {
			apiUser.setPassword(bCryptPasswordEncoder.encode(API_PASSWORD));
			apiUser.setProject(null);
			apiUser.setRoles(Arrays.asList(this.rolesRepository.findByName("ROLE_USER")));
			apiUser.setEnabled(true);
			ApiUser createdApiUser = this.apiUsersRepository.save(apiUser);
			this.eventsRepository.save(new Event("User Created",createdApiUser ,
				new Date(System.currentTimeMillis()).getTime(), false));
			createdApiUsers.add(createdApiUser);
		});
		return createdApiUsers;
	}
	public ApiUser getApiUserByUserName(String userName) {
		return this.apiUsersRepository.findByUserName(userName);
	}
	public ApiUser getApiUserById(Long apiUserId) {
		return this.apiUsersRepository.findById(apiUserId).get();
	}
}
