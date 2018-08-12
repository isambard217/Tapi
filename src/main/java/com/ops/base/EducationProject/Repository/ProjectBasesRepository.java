package com.ops.base.EducationProject.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ops.base.EducationProject.Domains.ProjectBase;

@Repository
public interface ProjectBasesRepository extends CrudRepository<ProjectBase, Long> {
}
