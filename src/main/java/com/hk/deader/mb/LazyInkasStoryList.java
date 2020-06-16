package com.hk.deader.mb;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.hk.deader.dao.InkasHistoryDAO;
import com.hk.deader.domains.InkHist;
import com.hk.deader.domains.Inkasshistory;

public class LazyInkasStoryList extends LazyDataModel<InkHist> {

	private static final long serialVersionUID = -3427352323531197289L;
	private List<InkHist> lazyIkasStoryList;
	private InkasHistoryDAO ihdao;
	private String filterValue;
	private Date fromDate,toDate;
	private int i;  // key for session factory select
	
	public LazyInkasStoryList(){}
	
	public LazyInkasStoryList(Date from, Date to, int i)
	{
		this.fromDate = from;
		this.toDate = to;
		this.i = i;
	}
	

	@Override
	public List<InkHist> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) 
	{
		ihdao = new InkasHistoryDAO();
		if (sortField == null)
		{
			lazyIkasStoryList = ihdao.getLazyInkasHisory(first,pageSize,fromDate,toDate , i);
			setPageSize(pageSize);
			setRowCount(ihdao.countRows(first,pageSize,fromDate,toDate, i));
			ihdao = null;
			return lazyIkasStoryList;
		}
		else
		{
			lazyIkasStoryList = ihdao.getSortedLazyInkasHisory(sortField,sortOrder,first,pageSize,fromDate,toDate,i);
			setPageSize(pageSize);
			setRowCount(ihdao.countRows(first,pageSize,fromDate,toDate, i));
			ihdao = null;
			return lazyIkasStoryList;
		}
		
	}

}
