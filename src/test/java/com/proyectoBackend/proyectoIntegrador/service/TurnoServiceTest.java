package com.proyectoBackend.proyectoIntegrador.service;


import com.proyectoBackend.proyectoIntegrador.dto.TurnoDTO;
import com.proyectoBackend.proyectoIntegrador.entity.Domicilio;
import com.proyectoBackend.proyectoIntegrador.entity.Odontologo;
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
public class TurnoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private TurnoService turnoService;

    @Test
    @Order(1)
    public void registrarTurnoTest(){
        Paciente pacienteAgregado = pacienteService.guardarPaciente(new Paciente("Diana","Gowdy","1223",
                LocalDate.of(2022,12,11),"diana@gmail.com",new Domicilio("calle ab",123,"Envigado","Antioquia")));


        Odontologo odontologoAgregado = odontologoService.guardarOdontologo(new Odontologo("MP123","Mauricio","Abril"));

        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setFecha(LocalDate.of(2022,12,7));
        turnoDTO.setOdontologoId(odontologoAgregado.getId());
        turnoDTO.setPacienteId(pacienteAgregado.getId());
        turnoService.guardarTurno(turnoDTO);
        assertEquals(1L,turnoDTO.getOdontologoId());
    }


    @Test
    @Order(2)
    public void buscarTurnoPorIdTest(){
        Long idABuscar = 1L;
        Optional<TurnoDTO> turnoDTOBuscado = turnoService.buscarTurno(idABuscar);
        assertEquals(1L,turnoDTOBuscado.get().getOdontologoId());
    }

    @Test
    @Order(3)
    public void listarTurnos(){
        List<TurnoDTO> turnos = turnoService.listarTurnos();
        assertEquals(1,turnos.size());
    }


    @Test
    @Order(4)
    public void eliminarTurno() throws ResourceNotFoundException {
        Long idAEliminar = 1L;
        turnoService.eliminarTurno(idAEliminar);
        Optional<TurnoDTO> turnoEliminado = turnoService.buscarTurno(idAEliminar);
        assertFalse(turnoEliminado.isPresent());
    }


}
