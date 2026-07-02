package ar.edu.davinci.excusassa.notificacion;

public interface EmailSender {

    void enviarEmail(String destino, String origen, String asunto, String cuerpo);
}
