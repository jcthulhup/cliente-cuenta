package com.app.clientes.test;

import com.app.clientes.dto.ClienteDto;
import com.app.clientes.dto.ClienteReq;
import com.app.clientes.entity.Cliente;
import com.app.clientes.entity.Persona;
import com.app.clientes.service.ClienteService;
import com.app.clientes.service.PersonaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@SpringBootTest
public class ClienteTest {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PersonaService personaService;

    @Test
    public void testExceptionByEdad(){
        ClienteReq clienteReq = new ClienteReq();
        clienteReq.setClave("1133423");
        clienteReq.setNombres("persona test 2");
        clienteReq.setDireccion("Av Peru 123");
        clienteReq.setIdentificacion("1235478");
        clienteReq.setGenero("M");
        clienteReq.setEdad(17);
        clienteReq.setTelefono("987654321");
        assertThrows(IllegalArgumentException.class,
                ()->{
                    personaService.create(clienteReq);
                });
    }

    @Test
    public void testSavePersona(){
        ClienteReq clienteReq = new ClienteReq();
        clienteReq.setClave("11334455");
        clienteReq.setNombres("persona test 2");
        clienteReq.setDireccion("Av Peru 123");
        clienteReq.setIdentificacion("123545");
        clienteReq.setGenero("M");
        clienteReq.setEdad(18);
        clienteReq.setTelefono("987654321");
        Persona persona = personaService.create(clienteReq);
        Assertions.assertNotNull(persona);
        Cliente cliente = clienteService.create(clienteReq, persona);
        Assertions.assertNotNull(cliente);
    }

    @Test
    public void testGetPersonaByIdentificacion(){
        ClienteReq clienteReq = new ClienteReq();
        clienteReq.setClave("11334455");
        clienteReq.setNombres("persona test 2");
        clienteReq.setDireccion("Av Peru 123");
        clienteReq.setIdentificacion("123666");
        clienteReq.setGenero("M");
        clienteReq.setEdad(18);
        clienteReq.setTelefono("987654321");
        Persona persona = personaService.create(clienteReq);
        Cliente cliente = clienteService.create(clienteReq, persona);

        String identification = "123666";
        ClienteDto clienteDto = clienteService.getByIdentificacion(identification);
        Assertions.assertEquals(identification, clienteDto.getPerIdentificacion());
    }

    @Test
    public void testGetPersonaByIdentificacionException(){
        String identification = "9999999999";
        assertThrows(NoSuchElementException.class,
                ()->{
                    clienteService.getByIdentificacion(identification);
                });
    }
}
