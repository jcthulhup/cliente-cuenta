package com.app.clientes.repository;


import com.app.clientes.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query("SELECT cl FROM Cliente cl")
    List<Cliente> getAll();

    @Query("SELECT cl FROM Cliente cl WHERE cl.persona.perIdentificacion = :iden")
    Cliente getByIdentificacion(@Param("iden") String iden);

}
