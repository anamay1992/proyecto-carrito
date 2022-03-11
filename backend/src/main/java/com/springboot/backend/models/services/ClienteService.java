package com.springboot.backend.models.services;

import com.springboot.backend.models.dto.ClienteDto;
import com.springboot.backend.utils.RestResponse;

public interface ClienteService {
	
	public RestResponse obtenerClientes();
	
	public RestResponse obtenerClientePorId(Long id);
	
	public RestResponse guardarCliente(ClienteDto clienteDto);
	
	public RestResponse actualizarCliente(ClienteDto clienteDto, Long id);
	
	public RestResponse eliminarCliente(Long id);

}
