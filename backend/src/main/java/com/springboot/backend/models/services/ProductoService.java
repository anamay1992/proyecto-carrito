package com.springboot.backend.models.services;

import com.springboot.backend.models.dto.ProductoDto;
import com.springboot.backend.utils.RestResponse;
import com.springboot.backend.utils.RestResponsePaginate;

public interface ProductoService {

	public RestResponsePaginate obtenerProductos(Integer nPagina, Integer tPagina, String campo, String ordenar);
	
	public RestResponse obtenerProductoPorId(Long id);
	
	public RestResponse guardarProducto(ProductoDto productoDto);
	
	public RestResponse actualizarProducto(ProductoDto productoDto, Long id);
	
	public RestResponse eliminarProducto(Long id);
	
}
