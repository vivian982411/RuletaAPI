package com.ibm.academia.apirest.RuletaAPI.services;

import com.ibm.academia.apirest.RuletaAPI.exceptions.BadRequestException;
import com.ibm.academia.apirest.RuletaAPI.models.dto.AbrirRuletaDTO;
import com.ibm.academia.apirest.RuletaAPI.models.dto.CrearRuletaDTO;
import com.ibm.academia.apirest.RuletaAPI.models.dto.RuletaDTO;
import com.ibm.academia.apirest.RuletaAPI.models.entities.Ruleta;
import com.ibm.academia.apirest.RuletaAPI.repositories.RuletaRepository;
import com.ibm.academia.apirest.RuletaAPI.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RuletaDAOImpl  extends GenericoDAOImpl<Ruleta, RuletaRepository> implements RuletaDAO{
    @Autowired
    private RuletaRepository ruletaRepository;

    @Autowired
    public RuletaDAOImpl(RuletaRepository repository)
    {
        super(repository);
    }


    @Override
    public CrearRuletaDTO crearRuleta() {
        Ruleta ruleta= new Ruleta(null,"cerrada");
        ruleta=repository.save(ruleta);
        return new CrearRuletaDTO(ruleta.getId());
    }

    @Override
    public AbrirRuletaDTO abrirRuleta(Integer ruletaId) {
        AbrirRuletaDTO response= new AbrirRuletaDTO();
        if (ruletaId<0)
            throw new BadRequestException("El identificador debe ser un numero positivo");

        Optional<Ruleta> optionalRuleta =repository.findById(ruletaId);
        if (!optionalRuleta.isPresent())
            throw new NotFoundException("El identificador no fue encontrado");

        Ruleta ruleta= optionalRuleta.get();
        ruleta.setEstado("abierta");
        repository.save(ruleta);
        response.setEstado("Operacion Exitosa");
        return response;
    }

    @Override
    public List<RuletaDTO> listar() {
        List<Ruleta> ruletas= (List<Ruleta>) repository.findAll();
        if (ruletas.size()<=0)
            throw new NotFoundException("No se encontraron ruletas");

        List<RuletaDTO> ruletaDTOList=ruletas
                .stream()
                .map(ruleta-> new RuletaDTO(ruleta.getId(), ruleta.getEstado()))
                .collect(Collectors.toList());
        return  ruletaDTOList;
    }
}
