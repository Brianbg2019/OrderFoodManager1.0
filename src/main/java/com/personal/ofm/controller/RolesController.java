package com.personal.ofm.controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.ofm.entity.Productos;
import com.personal.ofm.entity.Roles;
import com.personal.ofm.repository.IRoles;
import com.personal.ofm.service.RolesService;

@Controller
@RequestMapping("roles")
public class RolesController {

	@Autowired
	RolesService daoRoles;
	
	@GetMapping("index")
	public String listar(Model m) {
		return "Roles/RolesList";
	}
	
	@GetMapping(value = "getRoles/{id}")
	@ResponseBody
	@CrossOrigin
	public Roles getRol(@PathVariable int id) {
		return daoRoles.getIdRol(id);
	}
	
	@GetMapping(value = "listar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public Object listRoles() {
		List<HashMap<String, Object>> registros = new ArrayList<>();
		List<Roles> rolList = daoRoles.listRoles();
		
		for(Roles rol : rolList) {
			HashMap<String, Object> hm = new HashMap<>();
			hm.put("idRol", rol.getIdRol());
			hm.put("rol", rol.getRol());
			hm.put("Editar", "<button type='button' onclick='preEditar("+rol.getIdRol()+")' class='btn btn-warning'>Editar</button>");
			hm.put("Eliminar", "<button type='button' onclick='preEliminar("+rol.getIdRol()+")' class='btn btn-danger'>Eliminar</button>");
			registros.add(hm);
		}
		return Collections.singletonMap("data", registros);
	}
	
	@GetMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public HashMap<String, String> guardar(@RequestParam String rol){
		Roles roles = new Roles();
		HashMap<String, String> hm = new HashMap<>();
		roles.setRol(rol);
		try {
			daoRoles.saveOrUpdateRol(roles);
			hm.put("Mnesaje", "Se guardo correctamente");
		} catch (Exception e) {
			hm.put("Mensaje", "Error al guardar");
		}
		return hm;
	}
	
	@GetMapping(value = "update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public HashMap<String, String> modificar(@RequestParam int id, @RequestParam String rol){
		Roles roles = new Roles();
		HashMap<String, String> hm = new HashMap<>();
		
		roles.setIdRol(id);
		roles.setRol(rol);
		try {
			daoRoles.saveOrUpdateRol(roles);
			hm.put("Mensaje", "Registro modificado");
		} catch (Exception e) {
			hm.put("Mensaje", "Error al modificar");
		}
		return hm;
	}
	
	@GetMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public HashMap<String, String> borrar (@PathVariable int id){
		HashMap<String, String> hm = new HashMap<>();
		Roles roles = daoRoles.getIdRol(id);
		try {
			daoRoles.Delete(roles);
			hm.put("Mensaje", "Registro Eliminado");
		} catch (Exception e) {
			hm.put("Mensaje", "Error al eliminar");
		}
		return hm;
	}
	
	
	
}
