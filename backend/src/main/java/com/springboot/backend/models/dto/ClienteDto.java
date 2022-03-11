package com.springboot.backend.models.dto;

import java.util.Date;
import java.util.Set;
import javax.validation.constraints.*;
import com.springboot.backend.models.entity.Venta;

public class ClienteDto {

	private Long id;

	@NotEmpty
	private String nombres;

	@NotEmpty
	private String apellidos;

	@NotEmpty
	private String dni;

	private String telefono;
	private String email;
	private Set<Venta> ventas;

	private Date createAt;

	public ClienteDto() {
	}

	public ClienteDto(@NotEmpty String nombres, @NotEmpty String apellidos, @NotEmpty String dni, String telefono,
			String email, Set<Venta> ventas) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
		this.ventas = ventas;
	}

	public ClienteDto(Long id, @NotEmpty String nombres, @NotEmpty String apellidos, @NotEmpty String dni,
			String telefono, String email, Set<Venta> ventas) {
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
		this.ventas = ventas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

}
