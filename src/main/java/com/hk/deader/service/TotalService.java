package com.hk.deader.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.hk.deader.utils.HiberUtil;

public class TotalService implements Serializable {

	private static final long serialVersionUID = 5499110834876410309L;
	private Session session ; 
	private Transaction transaction = null;
	private SessionFactory sessionFactory = HiberUtil.getSessionFactory();
	
	public TotalService(){}

	public Double getSalesNal(List<Integer> alist2, Date from, Date to) 
	{
		try
		{
			Double result;
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Long res = (Long) session.createQuery("select sum(cash.litrcash) from Cash cash where cash.id.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist2)
					.setParameter("dat1", from)
					.setParameter("dat2", to)
					.uniqueResult();
			transaction.commit();
			if (res == null)result = new Double(0);else result = new Double(res/100) ;
			//(res==null?res = new Double(0):res = new Double(res/100)) ;
			transaction = session.beginTransaction();
			Long res2 = (Long) session.createQuery("select sum(cash.butcash) from Cash cash where cash.id.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist2)
					.setParameter("dat1", from)
					.setParameter("dat2", to)
					.uniqueResult();
			transaction.commit();
			if (res2 != null) result += new Double(res2/100) ;
			return result;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}

	public Double getSalesBeznal(List<Integer> alist2, Date from, Date to) 
	{
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Double res = (Double) session.createQuery("select sum(cash.sumsub) from Keyhistory cash where cash.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist2)
					.setParameter("dat1", from)
					.setParameter("dat2", to)
					.uniqueResult();
			transaction.commit();
			if(res==null)res=new Double(0);
			transaction = session.beginTransaction();
			Double res2 = (Double) session.createQuery("select sum(cash.sumbut) from Keyhistory cash where cash.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist2)
					.setParameter("dat1", from)
					.setParameter("dat2", to)
					.uniqueResult();
			transaction.commit();
			if(res2!=null)res+=res2;
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

	public Double getTotalInkas(List<Integer> alist2, Date from, Date to) 
	{
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Double res = (Double) session.createQuery("select sum(cash.sumadd) from Inkasshistory cash where cash.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist2)
					.setParameter("dat1", from)
					.setParameter("dat2", to)
					.uniqueResult();
			transaction.commit();
			return(res==null?new Double(0):res);
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}

	public List<Integer> getAList() 
	{
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Integer>alist = session.createQuery("select avt.num from Avtomat avt").list();
			if(alist == null) return null;			
			transaction.commit();
			return alist;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}

	public Double getTotalSpisano(List<Integer> alist, Date from, Date to) 
	{
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Double res = (Double) session.createQuery("select sum(cash.sumrez) from Inkasshistory cash where cash.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist)
					.setParameter("dat1", from)
					.setParameter("dat2", to)
					.uniqueResult();
			transaction.commit();
			return(res==null?new Double(0):res) ;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}

	public Double getTotalPop(List<Integer> alist, Date from, Date to) 
	{
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Double res = (Double) session.createQuery("select sum(cash.sumadd) from Keyhistory cash where cash.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist)
					.setParameter("dat1", from)
					.setParameter("dat2", to)
					.uniqueResult();
			transaction.commit();
			return(res==null?new Double(0):res) ;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}

	public Long getTotalLitrNal(List<Integer> alist, Date from, Date to) 
	{
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Long res = (Long) session.createQuery("select sum(cash.litr) from Cash cash where cash.id.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist)
					.setParameter("dat1", from)
					.setParameter("dat2", to)
					.uniqueResult();
			transaction.commit();
			return(res==null?new Long(0):res) ;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}
	}

	public Long getTotalLitrBeznal(List<Integer> alist, Date from, Date to) 
	{
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Long res = (Long) session.createQuery("select sum(cash.litr) from Keyhistory cash where cash.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist)
					.setParameter("dat1", from)
					.setParameter("dat2", to)
					.uniqueResult();
			transaction.commit();
			return(res==null?new Long(0):res) ;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}

	public Double getTotalKopilka(List<Integer> alist) 
	{
		try
		{
			Double rres = (double)0;
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			for(Integer num: alist)
			{
				Integer res = (Integer) session.createQuery("select avt.po from Avtomat avt where avt.num = :anum ")
						.setParameter("anum", num)
						.uniqueResult();
				if(res != null)
				{
					Integer po = (int) (res%100);
					rres = rres + ((res - po)/10000);
				}				 
			}			
			transaction.commit();
			return(rres==null?new Double(0):rres) ;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}

	public List getBeznalOpers(List<Integer> alist, Date from, Date to) 
	{
		//List reslist = new ArrayList();
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			List reslist = (List) session.createQuery("select sum(keyhist.sumadd),sum(keyhist.sumsub),sum(keyhist.litr),sum(keyhist.sumbut),sum(keyhist.but) from Keyhistory keyhist where (keyhist.avtomat in (:avt)) and (keyhist.id.dat >= :dat1) and (keyhist.id.dat"+
					"<= :dat2)")
					.setParameter("avt", alist)
					.setParameter("dat1", from)
					.setParameter("dat2", to)
					.uniqueResult();
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

	public List getNalOpers(List<Integer> alist, Date from, Date to) 
	{
		List reslist = new ArrayList();
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			reslist = (List) session.createQuery("select new List(sum(cash.litr),sum(cash.litrcash),sum(cash.but),sum(cash.butcash)) from Cash cash" +
					" where cash.id.avtomat in (:avt) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2").setParameter("avt", alist).setParameter("dat1", from).setParameter("dat2", to).uniqueResult();
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

	public List getInkasOpers(List<Integer> alist, Date from, Date to) 
	{
		List<Double> reslist = new ArrayList<Double>();
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			reslist = (List) session.createQuery("select new List(sum(inkhist.sumadd),sum(inkhist.sumrez)) from Inkasshistory inkhist" +
					" where inkhist.avtomat in (:avt) and inkhist.id.dat >= :dat1 and inkhist.id.dat <= :dat2").setParameter("avt", alist).setParameter("dat1", from).setParameter("dat2", to).uniqueResult();
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

	public Long getTotalButBezNal(List<Integer> alist, Date from, Date to) 
	{
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Long res = (Long) session.createQuery("select sum(cash.but) from Keyhistory cash where cash.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist)
					.setParameter("dat1", from)
					.setParameter("dat2", to)
					.uniqueResult();
			transaction.commit();
			return(res==null?new Long(0):res) ;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}

	public Long getTotalButNal(List<Integer> alist, Date from, Date to) 
	{
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Long res = (Long) session.createQuery("select sum(cash.but) from Cash cash where cash.id.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist)
					.setParameter("dat1", from)
					.setParameter("dat2", to)
					.uniqueResult();
			transaction.commit();
			return(res==null?new Long(0):res) ;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}
	}

/*	public Double getArchiveSalesNal(List<Integer> alist, Date fromDate, Date toDate) 
	{
		try
		{
			Double result;
			session = archiveSessionFactory.openSession();
			transaction = session.beginTransaction();
			Long res = (Long) session.createQuery("select sum(cash.litrcash) from Cash cash where cash.id.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist)
					.setParameter("dat1", fromDate)
					.setParameter("dat2", toDate)
					.uniqueResult();
			transaction.commit();
			if (res == null)result = new Double(0);else result = new Double(res/100) ;
			//(res==null?res = new Double(0):res = new Double(res/100)) ;
			transaction = session.beginTransaction();
			Long res2 = (Long) session.createQuery("select sum(cash.butcash) from Cash cash where cash.id.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist)
					.setParameter("dat1", fromDate)
					.setParameter("dat2", toDate)
					.uniqueResult();
			transaction.commit();
			if (res2 != null) result += new Double(res2/100) ;
			return result;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}

	public Double getArchiveSalesBeznal(List<Integer> alist, Date fromDate,	Date toDate) 
	{
		try
		{
			session = archiveSessionFactory.openSession();
			transaction = session.beginTransaction();
			Double res = (Double) session.createQuery("select sum(cash.sumsub) from Keyhistory cash where cash.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist)
					.setParameter("dat1", fromDate)
					.setParameter("dat2", toDate)
					.uniqueResult();
			transaction.commit();
			if(res==null)res=new Double(0);
			transaction = session.beginTransaction();
			Double res2 = (Double) session.createQuery("select sum(cash.sumbut) from Keyhistory cash where cash.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist)
					.setParameter("dat1", fromDate)
					.setParameter("dat2", toDate)
					.uniqueResult();
			transaction.commit();
			if(res2!=null)res+=res2;
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

	public Double getArchiveTotalInkas(List<Integer> alist, Date fromDate, Date toDate) 
	{
		try
		{
			session = archiveSessionFactory.openSession();
			transaction = session.beginTransaction();
			Double res = (Double) session.createQuery("select sum(cash.sumadd) from Inkasshistory cash where cash.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist)
					.setParameter("dat1", fromDate)
					.setParameter("dat2", toDate)
					.uniqueResult();
			transaction.commit();
			return(res==null?new Double(0):res);
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}

	public Double getArchiveTotalSpisano(List<Integer> alist, Date fromDate, Date toDate)
	{
		try
		{
			session = archiveSessionFactory.openSession();
			transaction = session.beginTransaction();
			Double res = (Double) session.createQuery("select sum(cash.sumrez) from Inkasshistory cash where cash.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist)
					.setParameter("dat1", fromDate)
					.setParameter("dat2", toDate)
					.uniqueResult();
			transaction.commit();
			return(res==null?new Double(0):res) ;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}

	public Double getArchiveTotalPop(List<Integer> alist, Date fromDate, Date toDate) 
	{
		try
		{
			session = archiveSessionFactory.openSession();
			transaction = session.beginTransaction();
			Double res = (Double) session.createQuery("select sum(cash.sumadd) from Keyhistory cash where cash.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist)
					.setParameter("dat1", fromDate)
					.setParameter("dat2", toDate)
					.uniqueResult();
			transaction.commit();
			return(res==null?new Double(0):res) ;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}

	public Long getArchiveTotalLitrNal(List<Integer> alist, Date fromDate, Date toDate) 
	{
		try
		{
			session = archiveSessionFactory.openSession();
			transaction = session.beginTransaction();
			Long res = (Long) session.createQuery("select sum(cash.litr) from Cash cash where cash.id.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist)
					.setParameter("dat1", fromDate)
					.setParameter("dat2", toDate)
					.uniqueResult();
			transaction.commit();
			return(res==null?new Long(0):res) ;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}
	}

	public Long getArchiveTotalLitrBeznal(List<Integer> alist, Date fromDate, Date toDate) 
	{
		try
		{
			session = archiveSessionFactory.openSession();
			transaction = session.beginTransaction();
			Long res = (Long) session.createQuery("select sum(cash.litr) from Keyhistory cash where cash.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist)
					.setParameter("dat1", fromDate)
					.setParameter("dat2", toDate)
					.uniqueResult();
			transaction.commit();
			return(res==null?new Long(0):res) ;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}

	public Long getArchiveTotalButBezNal(List<Integer> alist, Date fromDate, Date toDate) 
	{
		try
		{
			session = archiveSessionFactory.openSession();
			transaction = session.beginTransaction();
			Long res = (Long) session.createQuery("select sum(cash.but) from Keyhistory cash where cash.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist)
					.setParameter("dat1", fromDate)
					.setParameter("dat2", toDate)
					.uniqueResult();
			transaction.commit();
			return(res==null?new Long(0):res) ;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}	
	}

	public Long getArchiveTotalButNal(List<Integer> alist, Date fromDate, Date toDate) 
	{
		try
		{
			session = archiveSessionFactory.openSession();
			transaction = session.beginTransaction();
			Long res = (Long) session.createQuery("select sum(cash.but) from Cash cash where cash.id.avtomat in(:alist) and cash.id.dat >= :dat1 and cash.id.dat <= :dat2")
					.setParameterList("alist", alist)
					.setParameter("dat1", fromDate)
					.setParameter("dat2", toDate)
					.uniqueResult();
			transaction.commit();
			return(res==null?new Long(0):res) ;
		}
		catch (HibernateException e) 
		{
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
		 } 
		finally {session.close();}
	}*/

	
}
