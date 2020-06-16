package com.hk.deader.mb;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.hk.deader.dao.BonusHistoryDAO;
import com.hk.deader.dao.InkasHistoryDAO;
import com.hk.deader.domains.BonHist;
import com.hk.deader.domains.Bonushistory;

public class LazyBonusStoryList extends LazyDataModel<BonHist> {

	private static final long serialVersionUID = -4205594950252167114L;
	private List<BonHist> lazyBonusStoryList;
	private BonusHistoryDAO bhdao;
	private String filterValue;
	private Date fromDate,toDate;
	
	public LazyBonusStoryList(){};
	
	public LazyBonusStoryList(Date from, Date to)
	{
		this.fromDate = from;
		this.toDate = to;
	};

	@Override
	public List<BonHist> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) 
	{
		bhdao = new BonusHistoryDAO();
		if (sortField == null)
		{
			lazyBonusStoryList = bhdao.getLazyBonusHisory(first,pageSize,fromDate,toDate);
			setPageSize(pageSize);
			setRowCount(bhdao.countRows(fromDate,toDate));
			return lazyBonusStoryList;
		}
		else
		{
			lazyBonusStoryList = bhdao.getSortedLazyBonusHisory(sortField,sortOrder,first,pageSize,fromDate,toDate);
			setPageSize(pageSize);
			setRowCount(bhdao.countRows(fromDate,toDate));
			return lazyBonusStoryList;
		}
	}

}
