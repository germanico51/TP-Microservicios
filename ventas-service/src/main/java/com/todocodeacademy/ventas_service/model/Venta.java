package com.todocodeacademy.ventas_service.model;

import com.todocodeacademy.ventas_service.dto.CarritoDTO;
import com.todocodeacademy.ventas_service.dto.ProductoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    private Long idCarrito;

}
