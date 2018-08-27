package com.ops.base.education.project.loader;

import com.opencsv.CSVReader;
import com.ops.base.education.project.Repository.TechniquesRepository;
import com.ops.base.education.project.domain.Technique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;

@Component
public class TechniquesLoader implements CommandLineRunner {
  private final TechniquesRepository techniquesRepository;

  @Autowired
  public TechniquesLoader(TechniquesRepository techniquesRepository){
    this.techniquesRepository = techniquesRepository;
  }
  /**
   * Callback used to run the bean.
   *
   * @param args incoming main method arguments
   * @throws Exception on error
   */
  @Override
  public void run(String... args) throws Exception {
    String fileName = "uploads/TechniquesOverview.csv";
    ClassLoader classLoader = this.getClass().getClassLoader();
    File techniquesFile;
    try {
      techniquesFile = new File(classLoader.getResource(fileName).getFile());
      FileReader fileReader = new FileReader(techniquesFile);
      CSVReader csvReader = new CSVReader(fileReader);
      String [] techniqueRecord;
      // skip header line
      csvReader.readNext();
      while((techniqueRecord = csvReader.readNext()) != null) {
        this.techniquesRepository.save(new Technique(techniqueRecord[0], techniqueRecord[1]));
      }
    }
    catch (NullPointerException e){
      e.printStackTrace();
    }
  }
}
