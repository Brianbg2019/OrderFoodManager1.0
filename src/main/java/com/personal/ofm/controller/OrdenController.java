package com.personal.ofm.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.personal.ofm.entity.Ordenes;
import com.personal.ofm.repository.IOrdenes;

@Controller
@RequestMapping("orden")
public class OrdenController {
	
	@Autowired
	IOrdenes iordenes;
	
	@RequestMapping(value = "listar")
	public String vista(Model m) {
		List<Ordenes> ordenes = (List<Ordenes>) iordenes.findAll();
		m.addAttribute("items", ordenes);
		return "Ordenes/OrdenesList";
	}
	
	@GetMapping(value = "ventas")
	public String ventas(Model m) {
		List<Ordenes> ordenes = (List<Ordenes>) iordenes.findAll();
		m.addAttribute("items", ordenes);
		return "Ventas/ventas";
	}		
}
