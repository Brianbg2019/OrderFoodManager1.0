package com.personal.ofm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("vista")
public class Test {

	@GetMapping(value = "mostrar")
	public String mostrar() {
		return "OrdenControl";
	}
	
	@GetMapping(value = "dmvista")
	public String detalle() {
		return "DMVentas";
	}

	@GetMapping(value="buscar")
	public String motrar() {
		return "vistaCliente/index";
	}

	@GetMapping(value = "menu")
	public String menu() {
		return "Enlaces/EnlaceMenu";
	}
	
}