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
import java.io.File;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.mail.EmailException;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Paulo
 */
public class EnvioEmail {

    public void enviaEmail(Requerimento requerimento, String mensagemResposta, UploadedFile file) throws IOException {

        try {
            MensagemEmail mensEmail = new MensagemEmail();
            mensEmail.setTitulo("Requerimento SESI SENAI");
            mensEmail.setMensagem(constroiMensagem(requerimento, mensagemResposta));
            mensEmail.setDestino(requerimento.getCpfRequerente().getEmail());

            EmailUtil.enviaEmail(mensEmail, file);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "E-mail enviado com sucesso para: "
                    + mensEmail.getDestino(), "Informação"));

        } catch (EmailException ex) {
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Falha ao enviar o email "
                    + ex, "Informação"));
        }

    }

    private String constroiMensagem(Requerimento requerimento, String mens) {
        String mensagem = null;
        if (mens == null) {
            mens = "";
        }

        switch (requerimento.getStatus()) {
            case "ENVIADO":
                mensagem = "Prezado " + requerimento.getCpfRequerente().getNomeRequerente()
                        + " , seu requerimento " + requerimento.getCodigoTipoRequerimento().getDescricaoTipoRequerimento()
                        + " foi enviado com sucesso. "
                        + "Fique atento ao seu e-mail porque toda comunicação será feita por ele. "
                        + "Em breve entraremos em contato novamente.";
                break;
            case "ANALISE":
                mensagem = "Prezado " + requerimento.getCpfRequerente().getNomeRequerente()
                        + " , seu requerimento " + requerimento.getCodigoTipoRequerimento().getDescricaoTipoRequerimento()
                        + " está em análise. "
                        + " Fique atento ao seu e-mail porque toda comunicação será feita por ele."
                        + " Em breve entraremos em contato novamente.";

                break;
            case "FINALIZADO":
                mensagem = "Prezado " + requerimento.getCpfRequerente().getNomeRequerente()
                        + " o requerimento " + requerimento.getCodigoTipoRequerimento().getDescricaoTipoRequerimento()
                        + " foi foi concluido. "
                        + mens;
                break;
            default:
                break;
        }

        return mensagem;

    }

}
