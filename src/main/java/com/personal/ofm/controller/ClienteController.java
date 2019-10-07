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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.personal.ofm.entity.Clientes;
import com.personal.ofm.repository.IClientes;

import com.personal.ofm.entity.Clientes;
import com.personal.ofm.repository.IClientes;

@Controller
@RequestMapping("cliente")
public class ClienteController {
	
	@Autowired
	IClientes icliente;
	
	@GetMapping(value = "listar")
	public String listar(Model m) {
		List<Clientes> cliente = (List<Clientes>) icliente.findAll();
		m.addAttribute("items", cliente);
		return "Clientes/ClientesList";
	}
	
	@GetMapping(value = "guardar")
	public String nuevo() {
		return "Clientes/ClientesList";
	}
	
	@PostMapping(value = "guardar")
	public String guardar(@RequestParam String nombre,  RedirectAttributes redirectAttrs) {
		@Valid Clientes cliente = new Clientes();
		cliente.setNombre(nombre);
		icliente.save(cliente);
		redirectAttrs
        .addFlashAttribute("mensaje", "Agregado correctamente")
        .addFlashAttribute("clase", "success");
		return "redirect:/cliente/listar";
	}
	
	@GetMapping(value = "modificar/{idCliente}")
	public String modificar(Model m, @PathVariable Long idCliente){
		Clientes cliente = icliente.findById(idCliente).get();
		m.addAttribute("items", cliente);
		return "Clientes/ClientesEdit";
	}
	
	@PostMapping(value = "modificar")
	public String editar(@RequestParam Long idCliente, @RequestParam String nombre){
		Clientes cliente = new Clientes();
		cliente.setIdCliente(idCliente);
		cliente.setNombre(nombre);
		icliente.save(cliente);
		return "redirect:/cliente/listar";
	}
	
	@GetMapping(value = "eliminar/{idCliente}")
	public String eliminar(@PathVariable Long idCliente){
		Clientes cliente = icliente.findById(idCliente).get();
		icliente.delete(cliente);
		return "redirect:/cliente/listar";
	}
}
