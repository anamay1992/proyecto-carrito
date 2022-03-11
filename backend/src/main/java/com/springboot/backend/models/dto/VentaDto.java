package com.springboot.backend.models.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.*;
import com.springboot.backend.models.entity.Cliente;
import com.springboot.backend.models.entity.VentaDetalle;

public class VentaDto {

	private Long id;
	private Cliente cliente;

	@NotEmpty
	private Date fecha;

	private Set<VentaDetalle> detalles;

	private Date createAt;

	public VentaDto(Cliente cliente, @NotEmpty Date fecha) {
		this.cliente = cliente;
		this.fecha = fecha;
	}

	public VentaDto() {
	}

	public VentaDto(Long id, Cliente cliente, @NotEmpty Date fecha) {
		this.id = id;
		this.cliente = cliente;
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<VentaDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(Set<VentaDetalle> detalles) {
		this.detalles = detalles;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return "VentaDto [id=" + id + ", cliente=" + cliente + ", fecha=" + fecha + ", createAt=" + createAt + "]";
	}

}
