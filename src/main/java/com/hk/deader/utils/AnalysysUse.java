package com.hk.deader.utils;

public class AnalysysUse {

	private String operName;
	private Integer avtoCol;
	
	public AnalysysUse(){}
	
	public AnalysysUse(String str, Integer col)
	{
		this.operName = str;
		this.avtoCol = col;
	}
	
	public Integer getAvtoCol() {
		return avtoCol;
	}

	public void setAvtoCol(Integer avtoCol) {
		this.avtoCol = avtoCol;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

}
