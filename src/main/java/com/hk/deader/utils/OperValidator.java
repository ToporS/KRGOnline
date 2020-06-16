package com.hk.deader.utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.hk.deader.service.OperatorService;
@FacesValidator("opervalidator")
public class OperValidator implements Validator {

	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
	{
		if(value==null||value.toString().equalsIgnoreCase(""))
		{
			FacesMessage msg = new FacesMessage("Ошибка","Поле не может быть пустым");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		OperatorService os = new OperatorService();
		String on = null;//os.checkOperId(value.toString());
		os = null;			
	}
}
