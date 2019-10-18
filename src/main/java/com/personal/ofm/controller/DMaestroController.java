package com.personal.ofm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.ofm.entity.Clientes;
import com.personal.ofm.repository.IClientes;
import com.personal.ofm.service.VentasService;

@Controller
@RequestMapping("detalleventa")
public class DMaestroController {
	
	@Autowired
	VentasService vService;
	
	//@PostMapping(value = "cargarCliente")
	//public String vistaDatos(@RequestParam ) {
		
	//	return "DMVentas";
	//}
}
