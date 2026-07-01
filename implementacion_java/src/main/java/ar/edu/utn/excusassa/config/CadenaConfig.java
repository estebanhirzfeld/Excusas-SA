package ar.edu.utn.excusassa.config;

import ar.edu.utn.excusassa.evaluacion.CadenaDeResponsablesBuilder;
import ar.edu.utn.excusassa.evaluacion.Responsable;
import ar.edu.utn.excusassa.notificacion.EmailSender;
import ar.edu.utn.excusassa.prontuario.AdministradorProntuarios;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadenaConfig {

    @Bean
    public AdministradorProntuarios administradorProntuarios() {
        return new AdministradorProntuarios();
    }

    @Bean
    public CadenaDeResponsablesBuilder cadenaDeResponsablesBuilder() {
        return new CadenaDeResponsablesBuilder();
    }

    @Bean
    public Responsable cadenaDeResponsables(EmailSender emailSender,
                                             AdministradorProntuarios adminProntuarios) {
        return new CadenaDeResponsablesBuilder()
            .agregarRecepcionista("María", "maria@excusassa.com", 10, emailSender)
            .agregarSupervisora("Laura", "laura@excusassa.com", 20, emailSender)
            .agregarGerenteRRHH("Carlos", "carlos@excusassa.com", 30, emailSender)
            .agregarCEO("Jefe", "jefe@excusassa.com", 1, emailSender, adminProntuarios)
            .construir();
    }
}
