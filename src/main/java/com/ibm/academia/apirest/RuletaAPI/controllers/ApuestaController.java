package com.ibm.academia.apirest.RuletaAPI.controllers;

import com.ibm.academia.apirest.RuletaAPI.services.ApuestaDAO;
import com.ibm.academia.apirest.RuletaAPI.services.RuletaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apuesta")
public class ApuestaController {
    @Autowired
    private ApuestaDAO apuestaDAO;

    /**
     * Endpoint para crear una apuesta
     * @param cantidad Cantidad de "dinero a apostar"
     * @param ruletaId Identificador de la ruleta
     * @param numero Numero por el que se apostara
     * @param color Color por el que se apostara
     * @return Objeto de tipo CrearApuestaDTO con el identificador de la apuesta  y su estado
     * @BadRequestException Si se encuentra algun error en los datos ingresados
     * @NotFoundException en caso de no encontrar una ruleta con el identificador dado
     * @Author Vivian Juarez 20/12/2021
     */
    @PostMapping("/apostar")
    public ResponseEntity<?> crearApuesta(@RequestParam Integer cantidad,
                                          @RequestParam Integer ruletaId,
                                          @RequestParam(required = false) Integer numero,
                                          @RequestParam(required = false) String color){
        return new ResponseEntity<>(apuestaDAO.crearApuesta(ruletaId,cantidad,numero,color), HttpStatus.CREATED);
    }

    /**
     * Endpoint para cerrar las apuestas de una ruleta y mostrar todas las apuestas que se han hecho en ella
     * @param ruletaId Identificador de la ruleta
     * @return Lista de ApuestaDTO con las apuestas que se han hecho por la ruleta con el identificador dado
     * @BadRequestException Si se encuentra algun error en los datos ingresados
     * @NotFoundException en caso de no encontrar una ruleta con el identificador dado
     * @Author Vivian Juarez 20/12/2021
     */
    @PostMapping("/cerrarApuestas/ruletaId/{ruletaId}")
    public ResponseEntity<?> cerrarApuestas(Integer ruletaId){
        return new ResponseEntity<>(apuestaDAO.cerrarApuestas(ruletaId), HttpStatus.OK);
    }
}
