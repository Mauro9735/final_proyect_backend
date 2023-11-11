package com.proyectoBackend.proyectoIntegrador;

import com.proyectoBackend.proyectoIntegrador.entity.Domicilio;
import com.proyectoBackend.proyectoIntegrador.entity.Paciente;
import com.proyectoBackend.proyectoIntegrador.service.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProyectoIntegradorApplicationTests {


	@Autowired
	private PacienteService pacienteService;


	@Test
	public void guardarPacienteTest(){
		Paciente pacienteAGuardar = new Paciente("Mauricio","Abril","123", LocalDate.of(2022,12,01),
				"mauricio@gmail.com",new Domicilio("Bernal",123,"Envigado","Antioquia"));

		Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);
		assertEquals(1L,pacienteGuardado.getId());
	}

	@Test
	public void buscarPacienteId1(){
		//PacienteService pacienteService=new PacienteService();
		Optional<Paciente> pacienteBuscado=pacienteService.buscarPaciente(1L);
		Assertions.assertEquals("Abril",pacienteBuscado.get().getApellido());
	}






	@Test
	void contextLoads() {
	}

}
