package com.ops.base.education.project.Service.storage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.stream.Stream;
import com.ops.base.education.project.Repository.ApiUsersRepository;
import com.ops.base.education.project.Repository.ProjectsRepository;
import com.ops.base.education.project.Service.ProjectsService;
import com.ops.base.education.project.domain.ApiUser;
import com.ops.base.education.project.domain.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
@Service
public class FileSystemStorageService implements StorageService {
  private final Path fileStorageLocation;
  private final ApiUsersRepository apiUsersRepository;
  private final ProjectsRepository projectsRepository;
  private final Logger logger;
  @Autowired
  public FileSystemStorageService(StorageProperties storageProperties,
                                  ApiUsersRepository apiUsersRepository,
                                  ProjectsRepository projectsRepository) {
    this.fileStorageLocation = Paths.get(storageProperties.getUploadDir()).toAbsolutePath().normalize();
    this.apiUsersRepository = apiUsersRepository;
    this.logger = LoggerFactory.getLogger(this.getClass());
    this.projectsRepository = projectsRepository;
  }
  @Override
  @Transactional
  public String storeFileForUserId(MultipartFile file, long apiUserId) throws Exception {
    // Normalize file name
    String originalFileName = file.getOriginalFilename();
    if(originalFileName == null) {
      throw new Exception("file name is null ...!");
    }
    String fileName = StringUtils.cleanPath(originalFileName);
    // stop users from writing up the directory hierarchy as that may affect other running process
    // using that space
    if(fileName.contains("..")){
      throw new Exception("Sorry! FileName contains invalid path sequence " + fileName);
    }
    // Copy clean file to the target location (apply Replace existing file policy with the same name)
    Path pathToTargetLocation = this.fileStorageLocation.resolve(fileName);
    Files.copy(file.getInputStream(), pathToTargetLocation, StandardCopyOption.REPLACE_EXISTING);
    Optional<ApiUser> optionalApiUser = apiUsersRepository.findById(apiUserId);
    if(optionalApiUser.isPresent()) {
      ApiUser apiUser = optionalApiUser.get();
      Optional<Project> optionalProject = this.projectsRepository.findById(apiUser.getProject().getId());
      if (optionalProject.isPresent()) {
        Project project = optionalProject.get();
        project.setFileName(fileName);
        this.projectsRepository.save(project);
      }
      else {
        throw new Exception("User do not have project yet ... ");
      }
    }
    else {
      throw new Exception("Sorry ! User with id: " + apiUserId + " is not found ...!");
    }
    return fileName;
  }
}
