/**
 * 
 */
package com.hk.deader.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.hk.deader.domains.Avtomat;
import com.hk.deader.domains.Bonus;
import com.hk.deader.domains.Bonushistory;
import com.hk.deader.domains.BonushistoryId;
import com.hk.deader.domains.Cash;
import com.hk.deader.domains.Inkas;
import com.hk.deader.domains.Inkasshistory;
import com.hk.deader.domains.Key;
import com.hk.deader.domains.Keyhistory;
import com.hk.deader.domains.Operator;
import com.hk.deader.domains.OperatorId;
import com.hk.deader.domains.Polzov;



/**
 * @author �����
 *
 */
@SuppressWarnings("deprecation")
public class HiberUtil {
	private static final SessionFactory sessionFactory;
	static {
	try {
	
		Configuration cfg =  new AnnotationConfiguration()
		.addAnnotatedClass(Avtomat.class)
		.addAnnotatedClass(Operator.class)
		.addAnnotatedClass(OperatorId.class)
		.addAnnotatedClass(Polzov.class)
		.addAnnotatedClass(Key.class)
		.addAnnotatedClass(Keyhistory.class)
		.addAnnotatedClass(Inkasshistory.class)
		.addAnnotatedClass(Inkas.class)
		.addAnnotatedClass(Cash.class)
		.addAnnotatedClass(Bonushistory.class)
		.addAnnotatedClass(Bonus.class)
		.addAnnotatedClass(BonushistoryId.class)
		.configure("hibernate.cfg.xml");
		
		sessionFactory =cfg.buildSessionFactory();
	System.out.println("Creation Session factory... Success!");
	} catch (Throwable ex) {
	System.err.println("Initial SessionFactory creation failed." + ex);
	throw new ExceptionInInitializerError(ex);
	}
	}
	 
	public static SessionFactory getSessionFactory() {
	return sessionFactory;
	}
}
