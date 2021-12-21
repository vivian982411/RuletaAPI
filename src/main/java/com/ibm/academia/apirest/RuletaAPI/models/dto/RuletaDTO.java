package com.ibm.academia.apirest.RuletaAPI.models.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RuletaDTO implements Serializable {
    private Integer id;
    private String estado;
}
