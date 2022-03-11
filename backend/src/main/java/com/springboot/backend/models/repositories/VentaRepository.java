package com.springboot.backend.models.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.springboot.backend.models.entity.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {
		
	@Query("select v from Venta v join fetch v.cliente c where c.id = ?1")
	public List<Venta> findByClienteId(Long clienteId);
	
	@Query("select v from Venta v join fetch v.cliente c join fetch v.detalles d join fetch d.producto where c.id = :clienteId and v.id = :ventaId")
	public Venta findByClienteIdAndVentaId(@Param("clienteId") Long clienteId, @Param("ventaId") Long ventaId);
	
	@Query("select v from Venta v join fetch v.cliente c join fetch v.detalles d join fetch d.producto where v.fecha = :fecha")
	public List<Venta> findByFechaVenta(@Param("fecha") Date fecha);
	
	@Query("select v from Venta v join fetch v.cliente c join fetch v.detalles d join fetch d.producto where c.id = :clienteId and v.fecha = :fecha")
	public List<Venta> findByClienteIdAndFechaVenta(@Param("clienteId") Long clienteId, @Param("fecha") Date fecha);

}
