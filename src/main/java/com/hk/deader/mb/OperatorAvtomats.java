package com.hk.deader.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.hk.deader.service.AvtomatService;


public class OperatorAvtomats implements Serializable {

	private static final long serialVersionUID = 490414063717434395L;
	private List<Integer> operAvto = null;
	AvtomatService as = null;
	@ManagedProperty(value="#{login}")
	CredentialsMB cred;

	public List<Integer> getOperAvto() {
		return operAvto;
	}
	public void setOperAvto(List<Integer> operAvto) {
		this.operAvto = operAvto;
	}
	public CredentialsMB getCred() {
		return cred;
	}
	public void setCred(CredentialsMB cred) {
		this.cred = cred;
	}

	public OperatorAvtomats()
	{}
	public List<Integer> setOperAvtomats() {
		// TODO Auto-generated method stub
		as = new AvtomatService();
		operAvto = as.getOperAutomats();
		as = null;
		return operAvto;
	}
}
