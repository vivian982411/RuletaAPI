package com.ibm.academia.apirest.RuletaAPI.models.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApuestaDTO {
    private Integer id;
    private Integer cantidad;
    private String resultado;
}
