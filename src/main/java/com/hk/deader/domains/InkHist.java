package com.hk.deader.domains;

import java.io.Serializable;

import com.hk.deader.dao.InkasHistoryDAO;
import com.hk.deader.dao.InkassDAO;

public class InkHist extends Inkasshistory implements Serializable {

	private static final long serialVersionUID = -1155735667823243458L;
	private String inkName;
	private InkassDAO idao=null;
	private Inkasshistory inkHistDom;
	
	public InkHist(){}
	public InkHist(Inkasshistory iHist)
	{
		this.inkHistDom=iHist;
		idao = new InkassDAO();
		inkName = idao.getInkName(iHist.getId().getDatakey());
		idao = null;
	}
	
	public String getInkName() {
		return inkName;
	}
	public void setInkName(String inkName) {
		this.inkName = inkName;
	}
	public Inkasshistory getInkHistDom() {
		return inkHistDom;
	}
	public void setInkHistDom(Inkasshistory inkHistDom) {
		this.inkHistDom = inkHistDom;
	}

}
