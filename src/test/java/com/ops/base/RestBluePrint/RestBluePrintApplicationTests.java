package com.ops.base.RestBluePrint;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.ops.base.RestBluePrint.routes.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestBluePrintApplicationTests {
	@Autowired
	private Users users;
	@Autowired
    Techniques techniquesRoute;
	@Test
	public void contextLoads() throws  Exception{
	    assertThat(users).isNotNull();
	    assertThat(techniquesRoute).isNotNull();
    }

}
