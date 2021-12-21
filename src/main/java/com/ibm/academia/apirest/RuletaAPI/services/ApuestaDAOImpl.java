package com.ibm.academia.apirest.RuletaAPI.services;

import com.ibm.academia.apirest.RuletaAPI.exceptions.BadRequestException;
import com.ibm.academia.apirest.RuletaAPI.models.dto.ApuestaDTO;
import com.ibm.academia.apirest.RuletaAPI.models.dto.CrearApuestaDTO;
import com.ibm.academia.apirest.RuletaAPI.models.entities.Apuesta;
import com.ibm.academia.apirest.RuletaAPI.models.entities.Ruleta;
import com.ibm.academia.apirest.RuletaAPI.models.entities.Tapete;
import com.ibm.academia.apirest.RuletaAPI.repositories.ApuestaRepository;
import com.ibm.academia.apirest.RuletaAPI.repositories.RuletaRepository;
import com.ibm.academia.apirest.RuletaAPI.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ApuestaDAOImpl extends GenericoDAOImpl<Apuesta, ApuestaRepository> implements ApuestaDAO {
    @Autowired
    private ApuestaRepository apuestaRepository;

    @Autowired
    public ApuestaDAOImpl(ApuestaRepository repository)
    {
        super(repository);
    }
    @Autowired
    private RuletaRepository ruletaRepository;

    @Override
    public CrearApuestaDTO crearApuesta(Integer ruletaId, Integer cantidad, Integer numero, String color) {
        if (ruletaId<0)
            throw new BadRequestException("El identificador de ruleta debe ser un numero positivo");
        if (color== null && numero==null)
            throw new BadRequestException("Debe ingresar un numero o un color");
        if (cantidad<=0)
            throw new BadRequestException("La apuesta debe ser mayor a cero");
        if (cantidad>10000)
            throw new BadRequestException("La apuesta debe ser mayor a cero");
        if (color!=null && !(color.equalsIgnoreCase("rojo" ) || color.equalsIgnoreCase("negro")))
            throw new BadRequestException("El color de apuesta no es valido");
        if (numero!=null && (numero<0 || numero>36))
            throw new BadRequestException("El numero de apuesta no es valido");

        Optional<Ruleta> optionalRuleta=ruletaRepository.findById(ruletaId);
        if (!optionalRuleta.isPresent())
            throw new NotFoundException("La ruleta no fue encontrada");

        Tapete tapete=new Tapete();
        Ruleta ruleta=optionalRuleta.get();

        if (ruleta.getSaldo()<0)
            throw new BadRequestException("No se puede apostar en la ruleta");

        Apuesta apuesta= new Apuesta(null, cantidad, numero, color, ruleta);


        if ((tapete.girarRuleta(numero, color))) {
            apuesta.setResultado("Ganador");
            ruleta.setSaldo(ruleta.getSaldo()-cantidad);
        } else {
            apuesta.setResultado("Perdedor");
            ruleta.setSaldo(ruleta.getSaldo()+cantidad);
        }
        apuesta= repository.save(apuesta);
        ruleta=ruletaRepository.save(ruleta);

        return new CrearApuestaDTO(apuesta.getId(), apuesta.getResultado());
    }

    @Override
    public List<ApuestaDTO> cerrarApuestas(Integer ruletaId) {
        if (ruletaId<0)
            throw new BadRequestException("El identificador debe ser un numero positivo");

        Optional<Ruleta> optionalRuleta =ruletaRepository.findById(ruletaId);
        if (!optionalRuleta.isPresent())
            throw new NotFoundException("El identificador no fue encontrado");

        Ruleta ruleta= optionalRuleta.get();
        ruleta.setEstado("cerrada");
        ruleta=ruletaRepository.save(ruleta);

        List<Apuesta> apuestas= (List<Apuesta>) repository.findApuestaByRuleta(ruleta);
        if (apuestas.size()<=0)
            throw new NotFoundException("No se encontraron apuestas");
        return apuestas.stream().map(apuesta->new ApuestaDTO(apuesta.getId(),apuesta.getCantidad(),apuesta.getResultado())).collect(Collectors.toList());

    }

}
