/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author paule
 */
@Entity
public class TipoRequerimento implements Serializable {

    @Id
    private int codigoTipoRequerimento;
    private String descricaoTipoRequerimento;
    private String setorResponsavel;
    @OneToMany
    private List<Requerimento> listaRequerimento;

    @Override
    public String toString() {
        return descricaoTipoRequerimento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.codigoTipoRequerimento;
        hash = 53 * hash + Objects.hashCode(this.descricaoTipoRequerimento);
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
        final TipoRequerimento other = (TipoRequerimento) obj;
        if (this.codigoTipoRequerimento != other.codigoTipoRequerimento) {
            return false;
        }
        return true;
    }

 

    public int getCodigoTipoSolicitacao() {
        return codigoTipoRequerimento;
    }

    public String getDescricaoTipoSolicitacao() {
        return descricaoTipoRequerimento;
    }

    public String getSetor() {
        return setorResponsavel;
    }

    public void setCodigoTipoRequerimento(int codigoTipoRequerimento) {
        this.codigoTipoRequerimento = codigoTipoRequerimento;
    }

    public List<Requerimento> getListaRequerimento() {
        return listaRequerimento;
    }

    public void setListaRequerimento(List<Requerimento> listaRequerimento) {
        this.listaRequerimento = listaRequerimento;
    }

    public String getDescricaoTipoRequerimento() {
        return descricaoTipoRequerimento;
    }

    public void setDescricaoTipoRequerimento(String descricaoTipoRequerimento) {
        this.descricaoTipoRequerimento = descricaoTipoRequerimento;
    }

    public String getSetorResponsavel() {
        return setorResponsavel;
    }

    public void setSetorResponsavel(String setorResponsavel) {
        this.setorResponsavel = setorResponsavel;
    }

}
