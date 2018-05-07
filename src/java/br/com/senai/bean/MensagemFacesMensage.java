/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Paulo
 */
public class MensagemFacesMensage {

    public void constroiMensagemErro(FacesContext context,String titulo, String msg) {

       FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo,
               msg);
        context.getCurrentInstance().addMessage(null, mensagem);
    }
    
    public void constroiMensagemCerto(FacesContext context,String titulo, String msg) {

       FacesMessage mensagem = new FacesMessage(titulo ,
               msg);
        context.getCurrentInstance().addMessage(null, mensagem);
    }
}
