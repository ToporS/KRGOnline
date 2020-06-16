package com.hk.deader.utils;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
@FacesConverter("doubleconvert")
public class DoubleConverter implements Serializable, Converter {

	private static final long serialVersionUID = -94499671628363118L;

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// TODO Auto-generated method stub
		Double ig = Double.parseDouble(value.toString());
		return ig;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		// TODO Auto-generated method stub
		return value.toString();
	}

}
