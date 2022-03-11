package com.springboot.backend.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.backend.models.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
