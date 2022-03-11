package com.springboot.backend.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.backend.models.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
