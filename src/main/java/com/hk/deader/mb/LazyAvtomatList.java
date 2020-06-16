package com.hk.deader.mb;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.hk.deader.dao.AvtomatDAO;
import com.hk.deader.domains.AvtomatWrapper;

public class LazyAvtomatList extends LazyDataModel<AvtomatWrapper> {

	private static final long serialVersionUID = -6852432541574432012L;
	private List<AvtomatWrapper> lazyAvtoList;
	private AvtomatWrapper aw;
	private AvtomatDAO adao = null;
	private Integer aNum;
	private String opername;
	
	public LazyAvtomatList(String oper)
	{
		this.opername = oper;
	}
	
	public LazyAvtomatList(Integer oper)
	{
		this.aNum = oper;
	}


	@Override
	public List<AvtomatWrapper> load(int first, int pageSize, String sortField,SortOrder sortOrder, Map<String, String> filters) 
	{
		adao = new AvtomatDAO();
		if (aNum == null)
		{
		if(filters.isEmpty() && sortField == null)
		{
			lazyAvtoList = adao.getAvtoList(first,pageSize);
			setRowCount(adao.countPlayersTotal());       
			setPageSize(pageSize);
			adao = null;	
			return lazyAvtoList;
		}
		if (filters.isEmpty() && sortField != null)
		{
			lazyAvtoList = adao.getSortedLazyList(sortField,sortOrder,first,pageSize);
			setRowCount(adao.countPlayersTotal());       
			setPageSize(pageSize);
			return lazyAvtoList;
		}
		}else
		{
			lazyAvtoList = adao.getAvtoListById(aNum);
			setRowCount(1);       
			setPageSize(pageSize);
			return lazyAvtoList;
		}
		return lazyAvtoList;
	}

}
