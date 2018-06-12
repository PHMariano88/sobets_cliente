/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.bean;

import br.com.senai.util.FacesUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Paulo
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    private final String[] arraySetor = {"PEDAGOGIA", "SECRETARIA", "FINANCEIRO"};
    private String setor;
    private String nomeLogin;

    public LoginBean() {

    }

    public String[] getArraySetor() {
        return arraySetor;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getNomeLogin() {
        return nomeLogin;
    }

    public void setNomeLogin(String nomeLogin) {
        this.nomeLogin = nomeLogin;
    }

    public String login() {
        FacesUtil.setApplicationMapValue("paramSetor", this.setor);
        FacesUtil.setApplicationMapValue("paramNome", this.nomeLogin);
        return "sobetsGerencialMenu";
    }
}
