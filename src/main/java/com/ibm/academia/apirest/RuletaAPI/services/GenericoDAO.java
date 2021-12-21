package com.ibm.academia.apirest.RuletaAPI.services;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GenericoDAO<E> {
    public Optional<E> buscarPorId(Integer id);
    public E guardar(E entidad);
    public Iterable<E> buscarTodos();
    public void eliminarPorId(Integer id);
}
