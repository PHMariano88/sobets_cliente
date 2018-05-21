/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.converter;

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

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value != null && !"".equals(value)) {
            return (TipoRequerimento) component.getAttributes().get(value);
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value instanceof TipoRequerimento) {
            TipoRequerimento entity = (TipoRequerimento) value;
            if ((entity instanceof TipoRequerimento)
                    && (entity.getDescricaoTipoSolicitacao() != null)) {
                 component.getAttributes().put(String.valueOf(entity.getCodigoTipoSolicitacao()), entity);
                return String.valueOf(entity.getCodigoTipoSolicitacao());
            }

        }

        return "";
    }
}
