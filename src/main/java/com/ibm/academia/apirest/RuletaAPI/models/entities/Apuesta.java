package com.ibm.academia.apirest.RuletaAPI.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "apuestas", schema = "ruletas")
@ToString
public class Apuesta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "numero_apuesta")
    private Integer numeroApuesta;

    @Column(name = "color_apuesta")
    private String colorApuesta;

    @Column(name = "resultado")
    private String resultado;


    @ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "ruleta_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","apuestas"})
    private Ruleta ruleta;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    public Apuesta(Integer id, Integer cantidad, Integer numeroApuesta, String colorApuesta, Ruleta ruleta) {
        this.id = id;
        this.cantidad = cantidad;
        this.numeroApuesta = numeroApuesta;
        this.colorApuesta = colorApuesta;
        this.ruleta = ruleta;
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
