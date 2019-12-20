package br.com.centraldeerro.services.interfaces;

import java.util.List;

public interface EmailService {

    Boolean enviar(List<String> texto, List<String> destinatarios, String assunto);

    Boolean enviar(List<String> texto, String destinatario, String assunto);
}
