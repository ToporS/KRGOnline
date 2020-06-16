package com.hk.deader.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.primefaces.model.SortOrder;

import com.hk.deader.domains.InkHist;
import com.hk.deader.domains.Inkasshistory;
import com.hk.deader.domains.InkasshistoryId;
import com.hk.deader.domains.Keyhistory;
import com.hk.deader.service.IncassHistoryService;
import com.hk.deader.service.KeyHistoryService;

public class InkasHistoryDAO implements Serializable {
	
	private static final long serialVersionUID = 5687104735257439878L;
	private IncassHistoryService ihs;
	private List<InkHist> ihList;
	private List<List> tmpList;
	
	public InkasHistoryDAO(){}

/*	public List<Inkasshistory> getOperStoryByDate(Date fromDate, Date toDate, String opername) 
	{
		ihList = new ArrayList<InkHist>();
		ihs = new IncassHistoryService();
		tmpList = ihs.getOperStoryByDate(fromDate, toDate, opername);
		ihs = null;
		if(tmpList == null) return null;
		@SuppressWarnings({ "rawtypes" })
		Iterator<List> iter = tmpList.iterator();
		while (iter.hasNext())
		{	
			List tmplst = iter.next();
			InkasshistoryId khid = new InkasshistoryId((Date)tmplst.get(0), tmplst.get(1).toString());
			Inkasshistory khrec = new Inkasshistory();
			khrec.setId(khid);
			if(tmplst.get(2) != null){khrec.setSumadd((Double)tmplst.get(2));}else khrec.setSumadd(new Double(0));
			if(tmplst.get(3) != null){khrec.setSumtotal((Double)tmplst.get(3));}else khrec.setSumtotal(new Double(0));
			if(tmplst.get(4) != null){khrec.setSumrez((Double)tmplst.get(4));}else khrec.setSumrez(new Double(0));
			if(tmplst.get(5) != null){khrec.setAvtomat((Integer)tmplst.get(5));}else khrec.setAvtomat(new Integer(0));					
			this.ihList.add(khrec);				
		}
		
		return ihList;
	}*/

	public List<InkHist> getLazyInkasHisory(int first, int pageSize, Date fromDate, Date toDate, int i) 
	{
		ihList = new ArrayList<InkHist>();
		ihs = new IncassHistoryService();
		tmpList = ihs.getLazyInkasStory(first,pageSize,fromDate,toDate,i);
		ihs = null;
		if(tmpList == null) return null;
		@SuppressWarnings({ "rawtypes" })
		Iterator<List> iter = tmpList.iterator();
		//operKeyStoryList = null;
		while (iter.hasNext())
		{	
			List tmplst = iter.next();
			InkasshistoryId khid = new InkasshistoryId((Date)tmplst.get(0), tmplst.get(1).toString());
			Inkasshistory khrec = new Inkasshistory();
			khrec.setId(khid);
			if(tmplst.get(2) != null){khrec.setSumadd((Double)tmplst.get(2));}else khrec.setSumadd(new Double(0));
			if(tmplst.get(3) != null){khrec.setSumtotal((Double)tmplst.get(3));}else khrec.setSumtotal(new Double(0));
			if(tmplst.get(4) != null){khrec.setSumrez((Double)tmplst.get(4));}else khrec.setSumrez(new Double(0));
			if(tmplst.get(5) != null){khrec.setAvtomat((Integer)tmplst.get(5));}else khrec.setAvtomat(new Integer(0));
			InkHist ih = new InkHist(khrec);
			this.ihList.add(ih);				
		}
		
		return ihList;
		
	}

	public int countRows(int first, int pageSize, Date fromDate, Date toDate, int i) 
	{
		ihs = new IncassHistoryService();
		int num = ihs.getLazyRowCount(first,pageSize,fromDate,toDate, i);
		ihs = null;
		return num;
	}

	public List<InkHist> getSortedLazyInkasHisory(String sortField,SortOrder sortOrder, int first, int pageSize, Date fromDate, Date toDate, int i) 
	{
		String order;
		if (sortOrder.toString().equalsIgnoreCase("ASCENDING")){order = "asc";} else {order = "desc";}
		String query = "select new List(inkhist.id.dat,inkhist.id.datakey,inkhist.sumadd,inkhist.sumtotal,inkhist.sumrez,inkhist.avtomat) " +
				"from Inkasshistory inkhist where inkhist.avtomat in (:alist) and inkhist.id.dat >= :dat1 and inkhist.id.dat <= :dat2 order by inkhist"+sortField.substring(10)+" "+order;
		ihList = new ArrayList<InkHist>();
		ihs = new IncassHistoryService();
		tmpList = ihs.getSortedLazyInkasStory(first,pageSize,fromDate,toDate,query,i);
		ihs = null;
		if(tmpList == null) return null;
		@SuppressWarnings({ "rawtypes" })
		Iterator<List> iter = tmpList.iterator();
		while (iter.hasNext())
		{	
			List tmplst = iter.next();
			InkasshistoryId khid = new InkasshistoryId((Date)tmplst.get(0), tmplst.get(1).toString());
			Inkasshistory khrec = new Inkasshistory();
			khrec.setId(khid);
			if(tmplst.get(2) != null){khrec.setSumadd((Double)tmplst.get(2));}else khrec.setSumadd(new Double(0));
			if(tmplst.get(3) != null){khrec.setSumtotal((Double)tmplst.get(3));}else khrec.setSumtotal(new Double(0));
			if(tmplst.get(4) != null){khrec.setSumrez((Double)tmplst.get(4));}else khrec.setSumrez(new Double(0));
			if(tmplst.get(5) != null){khrec.setAvtomat((Integer)tmplst.get(5));}else khrec.setAvtomat(new Integer(0));	
			InkHist ih = new InkHist(khrec);
			this.ihList.add(ih);				
		}
		
		return ihList;
	}
	
	
}
