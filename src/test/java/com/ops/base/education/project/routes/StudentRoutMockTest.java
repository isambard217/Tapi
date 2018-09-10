//package com.ops.base.education.project.routes;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//
//import java.util.ArrayList;
//
//import com.ops.base.education.project.Service.ApiUsersService;
//import com.ops.base.education.project.domain.ApiUser;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(ApiUsers.class)
//public class StudentRoutMockTest {
//
//  @Autowired
//	private MockMvc mockMvc;
//
//  public StudentRoutMockTest(){}
//
//	@MockBean
//	private ApiUsersService studentsService;
//
//	@Test
//	public void getStudentsShouldReturnAllUserRecordsStored() throws Exception {
//		// given
//		ApiUser alhaytham = new ApiUser("Alhaytham", "Elhassan");
//		ApiUser isambard = new ApiUser("Isambard", "Chey");
//		ArrayList<ApiUser> students = new ArrayList<>();
//		students.add(alhaytham);
//		students.add(isambard);
//		when(studentsService.getStudnets()).thenReturn(students);
//		// when
//		this.mockMvc.perform(get("/api/students")).andDo(print())
//      // then
//      .andExpect(status().isOk())
//		  .andExpect(content().string(containsString(alhaytham.getFirstname())))
//		  .andExpect(content().string(containsString(isambard.getFirstname())));
//	}
//}
