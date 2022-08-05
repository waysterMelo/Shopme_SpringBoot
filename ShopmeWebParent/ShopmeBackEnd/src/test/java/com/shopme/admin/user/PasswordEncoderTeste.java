package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTeste {


	@Test
	public void testeEncoderPassword() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "1234Deus";
		String encondePassword  = encoder.encode(rawPassword);
		System.out.println(encondePassword); 
		
		boolean matches = encoder.matches(rawPassword, encondePassword);
		
		
		assertThat(matches).isTrue();
 	}
	
	
	
}
