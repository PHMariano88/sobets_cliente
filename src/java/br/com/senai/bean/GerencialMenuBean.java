/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.bean;

import br.com.senai.dao.RequerimentoDAO;
import br.com.senai.pojo.Requerimento;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Paulo
 */
@Named(value = "gerencialMenuBean")
@SessionScoped
public class GerencialMenuBean implements Serializable {

    private List<Requerimento> requerimento;
    private RequerimentoDAO reqDao;

    public GerencialMenuBean() {
        reqDao = new RequerimentoDAO();
    }

    @PostConstruct
    public void init() {
        requerimento = reqDao.selectRequerimentoBySetor(LoginBean.SETOR_SELECIONADO);
        System.out.println("passou aki " + requerimento.size());
    }

    public List<Requerimento> getRequerimento() {
        return requerimento;
    }

    public void setRequerimento(List requerimento) {
        this.requerimento = requerimento;
    }

}
