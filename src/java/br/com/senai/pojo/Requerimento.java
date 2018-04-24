/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author paule
 */
@Entity
public class Requerimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigoRequerimento;
    @ManyToOne
    @JoinColumn(name = "cpfRequerente")
    private Requerente cpfRequerente;
    @ManyToOne
    @JoinColumn(name = "codigoTipoRequerimento")
    private TipoRequerimento codigoTipoRequerimento;
    private String status;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataRequerimento;
    private String observacao;
    private String responsavel;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFinalizacaoRequerimento;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) (this.codigoRequerimento ^ (this.codigoRequerimento >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Requerimento other = (Requerimento) obj;
        if (this.codigoRequerimento != other.codigoRequerimento) {
            return false;
        }
        return true;
    }
    
    
    

    public long getCodigoRequerimento() {
        return codigoRequerimento;
    }

    public void setCodigoRequerimento(long codigoRequerimento) {
        this.codigoRequerimento = codigoRequerimento;
    }

    public Requerente getCpfRequerente() {
        return cpfRequerente;
    }

    public void setCpfRequerente(Requerente cpfRequerente) {
        this.cpfRequerente = cpfRequerente;
    }

    public TipoRequerimento getCodigoTipoRequerimento() {
        return codigoTipoRequerimento;
    }

    public void setCodigoTipoRequerimento(TipoRequerimento codigoTipoRequerimento) {
        this.codigoTipoRequerimento = codigoTipoRequerimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataRequerimento() {
        return dataRequerimento;
    }

    public void setDataRequerimento(Date dataRequerimento) {
        this.dataRequerimento = dataRequerimento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Date getDataFinalizacaoRequerimento() {
        return dataFinalizacaoRequerimento;
    }

    public void setDataFinalizacaoRequerimento(Date dataFinalizacaoRequerimento) {
        this.dataFinalizacaoRequerimento = dataFinalizacaoRequerimento;
    }

}
