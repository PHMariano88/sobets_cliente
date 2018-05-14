/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 Editado por mim Paulo H
 */
package br.com.senai.dao;

import br.com.senai.pojo.Requerimento;
import br.com.senai.util.HibernateUtil;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author paule
 */
public class RequerimentoDAO {

    private Session sessao;

    public RequerimentoDAO() {
        sessao = HibernateUtil.getSessionFactory().openSession();
    }

    public void insert(Requerimento requerimento) throws HibernateException {

        try {
            sessao.beginTransaction();
            requerimento.setDataRequerimento(new Date());
            requerimento.setStatus("ENVIADO");
            sessao.save(requerimento);
            sessao.getTransaction().commit();
        } finally {
            sessao.close();
        }
    }

    public List selectRequerimentoBySetor(String setor) {

        sessao.getTransaction();

        Query query = sessao.createQuery(
                "select requerimento\n"
                + "from Requerente req, \n"
                + "	 Requerimento requerimento, \n"
                + "     TipoRequerimento tpRequerimento\n"
                + "where \n"
                + "     requerimento.codigoTipoRequerimento = tpRequerimento.codigoTipoRequerimento and\n"
                + "     req.cpf = requerimento.cpfRequerente and\n"
                + "     requerimento.status like 'ENVIADO' and\n"
                + "     tpRequerimento.setorResponsavel = :setor \n"
                + "order by \n"
                + "	 requerimento.dataRequerimento	\n"
                + "     ");
        query.setParameter("setor", setor);
        List lista = query.list();

        return lista;
    }
}
