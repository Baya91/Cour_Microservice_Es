package com.spring.usuario.service.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.spring.usuario.service.entidades.Usuario;
import com.spring.usuario.service.modelos.Carro;
import com.spring.usuario.service.modelos.Moto;
import com.spring.usuario.service.servicio.UsuarioServicio;

@RestController
@RequestMapping("/usuario")
public class UsarioController {

    @Autowired
    private UsuarioServicio usuarioService;

    /****************************code Microservice Carro/****************************/

    @PostMapping("set_carro/{usuarioId}") //  http://localhost:8001/usuario/set_carro/**
    public ResponseEntity<Carro> guardarCarro(@PathVariable("usuarioId") Long usuarioId,
                    @RequestBody Carro carro) {
        Carro carroNuevo = this.usuarioService.saveCarro(usuarioId, carro);

        return ResponseEntity.ok(carroNuevo);
    }

    /****************************code Microservice Moto/****************************/

    @PostMapping("set_moto/{usuarioId}") //  http://localhost:8001/usuario/set_moto/**
    public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") Long usuarioId,
                    @RequestBody Moto moto) {
        Moto motoNuevo = this.usuarioService.saveMoto(usuarioId, moto);

        return ResponseEntity.ok(motoNuevo);
    }

    @GetMapping("motos_by_usuario/{usuarioId}") //  http://localhost:8001/usuario/motos_by_usuario/**
    public ResponseEntity<List<Moto>> listaMotosbyUsuario(@PathVariable Long usuarioId) {
        List<Moto> motos = this.usuarioService.getMotos(usuarioId);
        if (motos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }
    /****************************get  Todos/****************************/
    @GetMapping("totos/{usuarioId}") //  http://localhost:8001/usuario/totos/**
    public ResponseEntity<Map<String, Object>> lista_Carros_Motos_By_UsarioId(@PathVariable Long usuarioId) {
        Map<String, Object> resulta= new HashMap<>();
        resulta=this.usuarioService.getTodos(usuarioId);
        return ResponseEntity.ok(resulta);
    }

    /****************************code Usuario/****************************/

    @GetMapping //  http://localhost:8001/usuario
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.getAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}") //   http://localhost:8001/usuario/**
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") Long id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping // http://localhost:8001/usuario
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }
}
