package com.personal.ofm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.personal.ofm.service.VentasService;
@Controller
@RequestMapping("detalleventa")
public class DMaestroController {

	@Autowired
	VentasService vService;

	@GetMapping(value = "cargarCliente")
	public String vistaDatos() {
		return "DMVentas";
	}
}
