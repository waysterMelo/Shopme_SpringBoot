package com.shopme.admin.user;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.Users;

@Service
public class UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<Users> listAll(){
		return (List<Users>) userRepository.findAll();
	}
	
	public List<Role> listRoles(){
		return (List<Role>) roleRepository.findAll();
	}

	public void save(Users users) {
		boolean haveAnUser = (users.getId() != null);
		
		if (haveAnUser) {
			Users existUser = userRepository.findById(users.getId()).get();
			
			if (users.getPassword().isEmpty()) {
				users.setPassword(existUser.getPassword());
			}else {
				encodePassword(users); 
			}
			
		}else {
			userRepository.save(users);
		}
		
		encodePassword(users); 
		userRepository.save(users);
		
	}
	
	public void encodePassword(Users users) {
		String encodePassword = passwordEncoder.encode(users.getPassword());
		users.setPassword(encodePassword); 
	}
	
	public boolean isEmailUnique(String email, Integer id) {
		Users userByemail = userRepository.getByEmail(email);
		
		if (userByemail == null) return true;
		
		boolean isCreatingNew = (id == null);
		
		if (isCreatingNew) {
			
			if (userByemail != null ) return false;
		}else {
			if (userByemail.getId() != id) {
				return false;
			}
		}
		
		return true;
	}

	public Users get(Integer id) {
		try {
			return userRepository.findById(id).get();
		} catch (NoSuchElementException ex) {
			throw new UsernameNotFoundException("User not found with id" + id);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
