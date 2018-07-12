package com.ops.base.RestBluePrint.routes;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ops.base.RestBluePrint.Domains.User;
import com.ops.base.RestBluePrint.Service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(Users.class)
public class UserRoutMockTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void getUsersShouldReturnAllUserRecordsStored() throws Exception {
		// given
		User alhaytham = new User("Alhaytham", "Elhassan");
		User isambard = new User("Isambard", "Chey");
		ArrayList<User> users = new ArrayList<>();
		users.add(alhaytham);
		users.add(isambard);
		// when 
		when(userService.getUsers()).thenReturn(users);
		// then
		this.mockMvc.perform(get("/api/user")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString(alhaytham.getFirstname())))
		.andExpect(content().string(containsString(isambard.getFirstname())));
	}
}
