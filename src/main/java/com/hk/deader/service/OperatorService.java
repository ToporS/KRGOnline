package com.hk.deader.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import com.hk.deader.domains.OperatorId;
import com.hk.deader.domains.Operator;
import com.hk.deader.domains.Polzov;
import com.hk.deader.utils.HiberUtil;
import com.hk.deader.utils.MD5;

public class OperatorService {
	
	public OperatorService(){}
	
	private Session session ; 
	private Transaction transaction = null;
	private SessionFactory sessionFactory = HiberUtil.getSessionFactory();
	
	public Operator getOper(String user, String pass)
	{	
		try
		{
			session = sessionFactory.openSession();			
			transaction = session.beginTransaction();
			Query query = session.createQuery("Select new List(oper.operid,oper.operpass,oper.name,oper.proc) from Operator oper where oper.operid = :oid and oper.operpass = :opass");
			query.setString("oid", user);
			query.setString("opass", pass);
			List oper = (List) query.uniqueResult();
			transaction.commit();
			if (oper == null) {return null;}
			
			Operator op = new Operator(oper.get(0).toString(),oper.get(1).toString(),oper.get(2).toString(), new Short((Short) oper.get(3)));
			//if (oper == null){System.out.println("Boroda");}			
			
		return (Operator) op;
		}
		 catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
			  } finally {
			 session.close();
			  }				
	}

	public Polzov getPolz(String polzid, String polzpass) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();			
		transaction = session.beginTransaction();
		Query query = session.createQuery("from Polzov polz where polz.userid = :uid and polz.userpass = :upass");
		query.setString("uid", polzid);
		query.setString("upass", polzpass);
		Polzov polz = (Polzov) query.uniqueResult();		
		transaction.commit();		
		return polz;
	}

	@SuppressWarnings("unchecked")
/*	public List<String> getOperList() 
	{
		try
		{
			session = sessionFactory.openSession();			
			transaction = session.beginTransaction();
			List<String> oList = session.createQuery("Select from Operator oper").list();
			transaction.commit();			
		return oList;
		}
		 catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
			  } finally {
			 session.close();
			  }	
	}*/

	public List<Operator> getOperatorList() 
	{
		try
		{
			session = sessionFactory.openSession();			
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Operator> opList = (List<Operator>)session.createQuery("from Operator oper").list();
			transaction.commit();			
		return opList;
		}
		 catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
			  } finally {
			 session.close();
			  }	
	}

/*	public String checkOperId(String string) 
	{
		try
		{
			session = sessionFactory.openSession();			
			transaction = session.beginTransaction();
			String op = (String)session.createQuery("select oper.name from Operator oper where oper.operid=:op").setParameter("op", string).uniqueResult();
			transaction.commit();			
		return op;
		}
		 catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return "Err";
			  } finally {
			 session.close();
			  }	
	}

	public void saveOper(Operator selectedOperator) 
	{
		try
		{
			String hash = new MD5().getHash(selectedOperator.getOperpass());
			String hashedpass = hash.substring(0, 15);
			session = sessionFactory.openSession();			
			transaction = session.beginTransaction();
			session.createQuery("update Operator oper set oper.operid = :oid,oper.operpass=:opass,oper.prim = :oprim,oper.adr = :oadr,oper.tel = :otel,oper.fio = :ofio,oper.proc = :oproc where oper.name = :oname")
			.setParameter("oid", selectedOperator.getOperid())
			.setParameter("opass", hashedpass)
			.setParameter("oprim", selectedOperator.getPrim())
			.setParameter("oadr", selectedOperator.getAdr())
			.setParameter("otel", selectedOperator.getTel())
			.setParameter("ofio", selectedOperator.getFio())
			.setParameter("oproc", selectedOperator.getProc())
			.setParameter("oname", selectedOperator.getName())
			.executeUpdate();
			transaction.commit();	
		}
		 catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  } finally {
			 session.close();
			  }	
	}*/

/*	public String checkOperName(String string) 
	{
		try
		{
			session = sessionFactory.openSession();			
			transaction = session.beginTransaction();
			String op = (String)session.createQuery("select oper.operid from Operator oper where oper.name=:op").setParameter("op", string).uniqueResult();
			transaction.commit();			
		return op;
		}
		 catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return "Err";
			  } finally {
			 session.close();
			  }	
	}*/

	public void addOper(Operator selectedOperator) 
	{
		try
		{
			String hash = new MD5().getHash(selectedOperator.getOperpass());
			String hashedpass = hash.substring(0, 15);
			selectedOperator.setOperpass(hashedpass);
			session = sessionFactory.openSession();			
			transaction = session.beginTransaction();
			session.save(selectedOperator);
			transaction.commit();	
		}
		 catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  } finally {
			 session.close();
			  }	
	}

	public void delOper(Operator selectedOperator) 
	{
		try
		{
			session = sessionFactory.openSession();			
			transaction = session.beginTransaction();
			session.createQuery("delete from Operator oper where oper.name = :oname")
			.setParameter("oname", selectedOperator.getName())
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
}
