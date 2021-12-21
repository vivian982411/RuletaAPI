package com.ibm.academia.apirest.RuletaAPI.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "ruletas", schema = "ruletas")
@ToString
public class Ruleta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull( message = "estado no puede ser nulo")
    @NotEmpty(message = "estado no puede estar vacio")
    @Size( min = 5, max = 80, message = "nombre debe tener entre 5 y 80 caracteres")
    @Column(name = "estado", unique = true, nullable = false, length = 80)
    private String estado;

    @Column(name = "saldo")
    private Integer saldo;

    @ManyToMany(mappedBy = "ruleta", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","ruleta"})
    private Set<Apuesta> apuestas;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    public Ruleta(Integer id, String estado) {
        this.id = id;
        this.estado = estado;
        this.saldo = 10000;
    }

    @PrePersist
    private void antesPersistir()
    {
        this.fechaCreacion = new Date();
    }

    @PreUpdate
    private void antesActualizar()
    {
        this.fechaModificacion = new Date();
    }

}
