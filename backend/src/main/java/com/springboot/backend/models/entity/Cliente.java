package com.springboot.backend.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clientes", uniqueConstraints = { @UniqueConstraint(columnNames = { "dni" }) })
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombres", nullable = false)
	private String nombres;

	@Column(name = "apellidos", nullable = false)
	private String apellidos;

	@Column(name = "dni", length = 8, nullable = false)
	private String dni;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "email")
	private String email;

	@JsonBackReference
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Venta> ventas = new HashSet<>();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at", nullable = false)
	private Date createAt;

	public Cliente() {
	}

	public Cliente(String nombres, String apellidos, String dni, String telefono, String email, Set<Venta> ventas,
			Date createAt) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
		this.ventas = ventas;
		this.createAt = createAt;
	}

	public Cliente(Long id, String nombres, String apellidos, String dni, String telefono, String email,
			Set<Venta> ventas, Date createAt) {
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
		this.ventas = ventas;
		this.createAt = createAt;
	}

	@PrePersist
	public void prePersist() {
		createAt = new Date();
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

	private static final long serialVersionUID = 1L;

}
