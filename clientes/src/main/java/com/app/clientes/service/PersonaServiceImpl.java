package com.app.clientes.service;

import com.app.clientes.dto.PersonaReq;
import com.app.clientes.entity.Cliente;
import com.app.clientes.entity.Persona;
import com.app.clientes.repository.PersonaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;
    @Override
    public Persona create(PersonaReq req) {
        Persona personaVerif = new Persona(req.getIdentificacion());
        if (personaRepository.exists(Example.of(personaVerif))) {
            throw new DuplicateKeyException("Ya existe persona con identificacion: " + req.getIdentificacion());
        }

        if(!req.getGenero().equals("M") && !req.getGenero().equals("F")){
            log.info("Género debe ser M (masculino) o F (femenino)");
            throw new IllegalArgumentException("Género debe ser M (masculino) o F (femenino)");
        }
        if(req.getEdad() < 18){
            log.info("Persona debe ser mayor de edad");
            throw new IllegalArgumentException("Persona debe ser mayor de edad");
        }
        Persona persona = new Persona();
        persona.setPerNombre(req.getNombres());
        persona.setPerDireccion(req.getDireccion());
        persona.setPerEdad(req.getEdad());
        persona.setPerGenero(req.getGenero());
        persona.setPerIdentificacion(req.getIdentificacion());
        persona.setPerTelefono(req.getTelefono());
        persona.setPerFeCrea(new Date());
        return personaRepository.save(persona);
    }

    @Override
    public List<Persona> getAll() {
        return personaRepository.findAll();
    }

    @Override
    public Persona getById(Integer perId) {
        Optional<Persona> opPersona= personaRepository.findById(perId);
        return opPersona.orElseThrow(() -> new NoSuchElementException("No existe la persona"));
    }

    @Override
    public Persona update(Integer id, Persona req) {
        Persona persona = getById(id);
        persona.setPerNombre(req.getPerNombre());
        persona.setPerIdentificacion(req.getPerIdentificacion());
        persona.setPerGenero(req.getPerGenero());
        persona.setPerDireccion(req.getPerDireccion());
        persona.setPerTelefono(req.getPerTelefono());
        persona.setPerFeActualiza(new Date());
        return personaRepository.save(persona);
    }
}
