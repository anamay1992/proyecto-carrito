package com.springboot.backend.controllers;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.backend.models.dto.VentaDto;
import com.springboot.backend.models.services.VentaService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class VentaController {

	@Autowired
	private VentaService ventaService;
	
	@GetMapping("/ventas")
	public ResponseEntity<Object> obtenerVentas() {
		return new ResponseEntity<>(ventaService.obtenerVentas(), HttpStatus.OK);
	}

	@GetMapping("/clientes/{clienteId}/ventas")
	public ResponseEntity<Object> obtenerVentasPorClienteId(@PathVariable Long clienteId) {
		return new ResponseEntity<>(ventaService.obtenerVentasPorClienteId(clienteId), HttpStatus.OK);
	}
	
	@GetMapping("/ventas/{id}")
	public ResponseEntity<Object> obtenerVentaPorId(@PathVariable Long id) {
		return new ResponseEntity<>(ventaService.obtenerVentaPorId(id), HttpStatus.OK);
	}

	@GetMapping("/clientes/{clienteId}/ventasPorId/{ventaId}")
	public ResponseEntity<Object> obtenerVentaPorClienteIdYVentaId(@PathVariable Long clienteId,
			@PathVariable Long ventaId) {
		return new ResponseEntity<>(ventaService.obtenerVentaPorClienteIdYVentaId(clienteId, ventaId), HttpStatus.OK);
	}
	
	@GetMapping("/ventasPorFecha/{fecha}")
	public ResponseEntity<Object> obtenerVentasPorFechaVenta(@PathVariable String fecha) throws ParseException {
		return new ResponseEntity<>(ventaService.obtenerVentaPorFechaVenta(fecha), HttpStatus.OK);
	}
	
	@GetMapping("/clientes/{clienteId}/ventasPorFecha/{fecha}")
	public ResponseEntity<Object> obtenerVentaPorClienteIdYFechaVenta(@PathVariable Long clienteId, @PathVariable String fecha) throws ParseException {
		return new ResponseEntity<>(ventaService.obtenerVentaPorClienteIdYFechaVenta(clienteId, fecha), HttpStatus.OK);
	}

	@PostMapping("/clientes/{clienteId}/ventas")
	public ResponseEntity<Object> guardarVenta(@PathVariable Long clienteId, @RequestBody VentaDto ventaDto ) {
		return new ResponseEntity<>(ventaService.guardarVenta(clienteId, ventaDto), HttpStatus.CREATED);
	}

	@PutMapping("/clientes/{clienteId}/ventas/{ventaId}")
	public ResponseEntity<Object> actualizarVenta(@PathVariable Long clienteId, @RequestBody VentaDto ventaDto, @PathVariable Long ventaId ) {
		return new ResponseEntity<>(ventaService.actualizarVenta(clienteId, ventaDto, ventaId), HttpStatus.OK);
	}

	@DeleteMapping("/clientes/{clienteId}/ventas/{ventaId}")
	public ResponseEntity<Object> eliminarVenta(@PathVariable Long clienteId, @PathVariable Long ventaId) {
		return new ResponseEntity<>(ventaService.eliminarVenta(clienteId, ventaId), HttpStatus.OK);
	}

}
