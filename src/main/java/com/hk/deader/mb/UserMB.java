package com.hk.deader.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.hk.deader.dao.PolzDAO;
import com.hk.deader.domains.Polzov;
@ManagedBean(name="users")
@ViewScoped
public class UserMB implements Serializable {

	private static final long serialVersionUID = 2858221245813733830L;
	@ManagedProperty(value="#{login}")
	private CredentialsMB CMB;
	private List<Polzov> userList = null;
	private PolzDAO pdao = null; 
	private Polzov selectedUser;
	private Polzov newPolz = new Polzov();
	
	public UserMB()
	{
		
	}
	
	public void showOperUsers()
	{
		pdao = new PolzDAO();
		userList = pdao.getUsers();
		pdao = null;
	}
	
	public void remUser()
	{
		pdao = new PolzDAO();
		pdao.remUser(selectedUser);
		pdao = null;
	}
	
	public void editUser()
	{
		pdao = new PolzDAO();
		pdao.editUser(selectedUser);
		pdao = null;
	}
	
	public void addUser()
	{
		pdao = new PolzDAO();
		pdao.addUser(newPolz);
		pdao = null;
	}
	
	public void setNewUser()
	{
		this.newPolz.setOperator("");
	}
		
	public CredentialsMB getCMB() {
		return CMB;
	}
	public void setCMB(CredentialsMB cMB) {
		CMB = cMB;
	}

	public List<Polzov> getUserList() 
	{
		if (userList == null)
		{
			pdao = new PolzDAO();
			userList = pdao.getUsers();
			pdao = null;
		}
		return userList;
	}

	public void setUserList(List<Polzov> userList) {
		this.userList = userList;
	}

	public Polzov getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(Polzov selectedUser) {
		this.selectedUser = selectedUser;
	}

	public Polzov getNewPolz() {
		newPolz.setOperator("");
		return newPolz;
	}

	public void setNewPolz(Polzov newPolz) {
		this.newPolz = newPolz;
	}

}
