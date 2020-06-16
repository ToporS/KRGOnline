package com.hk.deader.utils;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
@FacesConverter("strconverter")
public class StrConverter implements Serializable, Converter {

	private static final long serialVersionUID = 4526815049232488821L;

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		return value;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		// TODO Auto-generated method stub
		return value.toString();
	}

}
