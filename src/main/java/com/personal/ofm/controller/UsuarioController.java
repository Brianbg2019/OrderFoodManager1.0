package com.personal.ofm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.personal.ofm.entity.Roles;
import com.personal.ofm.entity.Usuarios;
import com.personal.ofm.repository.IRoles;
import com.personal.ofm.repository.IUsuarios;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	IUsuarios iusuario;
	@Autowired
	IRoles iroles;
	
	@GetMapping(value = "listar")
	public String listar(Model m) {
		m.addAttribute("items", (List<Usuarios>) iusuario.findAll());
		m.addAttribute("rol", (List<Roles>)iroles.findAll());
		return "Usuarios/UsuariosList";
	}
	
	@GetMapping(value = "guardar")
	public String nuevo(Model m) {
		m.addAttribute("roles", (List<Roles>) iroles.findAll());
		return "Usuarios/UsuariosList";
	}
	
	@PostMapping(value = "guardar")
	public String guardar(@RequestParam String nombre, @RequestParam String dui, @RequestParam String telefono, @RequestParam Integer rol){
		@Valid Usuarios user = new Usuarios();
		user.setNombre(nombre);
		user.setDui(dui);
		user.setTelefono(telefono);
		Roles roles = iroles.findById(rol).get();
		user.setIdRol(roles);
		iusuario.save(user);
		return "redirect:/usuario/listar";
	}
	
}
