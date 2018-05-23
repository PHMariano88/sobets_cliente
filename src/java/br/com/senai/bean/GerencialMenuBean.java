/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.bean;

import br.com.senai.dao.EnvioEmail;
import br.com.senai.dao.RequerimentoDAO;
import br.com.senai.pojo.Requerimento;
import br.com.senai.util.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;

/**
 *
 * @author Paulo
 */
@Named(value = "gerencialMenuBean")
@SessionScoped
public class GerencialMenuBean implements Serializable {

    private List<Requerimento> listaRequerimentoRecebido;
    private List<Requerimento> listaRequerimentoEmAnalise;
    private List<Requerimento> listaRequerimentoFinalizado;
    private RequerimentoDAO reqDao;
    private Requerimento requerimentoSelecionado;
    private MensagemFacesMensage mensagem;

    public GerencialMenuBean() {
        reqDao = new RequerimentoDAO();
        mensagem = new MensagemFacesMensage();
    }

    @PostConstruct
    public void init() {
        inicializaTab();
    }

    public List<Requerimento> getListaRequerimentoRecebido() {
        return listaRequerimentoRecebido;
    }

    public void setListaRequerimentoRecebido(List<Requerimento> listaRequerimentoRecebido) {
        this.listaRequerimentoRecebido = listaRequerimentoRecebido;
    }

    public List<Requerimento> getListaRequerimentoEmAnalise() {
        return listaRequerimentoEmAnalise;
    }

    public void setListaRequerimentoEmAnalise(List<Requerimento> listaRequerimentoEmAnalise) {
        this.listaRequerimentoEmAnalise = listaRequerimentoEmAnalise;
    }

    public List<Requerimento> getListaRequerimentoFinalizado() {
        return listaRequerimentoFinalizado;
    }

    public void setListaRequerimentoFinalizado(List<Requerimento> listaRequerimentoFinalizado) {
        this.listaRequerimentoFinalizado = listaRequerimentoFinalizado;
    }

    public Requerimento getRequerimentoSelecionado() {
        return requerimentoSelecionado;
    }

    public void setRequerimentoSelecionado(Requerimento requerimentoSelecionado) {
        this.requerimentoSelecionado = requerimentoSelecionado;

    }

    public void analisaRequerimento() {
        EnvioEmail email = new EnvioEmail();
        try {
            requerimentoSelecionado.setStatus("ANALISE");
            requerimentoSelecionado.setResponsavel(FacesUtil.getApplicationMapValue("paramNome").toString());
            reqDao.updateRequerimento(requerimentoSelecionado);
            email.enviaEmail(requerimentoSelecionado);
            mensagem.constroiMensagemCerto(FacesContext.getCurrentInstance(), "Atendimento realizado com sucesso",
                    "O Requerimento está em analise. Por favor confira a aba Em Análise.");
        } catch (HibernateException ex) {
            mensagem.constroiMensagemErro(FacesContext.getCurrentInstance(), "Erro",
                    "Tente novamente. Se o erro persistir contate o administrador do sistema!" + ex);
        }
    }

    public void finalizaRequerimento() {

        try {
            EnvioEmail email = new EnvioEmail();
            requerimentoSelecionado.setStatus("FINALIZADO");
            requerimentoSelecionado.setResponsavel(FacesUtil.getApplicationMapValue("paramNome").toString());
            reqDao.updateRequerimento(requerimentoSelecionado);
            email.enviaEmail(requerimentoSelecionado);
            mensagem.constroiMensagemCerto(FacesContext.getCurrentInstance(), "Atendimento finalizado com sucesso.",
                    "O atendimento a este requerimento foi finalizado com sucesso."
                    + " Os detalhes deste requerimento podem ser vistos na aba finalizados.");
        } catch (HibernateException ex) {
            mensagem.constroiMensagemErro(FacesContext.getCurrentInstance(), "Erro",
                    "Tente novamente. Se o erro persistir contate o administrador do sistema!" + ex);
        }

    }

    private void inicializaTab() {

        String setor = FacesUtil.getApplicationMapValue("paramSetor").toString();
        listaRequerimentoRecebido = reqDao.selectRequerimentoBySetor(setor,
                "ENVIADO");

        listaRequerimentoEmAnalise = reqDao.selectRequerimentoBySetor(setor, "ANALISE");

        listaRequerimentoFinalizado = reqDao.selectRequerimentoBySetor(setor, "FINALIZADO");

    }

}
