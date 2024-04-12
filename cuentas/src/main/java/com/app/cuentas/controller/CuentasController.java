package com.app.cuentas.controller;

import com.app.cuentas.dto.CuentaReq;
import com.app.cuentas.entity.Cuenta;
import com.app.cuentas.service.CuentaService;
import com.app.cuentas.utils.InvalidDataException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cuentas")
public class CuentasController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cuenta create(@Valid @RequestBody CuentaReq cuentaReq, BindingResult result) {
        log.info("create -> cuentas");
        if(result.hasErrors()){
            throw new InvalidDataException(result);
        }
        return cuentaService.create(cuentaReq);
    }

    @GetMapping
    public List<Cuenta> getAll() {
        log.info("getAll -> cuentas");
        return cuentaService.getAll();
    }

    @GetMapping("/{id}")
    public Cuenta findById(@PathVariable Integer id) {
        log.info("findById -> cuentas");
        log.info("Obtener cuenta con id {}", id);
        return cuentaService.getById(id);
    }

    @GetMapping("/identificacion/{iden}")
    public List<Cuenta> findByIden(@PathVariable String iden) {
        log.info("findByIden -> cuentas");
        log.info("Obtener cuentas con identificacion {}", iden);
        return cuentaService.getByIden(iden);
    }

    @PutMapping("/{id}")
    public Cuenta update(@PathVariable Integer id,
                          @RequestBody CuentaReq cuentaReq) {
        log.info("update -> cuentas");
        log.info("Actualizando cuentas con id {} y dto {}", id, cuentaReq);
        return cuentaService.update(id, cuentaReq);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        log.info("delete -> cuentas");
        log.info("Eliminar cuenta con id {}", id);
        cuentaService.delete(id);
    }
}
