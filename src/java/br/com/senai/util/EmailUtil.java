/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.util;

import br.com.senai.dao.ArquivoAnexo;
import br.com.senai.pojo.MensagemEmail;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author paule
 */
public class EmailUtil {

    private static final String HOSTNAME = "smtp.gmail.com";
    private static final String USERNAME = "requerimentosenai@gmail.com";
    private static final String PASSWORD = "senai123";
    private static final String REMETENTE = "requerimentosenai@gmail.com";

    private static MultiPartEmail conectEmail() throws EmailException {

        MultiPartEmail email = new MultiPartEmail();

        email.setHostName(HOSTNAME);
        email.setStartTLSEnabled(true);
        // email.setSSLOnConnect(true);
        email.setTLS(true);
        email.setSmtpPort(587);

        email.setAuthentication(USERNAME, PASSWORD);

        email.setFrom(REMETENTE);
        return email;

    }

    public static void enviaEmail(MensagemEmail mensagemEmail, UploadedFile file) throws EmailException, IOException {

        MultiPartEmail email = new MultiPartEmail();

        email = conectEmail();
        email.setSubject(mensagemEmail.getTitulo());
        email.setMsg(mensagemEmail.getMensagem());

        email.addTo(mensagemEmail.getDestino());
        if(file != null){
            email.attach(addAnexo(file));
        }

        email.send();

    }

    private static EmailAttachment addAnexo(UploadedFile upFile) throws IOException {

        ArquivoAnexo anexo = new ArquivoAnexo();
        InputStream stream = upFile.getInputstream();
        
        File file = new File(anexo.upload(upFile.getFileName(), upFile.getInputstream()));
        EmailAttachment emailAnexo = new EmailAttachment();
        emailAnexo.setPath(file.getAbsolutePath());
        emailAnexo.setName(file.getName());
        emailAnexo.setDisposition(EmailAttachment.ATTACHMENT);
        emailAnexo.setDescription("Anexo");
        return emailAnexo;

    }

}
