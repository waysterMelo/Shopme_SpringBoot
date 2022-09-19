package com.shopme.admin.user.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopme.common.entity.Users;

@Repository
public interface UserRepository extends PagingAndSortingRepository<Users, Integer> {
	
	
	@Query("select u from Users u where u.email = :email")
	public Users getByEmail(@Param("email") String email);
	
	public long countById(Integer id);
	
	@Query("Update Users u set u.enable = ?2 where u.id = ?1")
	@Modifying
	public void updateEnableStatus(Integer id, boolean enable);

}
