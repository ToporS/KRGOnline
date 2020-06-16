package com.hk.deader.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.hk.deader.dao.KeyDAO;
import com.hk.deader.dao.KeyHistoryDAO;
import com.hk.deader.domains.BonHist;
import com.hk.deader.domains.Bonushistory;

@ManagedBean(name="bonusstory")
@ViewScoped
public class BonusHistoryMB implements Serializable 
{
	private static final long serialVersionUID = 2850662676349952878L;
	@ManagedProperty(value="#{login}")
	private CredentialsMB CMB;
	private Date fromDate = new Date();
	private Date toDate = new Date();
	private LazyDataModel<BonHist> lazyBonusStory = null;
	private String group;
	private List<Integer> avtomatList = null;
	private KeyHistoryDAO khdao;
	private Integer anum;
	private Double bonadd;
	private KeyDAO kdao;
	
	public BonusHistoryMB(){}
	
	public CredentialsMB getCMB() {
		return CMB;
	}


	public void setCMB(CredentialsMB cMB) {
		CMB = cMB;
	}
	
	public Date getToDate() {
		if (toDate == null)
		{
			toDate =  new Date(); //By default setting toDay to the end of current day
			toDate.setHours(23);
			toDate.setMinutes(59);
			toDate.setSeconds(59);
		} 
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Date getFromDate() {
		if (fromDate == null)
		{
			fromDate = new Date();//By default setting fromDay to beginning of current day
			fromDate.setHours(0);
			fromDate.setMinutes(0);
			fromDate.setSeconds(1);
		}  
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public void setLazyBonusStory(LazyDataModel<BonHist> lazyBonusStory) {
		this.lazyBonusStory = lazyBonusStory;
	}
	public LazyDataModel<BonHist> getLazyBonusStory() {
		if(lazyBonusStory == null) getByDate();
		return lazyBonusStory;
	}	
	
	public List<Integer> getAvtomatList() {
		//if(avtomatList == null)
		//{
			khdao = new KeyHistoryDAO();
			avtomatList = khdao.getOperAvtomats();
			khdao = null;
			return avtomatList;
		//} else return avtomatList;
	}
	public void setAvtomatList(List<Integer> avtomatList) {
		this.avtomatList = avtomatList;
	}
	public Integer getAnum() {
		return anum;
	}
	public void setAnum(Integer anum) {
		this.anum = anum;
	}
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Double getBonadd() {
		return bonadd;
	}
	public void setBonadd(Double bonadd) {
		this.bonadd = bonadd;
	}
	
	public void getByDate() {
		toDate.setHours(23);
		toDate.setMinutes(59);
		toDate.setSeconds(59);
		fromDate.setHours(0);
		fromDate.setMinutes(0);
		fromDate.setSeconds(0);
		this.setLazyBonusStory(new LazyBonusStoryList(fromDate, toDate));
		
	}
	
	public void appbonadd()
	{
		kdao = new KeyDAO();
		kdao.appBonAdd(CMB.getPolzid(),bonadd,anum);
		kdao = null;
	}
	
	public void groupbonadd()
	{
		kdao = new KeyDAO();
		kdao.groupBonAdd(CMB.getPolzid(),bonadd,group);
		kdao = null;
	}
}
