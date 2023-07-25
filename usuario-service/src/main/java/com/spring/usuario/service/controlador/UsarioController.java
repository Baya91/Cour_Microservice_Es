package com.spring.usuario.service.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/usuario")
public class UsarioController {

    @Autowired
    private UsuarioServicio usuarioService;

    /****************************code Microservice Carro/****************************/

    @CircuitBreaker(name = "carrosCB", fallbackMethod = "fallBacksetCarros")
    @PostMapping("set_carro/{usuarioId}") //  http://localhost:8001/usuario/set_carro/**
    public ResponseEntity<Carro> guardarCarro(@PathVariable("usuarioId") Long usuarioId,
                    @RequestBody Carro carro) {
        Carro carroNuevo = this.usuarioService.saveCarro(usuarioId, carro);
        return ResponseEntity.ok(carroNuevo);
    }
    
    private ResponseEntity<Carro> fallBacksetCarros(RuntimeException exepion) {
        return new ResponseEntity(
                        "El usuario : tiene diniero para los carros",
                        HttpStatus.OK);
    }
    

    @CircuitBreaker(name = "carrosCB", fallbackMethod = "fallBackGetCarros")
    @GetMapping("carros_by_usuario/{usuarioId}") //  http://localhost:8001/usuario/carros_by_usuario/**
    public ResponseEntity<List<Carro>> listaCarrosbyUsuario( @PathVariable("usuarioId") Long usuarioId) {
        List<Carro> carros = this.usuarioService.getCarros(usuarioId);
        if (carros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }
    private ResponseEntity<List<Carro>> fallBackGetCarros(RuntimeException exepion) {
        return new ResponseEntity(
                        "El usuario : tiene diniero para los carros",
                        HttpStatus.OK);
    }

    /****************************code Microservice Moto/****************************/

    @CircuitBreaker(name = "motosCB", fallbackMethod = "fallBacksetMotos")
    @PostMapping("set_moto/{usuarioId}") //  http://localhost:8001/usuario/set_moto/**
    public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") Long usuarioId,
                    @RequestBody Moto moto) {
        Moto motoNuevo = this.usuarioService.saveMoto(usuarioId, moto);
        return ResponseEntity.ok(motoNuevo);
    }

    @CircuitBreaker(name = "motosCB", fallbackMethod = "fallBackGetMotos")
    @GetMapping("motos_by_usuario/{usuarioId}") //  http://localhost:8001/usuario/motos_by_usuario/**
    public ResponseEntity<List<Moto>> listaMotosbyUsuario(@PathVariable Long usuarioId) {
        List<Moto> motos = this.usuarioService.getMotos(usuarioId);
        if (motos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }

    /****************************get  Todos/****************************/

    @CircuitBreaker(name = "todosCB", fallbackMethod = "fallBackGetTodos")
    @GetMapping("totos/{usuarioId}") //  http://localhost:8001/usuario/totos/**
    public ResponseEntity<Map<String, Object>> lista_Carros_Motos_By_UsarioId(
                    @PathVariable Long usuarioId) {
        Map<String, Object> resulta = new HashMap<>();
        resulta = this.usuarioService.getTodos(usuarioId);
        return ResponseEntity.ok(resulta);
    }

    /****************************Methode  ====== Circuit Bareaker ====== ****************************/

  
   
    
    private ResponseEntity<Moto> fallBacksetMotos(
                    @PathVariable("usuarioId") Long usuarioId, @RequestBody Moto moto) {
        return new ResponseEntity(
                        "El usuario : " + usuarioId + " tiene los motos en el talle",
                        HttpStatus.OK);
    }

    private ResponseEntity<List<Moto>> fallBackGetMotos(@PathVariable("usuarioId") Long usuarioId,RuntimeException exepion) {
        return new ResponseEntity(
                        "El usuario : " + usuarioId + " tiene diniero para los motos",
                        HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> fallBackGetTodos(
                    @PathVariable("usuarioId") Long usuarioId, @RequestBody Moto moto) {
        return new ResponseEntity(
                        "El usuario : " + usuarioId + " tiene los vhiecolos en el talle",
                        HttpStatus.OK);
    }
   

    /****************************code Usuario/****************************/
 //  http://localhost:8001/usuario
    @GetMapping
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
