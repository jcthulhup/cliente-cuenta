package com.app.clientes.service;

import com.app.clientes.dto.ClienteDto;
import com.app.clientes.dto.ClienteReq;
import com.app.clientes.entity.Cliente;
import com.app.clientes.entity.Persona;

import java.util.List;

public interface ClienteService {
    Cliente create(ClienteReq req, Persona persona);

    List<Cliente> getAll();

    Cliente getById(Integer cliId);

    ClienteDto getByIdentificacion(String iden);

    Cliente update(Integer id, ClienteReq req);

    void delete(Integer id);

}
