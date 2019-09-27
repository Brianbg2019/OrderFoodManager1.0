package com.personal.ofm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("detalle")
public class DetalleController {
	@GetMapping(value = "vista")
	public String vista() {
	return "productosVista/producto";
	}

}
