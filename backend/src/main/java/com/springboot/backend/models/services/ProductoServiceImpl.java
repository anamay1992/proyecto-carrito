package com.springboot.backend.models.services;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import com.springboot.backend.exceptions.AppException;
import com.springboot.backend.exceptions.ResourceNotFoundException;
import com.springboot.backend.models.dto.ProductoDto;
import com.springboot.backend.models.entity.Producto;
import com.springboot.backend.models.repositories.ProductoRepository;
import com.springboot.backend.utils.RestResponse;
import com.springboot.backend.utils.RestResponsePaginate;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public RestResponsePaginate obtenerProductos(Integer nPagina, Integer tPagina, String campo, String ordenar) {
		Sort clasificar = ordenar.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(campo).ascending()
				: Sort.by(campo).descending();
		Pageable pageable = PageRequest.of(nPagina, tPagina, clasificar);
		Page<Producto> productosPaginado = productoRepository.findAll(pageable);
		List<Producto> productos = productosPaginado.getContent();
		List<ProductoDto> productosDto = productos.stream().map(producto -> mapearADto(producto))
				.collect(Collectors.toList());
		if (productosDto.size() == 0) {
			throw new AppException(HttpStatus.BAD_REQUEST, "lista vacia");
		}
		return new RestResponsePaginate(HttpStatus.OK, "lista de productos", productosDto, nPagina, tPagina,
				productosPaginado.getTotalElements(), productosPaginado.getTotalPages(), productosPaginado.isLast());
	}

	@Override
	public RestResponse obtenerProductoPorId(Long id) {
		Producto producto = productoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("producto", "id", id));
		if (producto == null) {
			throw new AppException(HttpStatus.BAD_REQUEST, "el producto no fue encontrado");
		}
		ProductoDto productoDto = mapearADto(producto);
		return new RestResponse(HttpStatus.OK, "el producto fue encontrado", productoDto);
	}

	@Override
	public RestResponse guardarProducto(ProductoDto productoDto) {
		Producto producto = mapearAEntidad(productoDto);
		Producto nuevoProducto = productoRepository.save(producto);
		if(nuevoProducto == null) {
			throw new AppException(HttpStatus.BAD_REQUEST, "el producto no fue guardado");
		}
		ProductoDto nuevoProductoDto = mapearADto(nuevoProducto);
		return new RestResponse(HttpStatus.OK, "el producto fue guardado", nuevoProductoDto); 
	}

	@Override
	public RestResponse actualizarProducto(ProductoDto productoDto, Long id) {
		Producto producto = productoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("producto", "id", id));
		if(producto == null) {
			throw new AppException(HttpStatus.BAD_REQUEST, "el producto no fue encontrado");
		}
		producto.setNombre(productoDto.getNombre());
		producto.setPrecio(productoDto.getPrecio());
		Producto nuevoProducto = productoRepository.save(producto);
		if(nuevoProducto == null) {
			throw new AppException(HttpStatus.BAD_REQUEST, "el producto no fue actualizado");
		}
		ProductoDto nuevoProductoDto = mapearADto(nuevoProducto);
		return new RestResponse(HttpStatus.OK, "el producto fue actualizado", nuevoProductoDto); 
	}

	@Override
	public RestResponse eliminarProducto(Long id) {
		Producto producto = productoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("producto", "id", id));
		if(producto == null) {
			throw new AppException(HttpStatus.BAD_REQUEST, "el producto no fue encontrado");
		}
		productoRepository.delete(producto);
		ProductoDto nuevoProductoDto = mapearADto(producto);
		return new RestResponse(HttpStatus.OK, "el producto fue eliminado", nuevoProductoDto); 
	}
	
	private Producto mapearAEntidad(ProductoDto productoDto) {
		Producto producto = modelMapper.map(productoDto, Producto.class);
		return producto;
	}

	private ProductoDto mapearADto(Producto producto) {
		ProductoDto productoDto = modelMapper.map(producto, ProductoDto.class);
		return productoDto;
	}

}
