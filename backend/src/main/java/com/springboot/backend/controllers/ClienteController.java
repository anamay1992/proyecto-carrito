package com.springboot.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.backend.models.dto.ClienteDto;
import com.springboot.backend.models.services.ClienteService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<Object> obtenerClientes() {
		return new ResponseEntity<>(clienteService.obtenerClientes(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> obtenerClientePorId(@PathVariable Long id) {
		return new ResponseEntity<>(clienteService.obtenerClientePorId(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> guardarCliente(@RequestBody ClienteDto clienteDto) {
		return new ResponseEntity<>(clienteService.guardarCliente(clienteDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> actualizarCliente(@RequestBody ClienteDto clienteDto, @PathVariable Long id) {
		return new ResponseEntity<>(clienteService.actualizarCliente(clienteDto, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminarCliente(@PathVariable Long id) {
		return new ResponseEntity<>(clienteService.eliminarCliente(id), HttpStatus.OK);
	}
	
}
