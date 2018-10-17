package com.ops.base.education.project.Service.storage;
import org.springframework.web.multipart.MultipartFile;
public interface StorageService {
    String storeFileForUserId(MultipartFile file, long apiUserId) throws Exception;
}
