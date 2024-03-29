package com.personal.ofm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;

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

import com.personal.ofm.entity.Detalles;
import com.personal.ofm.repository.IClientes;
import com.personal.ofm.repository.IDetalles;
import com.personal.ofm.repository.IOrdenes;
import com.personal.ofm.repository.IProductos;

@Controller
@RequestMapping("detalle")
public class DetalleController {

	@Autowired
	IDetalles idetalles;
	@Autowired
	IProductos iproductos;
	@Autowired
	IOrdenes iordenes;
	
	public static List<Detalles> detalles = new ArrayList<>();
	
	@PostMapping(value = "detallesOrden", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public Object saveDetalles(@RequestParam long idProducto, @RequestParam int cantidad) {
		Detalles detail = new Detalles();
		HashMap<String, String> hm = new HashMap<>();
		
		detail.setIdProducto(iproductos.findById(idProducto).get());
		detail.setCantidad(cantidad);
		
		
		try {
			detalles.add(detail);
			hm.put("Mensaje", "Detalle Guardado");
		} catch (Exception e) {
			hm.put("Mensaje", "Error al guardar Detalle");
		}
		return hm;
	}
	
	@GetMapping(value = "mostrarDetalles", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public Object getDetalles() {
		return detalles;
	}
	
	@GetMapping(value = "remove/{indice}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public Object remover(@PathVariable String indice) {
		HashMap<String, String> hm = new HashMap<>();
		try {
			detalles.remove(indice);
			hm.put("Mensaje", "Se removio correctamente");
		} catch (Exception e) {
			hm.put("Mensaje", "Error al remover");
		}
		
		return hm;
	}

	
	@GetMapping(value = "vista")
	public String vista(Model m) {
		List<Detalles> detalles = (List<Detalles>) idetalles.findAll();
		m.addAttribute("items", detalles);
		return "/vistaCliente/index";
	}

	@GetMapping(value = "mostrar")
	public String Mostrar(Model m) {
		List<Detalles> detalles = (List<Detalles>) idetalles.findAll();
		m.addAttribute("items", detalles);

		return "vistaCliente/ordenes";

	}

	@GetMapping(value = "orden", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String Mostrarorden() {

		return "";
	}

}
