package com.hk.deader.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hk.deader.domains.Key;
import com.hk.deader.domains.Keyhistory;
import com.hk.deader.domains.KeyhistoryId;
import com.hk.deader.utils.HiberUtil;

public class KeyHistoryService implements Serializable {

	private static final long serialVersionUID = 6284468501419528194L;
	private Session session ; 
	private Transaction transaction = null;
	private SessionFactory sessionFactory = HiberUtil.getSessionFactory();	
	private List<Keyhistory> operKeyStoryList = new ArrayList<Keyhistory>();
	private List avtolist = null;	
	private Query query;
		
	public List<List> getLazyStoryByDate(int first, int pageSize, Date fromDate, Date toDate, int i) 
	{

		try 
		{
			//session = sessionFactory.openSession();
			if (i==1)session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			avtolist = sessionFactory.openSession().createQuery("select avt.num from Avtomat avt").list();
			if (avtolist == null || avtolist.isEmpty()) avtolist.add(0);
			query =  session.createQuery("select new List(keyhist.id.dat,keyhist.id.numkey,keyhist.sumadd,keyhist.terminal,keyhist.sumsub,keyhist.sumbut,keyhist.avtomat,keyhist.but,keyhist.litr) " +
						"from Keyhistory keyhist where keyhist.avtomat in (:alist) and keyhist.id.dat >= :dat1 and keyhist.id.dat <= :dat2 order by keyhist.id.dat asc")
						.setParameterList("alist", avtolist)
						.setParameter("dat1", fromDate)
						.setParameter("dat2", toDate)
						.setFirstResult(first)
						.setMaxResults(first+pageSize);						
			@SuppressWarnings({ "rawtypes", "unchecked" })
			List<List> klst =query.list();	
			transaction.commit();
			return klst;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
			  } finally {
			  session.close();}		
	}

	public int getLazyRowCount(int first, int pageSize, Date fromDate, Date toDate, int i) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			avtolist = sessionFactory.openSession().createQuery("select avt.num from Avtomat avt").list();
			if (avtolist == null || avtolist.isEmpty()) avtolist.add(0);;
			query =  session.createQuery("select count(keyhist.id.numkey) from Keyhistory keyhist where (keyhist.avtomat in (:alist)) and (keyhist.id.dat >= :dat1) and (keyhist.id.dat <= :dat2)")
						.setParameterList("alist", avtolist)
						.setParameter("dat1", fromDate)
						.setParameter("dat2", toDate);						
			@SuppressWarnings({ "rawtypes", "unchecked" })
			int num =Integer.parseInt(query.uniqueResult().toString());	
			transaction.commit();
			return num;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return 0;
			  } finally {
			  session.close();}				
	}

	public List<List> getFilteredByKey(String str, Date fromDate, Date toDate, int first, int pageSize, int i) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query =  session.createQuery(str)
						.setParameter("dat1", fromDate)
						.setParameter("dat2", toDate)
						.setFirstResult(first + 1)
						.setMaxResults(pageSize+first);						
			@SuppressWarnings({ "rawtypes", "unchecked" })
			List<List>klst=query.list();
			transaction.commit();
			return klst;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
			  } finally {session.close();}
	}

	public List<List> getFiltered(String str, Date fromDate, Date toDate, Integer anum, int first, int pageSize, int i) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query =  session.createQuery(str)
						.setParameter("dat1", fromDate)
						.setParameter("dat2", toDate)
						.setParameter("anum", anum)
						.setFirstResult(first + 1)
						.setMaxResults(pageSize+first);						
			@SuppressWarnings({ "rawtypes", "unchecked" })
			List<List>klst=query.list();
			transaction.commit();
			return klst;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
			  } finally {session.close();}
	}

	public int getFilteredByKeyCount(String str, Date fromDate, Date toDate,int first, int pageSize, int i) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query =  session.createQuery(str)
					.setParameter("dat1", fromDate)
					.setParameter("dat2", toDate);						
			@SuppressWarnings({ "rawtypes", "unchecked" })
			int num =Integer.parseInt(query.uniqueResult().toString());	
			transaction.commit();
			return num;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return 0;
			  } finally {			  
			  session.close();}		
	}

	public int getFilteredCount(String str, Date fromDate, Date toDate,Integer anum, int first, int pageSize, int i) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query =  session.createQuery(str)
					.setParameter("dat1", fromDate)
					.setParameter("dat2", toDate)
					.setParameter("anum", anum);						
			@SuppressWarnings({ "rawtypes", "unchecked" })
			int num =Integer.parseInt(query.uniqueResult().toString());	
			transaction.commit();
			return num;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return 0;
			  } finally {			  
			  session.close();}	
	}

	public List<String> selKeysToAdd(Integer anum) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query =  session.createQuery("select distinct keyhist.id.numkey from Keyhistory keyhist where keyhist.avtomat = :anum")
					.setParameter("anum", anum);						
			@SuppressWarnings({ "rawtypes", "unchecked" })
			List<String> keyAcc =query.list();	
			transaction.commit();
			return keyAcc;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
			  } finally {			  
			  session.close();}	
	}

	public List<List> getSortedLazyStoryByDate(int first, int pageSize, Date fromDate, Date toDate, String queryString, int i) 
	{

		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			avtolist = sessionFactory.openSession().createQuery("select avt.num from Avtomat avt").list();
			if (avtolist == null || avtolist.isEmpty()) avtolist.add(0);
			query =  session.createQuery(queryString)
						.setParameterList("alist", avtolist)
						.setParameter("dat1", fromDate)
						.setParameter("dat2", toDate)
						.setFirstResult(first)
						.setMaxResults(first+pageSize);						
			@SuppressWarnings({ "rawtypes", "unchecked" })
			List<List> klst =query.list();	
			transaction.commit();
			return klst;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
			  } finally {
			  session.close();}		
	}
}
