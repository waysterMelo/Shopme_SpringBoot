package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.admin.user.repository.RoleRepository;
import com.shopme.common.entity.Role;

@Rollback(false)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RoleRepositoryTests {

	@Autowired
	private RoleRepository repository;

	@Test
	public void testCreateFirstRole() {
		Role role = new Role("User", "Manage all"); 
		Role saved = repository.save(role);
		
		assertThat(saved.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateOtherRoles() {
		Role roleSales = new Role("Sales", " manage product price, customers, shipping, orders and sales report" );
		Role roleEditor = new Role("Editor", " manage categories, brands, products, articles and menus" );
		Role roleShipper = new Role("Shipper", "view products, view orders, and update orders status" );
		Role roleAssistant = new Role("Assistant", "manage questions and reviews" );
		
		repository.saveAll(List.of(roleSales, roleEditor, roleShipper, roleAssistant)); 
		
		

	}
	
	
}
