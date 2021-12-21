package com.ibm.academia.apirest.RuletaAPI.models.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AbrirRuletaDTO implements Serializable {
    private String estado;
}
