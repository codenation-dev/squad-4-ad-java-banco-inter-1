package br.com.centraldeerro.services.implementations;

import br.com.centraldeerro.services.interfaces.EmailService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmailServiceImp implements EmailService {
    private final String EMAIL = "";
    private final String PASSWORD = "";
    private final String SMTP = "smtp.gmail.com";
    private final String PORTA = "465";

    @Override
    public Boolean enviar(List<String> texto, List<String> destinatarios, String Assunto) {

        try {
            Session session = getSession();

            /** Ativa Debug para sessão */
            session.setDebug(true);


            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL));
            //Remetente

            Address[] toUser = InternetAddress //Destinatário(s)
                    .parse(retornarDestinatarios(destinatarios));

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(Assunto);//Assunto
            message.setText(retornarTexto(texto));
            /**Método para enviar a mensagem criada*/
            Transport.send(message);

        } catch (MessagingException e) {
            return Boolean.FALSE;
        }catch (Exception ex){
            return Boolean.FALSE;
        }


        return Boolean.TRUE;
    }

    @Override
    public Boolean enviar(List<String> texto, String destinatario, String assunto) {
        List<String> destinatarios = new ArrayList<>();

        destinatarios.add(destinatario);

        return enviar(texto, destinatarios,assunto);
    }

    private String retornarDestinatarios(List<String> destinatarios){
        StringBuilder retorno = new StringBuilder();

        destinatarios.forEach(d -> {
            if(retorno.toString().equals(""))
                retorno.append(d);
            else
                retorno.append(", " + d);
        });

        return retorno.toString();
    }

    private String retornarTexto(List<String> texto){
        StringBuilder retorno = new StringBuilder();
        texto.forEach(t -> retorno.append(t + "\n"));

        return retorno.toString();
    }

    private Session getSession(){
        Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", SMTP);
        props.put("mail.smtp.socketFactory.port", PORTA);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", PORTA);

        return Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(EMAIL, PASSWORD);
                    }
                });
    }
}
