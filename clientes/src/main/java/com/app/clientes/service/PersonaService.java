package com.app.clientes.service;

import com.app.clientes.dto.PersonaReq;
import com.app.clientes.entity.Persona;

import java.util.List;

public interface PersonaService {
    Persona create(PersonaReq req);

    List<Persona> getAll();

    Persona getById(Integer perId);

    Persona update(Integer id, Persona req);

    void delete(Integer id);
}
