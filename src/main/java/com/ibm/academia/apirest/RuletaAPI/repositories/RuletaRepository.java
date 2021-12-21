package com.ibm.academia.apirest.RuletaAPI.repositories;

import com.ibm.academia.apirest.RuletaAPI.models.entities.Ruleta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RuletaRepository extends CrudRepository<Ruleta,Integer> {

    public Optional<Ruleta> findById(Integer id);
}
