package com.hk.deader.utils;

public class ForMaster 
{
	private Integer anum;
	private String rezh;
	
	ForMaster(){}
	ForMaster(int num, String rez)
	{
		this.anum = num;
		this.rezh = rez;
	}

	public Integer getAnum() {
		return anum;
	}

	public void setAnum(Integer anum) {
		this.anum = anum;
	}

	public String getRezh() {
		return rezh;
	}

	public void setRezh(String rezh) {
		this.rezh = rezh;
	}	
}
