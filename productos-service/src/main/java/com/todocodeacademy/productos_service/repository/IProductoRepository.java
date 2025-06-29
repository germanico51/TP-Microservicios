package com.todocodeacademy.productos_service.repository;

import com.todocodeacademy.productos_service.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
}
