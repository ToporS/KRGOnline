package com.hk.deader.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.primefaces.model.SortOrder;

import com.hk.deader.domains.BonHist;
import com.hk.deader.domains.Bonushistory;
import com.hk.deader.domains.BonushistoryId;
import com.hk.deader.domains.Inkasshistory;
import com.hk.deader.domains.InkasshistoryId;
import com.hk.deader.service.BonusService;
import com.hk.deader.service.IncassHistoryService;
import com.hk.deader.service.KeyService;

public class BonusHistoryDAO implements Serializable{

	private static final long serialVersionUID = -3658849333064334454L;
	private BonusService bs;
	private List<BonHist> bhList;
	private List<List> tmpList;
	private KeyService ks;
	
	public BonusHistoryDAO(){}

	public List<BonHist> getLazyBonusHisory(int first, int pageSize, Date fromDate, Date toDate) 
	{
		bhList = new ArrayList<BonHist>();
		bs = new BonusService();
		tmpList = bs.getLazyBonusStory(first,pageSize,fromDate,toDate);
		bs = null;
		if(tmpList == null) return null;
		@SuppressWarnings({ "rawtypes" })
		Iterator<List> iter = tmpList.iterator();
		//operKeyStoryList = null;
		while (iter.hasNext())
		{	
			List tmplst = iter.next();
			BonushistoryId bhid = new BonushistoryId(tmplst.get(1).toString(), (Date)tmplst.get(0));
			//Bonushistory bhrec = new Bonushistory();
			Integer bonadd;
			String operator, userid;			
			if(tmplst.get(2) != null){bonadd = ((Integer)tmplst.get(2));}else bonadd = 0;
			if(tmplst.get(3) != null){operator = tmplst.get(3).toString();}else operator = "";
			if(tmplst.get(4) != null){userid = tmplst.get(4).toString();}else userid = "";
			BonHist bhrec = new BonHist(bhid, bonadd, operator, userid);
			this.bhList.add(bhrec);				
		}		
		return bhList;
	}

	public int countRows(Date fromDate, Date toDate) 
	{
		bs = new BonusService();
		int num = bs.getLazyRowCount(fromDate,toDate);
		bs = null;
		return num;
	}

	public List<BonHist> getSortedLazyBonusHisory(String sortField, SortOrder sortOrder, int first, int pageSize, Date fromDate, Date toDate) 
	{
		String order;
		if (sortOrder.toString().equalsIgnoreCase("ASCENDING")){order = "asc";} else {order = "desc";}
		String query = "select new List(bonhist.id.dat,bonhist.id.numkey,bonhist.bonadd,bonhist.operator,bonhist.userid) " +
						"from Bonushistory bonhist where bonhist.id.dat >= :dat1 and bonhist.id.dat <= :dat2 order by "+sortField+" "+order;
		bhList = new ArrayList<BonHist>();
		bs = new BonusService();
		tmpList = bs.getSortedLazyBonusStory(first,pageSize,fromDate,toDate,query);
		bs = null;
		if(tmpList == null) return null;
		@SuppressWarnings({ "rawtypes" })
		Iterator<List> iter = tmpList.iterator();
		//operKeyStoryList = null;
		while (iter.hasNext())
		{	
			List tmplst = iter.next();
			BonushistoryId bhid = new BonushistoryId(tmplst.get(1).toString(), (Date)tmplst.get(0));
			//Bonushistory bhrec = new Bonushistory();
		//	bhrec.setId(bhid);
			Integer bonadd;
			String operator, userid;	
			if(tmplst.get(2) != null){bonadd = ((Integer)tmplst.get(2));}else bonadd = 0;
			if(tmplst.get(3) != null){operator = tmplst.get(3).toString();}else operator = "";
			if(tmplst.get(4) != null){userid = tmplst.get(4).toString();}else userid = "";
			BonHist bhrec = new BonHist(bhid, bonadd, operator, userid);				
			this.bhList.add(bhrec);				
		}		
		return bhList;
	}

	public List<String> getFG(String numkey) 
	{
		ks = new KeyService();
		List<String> fg = ks.getFGList(numkey);
		ks = null;
		return fg;
	}

}
