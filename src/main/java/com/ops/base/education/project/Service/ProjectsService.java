package com.ops.base.education.project.Service;
import com.google.common.collect.Lists;
import com.ops.base.education.project.Repository.ProjectRepository;
import com.ops.base.education.project.Repository.ApiUsersRepository;
import com.ops.base.education.project.Repository.TemplatesRepository;
import com.ops.base.education.project.domain.Achievable;
import com.ops.base.education.project.domain.ApiUser;
import com.ops.base.education.project.domain.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ProjectsService {
  private ProjectRepository projectRepository;
  private TemplatesRepository templatesRepository;
  private ApiUsersRepository apiUsersRepository;
  private static Logger logger = LoggerFactory.getLogger(ProjectsService.class);
  @Autowired
  public ProjectsService(ProjectRepository projectRepository,
                         TemplatesRepository templatesRepository,
                         ApiUsersRepository apiUsersRepository) {
    this.projectRepository = projectRepository;
    this.templatesRepository = templatesRepository;
    this.apiUsersRepository = apiUsersRepository;
  }
  public List<Achievable> getProjects(Long studentId){
    List<Achievable> projects = new ArrayList<>();
    ApiUser apiUser;
    Optional<ApiUser> returnEntity = this.apiUsersRepository.findById(studentId);
    logger.debug("checking if apiUser with id: " + studentId + " exists?");
    if(returnEntity.isPresent()) {
      apiUser = returnEntity.get();
      logger.debug("getting project object from apiUser" + apiUser.getFirstName() + " " + apiUser.getLastName() + "Entity");
      Project project = apiUser.getProject();
      if (project != null) {
        projects.add(project);
      } else {
        projects = Lists.newArrayList(this.templatesRepository.findAll());
      }
    }
    return projects;
  }
}
