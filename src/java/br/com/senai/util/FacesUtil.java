/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.util;

import javax.faces.context.FacesContext;

/**
 *
 * @author Paulo
 */
public class FacesUtil {
    
    
    public static Object getApplicationMapValue(String key){
        return FacesContext.getCurrentInstance().getExternalContext().
                getApplicationMap().get(key);
    }
    
    public static void setApplicationMapValue(String key, Object obj){
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put(key, obj);
    }
}
