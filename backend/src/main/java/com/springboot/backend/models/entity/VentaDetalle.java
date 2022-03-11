package com.springboot.backend.models.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "venta_detalles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VentaDetalle implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer cantidad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_id")
	private Producto producto;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	private Date createAt;

	public VentaDetalle() {
	}

	public VentaDetalle(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public VentaDetalle(Long id, Integer cantidad) {
		this.id = id;
		this.cantidad = cantidad;
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

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	

	@Override
	public String toString() {
		return "VentaDetalle [id=" + id + ", cantidad=" + cantidad + ", producto=" + producto + ", createAt=" + createAt
				+ "]";
	}

	private static final long serialVersionUID = 1L;

}
