package com.hk.deader.service;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hk.deader.domains.Inkas;
import com.hk.deader.utils.ForMaster;
import com.hk.deader.utils.HiberUtil;

public class InkasService {
	private Session session ; 
	private Transaction transaction = null;
	private SessionFactory sessionFactory = HiberUtil.getSessionFactory();
	private Query query; 
	//private Inkas ink;
	
	public InkasService(){};
	
	public List<List> getInkassators()
	{		
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session.createQuery("select new List(ink.id.datakey,ink.id.operator,ink.id.name,ink.id.dat,ink.id.prava,ink.id.summ,ink.id.prim) from Inkas ink");
			@SuppressWarnings({ "unchecked", "rawtypes" })
			List<List>klst = query.list();
			transaction.commit();
			return klst;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
			  } finally {
			  session.close();
			  }	
	}

	public void saveInk(Inkas selectedInkas) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			int numrec = session.createQuery("UPDATE Inkas ink set ink.id.prava = :prava,ink.id.prim = :prim, ink.id.name = :name where ink.id.datakey = :dk")
					.setParameter("prava",selectedInkas.getId().getPrava())
					.setParameter("prim", selectedInkas.getId().getPrim())
					.setParameter("name", selectedInkas.getId().getName())
					.setParameter("dk", selectedInkas.getId().getDatakey())
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

	public String getInkName(String datakey) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			String iName = (String) session.createQuery("select ink.id.name from Inkas ink where ink.id.datakey = :key")
					.setParameter("key",datakey).uniqueResult();
			transaction.commit();
			return iName;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return "";
			  } finally {			  
			  session.close();
			  }	
	}

	public void delInk(String datakey) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			int numrec = session.createQuery("DELETE Inkas ink where ink.id.datakey = :dk")
					.setParameter("dk", datakey)
					.executeUpdate();
			int numrec2 = session.createQuery("DELETE Key key where key.datakey = :dk")
					.setParameter("dk", datakey)
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

	public List<List> getAvtoRezh() 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			List<List> oname = (List<List>) session.createQuery("select new List(avt.num, avt.rezh) from Avtomat avt ").list();	
			transaction.commit();
			return oname;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
			  } finally {				  
			  session.close();
			  }	
	}

	public void saveMasterKey(Integer anum, String string) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			int oname = session.createQuery("update Avtomat avt set avt.rezh = :oname where avt.num = :anum")
					.setParameter("anum", anum)
					.setParameter("oname", string)
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
