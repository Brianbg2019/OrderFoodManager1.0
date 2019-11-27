
package com.personal.ofm.controller;

import java.util.List;
import javax.validation.Valid;
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
import com.personal.ofm.repository.IProductos;

@Controller
@RequestMapping("producto")
public class ProductoController {
	
	@Autowired
	IProductos iproductos;
	
	@Autowired
	ICategorias icategorias;
	
	@GetMapping("listar")
	public String listar(Model m) {
		m.addAttribute("items", (List<Productos>) iproductos.findAll());
		List<Categorias> categoria = (List<Categorias>) icategorias.findAll();
		m.addAttribute("categoria", categoria);
		return "/Productos/ProductosList";
	}
	
	@GetMapping(value = "guardar")
	public String guardar(Model m) {
		List<Categorias> categoria = (List<Categorias>) icategorias.findAll();
		m.addAttribute("categoria", categoria);
		return "/Productos/ProductosList";
	}
	@PostMapping(value = "guardar")
	public String save(@RequestParam String nombreProducto, @RequestParam Long categoria, @RequestParam float precio, @RequestParam String imagen) {
		@Valid Productos producto = new Productos();
		producto.setNombreProducto(nombreProducto);
		Categorias cat = icategorias.findById(categoria).get();
		producto.setIdCategoria(cat);
		producto.setPrecio(precio);
		producto.setImagen("/Fotos/"+imagen);
		iproductos.save(producto);
		return "redirect:/producto/listar";
	}
	
	@GetMapping(value = "modificar/{idProducto}")
	public String modificar(@PathVariable Long idProducto, Model m) {
		Productos pro = iproductos.findById(idProducto).get();
		m.addAttribute("items", pro);
		List<Categorias> cat = (List<Categorias>) icategorias.findAll();
		m.addAttribute("categorias", cat);
		return "Productos/ProductosEdit";
	}
	
	@PostMapping(value = "modificar")
	public String editar(@RequestParam Long idProducto, @RequestParam String nombreProducto, @RequestParam Long categoria, @RequestParam float precio,@RequestParam String imagen) {
		Productos pro = new Productos();
		pro.setIdProducto(idProducto);
		pro.setNombreProducto(nombreProducto);
		Categorias cat = icategorias.findById(categoria).get();
		pro.setImagen("/Fotos/"+imagen);
		pro.setIdCategoria(cat);
		pro.setPrecio(precio);
		iproductos.save(pro);
		return "redirect:/producto/listar";
	}
	
	@GetMapping(value = "eliminar/{idProducto}")
	public String eliminar(@PathVariable Long idProducto) {
		Productos pro = iproductos.findById(idProducto).get();
		iproductos.delete(pro);
		return "redirect:/producto/listar";
	}
	
	@GetMapping(value = "productos", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Productos> productos(@RequestParam String categoria) {
		return iproductos.findByCategoria(categoria);
	}
}



