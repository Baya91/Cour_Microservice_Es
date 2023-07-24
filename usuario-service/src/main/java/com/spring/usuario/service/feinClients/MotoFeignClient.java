package com.spring.usuario.service.feinClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.usuario.service.modelos.Moto;

@FeignClient(name = "moto-service")
public interface MotoFeignClient {

    @PostMapping("/moto")
    public Moto save(Moto moto);

    @GetMapping("/moto/usuario/{usuarioId}")
    public List<Moto> getMotosByUsuario(@PathVariable Long usuarioId);
}
