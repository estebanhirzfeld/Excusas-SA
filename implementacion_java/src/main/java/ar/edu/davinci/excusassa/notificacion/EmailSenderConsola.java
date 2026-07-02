package ar.edu.davinci.excusassa.notificacion;

import org.springframework.stereotype.Component;

@Component
public class EmailSenderConsola implements EmailSender {

    @Override
    public void enviarEmail(String destino, String origen, String asunto, String cuerpo) {
        System.out.printf("[EMAIL] De: %s → Para: %s | Asunto: %s | %s%n",
            origen, destino, asunto, cuerpo);
    }
}
