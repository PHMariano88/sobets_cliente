/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.converter;

import br.com.senai.dao.TipoRequerimentoDAO;
import br.com.senai.pojo.TipoRequerimento;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author paule
 */
@FacesConverter(value = "simpleConverterTipoRequerimento")
public class TipoRequerimentoConverter implements Converter {

    TipoRequerimentoDAO tipoDAO = new TipoRequerimentoDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TipoRequerimento tipo = new TipoRequerimento();
        if (value != null && !"".equals(value)) {
            tipo = tipoDAO.buscaTipoRequerimentoByDescricao(value);
           
        }

        return tipo;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        return value == null ? "" : value.toString();
    }
}
