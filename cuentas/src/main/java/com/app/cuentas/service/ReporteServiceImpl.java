package com.app.cuentas.service;

import com.app.cuentas.client.ClienteClient;
import com.app.cuentas.client.ClienteDto;
import com.app.cuentas.dto.CuentaDto;
import com.app.cuentas.repository.MovimientoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@Slf4j
public class ReporteServiceImpl implements ReporteService{
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    ClienteClient clienteClient;

    @Override
    public List<CuentaDto> obtenerReporteMovimientos(Date i, Date f, String identificacion) {
        if(i.after(f)){
            System.out.println("Fecha inicio está después de fecha final");
            throw new IllegalArgumentException("Fecha inicio debe estar antes de fecha fin");
        }
        f.setTime(f.getTime() + 86399000);

        log.info("fechaInicio -> {}", i);
        log.info("fechaFin -> {}", f);
        log.info("identificacion -> {}", identificacion);

        ClienteDto cliente;
        try {
            cliente = clienteClient.getCliente(identificacion);
        }catch (Exception ex){
            log.info("Hubo un error al conectarse a servicio clientes...");
            throw new NoSuchElementException("No se encontró el cliente con identificador: " + identificacion);
        }

        List<CuentaDto> cuentaDtoList = movimientoRepository.obtenerReporteMovimientos(i, f, cliente.getCliId());
        for (CuentaDto cdto: cuentaDtoList) {
            cdto.setCliente(cliente.getNombre());
        }
        return cuentaDtoList;
    }
}
