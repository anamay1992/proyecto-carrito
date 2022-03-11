package com.springboot.backend.models.services;

import java.text.*;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springboot.backend.exceptions.AppException;
import com.springboot.backend.exceptions.ResourceNotFoundException;
import com.springboot.backend.models.dto.VentaDto;
import com.springboot.backend.models.entity.Cliente;
import com.springboot.backend.models.entity.Venta;
import com.springboot.backend.models.repositories.ClienteRepository;
import com.springboot.backend.models.repositories.VentaRepository;
import com.springboot.backend.utils.RestResponse;

@Service
public class VentaServiceImpl implements VentaService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	@Transactional(readOnly = true)
	public RestResponse obtenerVentas() {
		List<Venta> ventas = ventaRepository.findAll();
		if (ventas.size() == 0) {
			throw new AppException(HttpStatus.BAD_REQUEST, "lista vacia");
		}
		List<VentaDto> ventasDto = ventas.stream().map(venta -> mapearADto(venta)).collect(Collectors.toList());
		return new RestResponse(HttpStatus.OK, "lista de ventas", ventasDto);
	}
	
	@Override
	@Transactional(readOnly = true)
	public RestResponse obtenerVentasPorClienteId(Long ClienteId) {
		List<Venta> ventas = ventaRepository.findByClienteId(ClienteId);
		if (ventas.size() == 0) {
			throw new AppException(HttpStatus.BAD_REQUEST, "lista vacia");
		}
		List<VentaDto> ventasDto = ventas.stream().map(venta -> mapearADto(venta)).collect(Collectors.toList());
		return new RestResponse(HttpStatus.OK, "lista de ventas", ventasDto);
	}
	
	@Override
	@Transactional(readOnly = true)
	public RestResponse obtenerVentaPorId(Long id) {
		Venta venta = ventaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("venta", "id", id));
		if (venta == null) {
			throw new AppException(HttpStatus.BAD_REQUEST, "la venta no fue encontrada");
		}
		VentaDto ventaDto = mapearADto(venta);
		return new RestResponse(HttpStatus.OK, "venta encontrada", ventaDto);
	}

	@Override
	@Transactional(readOnly = true)
	public RestResponse obtenerVentaPorClienteIdYVentaId(Long clienteId, Long ventaId) {
		Venta venta = ventaRepository.findByClienteIdAndVentaId(clienteId, ventaId);
		if (venta == null) {
			throw new AppException(HttpStatus.BAD_REQUEST, "la venta no fue encontrada");
		}
		VentaDto ventaDto = mapearADto(venta);
		return new RestResponse(HttpStatus.OK, "venta encontrada", ventaDto);
	}
	
	@Override
	@Transactional(readOnly = true)
	public RestResponse obtenerVentaPorFechaVenta(String fecha) throws ParseException {
		List<Venta> ventas = ventaRepository.findByFechaVenta(new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
		if (ventas.size() == 0) {
			throw new AppException(HttpStatus.BAD_REQUEST, "lista vacia");
		}
		List<VentaDto> ventasDto = ventas.stream().map(venta -> mapearADto(venta)).collect(Collectors.toList());
		return new RestResponse(HttpStatus.OK, "lista de ventas por fecha", ventasDto);
	}
	
	@Override
	@Transactional(readOnly = true)
	public RestResponse obtenerVentaPorClienteIdYFechaVenta(Long clienteId, String fecha) throws ParseException {
		List<Venta> ventas = ventaRepository.findByClienteIdAndFechaVenta(clienteId, new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
		if (ventas.size() == 0) {
			throw new AppException(HttpStatus.BAD_REQUEST, "lista vacia");
		}
		List<VentaDto> ventasDto = ventas.stream().map(venta -> mapearADto(venta)).collect(Collectors.toList());
		return new RestResponse(HttpStatus.OK, "lista de ventas por fecha", ventasDto);
	}

	@Override
	@Transactional
	public RestResponse guardarVenta(Long clienteId, VentaDto ventaDto) {
		Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new ResourceNotFoundException("cliente", "id", clienteId));
		Venta venta = mapearAEntidad(ventaDto);
		venta.setCliente(cliente);
		Venta nuevaVenta = ventaRepository.save(venta);
		if (nuevaVenta == null) {
			throw new AppException(HttpStatus.BAD_REQUEST, "la venta no fue guardada");
		}
		VentaDto nuevaVentaDto = mapearADto(nuevaVenta);
		return new RestResponse(HttpStatus.OK, "venta guardada", nuevaVentaDto);
	}

	@Override
	@Transactional
	public RestResponse actualizarVenta(Long clienteId, VentaDto ventaDto, Long ventaId) {
		Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new ResourceNotFoundException("cliente", "id", clienteId));
		Venta venta = ventaRepository.findById(ventaId).orElseThrow(() -> new ResourceNotFoundException("venta", "id", ventaId));
		if (!venta.getCliente().getId().equals(cliente.getId())) {
			throw new AppException(HttpStatus.BAD_REQUEST, "la venta no fue encontrada");
		}
		venta.setFecha(ventaDto.getFecha());
		Venta nuevaVenta = ventaRepository.save(venta);
		if (nuevaVenta == null) {
			throw new AppException(HttpStatus.BAD_REQUEST, "la venta no fue guardada");
		}
		VentaDto nuevaVentaDto = mapearADto(nuevaVenta);		
		return new RestResponse(HttpStatus.OK, "venta actualizada", nuevaVentaDto);		
	}

	@Override
	@Transactional
	public RestResponse eliminarVenta(Long clienteId, Long ventaId) {
		Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new ResourceNotFoundException("cliente", "id", clienteId));
		Venta venta = ventaRepository.findById(ventaId).orElseThrow(() -> new ResourceNotFoundException("venta", "id", ventaId));
		if (venta == null) {
			throw new AppException(HttpStatus.BAD_REQUEST, "la venta no fue guardada");
		}
		if (!venta.getCliente().getId().equals(cliente.getId())) {
			throw new AppException(HttpStatus.BAD_REQUEST, "la venta no fue encontrada");
		}
		ventaRepository.delete(venta);
		VentaDto nuevaVentaDto = mapearADto(venta);
		return new RestResponse(HttpStatus.OK, "venta eliminada", nuevaVentaDto);		
	}
	
	private Venta mapearAEntidad(VentaDto ventaDto) {
		Venta venta = modelMapper.map(ventaDto, Venta.class);
		return venta;
	}

	private VentaDto mapearADto(Venta venta) {
		VentaDto ventaDto = modelMapper.map(venta, VentaDto.class);
		return ventaDto;
	}

}
