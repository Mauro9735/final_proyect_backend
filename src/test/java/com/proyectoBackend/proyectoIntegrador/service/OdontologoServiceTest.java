package com.proyectoBackend.proyectoIntegrador.service;

import com.proyectoBackend.proyectoIntegrador.entity.Odontologo;
import com.proyectoBackend.proyectoIntegrador.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;


    @Test
    @Order(1)
    public void registrarOdontologoTest(){
        Odontologo odontologoAGuardar = new Odontologo("123","Priscila","Abril");

        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologoAGuardar);
        assertEquals(1L,odontologoGuardado.getId());
    }


    @Test
    @Order(2)
    public void buscarOdontologoPorIdTest(){
        Long idABuscar = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(idABuscar);
        assertEquals("Priscila",odontologoBuscado.get().getNombre());
    }

    @Test
    @Order(3)
    public void listarOdontologos(){
        List<Odontologo> odontologos = odontologoService.listarOdontologos();
        assertEquals(1,odontologos.size());
    }

    @Test
    @Order(4)
    public void actualizarOdontologo(){
        Odontologo odontologoAActualizar = new Odontologo(1L,"23233","Priscila","Abril");
        odontologoService.actualizarOdontologo(odontologoAActualizar);
        Optional<Odontologo> odontologoActualizado = odontologoService.buscarOdontologo(1L);
        assertEquals("23233",odontologoActualizado.get().getMatricula());
    }


    @Test
    @Order(5)
    public void eliminarOdontologo() throws ResourceNotFoundException {
        Long idAEliminar = 1L;
        odontologoService.eliminarOdontologo(idAEliminar);
        Optional<Odontologo> odontologoEliminado = odontologoService.buscarOdontologo(idAEliminar);
        assertFalse(odontologoEliminado.isPresent());
    }







}