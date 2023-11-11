package com.proyectoBackend.proyectoIntegrador;


import com.proyectoBackend.proyectoIntegrador.dto.TurnoDTO;
import com.proyectoBackend.proyectoIntegrador.entity.Domicilio;
import com.proyectoBackend.proyectoIntegrador.entity.Odontologo;
import com.proyectoBackend.proyectoIntegrador.entity.Paciente;
import com.proyectoBackend.proyectoIntegrador.service.OdontologoService;
import com.proyectoBackend.proyectoIntegrador.service.PacienteService;
import com.proyectoBackend.proyectoIntegrador.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnoTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PacienteService pacienteService;


    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private TurnoService turnoService;



    private  void cargarDatos(){
        Paciente pacienteAgregado = pacienteService.guardarPaciente(new Paciente("Diana","Gowdy","1223",
                LocalDate.of(2022,12,11),"diana@gmail.com",new Domicilio("calle ab",123,"Envigado","Antioquia")));


        Odontologo odontologoAgregado = odontologoService.guardarOdontologo(new Odontologo("MP123","Mauricio","Abril"));

        TurnoDTO turnoDTO= new TurnoDTO();
        turnoDTO.setFecha(LocalDate.of(2022,12,11));
        turnoDTO.setOdontologoId(odontologoAgregado.getId());
        turnoDTO.setPacienteId(pacienteAgregado.getId());
        turnoService.guardarTurno(turnoDTO);

    }

    @Test
    public void listarTurnosTest() throws Exception{
        cargarDatos();
        MvcResult respuesta=mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

}
