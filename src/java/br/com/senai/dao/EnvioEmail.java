/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.dao;

import br.com.senai.pojo.MensagemEmail;
import br.com.senai.pojo.Requerente;
import br.com.senai.pojo.Requerimento;
import br.com.senai.util.EmailUtil;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author Paulo
 */
public class EnvioEmail {
    
    public void enviaEmail(Requerimento requerimento) {

        try {
            MensagemEmail mensEmail = new MensagemEmail();
            mensEmail.setTitulo("Requerimento SESI SENAI");
            mensEmail.setMensagem("Prezado " + requerimento.getCpfRequerente().getNomeRequerente() + ", o status"
                    + " do seu requerimento é. Em breve entraremos em contato.");
            mensEmail.setDestino(requerimento.getCpfRequerente().getEmail());

            EmailUtil.enviaEmail(mensEmail);
            System.out.println("Sucesso");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "E-mail enviado com sucesso para: "
                    + mensEmail.getDestino(), "Informação"));

        } catch (EmailException ex) {
            System.out.println(" O erro" + ex);
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Falha ao enviar o email "
                    + ex, "Informação"));
        }

    }
    
}
