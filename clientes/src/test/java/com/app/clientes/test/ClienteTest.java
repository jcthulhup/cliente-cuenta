package com.app.clientes.test;

import com.app.clientes.dto.ClienteDto;
import com.app.clientes.dto.ClienteReq;
import com.app.clientes.entity.Cliente;
import com.app.clientes.entity.Persona;
import com.app.clientes.service.ClienteService;
import com.app.clientes.service.PersonaService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@SpringBootTest
@DisplayName("Test cliente")
public class ClienteTest {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PersonaService personaService;

    @Test
    @DisplayName("Genera excepción por ser menor de edad")
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
    @DisplayName("Guardar una persona-cliente")
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

    @Nested
    @DisplayName("Test requiere tener un cliente creado")
    public class testInitGetPersonaxIdentificacion{
        @BeforeEach
        public void setup(){
            ClienteReq clienteReq = new ClienteReq();
            clienteReq.setClave("11334455");
            clienteReq.setNombres("persona test 2");
            clienteReq.setDireccion("Av Peru 123");
            clienteReq.setIdentificacion("123666");
            clienteReq.setGenero("M");
            clienteReq.setEdad(18);
            clienteReq.setTelefono("987654321");
            Persona persona = personaService.create(clienteReq);
            clienteService.create(clienteReq, persona);
        }

        @Test
        @DisplayName("Obtener clienteDto mandandole la identificacion")
        public void testGetPersonaByIdentificacion(){
            String identification = "123666";
            ClienteDto clienteDto = clienteService.getByIdentificacion(identification);
            Assertions.assertEquals(identification, clienteDto.getPerIdentificacion());
        }
    }

    @Test
    @DisplayName("Generar error por persona con identificacion no existente")
    public void testGetPersonaByIdentificacionException(){
        String identification = "9999999999";
        assertThrows(NoSuchElementException.class,
                ()->{
                    clienteService.getByIdentificacion(identification);
                });
    }

    @Nested
    @DisplayName("Test requiere tener un cliente creado para luego eliminarlo")
    public class testInitEliminarPersona{
        @BeforeEach
        public void setup(){
            ClienteReq clienteReq = new ClienteReq();
            clienteReq.setClave("11334455");
            clienteReq.setNombres("persona test 2");
            clienteReq.setDireccion("Av Peru 123");
            clienteReq.setIdentificacion("123666");
            clienteReq.setGenero("M");
            clienteReq.setEdad(18);
            clienteReq.setTelefono("987654321");
            Persona persona = personaService.create(clienteReq);
            clienteService.create(clienteReq, persona);
        }

        @Test
        @DisplayName("Eliminar cliente y validar no existencia")
        public void testDeleteByIdentificacion(){
            String identification = "123666";
            ClienteDto clienteDto = clienteService.getByIdentificacion(identification);
            Assertions.assertEquals(identification, clienteDto.getPerIdentificacion());
            clienteService.delete(clienteDto.getCliId());

            assertThrows(NoSuchElementException.class,
                    ()->{
                        clienteService.getByIdentificacion(identification);
                    });
        }
    }
}
