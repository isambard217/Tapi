package com.ops.base.education.project.Repository;
import com.ops.base.education.project.domain.Specie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface Species extends CrudRepository<Specie, Long> {}
