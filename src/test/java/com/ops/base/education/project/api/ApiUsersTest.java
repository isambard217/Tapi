package com.ops.base.education.project.api;
import com.ops.base.education.project.Repository.ApiUsersRepository;
import com.ops.base.education.project.Service.ApiUsersService;
import com.ops.base.education.project.domain.ApiUser;
import com.ops.base.education.project.security.ApiUserCredentials;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import static com.ops.base.education.project.security.PublicSecurityConstants.API_PASSWORD;
import static com.ops.base.education.project.security.PublicSecurityConstants.API_USER_NAME;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiUsersTest {
  private final static Logger LOGGER = LoggerFactory.getLogger(ApiUsersTest.class);
  @LocalServerPort
  private int port;
  @Autowired
  private TestRestTemplate testRestTemplate;
  @Autowired
  private ApiUsersService apiUsersService;
  private String apiUsersUrl;
  @Autowired
  private ApiUsersRepository apiUsersRepository;
  private List<ApiUser> apiUsers = new ArrayList<>(100);
  @Before
  public void setUp() throws Exception {
    LOGGER.debug("Setting up security and other application context to test route api_users ...");
    apiUsers = this.apiUsersService.getApiUsers();
    ApiUserCredentials apiUserCredentials = new ApiUserCredentials(API_USER_NAME, API_PASSWORD);
    ApiUser apiUser = this.apiUsersRepository.findByUserName(apiUserCredentials.getUserName());
    String jwtToken = Jwts.builder()
      .setSubject(apiUser.getUserName())
      .setExpiration(new Date(System.currentTimeMillis() + 3600000))
      .signWith(SignatureAlgorithm.HS512, "SecretKeyToGenJWTs".getBytes(StandardCharsets.UTF_8))
      .compact();
    this.testRestTemplate.getRestTemplate().setInterceptors(
      Collections.singletonList(((request, body, execution) -> {
        request.getHeaders().add("auth", "ApiUser " + jwtToken);
        return execution.execute(request, body);
      }))
    );
    apiUsersUrl = "http://localhost:" + port + "/api/api_users";
    LOGGER.debug("Security and application context setup finish for testing route api_users ...");
    }
  @Test
  @Description("Should getApiUsers all ApiUser Entities persist in the system ...")
  public void getApiUsers() throws NullPointerException {
    ResponseEntity<ApiUser[]> responseEntity = this.testRestTemplate.getForEntity(apiUsersUrl, ApiUser[].class);
    ApiUser[] foundApiUsers = responseEntity.getBody();
    assertEquals(apiUsers.toArray().length, foundApiUsers.length);
  }
  @Test
  public void addStudents() {
  }
}
