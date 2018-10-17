package com.ops.base.education.project.api;
import com.ops.base.education.project.Service.storage.StorageService;
import com.ops.base.education.project.dto.UploadFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@RestController
@RequestMapping("/api/projectsFiles")
public class ProjectsFiles {
  private final StorageService storageService;
  private final Logger logger;
  @Autowired
  public ProjectsFiles(StorageService storageService) {
    this.storageService = storageService;
    this.logger = LoggerFactory.getLogger(this.getClass());
  }
  @PostMapping
  @ResponseBody
  public UploadFileResponse uploadFileForUserId(@RequestParam("file") MultipartFile file, @RequestHeader String auth,
                                                @RequestHeader long apiUserId) {
    String fileName = "fileNotSet";
    try {
      fileName = storageService.storeFileForUserId(file,apiUserId);
    } catch (Exception e) {
      logger.error("Sorry we couldn't store file : " + fileName + e.getMessage());
    }
    String fileDownLoadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
      .path("/downLoadFile/")
      .path(fileName)
      .toUriString();
    return new UploadFileResponse(fileName, fileDownLoadUri, file.getContentType(), file.getSize());
  }
}
