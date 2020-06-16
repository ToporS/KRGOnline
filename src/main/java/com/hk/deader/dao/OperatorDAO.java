package com.hk.deader.dao;

import java.io.Serializable;
import java.util.List;

import com.hk.deader.domains.Operator;
import com.hk.deader.service.OperatorService;


public class OperatorDAO implements Serializable{

	private static final long serialVersionUID = 7503817042771627123L;
	private Operator operator;
	private List<Operator> operlist;
	private OperatorService os = null; 
	
	public OperatorDAO()
	{
		//os = new OperatorService();
		//operlist = os.getAll();
	}

	/**
	 * @return the operlist
	 */
	public List<Operator> getOperlist() {
		return operlist;
	}

	/**
	 * @param operlist the operlist to set
	 */
	public void setOperlist(List<Operator> operlist) {
		this.operlist = operlist;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
}
