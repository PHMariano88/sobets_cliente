/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.bean;

import br.com.senai.dao.RequerimentoDAO;
import br.com.senai.pojo.Requerimento;
import br.com.senai.util.FacesUtil;
import java.io.File;

import java.io.InputStream;
import java.io.Serializable;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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
    
    private StreamedContent fileDownload;
    
    public GerencialMenuBean() {
        reqDao = new RequerimentoDAO();
    }
    
    @PostConstruct
    public void init() {
        String setor = FacesUtil.getApplicationMapValue("paramSetor").toString();
        listaRequerimentoRecebido = reqDao.selectRequerimentoBySetor(setor,
                "ENVIADO");
        
        listaRequerimentoEmAnalise = reqDao.selectRequerimentoBySetor(setor, "Analise");
        
        listaRequerimentoFinalizado = reqDao.selectRequerimentoBySetor(setor, "Finalizado");
        
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
        buscaArquivoAnexo();
    }
    
    public StreamedContent getFileDownload() {
        return fileDownload;
    }
    
    public void buscaArquivoAnexo() {
        
        try {
    
            InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(new File(requerimentoSelecionado.getDiretorioAnexo()).getAbsolutePath());
            System.out.println("O caminho é " + stream);
            if (stream != null) {
                System.out.println("O caminho é " + stream);
                fileDownload = new DefaultStreamedContent(stream);
            }
        } catch (Exception ex) {
            Logger.getLogger(GerencialMenuBean.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
}
