package com.todocodeacademy.ventas_service.repository;

import com.todocodeacademy.ventas_service.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<Venta,Long> {
}
