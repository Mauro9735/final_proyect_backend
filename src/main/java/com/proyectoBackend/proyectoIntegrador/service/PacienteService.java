package com.proyectoBackend.proyectoIntegrador.service;

import com.proyectoBackend.proyectoIntegrador.entity.Paciente;
import com.proyectoBackend.proyectoIntegrador.exceptions.ResourceNotFoundException;
import com.proyectoBackend.proyectoIntegrador.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private static final Logger LOGGER = Logger.getLogger(PacienteService.class);

    private PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardarPaciente(Paciente paciente){
        LOGGER.info("Se inició el proceso de guardado del paciente con nombre: "+paciente.getNombre());
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPaciente(Long id){
        LOGGER.info("Se inició el proceso de búsqueda de un paciente con id: "+id);
        return pacienteRepository.findById(id);
    }

    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = buscarPaciente(id);
        if (pacienteBuscado.isPresent()){
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se eliminó el paciente con id: "+id);
        }else {
            throw new ResourceNotFoundException("Error, no existe el id: "+id+", asociado a un paciente en la base de datos.");
        }
    }

    public void actualizarPaciente(Paciente paciente){
        LOGGER.info("Se inició el proceso de actualización del paciente con id: "+paciente.getId());
        pacienteRepository.save(paciente);
    }

    public List<Paciente> listarPacientes(){
        LOGGER.info("Se inició el proceso de búsqueda de los pacientes");
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPacientePorCorreo(String correo){
        LOGGER.info("Se inicio el proceso de busqueda de un paciente con email: "+correo);
        return pacienteRepository.findByEmail(correo);
    }




}
