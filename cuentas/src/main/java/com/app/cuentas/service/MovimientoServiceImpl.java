package com.app.cuentas.service;

import com.app.cuentas.dto.MovimientoReq;
import com.app.cuentas.entity.Cuenta;
import com.app.cuentas.entity.Movimiento;
import com.app.cuentas.repository.CuentaRepository;
import com.app.cuentas.repository.MovimientoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class MovimientoServiceImpl implements MovimientoService{
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Override
    public Movimiento create(MovimientoReq req) {
        Cuenta cuenta = cuentaRepository.findbyTipoyNumCuenta(req.getTipoCuenta().toUpperCase(), req.getNumeroCuenta());
        if(cuenta == null) throw new IllegalArgumentException("Cuenta de tipo " + req.getTipoCuenta() + " y numero " +  req.getNumeroCuenta() + " no existe");

        Movimiento movimiento = new Movimiento();
        movimiento.setMovEstado("1");
        movimiento.setMovFecha(new Date());
        movimiento.setMovCtaId(cuenta);
        movimiento.setMovValor(req.getMonto());
        movimiento.setMovSaldoIni(cuenta.getCtaSaldoTotal());
        movimiento.setMovSaldoFin(cuenta.getCtaSaldoTotal().add(movimiento.getMovValor()));
        if(movimiento.getMovSaldoFin().compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Saldo no disponible");

        //Actualizar cuenta
        cuenta.setCtaSaldoTotal(movimiento.getMovSaldoFin());
        cuenta.setCtaFeActualiza(new Date());
        cuentaRepository.save(cuenta);

        return movimientoRepository.save(movimiento);
    }

    @Override
    public List<Movimiento> getAll() {
        return movimientoRepository.findAll();
    }

    @Override
    public Movimiento getById(Integer movId) {
        Optional<Movimiento> opMovimiento =  movimientoRepository.findById(movId);
        return opMovimiento.orElseThrow(() -> new NoSuchElementException("No existe el movimiento con el id: " + movId));
    }

    @Override
    public Movimiento update(Integer id, MovimientoReq req) {
        Movimiento movimiento = getById(id);
        movimiento.setMovFecha(new Date());
        return movimientoRepository.save(movimiento);
    }

    @Override
    public void delete(Integer id) {
        movimientoRepository.deleteById(id);
    }
}
