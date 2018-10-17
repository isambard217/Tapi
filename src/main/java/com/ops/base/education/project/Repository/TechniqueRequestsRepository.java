package com.ops.base.education.project.Repository;
import com.ops.base.education.project.domain.TechniqueRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TechniqueRequestsRepository extends CrudRepository<TechniqueRequest, Long> {
}
