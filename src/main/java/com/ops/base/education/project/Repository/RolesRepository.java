package com.ops.base.education.project.Repository;
import com.ops.base.education.project.domain.Role;
import org.springframework.data.repository.CrudRepository;
public interface RolesRepository extends CrudRepository<Role, Long> {
  Role findByName(String roleName);
}
