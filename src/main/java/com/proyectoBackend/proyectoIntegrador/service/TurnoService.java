package com.proyectoBackend.proyectoIntegrador.service;

import com.proyectoBackend.proyectoIntegrador.dto.TurnoDTO;
import com.proyectoBackend.proyectoIntegrador.entity.Odontologo;
import com.proyectoBackend.proyectoIntegrador.entity.Paciente;
import com.proyectoBackend.proyectoIntegrador.entity.Turno;
import com.proyectoBackend.proyectoIntegrador.exceptions.ResourceNotFoundException;
import com.proyectoBackend.proyectoIntegrador.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private static final Logger LOGGER = Logger.getLogger(TurnoService.class);

    private TurnoRepository turnoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }


    public TurnoDTO guardarTurno(TurnoDTO turnoDTO){
        Turno turnoGuardado=turnoRepository.save(turnoDTOATurno(turnoDTO));
        LOGGER.info("Se inició el proceso de guardado del turno con id: "+turnoDTO.getId());
        return turnoATurnoDTO(turnoGuardado);
    }

    public Optional<TurnoDTO> buscarTurno(Long id){
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        LOGGER.info("Se inició el proceso de búsqueda de un turno con id: "+id);
        if (turnoBuscado.isPresent()){
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        }else{
            return Optional.empty();
        }
    }


    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        Optional<TurnoDTO> turnoBuscado = buscarTurno(id);
        if (turnoBuscado.isPresent()){
            turnoRepository.deleteById(id);
            LOGGER.warn("Se eliminó el turno con id: "+id);
        }else {
            throw new ResourceNotFoundException("Error, no existe el id: "+id+", asociado a un turno en la base de datos.");
        }
    }

    public void actualizarTurno(TurnoDTO turnoDTO){
        LOGGER.info("Se inició el proceso de actualización del turno con id: "+turnoDTO.getId());
        turnoRepository.save(turnoDTOATurno(turnoDTO));
    }

    public List<TurnoDTO> listarTurnos(){
        List<Turno> turnosEncontrados = turnoRepository.findAll();
        LOGGER.info("Se inició el proceso de búsqueda de los turnos");
        //Recorremos la lista para ir convirtiendo cada elemento en DTO
        List<TurnoDTO> respuesta = new ArrayList<>();

        for (Turno turno : turnosEncontrados) {
            respuesta.add(turnoATurnoDTO(turno));
        }

        return respuesta;
    }




    private TurnoDTO turnoATurnoDTO(Turno turno){
        //Convertir el turno a un turnoDTO
        TurnoDTO respuesta = new TurnoDTO();
        //Cargar la información de turno al turnoDTO
        respuesta.setId(turno.getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setFecha(turno.getFecha());

        //Devolucion
        return respuesta;
    }


    private Turno turnoDTOATurno(TurnoDTO turnoDTO){
        //Convertir el turnoDTO a un turno
        Turno respuesta = new Turno();
        //Cargar la información de turnoDTO a turno
        Paciente paciente = new Paciente();
        Odontologo odontologo = new Odontologo();
        paciente.setId(turnoDTO.getPacienteId());
        odontologo.setId(turnoDTO.getOdontologoId());


        respuesta.setId(turnoDTO.getId());
        respuesta.setFecha(turnoDTO.getFecha());
        respuesta.setPaciente(paciente);
        respuesta.setOdontologo(odontologo);

        //Devolucion
        return respuesta;
    }



}
