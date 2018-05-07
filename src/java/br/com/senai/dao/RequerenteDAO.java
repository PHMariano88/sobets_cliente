/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.dao;

import br.com.senai.pojo.Requerente;
import br.com.senai.util.HibernateUtil;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author paule
 */
public class RequerenteDAO {

    private Session sessao;

    public RequerenteDAO() {

        sessao = HibernateUtil.getSessionFactory().openSession();
    }

    public void insereRequerente(Requerente requerente) throws HibernateException {
        try {
            sessao.beginTransaction();
            sessao.merge(requerente);
            sessao.getTransaction().commit();
            System.out.println("Salvo com sucesso!!!");
        }finally{
            if(sessao.isOpen()){
                sessao.close();
            }
        }
        }

    }
