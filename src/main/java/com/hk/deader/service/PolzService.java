package com.hk.deader.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hk.deader.domains.Polzov;
import com.hk.deader.utils.HiberUtil;
import com.hk.deader.utils.MD5;

public class PolzService 
{
	private Session session ; 
	private Transaction transaction = null;
	private SessionFactory sessionFactory = HiberUtil.getSessionFactory();	
	private Query query;
	
	public PolzService(){}

	@SuppressWarnings("unchecked")
	public List<Polzov> getUsersList() 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			List<Polzov> keyList = (List<Polzov>)session.createQuery("from Polzov polz")
				.list();	
			transaction.commit();
			return keyList;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
			  } finally {
			  session.close();
			  }	
	}

	public String checkPolzId(String string) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			String str = (String)session.createQuery("select polz.userpass from Polzov polz where polz.userid = :uname")
				.setParameter("uname", string).uniqueResult();
			transaction.commit();
			return str;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return "err";
			  } finally {
			  session.close();
			  }	
	}

	public void editUser(Polzov selectedUser) 
	{
		try
		{
			String hash = new MD5().getHash(selectedUser.getUserpass());
			String hashedpass = hash.substring(0, 15);
			session = sessionFactory.openSession();			
			transaction = session.beginTransaction();
			session.createQuery("update Polzov polz set polz.userpass=:opass, polz.username = :uname,polz.arm = :uarm where polz.userid = :oname")
			.setParameter("opass", hashedpass)
			.setParameter("uname", selectedUser.getUsername())
			.setParameter("uarm", selectedUser.getArm())
			.setParameter("oname", selectedUser.getUserid())
			.executeUpdate();
			transaction.commit();	
		}
		 catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  } finally {
			 session.close();
			  }	
	}

	public void delUser(Polzov selectedUser) 
	{
		try
		{
			session = sessionFactory.openSession();			
			transaction = session.beginTransaction();
			session.createQuery("delete from Polzov oper where oper.userid = :oname")
			.setParameter("oname", selectedUser.getUserid())
			.executeUpdate();
			transaction.commit();	
		}
		 catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  } finally {
			 session.close();
			  }	
	}

	public void addUser(Polzov newPolz) 
	{
		try
		{
			session = sessionFactory.openSession();			
			transaction = session.beginTransaction();
			session.save(newPolz);
			transaction.commit();	
		}
		 catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  } finally {
			 session.close();
			  }	
	}
}
