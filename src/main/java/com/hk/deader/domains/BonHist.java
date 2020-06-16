package com.hk.deader.domains;

import java.io.Serializable;
import java.util.List;

import com.hk.deader.dao.BonusHistoryDAO;

public class BonHist extends Bonushistory implements Serializable 
{
	private String fio;
	private String ugroup;
	private BonusHistoryDAO bhdao;
	private List<String> fg;
	
	public BonHist() 
	{
		
	}

	public BonHist(BonushistoryId id, int bonadd, String operator, String userid) 
	{
		super(id, bonadd, operator, userid);
		bhdao = new BonusHistoryDAO();
		fg = bhdao.getFG(id.getNumkey());
		if (fg == null)
		{
			this.fio = "";
			this.ugroup = "";
		} else
		{
			if (fg.get(0) == null)   this.fio = ""; else this.fio = fg.get(0).toString();
			if (fg.get(1) == null)   this.ugroup = ""; else this.ugroup = fg.get(1).toString();
		}
				
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getUgroup() {
		return ugroup;
	}

	public void setUgroup(String ugroup) {
		this.ugroup = ugroup;
	}
	
	

}
