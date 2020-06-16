package com.hk.deader.utils;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
@FacesConverter("intconvert")
public class IntegerConverter implements Converter,Serializable {

	private static final long serialVersionUID = -3397204314958177745L;

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// TODO Auto-generated method stub
		if(value.equalsIgnoreCase("")) return 0;
		Integer ig = Integer.parseInt(value.toString());
		return ig;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		// TODO Auto-generated method stub
		return value.toString();
	}

}
