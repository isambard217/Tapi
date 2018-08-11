package com.ops.base.EducationProject;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ops.base.EducationProject.routes.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EducationalApplicationTests {
	@Autowired
	private Users users;
	@Autowired
	Techniques techniques;
	@Test
	public void contextLoads() throws  Exception{
	    assertThat(users).isNotNull();
	    assertThat(techniques).isNotNull();
    }

}
