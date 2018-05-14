/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.bean;

import br.com.senai.dao.RequerimentoDAO;
import br.com.senai.pojo.Requerimento;
import br.com.senai.util.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
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
    private int tabIndex;

    public GerencialMenuBean() {
        reqDao = new RequerimentoDAO();
    }

    @PostConstruct
    public void init() {
        requerimento = reqDao.selectRequerimentoBySetor(FacesUtil.getApplicationMapValue("paramSetor").toString());
        System.out.println("passou aki " + requerimento.size());
      
    }

    public List<Requerimento> getRequerimento() {
        return requerimento;
    }

    public void setRequerimento(List requerimento) {
        this.requerimento = requerimento;
    }

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }
    
    public void mensagem(){
        System.out.println("A tab mudou" + tabIndex);
    
    }

}
