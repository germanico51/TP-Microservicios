package com.todocodeacademy.ventas_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductoDTO {

    private Long id;
    private String nombre;
    private String marca;
    private Double precio;

}
