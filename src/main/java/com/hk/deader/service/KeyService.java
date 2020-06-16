package com.hk.deader.service;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.hk.deader.domains.Key;
import com.hk.deader.utils.HiberUtil;

public class KeyService {
	
	private Session session ; 
	private Transaction transaction = null;
	private SessionFactory sessionFactory = HiberUtil.getSessionFactory();	
	private Query query;
	
	public KeyService(){}

	public List<Key> getOperKeys(String opername) {
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			if (opername.equalsIgnoreCase("провайдер"))
			{
				query = session.createQuery("from Key key");
			} else 
			{
				query = session.createQuery("from Key key where (key.operator = :oname) and (not (key.numkey = '000000')) order by key.numkey").setString("oname", opername);
			}
			@SuppressWarnings("unchecked")
			List<Key> keyList = (List<Key>)query.list();
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

	public List<Key> getOperKeysLazy(int first, int pageSize) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			//if(opername.equalsIgnoreCase("Провайдер")) query =  session.createQuery("from Key key where (not (key.numkey = '000000')) order by key.numkey");
		//	else query = session.createQuery("from Key key where (key.operator = :oname) and (not (key.numkey = '000000')) order by key.numkey").setString("oname", opername);
			query =  session.createQuery("from Key key where (not (key.numkey = '000000')) order by key.numkey");
			query.setFirstResult(first);
			query.setMaxResults(first + pageSize);
			@SuppressWarnings("unchecked")
			List<Key> keyList = (List<Key>)query.list();
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

	public Integer getRowCount() 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			//if(opername.equalsIgnoreCase("Провайдер")) query = session.createQuery("select count(key.numkey)from Key key where (not (key.numkey = '000000'))");
			//else query = session.createQuery("select count(key.numkey)from Key key where (key.operator = :oname) and (not (key.numkey = '000000'))").setParameter("oname", opername);
			query = session.createQuery("select count(key.numkey)from Key key where (not (key.numkey = '000000'))");
			@SuppressWarnings("unchecked")
			Integer keyL = Integer.parseInt(query.uniqueResult().toString());	
			transaction.commit();
			return keyL;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
			  } finally {
			  session.close();
			  }	
	}

	public List<Key> getFilteredKeys(int first, int pageSize, String filterValue) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session.createQuery("from Key key where (not (key.numkey = '000000')) and (key.numkey like :filter) order by key.numkey");
			query.setParameter("filter", filterValue+"%");
			query.setFirstResult(first);
			query.setMaxResults(first + pageSize);
			@SuppressWarnings("unchecked")
			List<Key> keyList = (List<Key>)query.list();	
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

	public Integer getFilteredRowCount(String filterValue) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session.createQuery("select count(key.numkey)from Key key where (not (key.numkey = '000000')) and (key.numkey like :filter)");
			query.setParameter("filter", filterValue+"%");
			@SuppressWarnings("unchecked")
			Integer keyL = Integer.parseInt(query.uniqueResult().toString());	
			transaction.commit();
			return keyL;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
			  } 
		finally 
			 {
				session.close();
			  }	
	}

	public void addbon(Double sum, String key) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session.createQuery("update Key key set key.bonadd = :sum where key.numkey = :numkey").setParameter("sum", sum).setParameter("numkey", key);
			query.executeUpdate();	
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

	public void remKey(String key) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session.createQuery("delete Key key where key.numkey = :numkey").setParameter("numkey", key);
			query.executeUpdate();	
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

	public Double checkBonus(String key) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session.createQuery("select key.bonadd from Key key where key.numkey = :numkey").setParameter("numkey", key);
			Double bon = (Double) query.uniqueResult();
			transaction.commit();
			return bon;
		}
		catch (HibernateException e) {
			  transaction.rollback();			  
			  e.printStackTrace();
			  return null;
			  } 
		finally 
			  {
				  session.close();
			  }	
	}

	public void editKey(Key selectedKey) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session.createQuery("update Key key set key.FIO = :sum, key.ugroup = :group where key.numkey = :numkey")
					.setParameter("sum", selectedKey.getFIO())
					.setParameter("group", selectedKey.getUgroup())
					.setParameter("numkey", selectedKey.getNumkey());
			query.executeUpdate();	
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

	public List<String> groupKeySelect(String group) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session.createQuery("select key.numkey from Key key where  (key.ugroup = :gr) ")
					.setParameter("gr", group)					;
			List<String> kList = (List<String>) query.list();
			transaction.commit();
			return kList;
		}
		catch (HibernateException e) {
			  transaction.rollback();			  
			  e.printStackTrace();
			  return null;
			  } 
		finally 
			  {
				  session.close();
			  }	
	}

	public List<String> getFGList(String numkey) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session.createQuery("select new List(key.FIO, key.ugroup)from Key key where key.numkey = :numkey")
					.setParameter("numkey", numkey)			;
			List<String> kList = (List<String>) query.uniqueResult();
			transaction.commit();
			return kList;
		}
		catch (HibernateException e) {
			  transaction.rollback();			  
			  e.printStackTrace();
			  return null;
			  } 
		finally 
			  {
				  session.close();
			  }	
	}

	public List<Key> getSortedKeyList(int first, int pageSize, String query2) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session.createQuery(query2)
					.setMaxResults(pageSize)
					.setFirstResult(first);
			List<Key> kList = (List<Key>) query.list();
			transaction.commit();
			return kList;
		}
		catch (HibernateException e) {
			  transaction.rollback();			  
			  e.printStackTrace();
			  return null;
			  } 
		finally 
			  {
				  session.close();
			  }	
	}
}