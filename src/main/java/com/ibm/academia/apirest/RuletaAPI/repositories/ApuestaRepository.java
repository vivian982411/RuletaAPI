package com.ibm.academia.apirest.RuletaAPI.repositories;

import com.ibm.academia.apirest.RuletaAPI.models.entities.Apuesta;
import com.ibm.academia.apirest.RuletaAPI.models.entities.Ruleta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApuestaRepository extends CrudRepository<Apuesta,Integer> {
    public Iterable<Apuesta> findApuestaByRuleta(Ruleta ruleta);
}
