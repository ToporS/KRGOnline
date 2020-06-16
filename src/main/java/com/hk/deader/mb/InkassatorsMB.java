package com.hk.deader.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.hk.deader.dao.InkassDAO;
import com.hk.deader.domains.Inkas;
@ManagedBean(name="inkasatori")
@ViewScoped
public class InkassatorsMB implements Serializable {

	private static final long serialVersionUID = 7615647009066366704L;
	/*@ManagedProperty(value="#{login}")
	private CredentialsMB CMB;*/
	private List<Inkas> inkasatori;
	private InkassDAO idao;
	private Inkas selectedInkas;
	private List<String> prava = new ArrayList<String>();
	
	public InkassatorsMB()
	{
		prava.add("Разрешено");
		prava.add("Запрещено");
	}
	
	public List<Inkas> getInkasatori() {
		if (inkasatori == null)
		{
			idao = new InkassDAO();
			inkasatori=idao.getInkassators();			
			idao = null;
			}
		return inkasatori;
	}
	public void setInkasatori(List<Inkas> inkasatori) {
		this.inkasatori = inkasatori;
	}
	public Inkas getSelectedInkas() {
		return selectedInkas;
	}
	public void setSelectedInkas(Inkas selectedInkas) {
		this.selectedInkas = selectedInkas;
	}
	public List<String> getPrava() {
		return prava;
	}
	public void setPrava(List<String> prava) {
		this.prava = prava;
	}
	
	public void saveInkas()
	{
		idao = new InkassDAO();
		idao.saveInkas(selectedInkas);
		idao = null;
	}
	
	public void delinkas()
	{
		idao = new InkassDAO();
		idao.delInkas(selectedInkas.getId().getDatakey());
		idao = null;
	}
	
	public void saveAsMaster()
	{
		idao = new InkassDAO();
		if(idao.saveAsMaster(selectedInkas.getId().getDatakey()) == null)
		{
			FacesContext context = FacesContext.getCurrentInstance();  	          
	        context.addMessage(null, new FacesMessage("Ошибка", "Ключ "+selectedInkas.getId().getDatakey()+" НЕ установлен как мастер-ключ!"));
		}
		else
		{
			FacesContext context = FacesContext.getCurrentInstance();  	          
	        context.addMessage(null, new FacesMessage("Удачно", "Ключ "+selectedInkas.getId().getDatakey()+" установлен как мастер-ключ!"));
		}
		idao = null;
	}

}
