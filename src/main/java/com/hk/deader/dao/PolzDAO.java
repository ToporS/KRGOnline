package com.hk.deader.dao;

import java.util.ArrayList;
import java.util.List;

import com.hk.deader.domains.Polzov;
import com.hk.deader.service.PolzService;
import com.hk.deader.utils.MD5;

public class PolzDAO 
{
	private List<Polzov> pList = new ArrayList<Polzov>();
	private PolzService ps;
	
	public PolzDAO(){}

	public List<Polzov> getUsers() 
	{
		ps = new PolzService();
		pList = ps.getUsersList();
		ps = null;
		return pList;
	}

	public void editUser(Polzov selectedUser) 
	{
		ps = new PolzService();
		ps.editUser(selectedUser);
		ps = null;
	}

	public void remUser(Polzov selectedUser) 
	{
		ps = new PolzService();
		ps.delUser(selectedUser);
		ps = null;
	}

	public void addUser(Polzov newPolz) 
	{
		ps = new PolzService();
		String hash = new MD5().getHash(newPolz.getUserpass());
		String hashedpass = hash.substring(0, 15);
		newPolz.setUserpass(hashedpass);
		newPolz.setRasr("Разрешено");
		ps.addUser(newPolz);
		ps = null;
	}

}
