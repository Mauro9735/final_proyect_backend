package com.proyectoBackend.proyectoIntegrador.security;

import com.proyectoBackend.proyectoIntegrador.entity.Usuario;
import com.proyectoBackend.proyectoIntegrador.entity.UsuarioRole;
import com.proyectoBackend.proyectoIntegrador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosInicialesUsuarios implements ApplicationRunner {

    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        //Crear un usuario tipo user
        //Guardarlo en la base

        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        String passSinCifrar = "Digital";
        String passCifrada = cifrador.encode(passSinCifrar);
        Usuario usuarioAInsertar = new Usuario("Mauricio","Mauricio","mauriciovilladiego1999@gmail.com",passCifrada, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuarioAInsertar);

        //Crear un usuario tipo admin
        //Guardarlo en la base

        String passSinCifrar2 = "House";
        String passCifrada2 = cifrador.encode(passSinCifrar2);
        usuarioAInsertar = new Usuario("Andres","Andres","villa.abril@hotmail.com",passCifrada2,UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuarioAInsertar);
    }
}
