package com.personal.ofm.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.ofm.entity.Roles;
import com.personal.ofm.entity.Usuarios;
import com.personal.ofm.service.RolesService;
import com.personal.ofm.service.UsuarioService;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	UsuarioService daoUsuario;
	@Autowired
	RolesService daoRoles;
	
	@GetMapping(value = "index")
	public String listar() {
		return "Empleados/EmpleadosList";
	}
	
	@GetMapping(value = "listar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public Object listEmpleados() {
		List<HashMap<String, Object>> registros = new ArrayList<>();
		List<Usuarios> usuariosList = daoUsuario.listUsuarios();
		
		for(Usuarios usuarios : usuariosList) {
			HashMap<String, Object> hm = new HashMap<>();
			hm.put("idUsuario", usuarios.getIdUsuario());
			hm.put("nombre", usuarios.getNombre());
			hm.put("dui", usuarios.getDui());
			hm.put("telefono", usuarios.getTelefono());
			hm.put("rol", usuarios.getIdRol().getRol());
			hm.put("operaciones", "<button type='button' onclick='preModificar("+usuarios.getIdUsuario()+")' class='btn btn-warning'>Editar</button>" + 
					"<button type='button' onclick='preEliminar("+usuarios.getIdUsuario()+")' class='btn btn-danger ml-2'>Eliminar</button>");
			registros.add(hm);
		}
		return Collections.singletonMap("data", registros);
	}
	
	@GetMapping(value = "getUsuario/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public Usuarios getUsuario(@PathVariable long id) {
		return daoUsuario.getIdUsuario(id);
	}
	
	@GetMapping(value = "getAllRoles" ,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public List<Roles> getAllRolles(){
		return (List<Roles>) daoRoles.listRoles();
	}
	
	@GetMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public HashMap<String, String> guardar(@RequestParam String nombre, @RequestParam String dui, @RequestParam String telefono, @RequestParam int idRol){
		Usuarios usuarios = new Usuarios();
		HashMap<String, String> hm = new HashMap<>();
		
		usuarios.setNombre(nombre);
		usuarios.setDui(dui);
		usuarios.setTelefono(telefono);
		usuarios.setIdRol(daoUsuario.getIdRol(idRol));
		try {
			daoUsuario.saveOrUpdate(usuarios);
			hm.put("mensaje", "Se guardo con exito");
		} catch (Exception e) {
			hm.put("mensaje", "Error al guardar");
		}
		return hm;
	}
	
	@GetMapping(value = "update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public HashMap<String, String> modificar(@RequestParam long idUsuario, @RequestParam String nombre, @RequestParam String dui,
			@RequestParam String telefono,@RequestParam int idRol){
		Usuarios usuarios = new Usuarios();
		HashMap<String, String> hm = new HashMap<>();
		
		usuarios.setIdUsuario(idUsuario);
		usuarios.setNombre(nombre);
		usuarios.setDui(dui);
		usuarios.setTelefono(telefono);
		usuarios.setIdRol(daoRoles.getIdRol(idRol));
		
		try {
			daoUsuario.saveOrUpdate(usuarios);
			hm.put("Mensaje", "Se actualizo correctamente");
		} catch (Exception e) {
			hm.put("Mensaje", "Error al actualizar");
		}
		return hm;
	}
	
	@GetMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public HashMap<String, String> borrar(@PathVariable long id){
		HashMap<String, String> hm = new HashMap<>();
		Usuarios user = daoUsuario.getIdUsuario(id);
		try {
			daoUsuario.delete(user);
			hm.put("Mensaje", "Registro eliminado");
		} catch (Exception e) {
			hm.put("Mensaje", "Error al eliminar");
		}
		return hm;
	}
}
