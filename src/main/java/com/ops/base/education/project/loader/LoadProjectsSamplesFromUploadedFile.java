package com.ops.base.education.project.loader;
import com.google.common.collect.Lists;
import com.ops.base.education.project.Repository.ApiUsersRepository;
import com.ops.base.education.project.Repository.ProjectsRepository;
import com.ops.base.education.project.Repository.SamplesRepository;
import com.ops.base.education.project.Service.storage.StorageProperties;
import com.ops.base.education.project.domain.ApiUser;
import com.ops.base.education.project.domain.Project;
import com.ops.base.education.project.domain.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class LoadProjectsSamplesFromUploadedFile implements ApplicationListener<ContextRefreshedEvent> {
  private final ApiUsersRepository apiUsersRepository;
  private final SamplesRepository samplesRepository;
  private final ProjectsRepository projectsRepository;
  private final Path storageLocation;
  private final Logger logger;
  private boolean alreadySetup;
  @Autowired
  public LoadProjectsSamplesFromUploadedFile(ApiUsersRepository apiUsersRepository,
                                             SamplesRepository samplesRepository,
                                             ProjectsRepository projectsRepository,
                                             StorageProperties storageProperties) {
    this.apiUsersRepository = apiUsersRepository;
    this.samplesRepository = samplesRepository;
    this.projectsRepository = projectsRepository;
    this.storageLocation = Paths.get(storageProperties.getUploadDir())
      .toAbsolutePath()
      .normalize();
    this.logger = LoggerFactory.getLogger(this.getClass());
    this.alreadySetup = true;
  }
  /**
   * Handle an application event.
   *
   * @param event the event to respond to
   */
  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {
    if(alreadySetup){
      logger.debug("Already all samples are loaded to their correspondent project ... \n");
      return;
    }
    logger.debug("==========================================================================\n");
    ArrayList<ApiUser> apiUsers = Lists.newArrayList(this.apiUsersRepository.findAll());
    logger.debug("api users count in trace database is : " + apiUsers.toArray().length + " users..!");
    apiUsers.forEach(apiUser -> {
      if(apiUser.getProject() != null) {
        Project project = apiUser.getProject();
        int index = 1;
        Path pathToProjectFile = this.storageLocation.resolve(apiUser.getProject().getFileName()).normalize();
        try (BufferedReader bufferedReader = Files.newBufferedReader(pathToProjectFile, StandardCharsets.UTF_8)){
          // read the first 2 lines
          bufferedReader.readLine();
          bufferedReader.readLine();
          List<Sample> samples = new ArrayList<>();
          String line = bufferedReader.readLine();
          while (line != null){
            String[] attributes = line.split(",");
            String sampleName = attributes[0].trim();
            Optional<Sample> optionalSample = this.samplesRepository.findByName(sampleName);
            if (optionalSample.isPresent()){
              logger.debug("Sample with name : " + sampleName + " already in database ...!");
            }
            else{
              this.samplesRepository.save(new Sample(sampleName, index, apiUser.getProject()));
            }
            index++;
            line = bufferedReader.readLine();
          }
        }
        catch (Exception e){
          logger.error(" Couldn't load file: " +
            apiUser.getProject().getFileName() + " for reason: " +  e.getMessage());
        }
      }
    });
    this.setAlreadySetup(true);
    logger.debug("All samples are loaded successfully = " + isAlreadySetup() + " ...  \n");
  }
  public boolean isAlreadySetup() {
    return alreadySetup;
  }
  public void setAlreadySetup(boolean alreadySetup) {
    this.alreadySetup = alreadySetup;
  }
}
