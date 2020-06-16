package com.hk.deader.utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.hk.deader.service.PolzService;
@FacesValidator("uservalidator")
public class UserValidator implements Validator {

	public void validate(FacesContext context, UIComponent component,Object value) throws ValidatorException 
	{
		if(value==null||value.toString().equalsIgnoreCase(""))
		{
			FacesMessage msg = new FacesMessage("Ошибка","Поле не может быть пустым");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		PolzService ps = new PolzService();
		String on = ps.checkPolzId(value.toString());
		ps = null;
		
		if(on!=null)
		{
			if(on.equalsIgnoreCase("err"))
			{
				FacesMessage msg = new FacesMessage("Ошибка","Ошибка проверки.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
			FacesMessage msg = new FacesMessage("Ошибка","Логин занят. Введите другой");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}

}
