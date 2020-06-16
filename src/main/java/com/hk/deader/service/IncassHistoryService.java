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

import com.hk.deader.domains.Inkasshistory;
import com.hk.deader.domains.InkasshistoryId;
import com.hk.deader.utils.HiberUtil;

public class IncassHistoryService implements Serializable{

	private static final long serialVersionUID = 8393701728318011850L;
	private Session session ; 
	private Transaction transaction = null;
	private SessionFactory sessionFactory = HiberUtil.getSessionFactory();
	
	private List<Inkasshistory> operKeyStoryList = new ArrayList<Inkasshistory>();
	private Query query;
	private List avtolist = null;
	
public List<List> getLazyInkasStory(int first, int pageSize, Date fromDate, Date toDate, int i) 
{
	try 
	{
		if (i==1)session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		
			avtolist = sessionFactory.openSession().createQuery("select avt.num from Avtomat avt ").list();
			if (avtolist == null || avtolist.isEmpty()) avtolist.add(0);
			query = session.createQuery("select new List(inkhist.id.dat,inkhist.id.datakey,inkhist.sumadd,inkhist.sumtotal,inkhist.sumrez,inkhist.avtomat) " +
					"from Inkasshistory inkhist where inkhist.avtomat in (:alist) and inkhist.id.dat >= :dat1 and inkhist.id.dat <= :dat2")
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
		  session.close();
		  }			
	
}


public int getLazyRowCount(int first, int pageSize, Date fromDate, Date toDate, int i) 
{
	try 
	{
		if (i==1)session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		
			avtolist = sessionFactory.openSession().createQuery("select avt.num from Avtomat avt ").list();
			if (avtolist == null || avtolist.isEmpty()) avtolist.add(0);
			query = session.createQuery("select count(inkhist.id.dat)from Inkasshistory inkhist where inkhist.avtomat in (:alist) and inkhist.id.dat >= :dat1 and inkhist.id.dat <= :dat2")
					.setParameterList("alist", avtolist)
					.setParameter("dat1", fromDate)
					.setParameter("dat2", toDate);
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Integer num =Integer.parseInt(query.uniqueResult().toString());
		transaction.commit();
		return num;		
	}
	catch (HibernateException e) {
		  transaction.rollback();
		  e.printStackTrace();
		  return 0;
		  } finally {		
		  session.close();
		  }			
}


public List<List> getSortedLazyInkasStory(int first, int pageSize,Date fromDate, Date toDate, String querystr, int i) 
{
	try 
	{
		if (i==1)session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		
			avtolist = sessionFactory.openSession().createQuery("select avt.num from Avtomat avt ").list();
			//if (avtolist == null) return null;
			query = session.createQuery(querystr)
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
		  session.close();
		  }		
}
}
