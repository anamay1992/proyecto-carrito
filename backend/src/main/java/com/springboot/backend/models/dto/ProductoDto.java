package com.springboot.backend.models.dto;

import java.util.Date;

public class ProductoDto {

	private Long id;
	private String nombre;
	private Double precio;
	private Date createAt;

	public ProductoDto() {
	}

	public ProductoDto(String nombre, Double precio, Date createAt) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.createAt = createAt;
	}

	public ProductoDto(Long id, String nombre, Double precio, Date createAt) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.createAt = createAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return "ProductoDto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", createAt=" + createAt + "]";
	}

}
