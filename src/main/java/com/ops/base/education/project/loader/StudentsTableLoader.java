package com.ops.base.education.project.loader;
import com.ops.base.education.project.Service.StudentsService;
import com.ops.base.education.project.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
@Component
public class StudentsTableLoader implements CommandLineRunner {
  private final StudentsService studentsService;
  @Autowired
  public StudentsTableLoader(StudentsService studentsService){
    this.studentsService = studentsService;
  }
  /**
   * Callback used to run the bean.
   *
   * @param args incoming main method arguments
   * @throws Exception on error
   */
  @Override
  public void run(String... args) throws Exception {
    ArrayList<Student> users = new ArrayList<>(1);
        Student alhaytham = new Student("ate5", "login227", "Basic");
        users.add(alhaytham);
        studentsService.saveAll(users);
  }
}
