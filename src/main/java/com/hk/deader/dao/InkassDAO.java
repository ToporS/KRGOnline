package com.hk.deader.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.hk.deader.domains.Inkas;
import com.hk.deader.domains.InkasId;
import com.hk.deader.service.InkasService;
import com.hk.deader.utils.ForMaster;

public class InkassDAO implements Serializable{

	private static final long serialVersionUID = -4264035909118050595L;
	private InkasService is;
	private List<List> klst;
	private List<Inkas> inkList;
	
	public InkassDAO(){}

	public List<Inkas> getInkassators() 
	{
		klst = new ArrayList<List>();
		inkList = new ArrayList<Inkas>();
		is = new InkasService();
		klst = is.getInkassators();
		is = null;
		if (klst==null) return null;
		
		
		@SuppressWarnings({ "rawtypes" })
		Iterator<List> iter = klst.iterator();
		
		while (iter.hasNext())
		{	
			List tmplst = iter.next();
			InkasId Inkid = new InkasId();
			if(tmplst.get(0) != null){Inkid.setDatakey(tmplst.get(0).toString()) ;}else Inkid.setDatakey("") ;
			if(tmplst.get(1) != null){Inkid.setOperator(tmplst.get(1).toString()) ;}else Inkid.setOperator("") ;
			if(tmplst.get(2) != null){Inkid.setName(tmplst.get(2).toString()) ;}else Inkid.setName("") ;
			if(tmplst.get(3) != null){Inkid.setDat((Date)tmplst.get(3));}else Inkid.setDat(new Date()) ;
			if(tmplst.get(4) != null){Inkid.setPrava(tmplst.get(4).toString()) ;}else Inkid.setPrava("") ;
			if(tmplst.get(5) != null){Inkid.setSumm((Double)tmplst.get(5)) ;}else Inkid.setSumm(new Double(0));
			if(tmplst.get(6) != null){Inkid.setPrim(tmplst.get(6).toString()) ;}else Inkid.setPrim("") ;
			Inkas ink = new Inkas(Inkid);
			inkList.add(ink);				
		}
		return inkList;	
	}

	public void saveInkas(Inkas selectedInkas) 
	{
		is = new InkasService();
		is.saveInk(selectedInkas);
		is = null;
	}

	public String getInkName(String datakey)
	{
		is = new InkasService();
		String iName = is.getInkName(datakey);
		is = null;
		return iName;
	}

	public void delInkas(String datakey) 
	{
		is = new InkasService();
		is.delInk(datakey);
		is = null;
	}

	public Integer saveAsMaster(String datakey) 
	{
		
		StringBuffer decDataKeySB = new StringBuffer();
		for(int i = 0, j = 4; i < 16; i+= 4, j+=4)
		{
			String st = datakey.substring(i+2, j)+datakey.substring(i, j-2);// Берем 4 знака ключа и попарно меняем местами(по принципу младший байт первый)			
			Integer integ = Integer.parseInt(st, 16);
			if(integ.toString().length()<5)
				for(int l = integ.toString().length();l<5;l++)
					decDataKeySB.append("0");
			decDataKeySB.append(integ.toString());
		}
		String decKey = new String(decDataKeySB);
		//decDataKeySB = null;
		System.out.println(decKey);
		
		is = new InkasService();
		List <List> rezhList = is.getAvtoRezh();
		if(rezhList != null && !rezhList.isEmpty())
		{
			for(List fm: rezhList)
			{
				if(decDataKeySB.capacity()>0) decDataKeySB.setLength(0);
				decDataKeySB.append(fm.get(1).toString().substring(0, 125));
				decDataKeySB.append(decKey);
				decDataKeySB.append(fm.get(1).toString().substring(145));
				is.saveMasterKey((Integer)fm.get(0),new String(decDataKeySB));
			}
			is = null;
			return 1;
		}
		else return null;		
	};
}
