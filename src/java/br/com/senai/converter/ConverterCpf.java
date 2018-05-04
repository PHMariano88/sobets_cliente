/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Paulo
 */
@FacesConverter(value = "converterCpf")
public class ConverterCpf implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

      
        if (value != null || !value.equals("")) {
            value = value.replaceAll("\\.","");
            value = value.replaceAll("\\-", "");
        }
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }

}
