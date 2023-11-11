package com.proyectoBackend.proyectoIntegrador.service;

import com.proyectoBackend.proyectoIntegrador.entity.Odontologo;
import com.proyectoBackend.proyectoIntegrador.exceptions.ResourceNotFoundException;
import com.proyectoBackend.proyectoIntegrador.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);


    private OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }


    public Odontologo guardarOdontologo(Odontologo odontologo){
        LOGGER.info("Se inició el proceso de guardado del odontólogo con nombre: "+odontologo.getNombre());
        return odontologoRepository.save(odontologo);
    }


    public Optional<Odontologo> buscarOdontologo(Long id){
        LOGGER.info("Se inició el proceso de búsqueda de un odontólogo con id: "+id);
        return odontologoRepository.findById(id);
    }

    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = buscarOdontologo(id);
        if(odontologoBuscado.isPresent()){
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se eliminó el odntólogo con id: "+id);
        }else{
            throw new ResourceNotFoundException("Error, no existe el id: "+id+ ", asociado a un odontologo" +
                    " en la base de datos.");
        }

    }

    public void actualizarOdontologo(Odontologo odontologo){
        LOGGER.info("Se inició el proceso de actualización del odontólogo con id: "+odontologo.getId());
        odontologoRepository.save(odontologo);
    }

    public List<Odontologo> listarOdontologos(){
        LOGGER.info("Se inició el proceso de búsqueda de los odontólogos");
        return odontologoRepository.findAll();
    }



}
