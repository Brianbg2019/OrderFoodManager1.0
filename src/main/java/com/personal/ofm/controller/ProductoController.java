
package com.personal.ofm.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.validation.Valid;
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

import com.personal.ofm.entity.Categorias;
import com.personal.ofm.entity.Productos;
import com.personal.ofm.repository.ICategorias;
import com.personal.ofm.repository.IProductos;
import com.personal.ofm.service.CategoriaService;
import com.personal.ofm.service.ProductosService;

@Controller
@RequestMapping("producto")
public class ProductoController {
	
	@Autowired
	ProductosService daoProducto;
	
	@Autowired
	CategoriaService daoCategoria;
	
	@GetMapping("index")
	public String listar(Model m) {
		return "/Productos/ProductosList";
	}
	
	@GetMapping(value = "getProducto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public Productos getProductoId(@PathVariable long id) {
		return daoProducto.getIdProducto(id);
	}
	
	@GetMapping(value = "listar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public Object listProductos() {
		List<HashMap<String, Object>> registros = new ArrayList<>();
		List<Productos> listProducto = daoProducto.ListProducto();
		
		for(Productos producto : listProducto) {
			HashMap<String, Object> hm = new HashMap<>();
			hm.put("idProducto", producto.getIdProducto());
			hm.put("imagen", "<img src='"+producto.getImagen()+"' width='100px' height='100px'></img>");
			hm.put("nombre", producto.getNombreProducto());
			hm.put("categoria", producto.getIdCategoria().getNombreCategoria());
			hm.put("precio", producto.getPrecio());
			hm.put("operaciones", "<button type='button' onclick='preModificar("+producto.getIdProducto()+")' class='btn btn-warning'>Editar</button>" + 
					"<button type='button' onclick='preEliminar("+producto.getIdProducto()+")' class='btn btn-danger ml-2'>Eliminar</button>");
			
			registros.add(hm);
		}
		return Collections.singletonMap("data", registros);
	}
	
	@GetMapping(value = "allCategorias")
	@ResponseBody
	@CrossOrigin
	public List<Categorias> allCategoria(){
		return daoCategoria.ListCategoria();
	}
	
	/***************************************************************************************************************************/
	
	@GetMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public HashMap<String, String> guardar(@RequestParam String imagen, @RequestParam String nombre, @RequestParam long idCategoria, @RequestParam float precio){
		Productos productos = new Productos();
		HashMap<String, String> hm = new HashMap<>();
		productos.setImagen("/Fotos/"+imagen);
		productos.setNombreProducto(nombre);
		productos.setIdCategoria(daoCategoria.getIdCategoria(idCategoria));
		productos.setPrecio(precio);
		
		try {
			daoProducto.saveOrUpdateProducto(productos);
			hm.put("mensaje", "Se guardo con exito");
		} catch (Exception e) {
			hm.put("mensaje", "Error al guardar");
		}
		return hm;
	}
	
	@GetMapping(value = "update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public HashMap<String, String> modificar(@RequestParam long idProducto, @RequestParam String imagen, @RequestParam String nombre, @RequestParam long idCategoria, @RequestParam float precio){
		Productos productos = new Productos();
		HashMap<String, String> hm = new HashMap<>();
		
		productos.setIdProducto(idProducto);
		productos.setImagen("/Fotos/"+imagen);
		productos.setNombreProducto(nombre);
		productos.setIdCategoria(daoCategoria.getIdCategoria(idCategoria));
		productos.setPrecio(precio);
		try {
			daoProducto.saveOrUpdateProducto(productos);
			hm.put("mensaje", "Modificado con exito");
		} catch (Exception e) {
			hm.put("mensaje", "Error al modificar");
		}
		return hm;
	}
	
	@GetMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public HashMap<String, String> borrar(@PathVariable long id){
		HashMap<String, String> hm = new HashMap<>();
		Productos product = daoProducto.getIdProducto(id);
		
		try {
			daoProducto.Delete(product);
			hm.put("Mensaje", "Registro eliminado");
		} catch (Exception e) {
			hm.put("Mensaje", "Error al eliminar");
		}
		return hm;
	}
	
	@GetMapping(value = "productos", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Productos> productos(@RequestParam String categoria) {
		return daoProducto.ListProductoByCategpria(categoria);
	}
}



