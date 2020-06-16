package com.hk.deader.mb;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.hk.deader.dao.KeyDAO;
import com.hk.deader.dao.KeyHistoryDAO;
import com.hk.deader.domains.Key;
import com.hk.deader.domains.Keyhistory;

public class LazyKeyStoryList extends LazyDataModel<Keyhistory> {

	private static final long serialVersionUID = -2167085767319753136L;
	private List<Keyhistory> lazyKeyStoryList;
	private KeyHistoryDAO khdao;
	private String filterValue;
	private Integer avtoNum;
	private Date fromDate,toDate;
	private Map<String,String> filtersAp = new HashMap<String,String>();
	private int i;
	
	public LazyKeyStoryList(){}
	
	public LazyKeyStoryList(Date from, Date to, int i)
	{
		this.fromDate = from;
		this.toDate = to;
		this.i = i;
	}
	
	public LazyKeyStoryList(Map filters)
	{
		filtersAp=filters;
	}

	public LazyKeyStoryList(Map<String, String> filtri, Date fromDate2,Date toDate2) 
	{
		filtersAp=filtri;
		this.fromDate = fromDate2;
		this.toDate=toDate2;
	}

	@Override
	public List<Keyhistory> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) 
	{
		khdao = new KeyHistoryDAO();
		if(filtersAp.isEmpty()||filtersAp==null)
		{
			if(sortField == null)
			{
				lazyKeyStoryList = khdao.getLazyKeyStoryList(first,pageSize,fromDate,toDate,i);
				setPageSize(pageSize);
				setRowCount(khdao.countRows(first,pageSize,fromDate,toDate,i));
			} else
			{
				lazyKeyStoryList = khdao.getSortedLazyKeyStoryList(first,pageSize,fromDate,toDate,sortField,sortOrder,i);
				setPageSize(pageSize);
				setRowCount(khdao.countRows(first,pageSize,fromDate,toDate,i));
			}
		}else
			if(sortField == null)
			{
				lazyKeyStoryList = khdao.getfilteredLazyKeyStoryList(first,pageSize,filtersAp,fromDate,toDate,i);
				setPageSize(pageSize);
				setRowCount(khdao.countFilteredRows(first,pageSize,filtersAp,fromDate,toDate,i));
			}else
			{
				lazyKeyStoryList = khdao.getfilteredSortedLazyKeyStoryList(first,pageSize,filtersAp,fromDate,toDate,sortField, sortOrder,i);
				setPageSize(pageSize);
				setRowCount(khdao.countFilteredRows(first,pageSize,filtersAp,fromDate,toDate,i));
			}
		khdao = null;
		return lazyKeyStoryList;
	}

}
