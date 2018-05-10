/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.bean;

import br.com.senai.dao.RequerimentoDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Paulo
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private final String[] arraySetor = {"PEDAGOGIA", "SECRETARIA", "FINANCEIRO"};
    private String setor;
    public static String SETOR_SELECIONADO;
    public static String RESPONSAVEL_LOGADO;
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
        SETOR_SELECIONADO = getSetor();
        RESPONSAVEL_LOGADO = getNomeLogin();
        return "sobetsGerencialMenu";
    }
}
