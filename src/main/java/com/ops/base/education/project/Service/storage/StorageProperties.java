package com.ops.base.education.project.Service.storage;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import static com.ops.base.education.project.api.ApiConstalnts.UPLOAD_DIR;
@Component
@ConfigurationProperties("storage")
public class StorageProperties {
    /**
     * Folder location for storing files
     */
    private String location = UPLOAD_DIR;
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

}
