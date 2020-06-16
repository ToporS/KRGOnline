package com.hk.deader.utils;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import com.hk.deader.domains.AvtomatWrapper;

public class LazySorter implements Comparator<AvtomatWrapper> 
{

	 private String sortField;	   
	 private SortOrder sortOrder;
	 
	 public LazySorter(String sortField, SortOrder sortOrder) 
	 {
		 this.sortField = sortField;
	     this.sortOrder = sortOrder;
	 }

	
	
	public int compare(AvtomatWrapper o1, AvtomatWrapper o2) {
		// TODO Auto-generated method stub
		 try {
	            /*Object value1 = AvtomatWrapper.class.getField(this.sortField).get(o1);
	            Object value2 = AvtomatWrapper.class.getField(this.sortField).get(o2);*/
			 
			 Object value1 = AvtomatWrapper.class.getField(this.sortField).get(o1);
	            Object value2 = AvtomatWrapper.class.getField(this.sortField).get(o2);

	            int value = ((Comparable)value1).compareTo(value2);
	           
	            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
	        }
	        catch(Exception e) {
	            throw new RuntimeException();
	        }
	}
}
