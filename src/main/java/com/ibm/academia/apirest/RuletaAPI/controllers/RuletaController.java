package com.ibm.academia.apirest.RuletaAPI.controllers;

import com.ibm.academia.apirest.RuletaAPI.models.dto.CrearRuletaDTO;
import com.ibm.academia.apirest.RuletaAPI.services.RuletaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ruleta")
public class RuletaController {
    @Autowired
    private RuletaDAO ruletaDAO;

    //@HystrixCommand(fallbackMethod = "falloCrearRuleta")

    /**
     * Enpoint para crear una Ruleta
     * @return regresa un identificador de la ruleta
     * @Author Vivian Juarez 20/12/2021
     */
    @PostMapping
    public ResponseEntity<?> crearRuleta(){
        return new ResponseEntity<>(ruletaDAO.crearRuleta(), HttpStatus.CREATED);
    }

    //@HystrixCommand(fallbackMethod = "falloAbrirRuleta")

    /**
     * Endpoint para abrir una ruleta y poder apostar
     * @param ruletaId Identificador de la ruleta
     * @return Estado de la transaccion
     * @BadRequestException Si se encuentra algun error en los datos ingresados
     * @NotFoundException en caso de no encontrar una ruleta con el identificador dado
     * @Author Vivian Juarez 20/12/2021
     */
    @PutMapping("/ruletaId/{ruletaId}")
    public ResponseEntity<?> abrirRuleta(@PathVariable Integer ruletaId){
        return new ResponseEntity<>(ruletaDAO.abrirRuleta(ruletaId), HttpStatus.OK);
    }

    //@HystrixCommand(fallbackMethod = "falloListadoRuletas")

    /**
     * Endpoint para listar las ruletas existentes y su estado actual
     * @return Listado de Ruletas
     * @NotFoundException Si no se han encontrado ruletas
     * @Author Vivian Juarez 21/12/2021
     */
    @GetMapping("/listado")
    public  ResponseEntity<?> listadoRuletas(){
        return new ResponseEntity<>(ruletaDAO.listar(),HttpStatus.OK);
    }

    /*public ResponseEntity<?> falloCrearRuleta(){
        return new ResponseEntity<>(ruletaDAO.crearRuleta(), HttpStatus.CREATED);
    }
    public ResponseEntity<?> falloAbrirRuleta(@PathVariable Integer ruletaId){
        return new ResponseEntity<>(ruletaDAO.abrirRuleta(ruletaId), HttpStatus.OK);
    }

    public  ResponseEntity<?> falloListadoRuletas(){
        return new ResponseEntity<>(ruletaDAO.listar(),HttpStatus.OK);
    }*/
}
