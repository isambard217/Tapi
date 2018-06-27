package com.ops.base.RestBluePrint.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ops.base.RestBluePrint.Domains.User;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {

	
	
}
