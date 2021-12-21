package com.ibm.academia.apirest.RuletaAPI.services;

import com.ibm.academia.apirest.RuletaAPI.models.dto.AbrirRuletaDTO;
import com.ibm.academia.apirest.RuletaAPI.models.dto.CrearRuletaDTO;
import com.ibm.academia.apirest.RuletaAPI.models.dto.RuletaDTO;
import com.ibm.academia.apirest.RuletaAPI.models.entities.Ruleta;

import java.util.List;

public interface RuletaDAO extends GenericoDAO<Ruleta> {
    public CrearRuletaDTO crearRuleta();
    public AbrirRuletaDTO abrirRuleta(Integer ruletaId);
    public List<RuletaDTO> listar();

}
