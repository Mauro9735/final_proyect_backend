package com.proyectoBackend.proyectoIntegrador;


import com.proyectoBackend.proyectoIntegrador.entity.Domicilio;
import com.proyectoBackend.proyectoIntegrador.entity.Paciente;
import com.proyectoBackend.proyectoIntegrador.service.PacienteService;
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
public class IntegracionPacienteTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PacienteService pacienteService;

    private void cargarDatos() {
        Paciente pacienteAgregado = pacienteService.guardarPaciente(new Paciente("Diana", "Gowdy", "1223",
                LocalDate.of(2022, 12, 11), "diana@gmail.com", new Domicilio("calle ab", 123, "Envigado", "Antioquia")));
    }


    @Test
    public void listarPacientesTest() throws Exception{
        cargarDatos();
        MvcResult respuesta=mockMvc.perform(MockMvcRequestBuilders.get("/pacientes").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

}