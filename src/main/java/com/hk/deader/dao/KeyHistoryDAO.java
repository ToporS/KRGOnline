package com.hk.deader.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import com.hk.deader.domains.Keyhistory;
import com.hk.deader.domains.KeyhistoryId;
import com.hk.deader.service.AvtomatService;
import com.hk.deader.service.KeyHistoryService;

public class KeyHistoryDAO implements Serializable {

	private static final long serialVersionUID = 7546674430114452295L;
	private List<Keyhistory> keyhist;
	private KeyHistoryService khs;
	private List<List> klst;
	private AvtomatService as = null;

	public List<Keyhistory> getKeyhist() {
		return keyhist;
	}

	public void setKeyhist(List<Keyhistory> keyhist) {
		this.keyhist = keyhist;
	}
	
	public List<Keyhistory> getLazyKeyStoryList(int first, int pageSize,  Date fromDate, Date toDate, int i) 
	{
		keyhist = new ArrayList<Keyhistory>();
		khs = new KeyHistoryService();
		klst = khs.getLazyStoryByDate(first,pageSize,fromDate,toDate,i);
		khs = null;
		if(klst == null) return null;
		@SuppressWarnings({ "rawtypes" })
		Iterator<List> iter = klst.iterator();
		while (iter.hasNext())
		{	
			List tmplst = iter.next();
			KeyhistoryId khid = new KeyhistoryId((Date)tmplst.get(0), tmplst.get(1).toString());
			Keyhistory khrec = new Keyhistory();
			khrec.setId(khid);
			if(tmplst.get(2) != null){khrec.setSumadd((Double)tmplst.get(2));}else khrec.setSumadd(new Double(0));
			if(tmplst.get(3) != null){khrec.setTerminal(tmplst.get(3).toString());}else khrec.setTerminal("");
			if(tmplst.get(4) != null){khrec.setSumsub((Double)tmplst.get(4));}else khrec.setSumsub(new Double(0));
			if(tmplst.get(5) != null){khrec.setSumbut((Double)tmplst.get(5));}else khrec.setSumbut(new Double(0));
			if(tmplst.get(6) != null){khrec.setAvtomat((Integer)tmplst.get(6));}else khrec.setAvtomat(new Integer(0));
			if(tmplst.get(7) != null){khrec.setBut((Integer)tmplst.get(7));}else khrec.setBut(new Integer(0));
			if(tmplst.get(8) != null){khrec.setLitr((Integer)tmplst.get(8));}else khrec.setLitr(new Integer(0));	
			keyhist.add(khrec);							
		}
		return keyhist;
	}

	public int countRows(int first, int pageSize,Date fromDate, Date toDate, int i) 
	{
		khs = new KeyHistoryService();
		int num = khs.getLazyRowCount(first,pageSize,fromDate,toDate,i);
		khs = null;
		return num;
	}

	public List<Integer> getOperAvtomats() 
	{
		as = new AvtomatService();
		List<Integer> alist = as.getOperAutomats();
		as = null;
		return alist;
	}

	public List<Keyhistory> getfilteredLazyKeyStoryList(int first, int pageSize, Map<String, String> filtersAp, Date fromDate, Date toDate, int i) 
	{
		keyhist = new ArrayList<Keyhistory>();
		khs = new KeyHistoryService();
		StringBuffer sb=new StringBuffer();
		sb.append("select new List(keyhist.id.dat,keyhist.id.numkey,keyhist.sumadd,keyhist.terminal,keyhist.sumsub,keyhist.sumbut,keyhist.avtomat,keyhist.but,keyhist.litr) " +
				"from Keyhistory keyhist where keyhist.id.dat >= :dat1 and keyhist.id.dat <= :dat2");
		if(filtersAp.containsKey("key")&&!filtersAp.containsKey("autonum"))
		{
			sb.append(" and keyhist.id.numkey like '"+filtersAp.get("key")+"%'");
			sb.append(" order by keyhist.id.dat asc");
			String str = new String(sb);
			klst=khs.getFilteredByKey(str,fromDate,toDate,first,pageSize,i);
		}
		if(!filtersAp.containsKey("key")&&filtersAp.containsKey("autonum"))
		{
			sb.append(" and keyhist.avtomat = :anum");
			sb.append(" order by keyhist.id.dat asc");
			String str = new String(sb);
			//String ssttrr = filtersAp.get("")
			Integer anum = Integer.parseInt(filtersAp.get("autonum"));
			klst=khs.getFiltered(str,fromDate,toDate,anum,first,pageSize,i);
		}
		if(filtersAp.containsKey("key")&&filtersAp.containsKey("autonum"))
		{
			sb.append(" and keyhist.id.numkey like '"+filtersAp.get("key")+"%'");
			sb.append(" and keyhist.avtomat = :anum");
			sb.append(" order by keyhist.id.dat asc");
			String str = new String(sb);
			Integer anum = Integer.parseInt(filtersAp.get("autonum"));
			klst=khs.getFiltered(str,fromDate,toDate,anum,first,pageSize,i);
		}
		khs=null;
		if(klst == null) return null;
		@SuppressWarnings({ "rawtypes" })
		Iterator<List> iter = klst.iterator();
		while (iter.hasNext())
		{	
			List tmplst = iter.next();
			KeyhistoryId khid = new KeyhistoryId((Date)tmplst.get(0), tmplst.get(1).toString());
			Keyhistory khrec = new Keyhistory();
			khrec.setId(khid);
			if(tmplst.get(2) != null){khrec.setSumadd((Double)tmplst.get(2));}else khrec.setSumadd(new Double(0));
			if(tmplst.get(3) != null){khrec.setTerminal(tmplst.get(3).toString());}else khrec.setTerminal("");
			if(tmplst.get(4) != null){khrec.setSumsub((Double)tmplst.get(4));}else khrec.setSumsub(new Double(0));
			if(tmplst.get(5) != null){khrec.setSumbut((Double)tmplst.get(5));}else khrec.setSumbut(new Double(0));
			if(tmplst.get(6) != null){khrec.setAvtomat((Integer)tmplst.get(6));}else khrec.setAvtomat(new Integer(0));
			if(tmplst.get(7) != null){khrec.setBut((Integer)tmplst.get(7));}else khrec.setBut(new Integer(0));
			if(tmplst.get(8) != null){khrec.setLitr((Integer)tmplst.get(8));}else khrec.setLitr(new Integer(0));	
			keyhist.add(khrec);							
		}
		return keyhist;
	}

	public int countFilteredRows(int first, int pageSize, Map<String, String> filtersAp, Date fromDate, Date toDate, int i) 
	{
		int recNum = 0;
		khs = new KeyHistoryService();
		StringBuffer sb=new StringBuffer();
		sb.append("select count(keyhist.id.numkey) from Keyhistory keyhist where (keyhist.id.dat >= :dat1) and (keyhist.id.dat <= :dat2)");
		if(filtersAp.containsKey("key")&&!filtersAp.containsKey("autonum"))
		{
			sb.append(" and keyhist.id.numkey like '"+filtersAp.get("key")+"%'");
			String str = new String(sb);
			recNum=khs.getFilteredByKeyCount(str,fromDate,toDate,first,pageSize,i);
		}
		if(!filtersAp.containsKey("key")&&filtersAp.containsKey("autonum"))
		{
			sb.append(" and keyhist.avtomat = :anum");
			String str = new String(sb);
			Integer anum = Integer.parseInt(filtersAp.get("autonum"));
			recNum=khs.getFilteredCount(str,fromDate,toDate,anum,first,pageSize,i);
		}
		if(filtersAp.containsKey("key")&&filtersAp.containsKey("autonum"))
		{
			sb.append(" and keyhist.id.numkey like '"+filtersAp.get("key")+"%'");
			sb.append(" and keyhist.avtomat = :anum");
			String str = new String(sb);
			Integer anum = Integer.parseInt(filtersAp.get("autonum"));
			recNum=khs.getFilteredCount(str,fromDate,toDate,anum,first,pageSize,i);
		}
		khs=null;
		return recNum;
	}

	public List<Keyhistory> getSortedLazyKeyStoryList(int first, int pageSize, Date fromDate, Date toDate, String sortField,
			SortOrder sortOrder, int i) 
	{
		String order;
		if (sortOrder.toString().equalsIgnoreCase("ASCENDING")){order = "asc";} else {order = "desc";}
		String queryString = "select new List(keyhist.id.dat,keyhist.id.numkey,keyhist.sumadd,keyhist.terminal,keyhist.sumsub,keyhist.sumbut,keyhist.avtomat,keyhist.but,keyhist.litr) " +
				"from Keyhistory keyhist where keyhist.avtomat in (:alist) and keyhist.id.dat >= :dat1 and keyhist.id.dat <= :dat2 order by keyhist." + sortField+" "+ order;
		keyhist = new ArrayList<Keyhistory>();
		khs = new KeyHistoryService();
		klst = khs.getSortedLazyStoryByDate(first,pageSize,fromDate,toDate,queryString,i);
		khs = null;
		if(klst == null) return null;
		@SuppressWarnings({ "rawtypes" })
		Iterator<List> iter = klst.iterator();
		while (iter.hasNext())
		{	
			List tmplst = iter.next();
			KeyhistoryId khid = new KeyhistoryId((Date)tmplst.get(0), tmplst.get(1).toString());
			Keyhistory khrec = new Keyhistory();
			khrec.setId(khid);
			if(tmplst.get(2) != null){khrec.setSumadd((Double)tmplst.get(2));}else khrec.setSumadd(new Double(0));
			if(tmplst.get(3) != null){khrec.setTerminal(tmplst.get(3).toString());}else khrec.setTerminal("");
			if(tmplst.get(4) != null){khrec.setSumsub((Double)tmplst.get(4));}else khrec.setSumsub(new Double(0));
			if(tmplst.get(5) != null){khrec.setSumbut((Double)tmplst.get(5));}else khrec.setSumbut(new Double(0));
			if(tmplst.get(6) != null){khrec.setAvtomat((Integer)tmplst.get(6));}else khrec.setAvtomat(new Integer(0));
			if(tmplst.get(7) != null){khrec.setBut((Integer)tmplst.get(7));}else khrec.setBut(new Integer(0));
			if(tmplst.get(8) != null){khrec.setLitr((Integer)tmplst.get(8));}else khrec.setLitr(new Integer(0));	
			keyhist.add(khrec);							
		}
		return keyhist;
	}

	public List<Keyhistory> getfilteredSortedLazyKeyStoryList(int first, int pageSize, Map<String, String> filtersAp, Date fromDate, Date toDate, String sortField, SortOrder sortOrder, int i) 
	{
		String order;
		if (sortOrder.toString().equalsIgnoreCase("ASCENDING")){order = "asc";} else {order = "desc";}
		keyhist = new ArrayList<Keyhistory>();
		khs = new KeyHistoryService();
		StringBuffer sb=new StringBuffer();
		sb.append("select new List(keyhist.id.dat,keyhist.id.numkey,keyhist.sumadd,keyhist.terminal,keyhist.sumsub,keyhist.sumbut,keyhist.avtomat,keyhist.but,keyhist.litr) " +
				"from Keyhistory keyhist where keyhist.id.dat >= :dat1 and keyhist.id.dat <= :dat2");
		if(filtersAp.containsKey("key")&&!filtersAp.containsKey("autonum"))
		{
			sb.append(" and keyhist.id.numkey like '"+filtersAp.get("key")+"%'");
			sb.append("order by keyhist." + sortField+" "+ order);
			String str = new String(sb);
			klst=khs.getFilteredByKey(str,fromDate,toDate,first,pageSize,i);
		}
		if(!filtersAp.containsKey("key")&&filtersAp.containsKey("autonum"))
		{
			sb.append(" and keyhist.avtomat = :anum");
			sb.append(" order by keyhist." + sortField+" "+ order);
			String str = new String(sb);
			//String ssttrr = filtersAp.get("")
			Integer anum = Integer.parseInt(filtersAp.get("autonum"));
			klst=khs.getFiltered(str,fromDate,toDate,anum,first,pageSize,i);
		}
		if(filtersAp.containsKey("key")&&filtersAp.containsKey("autonum"))
		{
			sb.append(" and keyhist.id.numkey like '"+filtersAp.get("key")+"%'");
			sb.append(" and keyhist.avtomat = :anum");
			sb.append(" order by keyhist." + sortField+" "+ order);
			String str = new String(sb);
			Integer anum = Integer.parseInt(filtersAp.get("autonum"));
			klst=khs.getFiltered(str,fromDate,toDate,anum,first,pageSize,i);
		}
		khs=null;
		if(klst == null) return null;
		@SuppressWarnings({ "rawtypes" })
		Iterator<List> iter = klst.iterator();
		while (iter.hasNext())
		{	
			List tmplst = iter.next();
			KeyhistoryId khid = new KeyhistoryId((Date)tmplst.get(0), tmplst.get(1).toString());
			Keyhistory khrec = new Keyhistory();
			khrec.setId(khid);
			if(tmplst.get(2) != null){khrec.setSumadd((Double)tmplst.get(2));}else khrec.setSumadd(new Double(0));
			if(tmplst.get(3) != null){khrec.setTerminal(tmplst.get(3).toString());}else khrec.setTerminal("");
			if(tmplst.get(4) != null){khrec.setSumsub((Double)tmplst.get(4));}else khrec.setSumsub(new Double(0));
			if(tmplst.get(5) != null){khrec.setSumbut((Double)tmplst.get(5));}else khrec.setSumbut(new Double(0));
			if(tmplst.get(6) != null){khrec.setAvtomat((Integer)tmplst.get(6));}else khrec.setAvtomat(new Integer(0));
			if(tmplst.get(7) != null){khrec.setBut((Integer)tmplst.get(7));}else khrec.setBut(new Integer(0));
			if(tmplst.get(8) != null){khrec.setLitr((Integer)tmplst.get(8));}else khrec.setLitr(new Integer(0));	
			keyhist.add(khrec);							
		}
		return keyhist;
	}
}
