/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.bean;

import br.com.senai.dao.RequerenteDAO;
import br.com.senai.dao.RequerimentoDAO;
import br.com.senai.dao.TipoRequerimentoDAO;
import br.com.senai.pojo.MensagemEmail;
import br.com.senai.pojo.Requerente;
import br.com.senai.pojo.Requerimento;
import br.com.senai.pojo.TipoRequerimento;
import br.com.senai.util.EmailUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author paule
 */
@ManagedBean
@RequestScoped
public class RequerimentoBean {

    /**
     * Creates a new instance of RequerenteBean
     */
    private Requerente requerente;
    private RequerenteDAO dao;
    private List<TipoRequerimento> listaRequerimento;
    private TipoRequerimento tipoRequerimento;
    private Requerimento requerimento;
    private String observacao;
    private String confirmaEmail;
    private String cpf;
    private boolean habilitaUploadArquivo;

    public RequerimentoBean() {
        requerente = new Requerente();
        dao = new RequerenteDAO();
        tipoRequerimento = new TipoRequerimento();
        requerimento = new Requerimento();
        habilitaUploadArquivo = true;

    }

    @PostConstruct
    public void init() {
        TipoRequerimentoDAO tipoDao = new TipoRequerimentoDAO();
        listaRequerimento = tipoDao.listaTipoRequerimento();
    }

    public Requerente getRequerente() {
        return requerente;
    }

    public void setRequerente(Requerente requerente) {
        this.requerente = requerente;
    }

    private void converteCPF() {
        if (!cpf.isEmpty()) {
            requerente.setCpf(Long.parseLong(cpf));
        }
    }

    public void realizaRequerimento() {

        RequerimentoDAO requerimentoDao = new RequerimentoDAO();
        converteCPF();
        if (validaEmail()) {
            this.requerimento.setCpfRequerente(this.requerente);
            this.requerimento.setCodigoTipoRequerimento(this.tipoRequerimento);
            this.requerimento.setObservacao(this.observacao);
            dao.insereRequerente(this.requerente);
            requerimentoDao.insert(this.requerimento);
            //   enviaEmail();
        }
    }

    public List<TipoRequerimento> getListaRequerimento() {
        return listaRequerimento;
    }

    public void setListaRequerimento(List<TipoRequerimento> listaRequerimento) {
        this.listaRequerimento = listaRequerimento;
    }

    public TipoRequerimento getTipoRequerimento() {
        return tipoRequerimento;
    }

    public void setTipoRequerimento(TipoRequerimento tipoRequerimento) {
        this.tipoRequerimento = tipoRequerimento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getConfirmaEmail() {
        return confirmaEmail;
    }

    public void setConfirmaEmail(String confirmaEmail) {
        this.confirmaEmail = confirmaEmail;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    private boolean validaEmail() {

        boolean retorno;
        FacesMessage msg = null;
        if (!this.confirmaEmail.equals(this.requerente.getEmail())) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inválido",
                    "E-mails informados não são iguais");
            retorno = false;
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            retorno = true;
        }
        return retorno;
    }

    

    public boolean isHabilitaUploadArquivo() {
        return habilitaUploadArquivo;
    }

    public void habilitarUploadArquivo() {
        habilitaUploadArquivo = tipoRequerimento.getCodigoTipoSolicitacao() != 1;

    }

}
