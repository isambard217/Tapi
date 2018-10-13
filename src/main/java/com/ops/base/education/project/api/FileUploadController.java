package com.ops.base.education.project.api;
import java.io.IOException;
import java.util.stream.Collectors;
import com.ops.base.education.project.Service.storage.StorageFileNotFoundException;
import com.ops.base.education.project.Service.storage.StorageService;
import com.ops.base.education.project.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
@Controller
@RequestMapping("/api/files")
public class FileUploadController {
  private final StorageService storageService;
  @Autowired
  public FileUploadController(StorageService storageService) {
    this.storageService = storageService;
  }
  @GetMapping
  public String listUploadedFiles(Model model, @RequestHeader String auth) throws IOException {
    model.addAttribute("files", storageService.loadAll().map(
      path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
        "serveFile", path.getFileName().toString()).build().toString())
      .collect(Collectors.toList()));
    return "uploadForm";
  }
  @GetMapping("/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> serveFile(@PathVariable String filename, @RequestHeader String auth) {

    Resource file = storageService.loadAsResource(filename);
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
      "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
  @PostMapping
  @ResponseBody
  public Project handleFileUpload(@RequestParam("file") MultipartFile file, @RequestHeader String auth,
                                  @RequestHeader long apiUserId) {
    return storageService.store(file,apiUserId);
  }
  @ExceptionHandler(StorageFileNotFoundException.class)
  public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
    return ResponseEntity.notFound().build();
  }
}
