package com.ops.base.education.project.Service.storage;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author alhaytham
 * This component mapp the properties of the multipart file from used profile
 * that means each prfile should at least set the upload directory
 */
@Component
@ConfigurationProperties(prefix = "file")
public class StorageProperties {
    private String uploadDir;
  public String getUploadDir() {
    return uploadDir;
  }
  public void setUploadDir(String uploadDir) {
    this.uploadDir = uploadDir;
  }
}
