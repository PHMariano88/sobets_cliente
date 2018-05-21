/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.util;

import br.com.senai.pojo.MensagemEmail;

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
        email.setStartTLSEnabled(true);
       // email.setSSLOnConnect(true);
        email.setTLS(true);
        email.setSmtpPort(587);
        
        email.setAuthentication(USERNAME, PASSWORD);
       
        
      
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


