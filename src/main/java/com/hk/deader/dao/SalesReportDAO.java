package com.hk.deader.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.hk.deader.domains.SalesReport;
import com.hk.deader.service.ReportService;

public class SalesReportDAO implements Serializable {

	private static final long serialVersionUID = -7612645630215744025L;
	private SalesReport sr;
	private ReportService rs;
	private List<Integer> avtolist;
	private List<SalesReport> srl;
	private Double ksumadd,nsumadd,ksumsub,nsumsub;
	private Double inkass;
	private Double spisano;
	
	public SalesReportDAO(){}

	public List<SalesReport> getReportList(Date fromDate, Date toDate)
	{
		if (avtolist != null) {avtolist.clear();}
		toDate.setHours(23);
		toDate.setMinutes(59);
		toDate.setSeconds(59);
		rs = new ReportService();
		if (srl != null) srl.clear(); else srl=new ArrayList<SalesReport>(); 
		avtolist = rs.getOperAvtomats();
		if(avtolist == null || avtolist.isEmpty()) return null;
		else
		{
			Iterator<Integer> iter = avtolist.iterator();
			while (iter.hasNext())
			{
				Integer avtnum = iter.next();
				sr = new SalesReport();
				sr.setAvtid(avtnum);
				List tmplst = rs.getSumAddSub(fromDate, toDate, avtnum);
				if(tmplst.get(1) != null) sr.setSumLitrBezNal((Double)tmplst.get(1)); else sr.setSumLitrBezNal(new Double (0)); //Key sales in rub
				if (tmplst.get(0) != null) sr.setPopolneno((Double)tmplst.get(0));else sr.setPopolneno(new Double(0));//key balance loading
				if (tmplst.get(2) != null)sr.setLitrBeznal((Long)tmplst.get(2));else sr.setLitrBeznal(new Long(0));
				if(tmplst.get(3) !=null) sr.setSumButBezNal((Double)tmplst.get(3));else sr.setSumButBezNal(new Double(0));
				if(tmplst.get(4) !=null) sr.setButBezNal(Integer.parseInt(tmplst.get(4).toString()));else sr.setButBezNal(new Integer(0));//butilki po beznalu
				sr.setSalesBeznal(sr.getSumButBezNal()+sr.getSumLitrBezNal());
				tmplst = rs.getSumAddRez(fromDate, toDate, avtnum);
				if (tmplst.get(0) != null)sr.setInkass((Double)tmplst.get(0)); else sr.setInkass(new Double(0));//Inkasazii
				if (tmplst.get(1) != null)sr.setSpisano((Double)tmplst.get(1)); else sr.setSpisano(new Double(0));//Spisano
				List inttmplst = rs.getNalSales(fromDate, toDate, avtnum);
				Long lng;
				if (inttmplst.get(1) == null) lng = new Long(0); else lng = (Long) inttmplst.get(1);
				Double ddd = (double)(lng/100);
				sr.setSumLitrNal(ddd);
				if (inttmplst.get(0) != null)sr.setLitrNal((Long)inttmplst.get(0)); else sr.setLitrNal(new Long(0));
				if(inttmplst.get(2)!=null)sr.setButNal(Integer.parseInt(inttmplst.get(2).toString())); else sr.setButNal(new Integer(0));
				if(inttmplst.get(3)!=null)sr.setSumButNal(Double.valueOf(inttmplst.get(3).toString())/100); else sr.setSumButNal(new Double(0));
				sr.setSalesNal(sr.getSumButNal()+sr.getSumLitrNal());
				sr.setTotallitr(sr.getLitrNal() + sr.getLitrBeznal());
				sr.setTotalmoney(sr.getSalesBeznal() + sr.getSalesNal());
				sr.setTotalbut(new Long(sr.getButBezNal()+sr.getButNal()));
				sr.setAdres(rs.getAddr(avtnum));
				sr.setOpername("");
				srl.add(sr);
			}
		}
			rs=null;
		return srl;
	}
}
