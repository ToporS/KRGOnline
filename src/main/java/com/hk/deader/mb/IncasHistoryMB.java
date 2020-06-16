package com.hk.deader.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.hk.deader.dao.InkasHistoryDAO;
import com.hk.deader.domains.InkHist;
import com.hk.deader.domains.Inkasshistory;

@ManagedBean(name="incasstory")
@ViewScoped
public class IncasHistoryMB  implements Serializable {

	private static final long serialVersionUID = -6618626838168632508L;
/*	@ManagedProperty(value="#{login}")
	private CredentialsMB CMB;*/
	private Date fromDate = new Date();
	private Date toDate = new Date();
	private LazyDataModel<InkHist> lazyKeyStory = null;
	
	
	
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
			fromDate.setSeconds(59);
		}  
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public void getByDate()
	{
		toDate.setHours(23);
		toDate.setMinutes(59);
		toDate.setSeconds(59);
		fromDate.setHours(0);
		fromDate.setMinutes(0);
		fromDate.setSeconds(0);
		this.setLazyKeyStory(new LazyInkasStoryList(fromDate, toDate, 1));		
	}
	public LazyDataModel<InkHist> getLazyKeyStory() {
		if (lazyKeyStory == null)getByDate();
		return lazyKeyStory;
	}
	public void setLazyKeyStory(LazyDataModel<InkHist> lazyKeyStory) {
		//if(lazyKeyStory.getRowData() == null) this.lazyKeyStory = new LazyInkasStoryList(); 
		this.lazyKeyStory = lazyKeyStory;
	}
	
	public void showUserAuto()
	{
		this.setLazyKeyStory(new LazyInkasStoryList(fromDate, toDate,1));
	}

}
