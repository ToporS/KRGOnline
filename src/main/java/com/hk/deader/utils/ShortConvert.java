package com.hk.deader.utils;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
@FacesConverter("shortconvert")
public class ShortConvert implements Converter, Serializable {

	private static final long serialVersionUID = 4306012292539348012L;

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		short val = (short)Short.parseShort(value);
		return val;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		// TODO Auto-generated method stub
		return value.toString();
	}

}
