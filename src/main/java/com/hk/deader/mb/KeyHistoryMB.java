package com.hk.deader.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.hk.deader.dao.KeyHistoryDAO;
import com.hk.deader.domains.Keyhistory;
@ManagedBean(name="keystory")
@ViewScoped
public class KeyHistoryMB implements Serializable{

	private static final long serialVersionUID = 3239018863634343595L;
	@ManagedProperty(value="#{login}")
	private CredentialsMB CMB;
	private LazyDataModel<Keyhistory> lazyKeyStory = null;
	private Date fromDate = null;
	private Date toDate = null;
	private Boolean isLazy = false;
	private List<Integer> avtomatList = null;
	private Integer avtoNum;
	private KeyHistoryDAO khdao;
	private String filterKey;
	private Boolean automatFilterEnabled = false;
	private Boolean keyFilterEnabled = false;
	private Map<String,String> filtri=new HashMap<String,String>();
	
	public KeyHistoryMB(){}
	
	public CredentialsMB getCMB() {
		return CMB;
	}

	public void setCMB(CredentialsMB cMB) {
		CMB = cMB;
	}

	public Date getFromDate() {
		if (fromDate == null)
		{
			fromDate = new Date();//By default setting fromDay to beginning of current day
			fromDate.setHours(0);
			fromDate.setMinutes(0);
			fromDate.setSeconds(59);
		}  
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
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
	
	public void getByDate()
	{
		if (toDate == null)
		{
			toDate = new Date();
			toDate.setHours(23); //setting fromDay to the start and toDay to the end of the day
			toDate.setMinutes(59);
			toDate.setSeconds(59);
		}
		if (fromDate == null)
		{
			fromDate = new Date();
			fromDate.setHours(0);
			fromDate.setMinutes(0);
			fromDate.setSeconds(0);
		}
		toDate.setHours(23);
		toDate.setMinutes(59);
		toDate.setSeconds(59);
		this.setLazyKeyStory(new LazyKeyStoryList( fromDate, toDate,1));
	}

	public LazyDataModel<Keyhistory> getLazyKeyStory() 
	{
		if(lazyKeyStory == null)getByDate();
		return lazyKeyStory;
	}

	public void setLazyKeyStory(LazyDataModel<Keyhistory> lazyKeyStory) {
		this.lazyKeyStory = lazyKeyStory;
	}

	public Boolean getIsLazy() {
		return isLazy;
	}

	public void setIsLazy(Boolean isLazy) {
		this.isLazy = isLazy;
	}

	
	public void showOperInkStory()
	{
		this.setLazyKeyStory(new LazyKeyStoryList(fromDate, toDate,1));
	}

	public List<Integer> getAvtomatList() {
		//if(avtomatList == null)
		//{
			khdao = new KeyHistoryDAO();
			avtomatList = khdao.getOperAvtomats();
			khdao = null;
			return avtomatList;
	//	}
		//return avtomatList;
	}

	public void setAvtomatList(List<Integer> avtomatList) {
		this.avtomatList = avtomatList;
	}

	public String getFilterKey() {
		return filterKey;
	}

	public void setFilterKey(String filterKey) {
		this.filterKey = filterKey;
	}

	public Integer getAvtoNum() {
		return avtoNum;
	}

	public void setAvtoNum(Integer avtoNum) {
		this.avtoNum = avtoNum;
	}
	
	public void filterBy()
	{
		if(keyFilterEnabled)filtri.put("key", filterKey);
		if(automatFilterEnabled)filtri.put("autonum", avtoNum.toString());
		if(keyFilterEnabled||automatFilterEnabled)
		toDate.setHours(23);
		toDate.setMinutes(59);
		toDate.setSeconds(59);
		{
			lazyKeyStory = new LazyKeyStoryList(filtri,fromDate,toDate);
		}
	}

	public Boolean getAutomatFilterEnabled() {
		return automatFilterEnabled;
	}

	public void setAutomatFilterEnabled(Boolean automatFilterEnabled) {
		this.automatFilterEnabled = automatFilterEnabled;
	}

	public Boolean getKeyFilterEnabled() {
		return keyFilterEnabled;
	}

	public void setKeyFilterEnabled(Boolean keyFilterEnabled) {
		this.keyFilterEnabled = keyFilterEnabled;
	}
	
	public void autoFil()
	{
		this.setAutomatFilterEnabled(true);
	}
	
	public void keyFil()
	{
		this.setKeyFilterEnabled(true);
	}

}
