package com.springboot.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springboot.backend.models.dto.ProductoDto;
import com.springboot.backend.models.services.ProductoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@GetMapping
	public ResponseEntity<Object> obtenerProductos(
			@RequestParam(defaultValue = "0", required = false) Integer nPagina,
			@RequestParam(defaultValue = "10", required = false) Integer tPagina,
			@RequestParam(defaultValue = "id", required = false) String campo,
			@RequestParam(defaultValue = "asc", required = false) String ordenar) {
		return new ResponseEntity<>(productoService.obtenerProductos(nPagina, tPagina, campo, ordenar), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> obtenerProductoPorId(@PathVariable Long id) {
		return new ResponseEntity<>(productoService.obtenerProductoPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> guardarProducto(@RequestBody ProductoDto productoDto) {
		return new ResponseEntity<>(productoService.guardarProducto(productoDto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> actualizarProducto(@RequestBody ProductoDto productoDto, @PathVariable Long id) {
		return new ResponseEntity<>(productoService.actualizarProducto(productoDto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminarProducto(@PathVariable Long id) {
		return new ResponseEntity<>(productoService.eliminarProducto(id), HttpStatus.OK);
	}

}
