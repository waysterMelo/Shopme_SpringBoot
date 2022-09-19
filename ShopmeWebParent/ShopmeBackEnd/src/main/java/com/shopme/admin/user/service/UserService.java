package com.shopme.admin.user.service;

import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.shopme.admin.user.repository.RoleRepository;
import com.shopme.admin.user.repository.UserRepository;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.Users;

@Service
@Transactional
public class UserService {
	
	public static final int USER_PER_PAGE = 4;

	
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

	public Users save(Users users) {
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
		return userRepository.save(users);
		
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
	
	public void delete(Integer id) {
		Long countedId = userRepository.countById(id);
		if (countedId == null || countedId == 0) {
			throw new UsernameNotFoundException("Could not find any user with id " + id);
		}
		userRepository.deleteById(id); 
	}
	
	
	public void updateUserEnableStatus(Integer id, boolean enable) {
		userRepository.updateEnableStatus(id, enable);
	}
	
	
	public Page<Users> listbyPage(int pageNum, String sortField, String sortDir){
		
		Sort sort = Sort.by(sortField);
		
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNum - 1, USER_PER_PAGE);
		return userRepository.findAll(pageable);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
