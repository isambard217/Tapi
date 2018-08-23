package com.ops.base.education.project.Repository;

import com.ops.base.education.project.domain.Template;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplatesRepository extends CrudRepository<Template, Long> {
}
