package com.spring.usuario.service.feinClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.usuario.service.modelos.Carro;
import com.spring.usuario.service.modelos.Moto;

@FeignClient(name = "carro-service")

public interface CarroFeignClient {

    @PostMapping("/carro")
    public Carro save(@RequestBody Carro carro);

    @GetMapping("/carro")
    public List<Carro> getCarrosByUsuario(@PathVariable Long usuarioId);
}
