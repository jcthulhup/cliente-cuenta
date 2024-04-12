package com.app.clientes.controller;

import com.app.clientes.dto.ClienteDto;
import com.app.clientes.dto.ClienteReq;
import com.app.clientes.entity.Cliente;
import com.app.clientes.entity.Persona;
import com.app.clientes.service.ClienteService;
import com.app.clientes.service.PersonaService;
import com.app.clientes.utils.InvalidDataException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/clientes")
public class ClientesController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PersonaService personaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente create(@Valid @RequestBody ClienteReq clienteReq, BindingResult result) {
        log.info("create -> clientes");
        if(result.hasErrors()){
            throw new InvalidDataException(result);
        }
        Persona persona = personaService.create(clienteReq);
        return clienteService.create(clienteReq, persona);
    }

    @GetMapping
    public List<Cliente> getAll() {
        log.info("getAll -> clientes");
        return clienteService.getAll();
    }

    @GetMapping("/{id}")
    public Cliente findById(@PathVariable Integer id) {
        log.info("findById -> clientes");
        log.info("Obtener cliente con id {}", id);
        return clienteService.getById(id);
    }

    @GetMapping("/identificacion/{iden}")
    public ClienteDto findByIdentificacion(@PathVariable String iden) {
        log.info("findByIdentificacion -> clientes");
        log.info("Obtener cliente con identificacion {}", iden);
        return clienteService.getByIdentificacion(iden);
    }

    @PutMapping("/{id}")
    public Cliente update(@PathVariable Integer id,
                       @RequestBody ClienteReq clienteReq) {
        log.info("update -> clientes");
        log.info("Actualizando cliente con id {} y dto {}", id, clienteReq);
//        if (result.hasErrors()) {
//            throw new InvalidDataException(result);
//        }
        return clienteService.update(id, clienteReq);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        log.info("delete -> clientes");
        log.info("Eliminar cliente con id {}", id);
        clienteService.delete(id);
    }
}
