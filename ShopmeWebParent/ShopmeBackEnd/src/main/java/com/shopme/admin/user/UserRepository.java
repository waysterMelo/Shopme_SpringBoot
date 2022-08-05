package com.shopme.admin.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopme.common.entity.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {
	
	
	@Query("select u from Users u where u.email = :email")
	public Users getByEmail(@Param("email") String email);

}
