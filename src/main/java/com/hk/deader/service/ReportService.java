package com.hk.deader.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hk.deader.domains.Keyhistory;
import com.hk.deader.domains.SalesReport;
import com.hk.deader.utils.HiberUtil;

public class ReportService implements Serializable{
	private static final long serialVersionUID = 1575614291767840097L;
	private Session session ; 
	private Transaction transaction = null;
	private SessionFactory sessionFactory = HiberUtil.getSessionFactory();	
	private List<SalesReport> srl;
	private SalesReport sr;
	private List<Integer> avtolist = null;	
	private Query query;
	private Double ksumadd,nsumadd,ksumsub,nsumsub,inkas;

	@SuppressWarnings("unchecked")
	public List<Integer> getOperAvtomats() 
	{
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			avtolist = session.createQuery("select avt.num from Avtomat avt ").list();
			transaction.commit();
			return avtolist;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
		
	}

	public Double getKSumSub(Date fromDate, Date toDate, Integer next) 
	{
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			ksumsub = (Double) session.createQuery("select sum(keyhist.sumsub) from Keyhistory keyhist where keyhist.avtomat = :avt and keyhist.id.dat >= :dat1 and keyhist.id.dat"+
					"<= :dat2").setParameter("avt", next).setParameter("dat1", fromDate).setParameter("dat2", toDate).uniqueResult();
			transaction.commit();
			return ksumsub;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}

	public Double getKSumAdd(Date fromDate, Date toDate, Integer next)
	{
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			ksumadd = (Double) session.createQuery("select sum(keyhist.sumadd) from Keyhistory keyhist where keyhist.avtomat = :avt and keyhist.id.dat >= :dat1 and keyhist.id.dat"+
					"<= :dat2").setParameter("avt", next).setParameter("dat1", fromDate).setParameter("dat2", toDate).uniqueResult();
			transaction.commit();
			return ksumadd;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}			
	}

	public Double getInkas(Date fromDate, Date toDate, Integer next)
	{
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			inkas = (Double) session.createQuery("select sum(inkhist.sumadd) from Inkasshistory inkhist where inkhist.avtomat = :avt and inkhist.id.dat >= :dat1 and inkhist.id.dat <= :dat2").setParameter("avt", next).setParameter("dat1", fromDate).setParameter("dat2", toDate).uniqueResult();
			transaction.commit();
			return inkas;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}		
		
	}
	
	public List getSumAddSub(Date fromDate, Date toDate, Integer next)
	{
		List reslist = new ArrayList();
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			reslist = (List) session.createQuery("select new List(sum(keyhist.sumadd),sum(keyhist.sumsub),sum(keyhist.litr),sum(keyhist.sumbut),sum(keyhist.but)) from Keyhistory keyhist where keyhist.avtomat = :avt and keyhist.id.dat >= :dat1 and keyhist.id.dat"+
					"<= :dat2").setParameter("avt", next).setParameter("dat1", fromDate).setParameter("dat2", toDate).uniqueResult();
			transaction.commit();
			return reslist;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}
	
	public List getSumAddRez(Date fromDate, Date toDate, Integer next)
	{
		List<Double> reslist = new ArrayList<Double>();
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			reslist = (List) session.createQuery("select new List(sum(inkhist.sumadd),sum(inkhist.sumrez)) from Inkasshistory inkhist" +
					" where inkhist.avtomat = :avt and inkhist.id.dat >= :dat1 and inkhist.id.dat <= :dat2").setParameter("avt", next).setParameter("dat1", fromDate).setParameter("dat2", toDate).uniqueResult();
			transaction.commit();
			return reslist;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}
	
	public List getNalSales(Date fromDate, Date toDate, Integer next)
	{
		List reslist = new ArrayList();
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			reslist = (List) session.createQuery("select new List(sum(cash.litr),sum(cash.litrcash),sum(cash.but),sum(cash.butcash)) from Cash cash" +
					" where cash.id.avtomat = :avt and cash.id.dat >= :dat1 and cash.id.dat <= :dat2").setParameter("avt", next).setParameter("dat1", fromDate).setParameter("dat2", toDate).uniqueResult();
			transaction.commit();
			return reslist;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}

	public Long getNalSalesInDay(Integer id, Date tmpDate, Date curDate)
	{
		Long res;
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			res = (Long) session.createQuery("select (sum(cash.litrcash)) from Cash cash where cash.id.avtomat = :avt and cash.id.dat >= :dat1 and cash.id.dat <= :dat2").setParameter("avt", id).setParameter("dat1", tmpDate).setParameter("dat2", curDate).uniqueResult();
			transaction.commit();
			return res;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}

	public Double getBezNalSalesInDay(Integer id, Date tmpDate, Date curDate)
	{
		Double res;
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			res = (Double) session.createQuery("select (sum(keyhist.sumsub)) from Keyhistory keyhist where keyhist.avtomat = :avt and keyhist.id.dat >= :dat1 and keyhist.id.dat<= :dat2").setParameter("avt",id).setParameter("dat1", tmpDate).setParameter("dat2", curDate).uniqueResult();
			transaction.commit();
			return res;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}
	}

	public String getAddr(Integer avtnum) 
	{	
		String addr;
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			addr = (String) session.createQuery("select avt.adr from Avtomat avt where avt.num = :avt ").setParameter("avt",avtnum).uniqueResult();
			transaction.commit();
			return addr;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}
		
	}

	public String getOpernameById(Integer avtnum) 
	{
		String addr;
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			addr = (String) session.createQuery("select avt.operator from Avtomat avt where avt.num = :avt ").setParameter("avt",avtnum).uniqueResult();
			transaction.commit();
			return addr;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}
		
	}

}
