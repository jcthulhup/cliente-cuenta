package com.app.clientes.service;

import com.app.clientes.dto.ClienteDto;
import com.app.clientes.dto.ClienteReq;
import com.app.clientes.entity.Cliente;
import com.app.clientes.entity.Persona;
import com.app.clientes.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public Cliente create(ClienteReq req, Persona persona) {
        Cliente cliente = new Cliente();
        cliente.setPersona(persona);
        cliente.setCliContra(req.getClave());
        cliente.setCliEstado("1");
        cliente.setCliFeCrea(new Date());
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> getAll() {
        return clienteRepository.getAll();
    }

    @Override
    public Cliente getById(Integer cliId) {
        Optional<Cliente> opCliente =  clienteRepository.findById(cliId);
        return opCliente.orElseThrow(() -> new NoSuchElementException("No existe el cliente con el id: " + cliId));
    }

    @Override
    public ClienteDto getByIdentificacion(String iden){
        ClienteDto dto = new ClienteDto();
        Cliente cliente = clienteRepository.getByIdentificacion(iden);
        if(cliente == null) throw new NoSuchElementException("No existe el cliente con el identificador: " + iden);

        dto.setPerIdentificacion(cliente.getPersona().getPerIdentificacion());
        dto.setCliId(cliente.getCliId());
        dto.setNombre(cliente.getPersona().getPerNombre());
        return dto;
    }

    @Override
    public Cliente update(Integer id, ClienteReq req) {
        Cliente cliente = getById(id);
        cliente.setCliContra(req.getClave());
        cliente.setCliFeActualiza(new Date());
        return clienteRepository.save(cliente);
    }

    @Override
    public void delete(Integer id) {
        clienteRepository.deleteById(id);
    }


}
