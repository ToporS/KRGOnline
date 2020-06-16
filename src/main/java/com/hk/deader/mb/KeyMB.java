package com.hk.deader.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.hk.deader.dao.AvtomatDAO;
import com.hk.deader.dao.KeyDAO;
import com.hk.deader.domains.AvtomatWrapper;
import com.hk.deader.domains.Key;

@ManagedBean(name="keys")
@ViewScoped
public class KeyMB implements Serializable {

	private static final long serialVersionUID = -35531049952929611L;
	@ManagedProperty(value="#{login}")
	private CredentialsMB CMB;
	private List<Key> keyList;
	private KeyDAO kdao;
	private Key selectedKey;
	private LazyDataModel<Key> lazyKeys = null;
	private String filterValue;
	private double adsumbon;
	
	public KeyMB(){}	
	
	public CredentialsMB getCMB() {
		return CMB;
	}
	public void setCMB(CredentialsMB cMB) {
		CMB = cMB;
	}
	public List<Key> getKeyList() {
		return keyList;
	}
	public void setKeyList(List<Key> keyList) {
		this.keyList = keyList;
	}
	public Key getSelectedKey() {
		return selectedKey;
	}
	public void setSelectedKey(Key selectedKey) {
		this.selectedKey = selectedKey;
	}
	
	public LazyDataModel<Key> getLazyKeys() {
		if (lazyKeys == null)
		{
			kdao = new KeyDAO();
			lazyKeys = new LazyKeyList("");
			kdao = null;
		}
		return lazyKeys;
	}

	public void setLazyKeys(LazyDataModel<Key> lazyKeys) {
		this.lazyKeys = lazyKeys;
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}
	
	public void filterBy()
	{
		lazyKeys = new LazyKeyList("", filterValue); 
	}
	
	public void addBalance()
	{
		kdao = new KeyDAO();
		kdao.addMoney(CMB.getPolzid(),selectedKey.getNumkey(),adsumbon);
		kdao = null;
	}
	
	public void editKey()
	{
		kdao = new KeyDAO();
		kdao.editKey(selectedKey);
		kdao = null;
	}
	
	public void remKey()
	{
		kdao = new KeyDAO();
		kdao.remKey(selectedKey);
		kdao = null;
	}


	public void showUserKeys()
	{
		kdao = new KeyDAO();
		lazyKeys = new LazyKeyList("");
		kdao = null;
	}

	public double getAdsumbon() {
		return adsumbon;
	}

	public void setAdsumbon(double adsumbon) {
		this.adsumbon = adsumbon;
	}

}
