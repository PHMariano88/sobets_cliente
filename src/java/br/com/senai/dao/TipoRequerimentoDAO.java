/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.dao;

import br.com.senai.pojo.TipoRequerimento;
import br.com.senai.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author paule
 */
public class TipoRequerimentoDAO {

    private Session sessao;

    public TipoRequerimentoDAO() {

        sessao = HibernateUtil.getSessionFactory().openSession();
    }

    public List listaTipoRequerimento() {

        sessao.beginTransaction();
        Criteria criteria = sessao.createCriteria(TipoRequerimento.class);
        return criteria.list();
    }

    public TipoRequerimento buscaTipoRequerimentoById(String tipoRequerimento) {

        sessao.beginTransaction();
        Criteria criteria = sessao.createCriteria(TipoRequerimento.class);
        criteria.add( Restrictions.like("descricaoTipoRequerimento", tipoRequerimento));
        return (TipoRequerimento) criteria.uniqueResult();
    }

}
