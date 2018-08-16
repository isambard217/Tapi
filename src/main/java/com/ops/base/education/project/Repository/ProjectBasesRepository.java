package com.ops.base.education.project.Repository;

import com.ops.base.education.project.domain.ProjectBase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectBasesRepository extends CrudRepository<ProjectBase, Long> {
}
