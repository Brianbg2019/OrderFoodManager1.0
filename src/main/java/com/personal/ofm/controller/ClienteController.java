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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.personal.ofm.entity.Clientes;
import com.personal.ofm.service.ClientesService;

@Controller
@RequestMapping("cliente")
public class ClienteController {
	
	@Autowired
	ClientesService daoCliente;
	
	@GetMapping(value = "index")
	public String listar() {
		return "Clientes/ClientesList";
	}
	
	@GetMapping(value = "listar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public Object listClientes() {
		List<HashMap<String, Object>> registros = new ArrayList<>();
		List<Clientes> clientList = daoCliente.listClientes();
		
		for(Clientes cliente : clientList) {
			HashMap<String, Object> hm = new HashMap<>();
			hm.put("idCliente", cliente.getIdCliente());
			hm.put("nombre", cliente.getNombre());
			hm.put("Editar", "<button type='button' class='btn btn-warning'>Editar</button>");
			hm.put("Eliminar", "<button type='button' class='btn btn-danger'>Eliminar</button>");
			registros.add(hm);
		}
		return Collections.singletonMap("data", registros);
	}
	
	/*Usar al guardar una orden*/
	@GetMapping(value = "save")
	@ResponseBody
	@CrossOrigin
	public HashMap<String, String> guardar(@RequestParam String nombre){
		Clientes cliente = new Clientes();
		HashMap<String, String> hm = new HashMap<String, String>();
		
		cliente.setNombre(nombre);
		try {
			daoCliente.saveOrUpdate(cliente);
			hm.put("Mensaje", "Se agrego correctamente");
		} catch (Exception e) {
			hm.put("Mensaje", "Error al guardar");
		}
		return hm;
	}
	
	@PostMapping(value = "delete")
	@ResponseBody
	@CrossOrigin
	public HashMap<String, String> eliminar(){
		return null;
	}
}
