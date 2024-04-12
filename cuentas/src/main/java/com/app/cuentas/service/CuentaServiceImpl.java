package com.app.cuentas.service;

import com.app.cuentas.client.ClienteClient;
import com.app.cuentas.client.ClienteDto;
import com.app.cuentas.dto.CuentaReq;
import com.app.cuentas.entity.Cuenta;
import com.app.cuentas.entity.TipoCuenta;
import com.app.cuentas.repository.CuentaRepository;
import com.app.cuentas.repository.TipoCuentaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CuentaServiceImpl implements CuentaService{
    @Autowired
    ClienteClient clienteClient;

    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    TipoCuentaRepository tipoCuentaRepository;

    @Override
    public Cuenta create(CuentaReq req) {
        //Validar si hay una cuenta con el numero de cuenta enviado...
        Cuenta cuentaVerif = new Cuenta(req.getNumeroCuenta());
        if (cuentaRepository.exists(Example.of(cuentaVerif))) {
        throw new DuplicateKeyException("Ya existe cuenta con número de cta: " + req.getNumeroCuenta());
        }

        //Validar el tipo de cuenta...
        TipoCuenta tipoCuenta = tipoCuentaRepository.findTCByNombre(req.getTipoCuenta().toUpperCase());
        if(tipoCuenta == null){
            throw new IllegalArgumentException("Tipo de cuenta no pertenece (Ahorros, Corriente)");
        }

        //Consultar al servicio clientes, la informacion de su cliente id con el identificador...
        ClienteDto cliente;
        try {
            cliente = clienteClient.getCliente(req.getIdentificacion());
        }catch (Exception ex){
            log.info("Hubo un error al conectarse a servicio clientes...");
            throw new NoSuchElementException("No se encontró el cliente con identificador: " + req.getIdentificacion());
        }

        if(req.getSaldoInicial().compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Saldo inicial no puede ser negativo");

        //Crear cuenta
        Cuenta cuenta = new Cuenta();
        cuenta.setCtaTipoCuenta(tipoCuenta);
        cuenta.setCtaNumCuenta(req.getNumeroCuenta());
        cuenta.setCtaCliId(cliente.getCliId());
        cuenta.setCtaEstado("1");
        cuenta.setCtaFeCrea(new Date());
        cuenta.setCtaSaldoTotal(req.getSaldoInicial());
        return cuentaRepository.save(cuenta);
    }

    @Override
    public List<Cuenta> getAll() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta getById(Integer ctaId) {
        Optional<Cuenta> opCliente =  cuentaRepository.findById(ctaId);
        return opCliente.orElseThrow(() -> new NoSuchElementException("No existe la cuenta con el id: " + ctaId));
    }

    @Override
    public List<Cuenta> getByIden(String iden) {
        ClienteDto cliente;
        try {
            cliente = clienteClient.getCliente(iden);
        }catch (Exception ex){
            log.info("Hubo un error al conectarse a servicio clientes...");
            throw new NoSuchElementException("No se encontró el cliente con identificador: " + iden);
        }
        return cuentaRepository.findByClienteId(cliente.getCliId());
    }

    @Override
    public Cuenta update(Integer id, CuentaReq req) {
        Cuenta cuenta = getById(id);
        if(req.getNumeroCuenta() != null){
            Cuenta cuentaVerif = new Cuenta(req.getNumeroCuenta());
            if (cuentaRepository.exists(Example.of(cuentaVerif))) {
                throw new DuplicateKeyException("Ya existe cuenta con número de cta: " + req.getNumeroCuenta());
            }
            cuenta.setCtaNumCuenta(req.getNumeroCuenta());
        }

        if(req.getTipoCuenta() != null){
            //Validar el tipo de cuenta...
            TipoCuenta tipoCuenta = tipoCuentaRepository.findTCByNombre(req.getTipoCuenta().toUpperCase());
            if(tipoCuenta == null){
                throw new IllegalArgumentException("Tipo de cuenta no pertenece (Ahorros, Corriente)");
            }
            cuenta.setCtaTipoCuenta(tipoCuenta);
        }

        if(req.getIdentificacion() != null){
            try {
                ClienteDto cliente = clienteClient.getCliente(req.getIdentificacion());
                cuenta.setCtaCliId(cliente.getCliId());
            }catch (Exception ex){
                log.info("Hubo un error al conectarse a servicio clientes...");
                throw new NoSuchElementException("No se encontró el cliente con identificador: " + req.getIdentificacion());
            }
        }

        cuenta.setCtaFeActualiza(new Date());

        return cuentaRepository.save(cuenta);
    }

    @Override
    public void delete(Integer id) {
        cuentaRepository.deleteById(id);
    }
}
