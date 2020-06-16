package com.hk.deader.mb;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.hk.deader.dao.KeyDAO;
import com.hk.deader.domains.Key;

public class LazyKeyList extends LazyDataModel<Key> {

	private String opername;
	private static final long serialVersionUID = 2397874149563562439L;
	private List<Key> lazyKeyList;
	private KeyDAO kdao;
	private String filterValue;
	
	public LazyKeyList(){};
	
	public LazyKeyList(String oper)
	{
		this.opername = oper;
	};
	
	public LazyKeyList(String oper, String filterValue) 
	{
		this.opername = oper;
		this.filterValue = filterValue;
	}
	

	@Override
	public List<Key> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) 
	{
		kdao = new KeyDAO();
		if (filterValue == null)
		{
			if (sortField == null)
			{
				lazyKeyList = kdao.getLazyKeyList(first,pageSize);
				setRowCount(kdao.countPlayersTotal());       
				setPageSize(pageSize);
			}else
			{
				lazyKeyList = kdao.getSortedLazyKeyList(first,pageSize,sortField,sortOrder);
				setRowCount(kdao.countPlayersTotal());
				setPageSize(pageSize);
			}
		}else
		{
			if(sortField == null)
			{
				lazyKeyList = kdao.getFilteredLazyKeys(first,pageSize,opername,filterValue);
				setRowCount(kdao.getFilteredRowCount(opername,filterValue));
				setPageSize(pageSize);
			}else
			{
				lazyKeyList = kdao.getSortedFilteredLazyKeys(first,pageSize,filterValue,sortField,sortOrder);
				setRowCount(kdao.getFilteredRowCount(opername,filterValue));
				setPageSize(pageSize);
			}
		}
		return lazyKeyList;
	}
	 @Override
	    public Object getRowKey(Key key) {
	        return key.getNumkey();
	    }
	 
	    @Override
	    public Key getRowData(String keyNum) {
	        Integer id = Integer.valueOf(keyNum);
	 
	        for (Key key : lazyKeyList) {
	            if(id.equals(key.getNumkey())){
	                return key;
	            }
	        }
	 
	        return null;
	    }

}
