package com.todocodeacademy.carritos_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarritoDTO {
    private Long id;
    private Double precioTotal;
    private List<ProductoDTO> productosList;
}
