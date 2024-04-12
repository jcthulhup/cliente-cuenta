package com.app.cuentas.repository;

import com.app.cuentas.entity.Cuenta;
import com.app.cuentas.entity.TipoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    @Query("SELECT c FROM Cuenta c WHERE UPPER(c.ctaTipoCuenta.tctaNombre) = :tipoCuenta AND c.ctaNumCuenta = :numCuenta")
    Cuenta findbyTipoyNumCuenta(@Param("tipoCuenta") String tipoCuenta, @Param("numCuenta") String numCuenta);

    @Query("SELECT c FROM Cuenta c WHERE c.ctaCliId = :cliId")
    List<Cuenta> findByClienteId(@Param("cliId") Integer cliId);
}
