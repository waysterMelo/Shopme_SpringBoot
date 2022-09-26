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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import com.shopme.admin.user.repository.UserRepository;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.Users;

@DataJpaTest(showSql = false)
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
	
	
//	@Test
//	public void countByIdTest() {
//		var id = 1;
//		var countById = userRepository.countById(id);
//		assertThat(countById).isNotNull().isGreaterThan(0);
//	}
//	
//	
	
//	@Test
//	public void disableTestUser() {
//		Integer id  = 1;
//		userRepository.updateEnableStatus(id, false);
//		
//	}
	
//	
//	@Test
//	public void enableTestUser() {
//		Integer id  = 1;
//		userRepository.updateEnableStatus(id, true);
//		
//	}
	
	
	@Test
	public void testListFirstPage() {
		int pageNumber = 1;
		int pageSize = 2;
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Users> page =  userRepository.findAll(pageable);
		List<Users> listUsers =  page.getContent();
		
		listUsers.forEach(user -> System.out.println(user));
		
		assertThat(listUsers.size()).isEqualTo(pageSize); 
	}
	
	@Test
	public void testSerchUsers() {
		String keyword = "teste";
		
		int pageNumber = 0;
		int pageSize = 4;
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Users> page = userRepository.findAll(pageable);
		List<Users> listUsers = page.getContent();
		assertThat(listUsers.size()).isGreaterThan(0);
	}
	
	
	
	
	
}
