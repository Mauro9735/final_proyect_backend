package com.proyectoBackend.proyectoIntegrador.service;

import com.proyectoBackend.proyectoIntegrador.entity.Domicilio;
import com.proyectoBackend.proyectoIntegrador.entity.Paciente;
import com.proyectoBackend.proyectoIntegrador.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPacienteTest(){
        Paciente pacienteAGuardar = new Paciente("Mauricio","Abril","123", LocalDate.of(2022,12,01),
                "mauricio@gmail.com",new Domicilio("Bernal",123,"Envigado","Antioquia"));

        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);
        assertEquals(1L,pacienteGuardado.getId());
    }


    @Test
    @Order(2)
    public void buscarPacientePorIdTest(){
        Long idABuscar = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(idABuscar);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void buscarPacientePorCorreo(){
        String correoABuscar = "mauricio@gmail.com";
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorCorreo(correoABuscar);
        assertNotNull(pacienteBuscado.get());
    }




    @Test
    @Order(4)
    public void listarPacientesTest(){
        List<Paciente> pacientes = pacienteService.listarPacientes();
        assertEquals(1,pacientes.size());
    }


    @Test
    @Order(5)
    public void actualizarPacienteTest(){
        Paciente pacienteAActualizar = new Paciente(1L,"Andrés","Abril","123", LocalDate.of(2022,12,01),
                "mauricio@gmail.com",new Domicilio(1L,"Bernal",123,"Envigado","Antioquia"));


        pacienteService.actualizarPaciente(pacienteAActualizar);
        Optional<Paciente> pacienteActualizado = pacienteService.buscarPaciente(1L);
        assertEquals("Andrés",pacienteActualizado.get().getNombre());
    }



    @Test
    @Order(6)
    public void eliminarPacienteTest() throws ResourceNotFoundException {
        Long idAEliminar=1L;
        pacienteService.eliminarPaciente(1L);
        Optional<Paciente> pacienteEliminado = pacienteService.buscarPaciente(idAEliminar);
        assertFalse(pacienteEliminado.isPresent());
    }











}