package com.ops.base.education.project.Repository;
import com.ops.base.education.project.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProjectsRepository extends CrudRepository<Project, Long> {}
