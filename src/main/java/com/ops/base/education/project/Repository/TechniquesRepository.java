package com.ops.base.education.project.Repository;

import com.ops.base.education.project.domain.Technique;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechniquesRepository extends CrudRepository<Technique, Long> {
}
