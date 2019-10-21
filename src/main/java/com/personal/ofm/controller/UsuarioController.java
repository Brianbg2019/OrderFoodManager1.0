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
		List<Roles> roles = (List<Roles>) iroles.findAll();
		m.addAttribute("rol", roles);
		return "Empleados/EmpleadosList";
	}
	
	@GetMapping(value = "guardar")
	public String nuevo(Model m) {
		return "Empleados/EmpleadosList";
	}
	
	@PostMapping(value = "guardar")
	public String guardar(@RequestParam String nombre, @RequestParam String dui, @RequestParam String telefono, @RequestParam Integer roles){
		@Valid Usuarios user = new Usuarios();
		user.setNombre(nombre);
		user.setDui(dui);
		user.setTelefono(telefono);
		Roles rol = iroles.findById(roles).get();
		user.setIdRol(rol);
		iusuario.save(user);
		return "redirect:/usuario/listar";
	}
	
	@GetMapping(value = "modificar/{idUsuario}")
	public String modificar(@PathVariable Long idUsuario, Model m) {
		Usuarios user = iusuario.findById(idUsuario).get();
		m.addAttribute("items", user);
		List<Roles> roles = (List<Roles>) iroles.findAll();
		m.addAttribute("roles", roles);
		return "Empleados/EmpleadosEdit";
	}
	
	@PostMapping(value = "modificar") 
	public String editar(@RequestParam Long idUsuario, @RequestParam String nombre, @RequestParam String dui, @RequestParam String telefono,@RequestParam Integer idRol) {
		Usuarios user = new Usuarios();
		user.setIdUsuario(idUsuario);
		user.setNombre(nombre);
		user.setDui(dui);
		user.setTelefono(telefono);
		Roles roles = iroles.findById(idRol).get();
		user.setIdRol(roles);
		iusuario.save(user);
		return "redirect:/usuario/listar";
	}
	
	@RequestMapping(value = "eliminar/{idUsuario}")
	public String elimanar(@PathVariable Long idUsuario) {
		Usuarios user = iusuario.findById(idUsuario).get();
		iusuario.delete(user);
		return "redirect:/usuario/listar";
	}
}
