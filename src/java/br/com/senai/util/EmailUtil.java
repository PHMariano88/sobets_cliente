/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.util;

import br.com.senai.pojo.MensagemEmail;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author paule
 */
public class EmailUtil {

    private static final String HOSTNAME = "smtp.gmail.com";
    private static final String USERNAME = "requerimentosenai@gmail.com";
    private static final String PASSWORD = "senai123";
    private static final String REMETENTE = "requerimentosenai@gmail.com";

    private static Email conectEmail() throws EmailException {

        Email email = new SimpleEmail();

        email.setHostName(HOSTNAME);
        email.setSSLOnConnect(true);
        email.setSmtpPort(465);
        email.setAuthentication(USERNAME, PASSWORD);
       // email.setTLS(true);
        
        email.setSSLOnConnect(false);
        email.setFrom(REMETENTE);
        return email;

    }

    public static void enviaEmail(MensagemEmail mensagemEmail) throws EmailException {

        Email email = new SimpleEmail();

        email = conectEmail();
        email.setSubject(mensagemEmail.getTitulo());
        email.setMsg(mensagemEmail.getMensagem());
        email.addTo(mensagemEmail.getDestino());
        String s = email.send();

    }

}


