package com.ops.base.education.project;

import static org.assertj.core.api.Assertions.assertThat;
import com.ops.base.education.project.routes.ApiUsers;
import com.ops.base.education.project.routes.Techniques;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EduProjectManagerTests {
	@Autowired
	private ApiUsers apiUsers;
	@Autowired
  Techniques techniques;
	@Test
	public void contextLoads() throws  Exception{
	    assertThat(apiUsers).isNotNull();
	    assertThat(techniques).isNotNull();
    }

}
