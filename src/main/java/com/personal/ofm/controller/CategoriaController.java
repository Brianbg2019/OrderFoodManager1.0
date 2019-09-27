package com.personal.ofm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.ofm.entity.Categorias;
import com.personal.ofm.entity.Productos;
import com.personal.ofm.repository.ICategorias;
import com.personal.ofm.service.CategoriaService;

@Controller
@RequestMapping("categoria")
public class CategoriaController {
	
	
	private CategoriaService cService;
	@Autowired
	ICategorias icategorias;
	
	@Autowired
	private CategoriaController(CategoriaService cService) {
		this.cService = cService;
	}
	
	@GetMapping("save")
	public String nuevo() {	
		return "Categorias/CategoriasList";
	}
	
	@PostMapping("save")
	public String save(HttpServletRequest request) {
		Categorias cat = new Categorias();
		cat.getNombreCategoria();
		
		return "redirect:/Categorias/CategoriasList";
	}
	
	@PostMapping("verificar")
	public String guardar(@RequestParam(value = "nombreCategoria", required = true)String nombreCategoria ) {
		Categorias catSave = new Categorias();
		Productos prodSave = new Productos();
		catSave.setNombreCategoria(nombreCategoria);
		cService.saveCategoria(catSave, prodSave);
		return "redirect:/categoria/listar";
	}
	
	@GetMapping(value = "listar")
	public String listar(Model m) {
		m.addAttribute("items", (List<Categorias>) cService.ListCategoria()) ;
		return "Categorias/CategoriasList";
		
	}
	
	@GetMapping("eliminar/{idCategoria}")
	public String borrar(@PathVariable Long idCategoria) {
		Categorias cat=icategorias.findById(idCategoria).get();
		icategorias.delete(cat);
		
		return new String("redirect:/categoria/listar");
	}
	
	@PostMapping("eliminar")
	public String eliminar(@PathVariable Long idCategoria) {
		Categorias cat=icategorias.findById(idCategoria).get();
		icategorias.delete(cat);
		
		return new String("redirect:/categoria/listar");
	}
	
	@GetMapping("modificar/{idCategoria}")
	public String editar(@PathVariable Long idCategoria, Model m) {
		Categorias cat = icategorias.findById(idCategoria).get();
		m.addAttribute("items", cat);
		return new String("Categorias/CategoriasEdit");
	}
	
	@PostMapping("modificar")
	public String modificar(@RequestParam Long idCategoria, @RequestParam String nombreCategoria) {
		Categorias cat = new Categorias();
		cat.setIdCategoria(idCategoria);
		cat.setNombreCategoria(nombreCategoria);
		icategorias.save(cat);
		return "redirect:/categoria/listar";
	}
	
}
