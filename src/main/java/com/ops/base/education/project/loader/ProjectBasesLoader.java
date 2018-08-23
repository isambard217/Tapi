package com.ops.base.education.project.loader;

import com.opencsv.CSVReader;
import com.ops.base.education.project.domain.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.ops.base.education.project.Repository.TemplatesRepository;

import java.io.File;
import java.io.FileReader;

@Component
public class ProjectBasesLoader implements CommandLineRunner {
    private final TemplatesRepository repository;

    @Autowired
    public ProjectBasesLoader(TemplatesRepository repository) {
      this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
      String fileName = "uploads/projectBasesData.csv";
      ClassLoader classLoader = this.getClass().getClassLoader();
      File projectBasesData;
      try {
        projectBasesData = new File(classLoader.getResource(fileName).getFile());
        FileReader fileReader = new FileReader(projectBasesData);
        CSVReader csvReader = new CSVReader(fileReader);
        String [] projectBaseRecord;
        // skip first line
        csvReader.readNext();
      while ((projectBaseRecord = csvReader.readNext()) != null){
        this.repository.save(new Template(projectBaseRecord[0],projectBaseRecord[1]));
      }
      }
      catch (NullPointerException e){
        e.printStackTrace();
      }
  }

}
