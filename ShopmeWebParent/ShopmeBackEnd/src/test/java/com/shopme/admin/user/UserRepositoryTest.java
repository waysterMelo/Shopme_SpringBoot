package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.Users;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	
//	@Test
//	public void createUserTest() {
//		Role admin = entityManager.find(Role.class, 1);
//		Users wayster = new Users("waystermelo@gmail.com", "deus", "wayster", "de melo");
//		
//		wayster.addRole(admin); 
//		
//		Users savedUser =  userRepository.save(wayster);
//		assertThat(savedUser.getId()).isGreaterThan(0);
//	}
//	
//	@Test
//	public void createUserWithTwoRoles() {
//		Users jose = new Users("jose@gmail.com", "132", "Jose", "almeida");
//		Role roleEditor = new Role(2);
//		Role roleShipper= new Role(3);
//		jose.addTwoRoles(roleEditor, roleShipper); 
//		
//		Users saved = userRepository.save(jose);
//		assertThat(saved.getId()).isGreaterThan(0);
//			
//	}
//	
//	
//	@Test
//	public void testListAllUsers() {
//		Iterable<Users> listUsers = userRepository.findAll();
//		listUsers.forEach(users -> System.out.println(users));
//	}
//
//	
//	
//	@Test
//	public void getUserByIdTest() {
//		Optional<Users> user =  userRepository.findById(1);
//		System.out.println(user);
//		assertThat(user).isNotNull();
//	}
//	
//	
//	@Test
//	public void updateUserDetailsTest() {
//		Users user = userRepository.findById(1).get();
//		user.setEnable(true);
//		userRepository.save(user);
//	}
//	
//	
//	@Test
//	public void updateUserRolesTests() {
//		Users user = userRepository.findById(1).get();
//		Role oldRole = new Role(1);
//		Role newRole = new Role(3);
//		
//		user.getRoles().remove(oldRole);
//		user.addRole(newRole);
//		userRepository.save(user);
//		
//	}
//	
//	
//	@Test
//	public void deleteUserTest() {
//		Integer id = 3;
//	userRepository.deleteById(id); 
//	
//	}
	
	
//	@Test
//	public void getUserByEmailTest() {
//		String email = "waystermelo@gmail.com";
//		Users user = userRepository.getByEmail(email);
//		
//		assertThat(user).isNotNull();
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
