package com.hk.deader.service;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hk.deader.domains.Bonushistory;
import com.hk.deader.domains.BonushistoryId;
import com.hk.deader.utils.HiberUtil;

public class BonusService 
{
	private Session session ; 
	private Transaction transaction = null;
	private SessionFactory sessionFactory = HiberUtil.getSessionFactory();	
	private Query query;
	
	public BonusService(){}

	public void addEntry(String key, Double sum, String operid) 
	{
		Date data = new Date();
		Integer bonadd = (int)Math.round(sum);
		BonushistoryId bhid = new BonushistoryId(key, data);
		Bonushistory bh = new Bonushistory(bhid, bonadd, "", operid);
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(bh);		
			transaction.commit();
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  } 
		finally 
			  {
				  session.close();
			  }		
	}

	public List<List> getLazyBonusStory(int first, int pageSize, Date fromDate, Date toDate) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();			
			query = session.createQuery("select new List(bonhist.id.dat,bonhist.id.numkey,bonhist.bonadd,bonhist.operator,bonhist.userid) " +
						"from Bonushistory bonhist where bonhist.id.dat >= :dat1 and bonhist.id.dat <= :dat2")
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

	public int getLazyRowCount(Date fromDate, Date toDate) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();			
			query = session.createQuery("select count(bonhist.id.dat)from Bonushistory bonhist where bonhist.id.dat >= :dat1 and bonhist.id.dat <= :dat2")
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

	public List<List> getSortedLazyBonusStory(int first, int pageSize, Date fromDate, Date toDate, String query2) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();			
			query = session.createQuery(query2)
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
