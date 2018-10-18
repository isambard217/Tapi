package com.ops.base.education.project.Repository;
import com.ops.base.education.project.domain.Sample;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface SamplesRepository extends CrudRepository<Sample, Long> {
  public Optional<Sample> findByName(String name);
}
