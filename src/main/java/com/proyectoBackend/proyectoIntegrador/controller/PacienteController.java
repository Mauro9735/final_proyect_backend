package com.proyectoBackend.proyectoIntegrador.controller;

import com.proyectoBackend.proyectoIntegrador.entity.Paciente;
import com.proyectoBackend.proyectoIntegrador.exceptions.ResourceNotFoundException;
import com.proyectoBackend.proyectoIntegrador.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);
        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/buscar/correo/{email}")
    public ResponseEntity<Paciente> buscarPacientePorCorreo(@PathVariable String email){
        Optional<Paciente>pacienteBuscado = pacienteService.buscarPacientePorCorreo(email);
        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        //Chequeo para controlar que sea un correo unico
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorCorreo(paciente.getEmail());

        if (pacienteBuscado.isPresent()){
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException{
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se eliminó el paciente" +
                " con id: "+id);
    }


    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
        Optional<Paciente>pacienteBuscado=pacienteService.buscarPaciente(paciente.getId());
        if (pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Se actualizó al paciente con id: " + paciente.getId());
        }else{
            return ResponseEntity.badRequest().body("No se pudo actualizar al paciente con id: " + paciente.getId() +
                    " ya que el mismo no existe en la base de datos.");
        }
    }








}
