package com.ops.base.education.project.Service.storage;
import com.ops.base.education.project.domain.Project;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;
public interface StorageService {
    void init();
    Project store(MultipartFile file, long apiUserId);
    Stream<Path> loadAll();
    Path load(String filename);
    Resource loadAsResource(String filename);
    void deleteAll();
}
