package com.shopme.admin.user.controller;


import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.shopme.admin.user.repository.UserRepository;
import com.shopme.admin.user.service.UserService;
import com.shopme.admin.utils.FilePartUtil;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.Users;


@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository user_repository;
	
	
	@GetMapping("/users")
	public String listFirstPage(Model model) {
		return listByPage(1, model, "firstName", "asc", null);
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
	public String saveUSer(Users users, RedirectAttributes redirectAttributes, @RequestParam("image") MultipartFile image) throws IOException {
		
		
		if (!image.isEmpty()) {
			String fileName = StringUtils.cleanPath(image.getOriginalFilename());
			
			 users.setPhoto(fileName);
			 
			Users userSaved = service.save(users);
			
			String dir = "user-photos/" + userSaved.getId();
			
			FilePartUtil.cleanDir(dir); 
			FilePartUtil.saveFile(dir, fileName, image); 
		}else {
			if(users.getPhoto().isEmpty()) users.setPhoto(null);
			service.save(users);
		}
		
	
		redirectAttributes.addFlashAttribute("message", "The user has been saved succesfully");
		
		return getRedirectUrlToAffectedUser(users);
	}

	private String getRedirectUrlToAffectedUser(Users users) {
		String firstPartOfEmail = users.getEmail().split("@")[0];
		
		return "redirect:/users/page/1?sortField=id&sortDir=asc&keywords=" + firstPartOfEmail;
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
	
	
	@GetMapping("/users/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir, @Param("keywords") String keywords){
		
		
		Page<Users> page = service.listbyPage(pageNum, sortField, sortDir, keywords);
		List<Users> list = page.getContent();
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		
		long startCount = (pageNum - 1) * UserService.USER_PER_PAGE + 1;
		long endCount = startCount + UserService.USER_PER_PAGE - 1;
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItens", page.getTotalElements());
		model.addAttribute("listUsers", list);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("sortField", sortField);
		model.addAttribute("keywords", keywords);
		model.addAttribute("reverseSortDir", reverseSortDir);
		return "users";
	}
	
	
	
	
	
	
	
	
	
}
