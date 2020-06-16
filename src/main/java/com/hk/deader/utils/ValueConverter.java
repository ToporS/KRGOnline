package com.hk.deader.utils;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("sobconverter")
public class ValueConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1782611876829142086L;
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// TODO Auto-generated method stub
		Long ig = Long.parseLong(value.toString());
		return ig;
	}
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		// TODO Auto-generated method stub
		return value.toString();
	}

}
