package com.app.cuentas.repository;

import com.app.cuentas.dto.CuentaDto;
import com.app.cuentas.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

    @Query("SELECT new com.app.cuentas.dto.CuentaDto(" +
            "TO_CHAR(mov.movFecha, 'DD/MM/YYYY') AS fecha," +
            "mov.movCtaId.ctaNumCuenta AS numCuenta," +
            "mov.movCtaId.ctaTipoCuenta.tctaNombre AS tipoCuenta," +
            "mov.movSaldoIni AS saldoInicioMovimiento," +
            "CASE WHEN (mov.movEstado = '1') THEN TRUE ELSE FALSE END AS estado," +
            "mov.movValor AS movimiento," +
            "mov.movSaldoFin AS saldoFinMovimiento," +
            "mov.movCtaId.ctaSaldoTotal AS saldoDisponibleCuenta" +
            ") FROM Movimiento mov " +
            "WHERE mov.movCtaId.ctaCliId = :cliId AND " +
            "mov.movFecha BETWEEN :inicio AND :fin")
    List<CuentaDto> obtenerReporteMovimientos(
            @Param("inicio") Date inicio,
            @Param("fin") Date fin,
            @Param("cliId") Integer cliId);
}
