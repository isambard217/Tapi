package com.ops.base.education.project.Service;
import com.google.common.collect.Lists;
import com.ops.base.education.project.Repository.ApiUsersRepository;
import com.ops.base.education.project.Repository.ProjectRepository;
import com.ops.base.education.project.Repository.TemplatesRepository;
import com.ops.base.education.project.domain.Achievable;
import com.ops.base.education.project.domain.ApiUser;
import com.ops.base.education.project.domain.Project;
import com.ops.base.education.project.domain.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static com.ops.base.education.project.Service.PublicServiceConstants.DEFAULT_PROJECT_BUDGET;
@Service
public class ProjectsService {
  private ApiUsersService apiUsersService;
  private TemplatesService templatesService;
  private ProjectRepository projectRepository;
  private TemplatesRepository templatesRepository;
  private ApiUsersRepository apiUsersRepository;
  private static Logger logger = LoggerFactory.getLogger(ProjectsService.class);
  @Autowired
  public ProjectsService(ProjectRepository projectRepository, TemplatesRepository templatesRepository,
                         ApiUsersRepository apiUsersRepository, ApiUsersService apiUsersService,
                         TemplatesService templatesService) {
    this.projectRepository = projectRepository;
    this.templatesRepository = templatesRepository;
    this.apiUsersRepository = apiUsersRepository;
    this.apiUsersService = apiUsersService;
    this.templatesService = templatesService;
  }
  public List<Achievable> getProjects(Long studentId){
    List<Achievable> projects = new ArrayList<>();
    ApiUser apiUser;
    Optional<ApiUser> returnEntity = this.apiUsersRepository.findById(studentId);
    logger.debug("checking if apiUser with id: " + studentId + " exists?");
    if(returnEntity.isPresent()) {
      apiUser = returnEntity.get();
      logger.debug("getting project object for apiUser: " + apiUser.getFirstName() + " " + apiUser.getLastName() + "Entity");
      Project project = apiUser.getProject();
      if (project != null) {
        projects.add(project);
      } else {
        projects = Lists.newArrayList(this.templatesRepository.findAll());
      }
    }
    return projects;
  }
  /**
   *  Assign a given student a project
   * @param apiUserId The api user id
   * @param templateId the title or templateId  that student is interested in
   * @return the templateId entity that student has selected
   */
  public String selectProject(Long apiUserId, Long templateId){
    //first load the user using ApiUsersService create method if you have to
    ApiUser apiUser = this.apiUsersService.getApiUserById(apiUserId);
    if (!apiUser.isEnabled())
      return "apiUser is disabled. Please ask Module admin to enable this account for you";
    if(apiUser.getProject() != null)
      return "Apologies, but you are already working on project: " + apiUser.getProject().getTemplate().getName();
    // second get the template using Template Service create service component if necessary
    Template template = this.templatesService.getTemplate(templateId);
    if (!template.getId().equals(templateId))
      return  "Requested template id=" + template.getId() +" doesn't match stored entity id";
    // third create a project from the given above entities
    Long startTime = new Date(System.currentTimeMillis()).getTime();
    Project project = new Project(template , startTime, DEFAULT_PROJECT_BUDGET);
    // persist the project
    this.projectRepository.save(project);
    // assign the project to the api user
    apiUser.setProject(project);
    // persist the apiUser i.e. put or patch operation
   this.apiUsersRepository.save(apiUser);
    return "apiUser assigned project successfully ... Project id: " + apiUser.getProject().getId();
  }
}
