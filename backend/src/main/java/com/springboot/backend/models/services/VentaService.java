package com.springboot.backend.models.services;

import java.text.ParseException;

import com.springboot.backend.models.dto.VentaDto;
import com.springboot.backend.utils.RestResponse;

public interface VentaService {

	public RestResponse obtenerVentas();
	
	public RestResponse obtenerVentasPorClienteId(Long clienteId);
	
	public RestResponse obtenerVentaPorId(Long id);
	
	public RestResponse obtenerVentaPorClienteIdYVentaId(Long clienteId, Long VentaId);
	
	public RestResponse obtenerVentaPorFechaVenta(String fecha) throws ParseException;
	
	public RestResponse obtenerVentaPorClienteIdYFechaVenta(Long clienteId, String fecha) throws ParseException;
	
	public RestResponse guardarVenta(Long clienteId, VentaDto ventaDto);
	
	public RestResponse actualizarVenta(Long clienteId, VentaDto ventaDto, Long id);
	
	public RestResponse eliminarVenta(Long clienteId, Long VentaId);
	
}
