package com.ops.base.education.project;

import static org.assertj.core.api.Assertions.assertThat;
import com.ops.base.education.project.routes.Students;
import com.ops.base.education.project.routes.Techniques;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EducationalApplicationTests {
	@Autowired
	private Students students;
	@Autowired
  Techniques techniques;
	@Test
	public void contextLoads() throws  Exception{
	    assertThat(students).isNotNull();
	    assertThat(techniques).isNotNull();
    }

}
