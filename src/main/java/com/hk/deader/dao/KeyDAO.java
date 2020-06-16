package com.hk.deader.dao;

import java.io.Serializable;
import java.util.List;

import org.primefaces.model.SortOrder;

import com.hk.deader.domains.Key;
import com.hk.deader.service.AvtomatService;
import com.hk.deader.service.BonusService;
import com.hk.deader.service.KeyHistoryService;
import com.hk.deader.service.KeyService;

public class KeyDAO implements Serializable{

	private static final long serialVersionUID = 2997203263809470796L;
	private List<Key> keyList;
	private BonusService bs;
	private KeyService ks;
	private KeyHistoryService khs;
	
	public KeyDAO(){}
	
	public List<Key> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<Key> keyList) {
		this.keyList = keyList;
	}

	public List<Key> getOperKeys(String opername) {
		ks = new KeyService();
		keyList = ks.getOperKeys(opername);
		ks = null;
		return keyList;
	}

	public List<Key> getLazyKeyList(int first, int pageSize) 
	{
		ks = new KeyService();
		keyList = ks.getOperKeysLazy(first,pageSize);
		ks = null;
		return keyList;
	}

	public int countPlayersTotal() 
	{
		ks = new KeyService();
		Integer rowCount = ks.getRowCount();
		ks = null;
		return rowCount;
	}

	public List<Key> getFilteredLazyKeys(int first, int pageSize, String opername, String filterValue) 
	{
		ks = new KeyService();
		keyList = ks.getFilteredKeys(first, pageSize, filterValue);
		ks = null;
		return keyList;
	}

	public int getFilteredRowCount(String opername, String filterValue) 
	{
		ks = new KeyService();
		Integer rowCount = ks.getFilteredRowCount(filterValue);
		ks = null;
		return rowCount;
	}

	public void addMoney(String operid, String key, double adsumbon)
	{
			
		//Double sum = (Double) (selectedKey.getBonadd()+adsumbon);
		bs = new BonusService();
		bs.addEntry(key,adsumbon,operid);
		bs = null;
		ks = new KeyService();
		Double existingBonus = ks.checkBonus(key);
		if(existingBonus == null) existingBonus = new Double(0);
		Double sum = existingBonus+adsumbon;			
		ks.addbon(sum,key);
		ks = null;		
	}

	public void remKey(Key selectedKey) 
	{
		ks = new KeyService();
		String key = selectedKey.getNumkey();
		ks.remKey(key);
		ks = null;
	}

	public void editKey(Key selectedKey) 
	{
		ks = new KeyService();
		ks.editKey(selectedKey);
		ks = null;
	}

	public void appBonAdd(String polzid, Double bonadd, Integer anum) 
	{
		khs = new KeyHistoryService();
		List<String> selKeys = khs.selKeysToAdd(anum);
		khs = null;
		if(selKeys != null)
		{
			for(String str: selKeys)
			{				
				addMoney(polzid, str, bonadd);
			}
		}
		
	}

	public void groupBonAdd(String polzid, Double bonadd, String group)
	{
		ks = new KeyService();
		List<String> selKeys = ks.groupKeySelect(group);
		ks = null;
		if(selKeys != null)
		{
			for(String str: selKeys)
			{				
				addMoney(polzid, str, bonadd);
			}
		}		
	}

	public List<Key> getSortedLazyKeyList(int first, int pageSize, String sortField, SortOrder sortOrder) 
	{
		String order;
		if (sortOrder.toString().equalsIgnoreCase("ASCENDING")){order = "asc";} else {order = "desc";}
		String query = "from Key key where (not (key.numkey = '000000'))  order by " + sortField+" "+order;
		ks = new KeyService();
		keyList = ks.getSortedKeyList(first, pageSize, query);
		ks = null;
		return keyList;
	}

	public List<Key> getSortedFilteredLazyKeys(int first, int pageSize,  String filterValue, String sortField, SortOrder sortOrder) 
	{
		String order;
		if (sortOrder.toString().equalsIgnoreCase("ASCENDING")){order = "asc";} else {order = "desc";}
		String query = "from Key key where (not (key.numkey = '000000')) and (key.numkey like "+filterValue+"%)  order by " + sortField+" "+order;
		ks = new KeyService();
		keyList = ks.getSortedKeyList(first, pageSize, query);
		ks = null;
		return keyList;
	}

}
