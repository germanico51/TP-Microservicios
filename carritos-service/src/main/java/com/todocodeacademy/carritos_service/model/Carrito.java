package com.todocodeacademy.carritos_service.model;

import com.todocodeacademy.carritos_service.dto.ProductoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double precioTotal;


    @ElementCollection
    private List<ProductoDTO> productosList;
}
