package com.app.cuentas.controller;

import com.app.cuentas.dto.CuentaReq;
import com.app.cuentas.dto.MovimientoReq;
import com.app.cuentas.entity.Cuenta;
import com.app.cuentas.entity.Movimiento;
import com.app.cuentas.service.MovimientoService;
import com.app.cuentas.utils.InvalidDataException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/movimientos")
public class MovimientosController {
    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movimiento create(@Valid @RequestBody MovimientoReq movimientoReq, BindingResult result) {
        log.info("create -> movimientos");
        if(result.hasErrors()){
            throw new InvalidDataException(result);
        }
        if(movimientoReq.getMonto().compareTo(BigDecimal.ZERO)==0) throw new IllegalArgumentException("Monto del movimiento no puede ser cero");
        return movimientoService.create(movimientoReq);
    }


    @GetMapping
    public List<Movimiento> getAll() {
        log.info("getAll -> movimientos");
        return movimientoService.getAll();
    }

    @GetMapping("/{id}")
    public Movimiento findById(@PathVariable Integer id) {
        log.info("findById -> movimientos");
        log.info("Obtener movimiento con id {}", id);
        return movimientoService.getById(id);
    }

    @PutMapping("/{id}")
    public Movimiento update(@PathVariable Integer id,
                         @RequestBody MovimientoReq movimientoReq) {
        log.info("update -> movimientos");
        log.info("Actualizando movimiento con id {} y dto {}", id, movimientoReq);
        return movimientoService.update(id, movimientoReq);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        log.info("delete -> movimientos");
        log.info("Eliminar movimiento con id {}", id);
        movimientoService.delete(id);
    }
}
