/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.senai.dao;

import br.com.senai.pojo.Requerimento;
import br.com.senai.util.HibernateUtil;
import java.time.LocalDateTime;
import java.util.Date;
import org.hibernate.Session;

/**
 *
 * @author paule
 */
public class RequerimentoDAO {
    
    private Session sessao;
    
    public RequerimentoDAO(){
        sessao = HibernateUtil.getSessionFactory().openSession();
    }
    
    
    public void insert(Requerimento requerimento){
    
        sessao.beginTransaction();
        requerimento.setDataRequerimento(new Date());
        requerimento.setStatus("ENVIADO");
        sessao.save(requerimento);
        sessao.getTransaction().commit();
    }
}