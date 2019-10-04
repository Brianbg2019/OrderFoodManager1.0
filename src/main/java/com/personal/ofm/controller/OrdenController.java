package com.personal.ofm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.ofm.entity.Productos;
import com.personal.ofm.repository.IDetalles;
import com.personal.ofm.repository.IProductos;
import com.sun.org.apache.regexp.internal.recompile;

@Controller
@RequestMapping("orden")
public class OrdenController {
	@Autowired
	IDetalles idetalles;
	@Autowired
	IProductos iproductos;
	@RequestMapping(value = "index")
	public String vista(Model m) {
		m.addAttribute("items", idetalles.findAll());
		return "vistaCliente/index";
	}
}
