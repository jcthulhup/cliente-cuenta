package com.app.cuentas.repository;

import com.app.cuentas.entity.Cuenta;
import com.app.cuentas.entity.TipoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TipoCuentaRepository extends JpaRepository<TipoCuenta, Integer> {

    @Query("SELECT tc FROM TipoCuenta tc WHERE UPPER(tc.tctaNombre) = :nombre")
    TipoCuenta findTCByNombre(@Param("nombre") String nombre);
}
