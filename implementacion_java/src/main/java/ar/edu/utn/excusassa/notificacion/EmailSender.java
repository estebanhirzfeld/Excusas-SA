package ar.edu.utn.excusassa.notificacion;

public interface EmailSender {

    void enviarEmail(String destino, String origen, String asunto, String cuerpo);
}
