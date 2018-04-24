/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.util;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author paule
 */
public class EmailUtil {

    Properties props = new Properties();
    Session session;

    public void configuration() {
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("requerimentosenai@gmail.com", "senai123");
            }
        });
    }
    
    public void sendEmail(){
        
        configuration();
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("requerimentosenai@gmail.com"));//de quem
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("pmariano@fiemg.com.br"));//para quem vai enviar
            message.setSubject("Testando menvio de email");// assunto
            message.setText("Testando o Email Enviado");
            Transport.send(message);
            System.out.println("Enviado");
            
        } catch (AddressException ex) {
            Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           
        
        }
    
    }
    

}
