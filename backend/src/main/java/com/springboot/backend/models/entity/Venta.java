package com.springboot.backend.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "ventas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Venta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "venta_id")
	private Set<VentaDetalle> detalles = new HashSet<>();

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha", nullable = false)
	private Date fecha;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	private Date createAt;

	public Venta() {
	}

	public Venta(Cliente cliente, Date fecha, Date createAt) {
		this.cliente = cliente;
		this.fecha = fecha;
		this.createAt = createAt;
	}

	public Venta(Long id, Cliente cliente, Date fecha, Date createAt) {
		this.id = id;
		this.cliente = cliente;
		this.fecha = fecha;
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
		return "Venta [id=" + id + ", cliente=" + cliente + ", detalles=" + detalles + ", fecha=" + fecha
				+ ", createAt=" + createAt + "]";
	}

	private static final long serialVersionUID = 1L;

}
