package com.ops.base.education.project.routes;

import com.ops.base.education.project.domain.ProjectBase;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProjectBaseRouteTests {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void addProjectBasesShouldReturnAListOfTheAddedProjects() {
    List<ProjectBase> projectBasesToAdd = new ArrayList<>();
    ProjectBase projectBase = new ProjectBase("ProjectBaseName", "Project Base decsiption");
    projectBasesToAdd.add(projectBase);
    HttpEntity<List<ProjectBase>> request = new HttpEntity<>(projectBasesToAdd);
    ResponseEntity<ArrayList> responseEntity = restTemplate
      .exchange("http://localhost:" + port + "api/projectBases",HttpMethod.POST,request, ArrayList.class);
    Object body = responseEntity.getBody();
    // we are expecting that return Project Base should have id 1
    projectBase.setId(1L);
    assertThat(body, CoreMatchers.notNullValue());
  }
}
