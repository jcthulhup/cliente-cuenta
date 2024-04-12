package com.app.cuentas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "clientes", url = "http://localhost:8081")
public interface ClienteClient {
    @RequestMapping(method= RequestMethod.GET, value="/clientes/identificacion/{identificacion}", consumes="application/json")
    ClienteDto getCliente(@PathVariable("identificacion") String identificacion);

}
