package com.ops.base.education.project.Repository;
import com.ops.base.education.project.domain.Privilege;
import org.springframework.data.repository.CrudRepository;
public interface PrivilegesRepository extends CrudRepository<Privilege, Long> {
  Privilege findByName(String privilegeName);
}
