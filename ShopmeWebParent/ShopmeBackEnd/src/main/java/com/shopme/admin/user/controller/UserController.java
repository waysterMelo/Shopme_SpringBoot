package com.shopme.admin.user.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.admin.user.repository.UserRepository;
import com.shopme.admin.user.service.UserService;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.Users;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository user_repository;
	
	
	@GetMapping("/users")
	public String listAll(Model model) {
		List<Users> listAll = service.listAll();
		model.addAttribute("listUsers", listAll);
		return "users";
	}
	
	//pagina cadastrar user
	@GetMapping("/users/new")
	public String pageNewUser(Model model) {
		List<Role> roles = service.listRoles();
		Users users = new Users();
		users.setEnable(true);
		model.addAttribute("users", users);
		model.addAttribute("roles", roles);
		model.addAttribute("pageTitle", "Create new User");
		return "users-form";
	}
	
	@PostMapping("/users/save") 
	public String saveUSer(Users users, RedirectAttributes redirectAttributes, @RequestParam("image") MultipartFile image) {
		//service.save(users);
		//redirectAttributes.addFlashAttribute("message", "The user has been saved succesfully");]
		System.out.println(image.getOriginalFilename());
		return "redirect:/users";
	}
	
	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes, Model model) {
		try {
			
			Users user = service.get(id);	
			Users userFull =  user_repository.findById(id).get();
			
			List<Role> roles = service.listRoles();
			
			model.addAttribute("users", user);
			model.addAttribute("roles", roles);
			model.addAttribute("pageTitle", "Edit user ( Name : " + userFull.getFirstName() +")");
			
			return "users-form";
			
		} catch (UsernameNotFoundException e) {
			 redirectAttributes.addFlashAttribute("message", e.getMessage()); 
			 return "redirect:/users";
		}
		
	}
	
	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes attributes) {
		try {
			service.delete(id);
			attributes.addFlashAttribute("message", "the user id " + id + " has been deleted successfully");
		} catch (Exception e) {
			attributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/users";
	}
	
	
	@GetMapping("/users/{id}/enable/{status}")
	public String updateUserEnableStatus(@PathVariable("id") Integer id, @PathVariable("status")
	boolean enable, RedirectAttributes attributes) {
		service.updateUserEnableStatus(id, enable);
		String status = enable ? "enable" : "disable";
		String message = "the user id " + id + "has been " + status;
		attributes.addFlashAttribute("message", message);
		return "redirect:/users";
	}
	
	
	
	
	
	
	
	
	
	
}
