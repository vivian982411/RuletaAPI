package com.ibm.academia.apirest.RuletaAPI.services;

import com.ibm.academia.apirest.RuletaAPI.models.dto.ApuestaDTO;
import com.ibm.academia.apirest.RuletaAPI.models.dto.CrearApuestaDTO;
import com.ibm.academia.apirest.RuletaAPI.models.entities.Apuesta;

import java.util.List;

public interface ApuestaDAO extends GenericoDAO<Apuesta>{
    public CrearApuestaDTO crearApuesta(Integer ruletaId, Integer cantidad, Integer numero, String color);
    public List<ApuestaDTO> cerrarApuestas(Integer ruletaId);

}
