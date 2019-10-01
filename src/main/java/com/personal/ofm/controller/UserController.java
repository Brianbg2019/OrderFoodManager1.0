package com.personal.ofm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.personal.ofm.entity.Users;
import com.personal.ofm.repository.IUser;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	IUser iuser;

	@GetMapping("listar")
	public String listar(Model m) {
		m.addAttribute("items", (List<Users>) iuser.findAll());
		return "index2";
	}

	@GetMapping("guardar")
	public String nuevo(Model m) {
		return "Users/UsersList";
	}

	@PostMapping("guardar")
	public String guardar(@RequestParam String user, @RequestParam String password, @RequestParam String role) {
		@Valid
		Users u = new Users();
		u.setUser(user);
		u.setPassword(password);
		u.setRole(role);
		return "redirect:/user/listar";
	}
}
