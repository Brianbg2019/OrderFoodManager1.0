package com.personal.ofm.controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.ofm.entity.Categorias;
import com.personal.ofm.service.CategoriaService;

@Controller
@RequestMapping("categoria")
public class CategoriaController {
	
	@Autowired
	CategoriaService daocategoria;
	
	
	@GetMapping(value = "index")
	public String listar(Model m) {
		return "Categorias/CategoriasList";	
	}
	
	@GetMapping(value = "listar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public Object listCategories() {
		
		List<HashMap<String, Object>> registros = new ArrayList<>();
		List<Categorias> listaCat = daocategoria.ListCategoria();
		for (Categorias cat : listaCat) {
			HashMap<String, Object> hm = new HashMap<>();
			hm.put("idCategoria", cat.getIdCategoria());
			hm.put("categoria", cat.getNombreCategoria());
			
			registros.add(hm);
		}
		
		return Collections.singletonMap("data", registros);
	}
	
	@GetMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public HashMap<String, String> guardarCategoria(@RequestParam String categoria){
		Categorias cat = new Categorias();
		HashMap<String, String> hm = new HashMap<String, String>();
		
		cat.setNombreCategoria(categoria);
		try {
			daocategoria.SaveOrUpdate(cat);
			hm.put("Mensaje", "Se guardo correctamente");
		} catch (Exception e) {
			hm.put("Mensaje", "Error al guardar");
		}
		return hm;
	}
	
	
}
