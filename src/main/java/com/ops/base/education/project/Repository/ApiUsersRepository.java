package com.ops.base.education.project.Repository;
import com.ops.base.education.project.domain.ApiUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ApiUsersRepository extends CrudRepository<ApiUser, Long> {
  public ApiUser findByUserName(String username);
}
