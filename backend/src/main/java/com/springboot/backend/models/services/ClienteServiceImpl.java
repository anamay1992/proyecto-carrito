package com.springboot.backend.models.services;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.backend.exceptions.AppException;
import com.springboot.backend.exceptions.ResourceNotFoundException;
import com.springboot.backend.models.dto.ClienteDto;
import com.springboot.backend.models.entity.Cliente;
import com.springboot.backend.models.repositories.ClienteRepository;
import com.springboot.backend.utils.RestResponse;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	@Transactional(readOnly = true)
	public RestResponse obtenerClientes() {
		List<Cliente> clientes = clienteRepository.findAll();
		if (clientes.size() == 0) {
			throw new AppException(HttpStatus.BAD_REQUEST, "lista vacia");
		}
		List<ClienteDto> clientesDto = clientes.stream().map(cliente -> mapearADto(cliente))
				.collect(Collectors.toList());
		return new RestResponse(HttpStatus.OK, "lista de clientes", clientesDto);
	}

	@Override
	@Transactional(readOnly = true)
	public RestResponse obtenerClientePorId(Long id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("cliente", "id", id));
		if (cliente == null) {
			throw new AppException(HttpStatus.BAD_REQUEST, "el cliente no fue encontrado");
		}
		ClienteDto clienteDto = mapearADto(cliente);
		return new RestResponse(HttpStatus.OK, "cliente encontrado", clienteDto);
	}

	@Override
	@Transactional
	public RestResponse guardarCliente(ClienteDto clienteDto) {
		Cliente cliente = mapearAEntidad(clienteDto);
		Cliente nuevoCliente = clienteRepository.save(cliente);
		if (nuevoCliente == null) {
			throw new AppException(HttpStatus.BAD_REQUEST, "el cliente no fue guardado");
		}
		ClienteDto nuevoClienteDto = mapearADto(nuevoCliente);
		return new RestResponse(HttpStatus.OK, "cliente guardado", nuevoClienteDto);
	}

	@Override
	@Transactional
	public RestResponse actualizarCliente(ClienteDto clienteDto, Long id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("cliente", "id", id));
		cliente.setNombres(clienteDto.getNombres());
		cliente.setApellidos(clienteDto.getApellidos());
		cliente.setDni(clienteDto.getDni());
		cliente.setTelefono(clienteDto.getTelefono());
		cliente.setEmail(clienteDto.getEmail());
		Cliente nuevoCliente = clienteRepository.save(cliente);
		if (nuevoCliente == null) {
			throw new AppException(HttpStatus.BAD_REQUEST, "el cliente no fue actualizado");
		}
		ClienteDto nuevoClienteDto = mapearADto(nuevoCliente);
		return new RestResponse(HttpStatus.OK, "cliente actualizado", nuevoClienteDto);
	}

	@Override
	public RestResponse eliminarCliente(Long id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("cliente", "id", id));
		if (cliente == null) {
			throw new AppException(HttpStatus.BAD_REQUEST, "el cliente no fue eliminado");
		}
		ClienteDto clienteDto = mapearADto(cliente);
		clienteRepository.delete(cliente);
		return new RestResponse(HttpStatus.OK, "cliente eliminado", clienteDto);
	}

	private Cliente mapearAEntidad(ClienteDto clienteDto) {
		Cliente cliente = modelMapper.map(clienteDto, Cliente.class);
		return cliente;
	}

	private ClienteDto mapearADto(Cliente cliente) {
		ClienteDto clienteDto = modelMapper.map(cliente, ClienteDto.class);
		return clienteDto;
	}

}
