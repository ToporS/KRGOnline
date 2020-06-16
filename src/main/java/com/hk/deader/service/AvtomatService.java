package com.hk.deader.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.hk.deader.domains.Avtomat;
import com.hk.deader.domains.AvtomatWrapper;
import com.hk.deader.utils.HiberUtil;

public class AvtomatService {	
	public AvtomatService(){};
	private Session session ; 
	private Transaction transaction = null;
	private SessionFactory sessionFactory = HiberUtil.getSessionFactory();
	private List<Integer> avtomatList;
	
	 
	public List<Avtomat> getByOper(String opername) {
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Avtomat> avtomatList = (List<Avtomat>) session.createQuery("from Avtomat avt where avt.operator = :oname order by avt.operator, avt.num asc")
					.setString("oname", opername).list();
			transaction.commit();
			return avtomatList;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
			  } finally {
				  
			  session.close();
			  }		
	}

	public List<Integer> getOperAutomats()
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();			
			avtomatList = (List<Integer>) session.createQuery("select avt.num from Avtomat avt order by avt.num asc").list();
			transaction.commit();
			return avtomatList;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
			  } finally {				  
			  session.close();
			  }		
	}

	public Avtomat getById(Integer id) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			Avtomat avtomat = (Avtomat) session.createQuery("from Avtomat avt where avt.num = :oname").setInteger("oname", id).uniqueResult();			
			transaction.commit();
			return avtomat;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;
			  } finally {				  
			  session.close();
			  }		
	}

	public short getFlg(Integer num)
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			short flag = (Short)session.createQuery("select avt.flag from Avtomat avt where avt.num = :oname").setInteger("oname", num).uniqueResult();
     		transaction.commit();
			return flag;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return (Short) null;
			  } finally {
			  session.close();
			  }		
	}

	public void remchk(Integer num, short flg) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			int flag = session.createQuery("update Avtomat avt set avt.flag = :flg where avt.num = :oname").setShort("flg", flg).setInteger("oname", num).executeUpdate();
			transaction.commit();
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();			  
			  } finally {			  
			  session.close();
			  }			
	}

	public ArrayList<Integer> getLazyAvtomats(Integer first, Integer pageSize) 
	{
		try 
		{
			Query qry;
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			//if(opername.equalsIgnoreCase("Провайдер"))
			{
				qry = session.createQuery("select avt.num from Avtomat avt order by avt.operator");
				qry.setFirstResult((int)first + 1);
				qry.setMaxResults((int)pageSize+(int)first);
			}
			//else
				/*{
					qry = session.createQuery("select avt.num from Avtomat avt where avt.operator = :oname");
					qry.setParameter("oname", opername);
					qry.setFirstResult((int)first + 1);
					qry.setMaxResults((int)pageSize+(int)first);					
				}*/
			avtomatList = qry.list();
			transaction.commit();
			return (ArrayList<Integer>) avtomatList;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;			  
			  } finally {
			  session.close();
			  }	
	}

	public Integer getRowCount(String opername) 
	{
		try 
		{
			Query qry;
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			//if(opername.equalsIgnoreCase("Провайдер"))
			{
				qry = session.createQuery("select Count (avt.num) from Avtomat avt");
			}
		/*	else
			{
				qry = session.createQuery("select Count (avt.num) from Avtomat avt where avt.operator = :oname");
				qry.setParameter("oname", opername);
			}		*/	
			Integer ret = Integer.parseInt(qry.uniqueResult().toString());
			transaction.commit();
			return ret;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;			  
			  } finally {
			  session.close();
			  }	
	}

	public ArrayList<Integer> getSortedLazyAvtomats(String query,  int first, int pageSize) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			Query qry = session.createQuery(query);
			//if (! opername.equalsIgnoreCase("Провайдер"))qry.setParameter("oname", opername);
			qry.setFirstResult((int)first + 1);
			qry.setMaxResults((int)pageSize+(int)first);
			avtomatList = qry.list();
			transaction.commit();
			return (ArrayList<Integer>) avtomatList;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;			  
			  } finally {
			  session.close();
			  }	
	}

	public Integer getSelected(String opername) 
	{
		
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			Query qry = session.createQuery("select avt.num from Avtomat avt where avt.operator = :oname");
			qry.setParameter("oname", opername);
			qry.setMaxResults(1);
			Integer avtomat = (Integer) qry.uniqueResult();
			transaction.commit();
			return avtomat;
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return null;			  
			  } finally {
			  session.close();
			  }	
	}
	public void remFlag(Short short1, int anum) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			short flg = (Short) session.createQuery("select avt.flag from Avtomat avt where avt.num = :anum").setParameter("anum", anum).uniqueResult();
			flg-=short1;
			@SuppressWarnings("unchecked")
			int flag = session.createQuery("update Avtomat avt set avt.flag = :flg where avt.num = :oname").setShort("flg", flg).setInteger("oname", anum).executeUpdate();
			transaction.commit();
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();			  
			  } finally {
			  session.close();
			  }		
	}

	public void changeSettings(String stringa, AvtomatWrapper selectedAvtomat, short flags) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query qryupd = session.createQuery("update Avtomat avt set avt.flag = :flg, avt.rezh = :sb,avt.tel = :tel,avt.prim = :prim,avt.cenabut = :cbut," +
					"avt.cena = :cena,avt.adr = :adres,avt.voda = :voda where avt.num = :oname");
			qryupd.setShort("flg", flags);
			qryupd.setInteger("oname", selectedAvtomat.getAvtomat().getNum());
			qryupd.setString("sb", stringa);
			qryupd.setString("prim", selectedAvtomat.getAvtomat().getPrim());
			qryupd.setString("tel", selectedAvtomat.getAvtomat().getTel());
			qryupd.setDouble("cbut", selectedAvtomat.getAvtomat().getCenabut());
			qryupd.setDouble("cena", selectedAvtomat.getAvtomat().getCena());
			qryupd.setString("adres", selectedAvtomat.getAvtomat().getAdr());
			qryupd.setDouble("voda", selectedAvtomat.getAvtomat().getVoda());
			qryupd.executeUpdate();
			transaction.commit();
			System.out.println("Update successfull.....");
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  } finally {
			  session.close();
			  }	
	}

	public String getOperName(Integer avtonum) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			String oname = (String) session.createQuery("select avt.operator from Avtomat avt where avt.num = :oname")
					.setParameter("oname", avtonum).uniqueResult();	
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

	public void saveAvtomat(Avtomat newAvto) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(newAvto);
			transaction.commit();
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  
			  } finally {
			  session.close();
			  }		
	}

	public void delAvtom(Integer avtonum) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			int oname = session.createQuery("delete from Avtomat avt where avt.num = :oname")
					.setParameter("oname", avtonum).executeUpdate();	
			transaction.commit();
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  } finally {				  
			  session.close();
			  }	
	}

	public void delAvtom(Integer avtonum, String operName) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			int oname = session.createQuery("update Avtomat avt set avt.operator = :oname where avt.num = :anum")
					.setParameter("anum", avtonum)
					.setParameter("oname", operName)
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

	public void changeSettingsForAll(String stringa, AvtomatWrapper selectedAvtomat, short flags) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query qryupd = session.createQuery("update Avtomat avt set avt.flag = :flg, avt.rezh = :sb,avt.cenabut = :cbut," +
					"avt.cena = :cena,avt.voda = :voda where avt.operator = :oname");
			qryupd.setShort("flg", flags);
			qryupd.setParameter("oname", selectedAvtomat.getAvtomat().getOperator());
			qryupd.setString("sb", stringa);
			qryupd.setDouble("cbut", selectedAvtomat.getAvtomat().getCenabut());
			qryupd.setDouble("cena", selectedAvtomat.getAvtomat().getCena());
			qryupd.setDouble("voda", selectedAvtomat.getAvtomat().getVoda());
			qryupd.executeUpdate();
			transaction.commit();
			System.out.println("Update successfull.....");
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  } finally {
			  session.close();
			  }	

	}

	public Integer getBeznalLitrSale(Integer aaa, Date fromDate, Date toDate) 
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();			
			Query query =  session.createQuery("select sum(keyhist.litr) from Keyhistory keyhist where (keyhist.id.dat >= :dat1) and (keyhist.id.dat <= :dat2) and keyhist.avtomat = :anum")
						.setParameter("dat1", fromDate)
						.setParameter("dat2", toDate)
						.setParameter("anum", aaa);
			Long lng = (Long) query.uniqueResult();
			transaction.commit();
			return (lng==null?0:Integer.parseInt(lng.toString()));
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return 0;
			  } finally {
			  session.close();}		
	}

	public Integer getNalLitrSale(Integer aaa, Date fromDate, Date toDate) 
	{

		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();			
			Query query =  session.createQuery("select sum(cash.litr) from Cash cash where (cash.id.dat >= :dat1) and (cash.id.dat <= :dat2) and (cash.id.avtomat = :anum)")
						.setParameter("dat1", fromDate)
						.setParameter("dat2", toDate)
						.setParameter("anum", aaa);
			Long lng = (Long) query.uniqueResult();
			transaction.commit();
			return (lng==null?0:Integer.parseInt(lng.toString()));
		}
		catch (HibernateException e) {
			  transaction.rollback();
			  e.printStackTrace();
			  return 0;
			  } finally {
			  session.close();}		
	}

	public void remUpdt(int avtonum)
	{
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			short oname = (short)session.createQuery("select avt.flag from Avtomat avt where avt.num = :oname")
					.setParameter("oname", avtonum).uniqueResult();	
			session.createQuery("update Avtomat avt set avt.flag = :flg where avt.num = :anum").setParameter("flg", (short)(oname-4)).setParameter("anum", avtonum).executeUpdate();
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