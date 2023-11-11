package com.proyectoBackend.proyectoIntegrador.controller;


import com.proyectoBackend.proyectoIntegrador.dto.TurnoDTO;
import com.proyectoBackend.proyectoIntegrador.exceptions.ResourceBadRequestException;
import com.proyectoBackend.proyectoIntegrador.exceptions.ResourceNotFoundException;
import com.proyectoBackend.proyectoIntegrador.service.OdontologoService;
import com.proyectoBackend.proyectoIntegrador.service.PacienteService;
import com.proyectoBackend.proyectoIntegrador.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }



    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable Long id){
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
        if (turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTurnos(){
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turnoDTO) throws ResourceBadRequestException {
        if (odontologoService.buscarOdontologo(turnoDTO.getOdontologoId()).isPresent()
        && pacienteService.buscarPaciente(turnoDTO.getPacienteId()).isPresent()){
            return ResponseEntity.ok(turnoService.guardarTurno(turnoDTO));
        }else {

            throw new ResourceBadRequestException("Error, no se puede registrar el turno, ya que no existe el odontologo y/o el paciente en la" +
                    " base de datos.");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException{
            turnoService.eliminarTurno(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se eliminó el turno" +
                    " con id: "+id);
    }


    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turnoDTO){
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(turnoDTO.getId());

        if (turnoBuscado.isPresent()){
            if (odontologoService.buscarOdontologo(turnoDTO.getOdontologoId()).isPresent()
            && pacienteService.buscarPaciente(turnoDTO.getPacienteId()).isPresent()){
                turnoService.actualizarTurno(turnoDTO);
                return ResponseEntity.ok("Se actualizó el turno con id: "+turnoDTO.getId());
            }else{
                return ResponseEntity.badRequest().body("No se puede actualizar el turno con" +
                        " id: "+turnoDTO.getId()+", ya que existe un error con el odontologo y/o " +
                        "paciente.");
            }
        }else {
            return ResponseEntity.badRequest().body("No se encontró el turno que se quiere modificar.");
        }
    }

}
