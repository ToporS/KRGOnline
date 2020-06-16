package com.hk.deader.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.LazyDataModel;

import com.hk.deader.dao.AvtomatDAO;
import com.hk.deader.domains.AvtomatWrapper;
import com.hk.deader.service.AvtomatService;
import com.hk.deader.service.OperatorService;

@ManagedBean(name="avtomatmb")
@ViewScoped

public class AvtomatMB implements Serializable{
	private static final long serialVersionUID = -5490747039551637320L;
	/*@ManagedProperty(value="#{login}")
	private CredentialsMB CMB;*/
	private AvtomatDAO adao;
	private Integer aNum;
	private List<AvtomatWrapper> avtomats = null;
	private LazyDataModel<AvtomatWrapper> lazyAvtomats = null;
	private AvtomatWrapper selectedAvtomat = new AvtomatWrapper();;
	private String msg;	
	private List<Long> drebinst = new ArrayList<Long>();
	private List<Long> restinst = new ArrayList<Long>();
	private List<String> urginst = new ArrayList<String>();
	private List<String> coninst = new ArrayList<String>();
	//private String operatorName;
	private AvtomatService as = null;
	
	public AvtomatMB()
	{
		urginst.add("Срочно");
		urginst.add("Не срочно");
		coninst.add("Замыкание");
		coninst.add("Размыкание");
		drebinst.add((long) 10);
		drebinst.add((long) 50);
		drebinst.add((long) 100);
		drebinst.add((long) 500);
		drebinst.add((long) 1000);
		drebinst.add((long) 5000);
		drebinst.add((long) 10000);
		drebinst.add((long) 30000);
		drebinst.add((long) 60000);
		restinst.add((long) 10);
		restinst.add((long) 50);
		restinst.add((long) 100);
		restinst.add((long) 500);
		restinst.add((long) 1000);
		restinst.add((long) 5000);
		restinst.add((long) 10000);
		restinst.add((long) 21600);
		restinst.add((long) 30000);
		restinst.add((long) 60000);
	}
		
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public AvtomatWrapper getSelectedAvtomat() {
		return selectedAvtomat;
	}
	public void setSelectedAvtomat(AvtomatWrapper selectedAvtomat) {
		this.selectedAvtomat = selectedAvtomat;
	}	
	
	public void setAvtomats(List<AvtomatWrapper> avtomats) {
		this.avtomats = avtomats;
	}
	
	public List<Long> getDrebinst() {
		return drebinst;
	}

	public List<Long> getRestinst() {
		return restinst;
	}
	
	public List<String> getUrginst() {
		return urginst;
	}

	public List<String> getConinst() {
		return coninst;
	}
	
	public LazyDataModel<AvtomatWrapper> getLazyAvtomats() {
		if(lazyAvtomats == null)
		{
			lazyAvtomats = new LazyAvtomatList("");
			/*adao = new AvtomatDAO();
			selectedAvtomat = adao.getSelected(getOperatorName()); 
			adao = null;*/
		}		
		return lazyAvtomats;
	}

	public void setLazyAvtomats(LazyDataModel<AvtomatWrapper> lazyAvtomats) {
		this.lazyAvtomats = lazyAvtomats;
	}
	
	public Integer getaNum() {
		return aNum;
	}

	public void setaNum(Integer aNum) {
		this.aNum = aNum;
	}
	
	public void avtoper(SelectEvent event)
	{
		selectedAvtomat = (AvtomatWrapper) event.getObject();
	}
	public void rowToggle(AvtomatWrapper sa)
	{
		//selectedAvtomat=(AvtomatWrapper)event.getData();
		selectedAvtomat = sa;
		if(selectedAvtomat.getSenSet().getPoUpdate()) 
		{
			AvtomatService as = new AvtomatService();
			as.remUpdt(selectedAvtomat.getAvtomat().getNum());
			as = null;
		}
		selectedAvtomat.getAvtomat().setFlag((short)(selectedAvtomat.getAvtomat().getFlag()-4));
	}
	public void uncheck(Integer num, Long code)
	{
		adao = new AvtomatDAO();
		adao.remcheck(num,code);
		adao = null;
	}	
	
	public void saveParams()
	{
		adao = new AvtomatDAO();
		adao.saveSettings(selectedAvtomat);
		adao = null;
	}
	
	public void saveParamsForAll()
	{
		adao = new AvtomatDAO();
		adao.saveSettingsForAll(selectedAvtomat);
		adao = null;
	}	

	public void showUserAuto()
	{
		lazyAvtomats = new LazyAvtomatList("");
		adao = new AvtomatDAO();
		selectedAvtomat = adao.getSelected(""); 
		adao = null;
	}
	
	public void showAutoById()
	{
		lazyAvtomats = new LazyAvtomatList(getaNum());
	}	
	
	public void resetAvtomat()
	{
		adao = new AvtomatDAO();
		adao.delAvto(selectedAvtomat.getAvtomat().getNum());
		adao.saveAvto(selectedAvtomat.getAvtomat());
		adao = null;
	}
}
