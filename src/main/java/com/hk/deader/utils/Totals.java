package com.hk.deader.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hk.deader.service.TotalService;

public class Totals implements Serializable
{

	private static final long serialVersionUID = 2930652833073065999L;
	private Double TotalsalesNal;
	private Double TotalsalesBeznal;//
	private Double Totalinkass;//
	private Double TotalSumButNal;
	private Double TotalSumButBezNal;
	private Double Totalspisano;
	private Double Totalpopolneno;//
	private Double Totaltotalmoney;
	private Long TotallitrNal;
	private Long TotallitrBeznal;//
	private Long Totaltotallitr;
	private Double totalKopilka;
	private Long totalButNal;
	private Long totalButBezNal;
	private Long totalBut;
	private Double totalSumBut;
	private TotalService ts;
	private Long Totaltotalbut;
	//private Long TotaltotalSumBut;
	private List<Integer> alist;
	
	public Totals(){}
	
	public Totals(Date from, Date to)
	{
		ts = new TotalService();
		alist = ts.getAList();
		if(alist == null || alist.isEmpty())
		{
			TotalsalesNal = (double) 0;
			TotalsalesBeznal = (double) 0;
			Totalinkass = (double) 0;
			Totalspisano = (double) 0;
			Totalpopolneno = (double) 0;
			Totaltotalmoney = (double) 0;
			Totaltotallitr = (long) 0;
			TotallitrNal = (long) 0;
			TotallitrBeznal = (long) 0;
			totalKopilka = (double)0;
			totalButNal = (long)0;
			totalButBezNal = (long)0;
			totalBut = (long)0;
			totalSumBut = (double)0;
			TotalSumButBezNal = (double)0;
			TotalSumButNal = (double)0;
			Totaltotalbut = (long) 0;
		}else
		{
			TotalsalesNal = ts.getSalesNal(alist,from,to);
			TotalsalesBeznal = ts.getSalesBeznal(alist,from,to);
			Totalinkass = ts.getTotalInkas(alist,from,to);
			Totalspisano = ts.getTotalSpisano(alist,from,to);
			Totalpopolneno = ts.getTotalPop(alist,from,to);
			TotallitrNal = ts.getTotalLitrNal(alist,from,to);
			TotallitrBeznal = ts.getTotalLitrBeznal(alist,from,to);
			totalKopilka = ts.getTotalKopilka(alist);
			totalButBezNal = ts.getTotalButBezNal(alist,from,to);
			totalButNal = ts.getTotalButNal(alist,from,to);
			Totaltotalbut = totalButNal + totalButBezNal;
			Totaltotalmoney = TotalsalesNal + TotalsalesBeznal;
			Totaltotallitr = TotallitrBeznal + TotallitrNal;
			
		}
	}
	
	public Totals(String operatorName, Date fromDate, Date toDate, String string) 
	{
		ts = new TotalService();
		alist = ts.getAList();
		if(alist == null || alist.isEmpty())
		{
			TotalsalesNal = (double) 0;
			TotalsalesBeznal = (double) 0;
			Totalinkass = (double) 0;
			Totalspisano = (double) 0;
			Totalpopolneno = (double) 0;
			Totaltotalmoney = (double) 0;
			Totaltotallitr = (long) 0;
			TotallitrNal = (long) 0;
			TotallitrBeznal = (long) 0;
			totalKopilka = (double)0;
			totalButNal = (long)0;
			totalButBezNal = (long)0;
			totalBut = (long)0;
			totalSumBut = (double)0;
			TotalSumButBezNal = (double)0;
			TotalSumButNal = (double)0;
			Totaltotalbut = (long) 0;
		}/*else
		{
			TotalsalesNal = ts.getArchiveSalesNal(alist,fromDate,toDate);
			TotalsalesBeznal = ts.getArchiveSalesBeznal(alist,fromDate,toDate);
			Totalinkass = ts.getArchiveTotalInkas(alist,fromDate,toDate);
			Totalspisano = ts.getArchiveTotalSpisano(alist,fromDate,toDate);
			Totalpopolneno = ts.getArchiveTotalPop(alist,fromDate,toDate);
			TotallitrNal = ts.getArchiveTotalLitrNal(alist,fromDate,toDate);
			TotallitrBeznal = ts.getArchiveTotalLitrBeznal(alist,fromDate,toDate);
			totalKopilka = ts.getTotalKopilka(alist);
			totalButBezNal = ts.getArchiveTotalButBezNal(alist,fromDate,toDate);
			totalButNal = ts.getArchiveTotalButNal(alist,fromDate,toDate);
			Totaltotalbut = totalButNal + totalButBezNal;
			Totaltotalmoney = TotalsalesNal + TotalsalesBeznal;
			Totaltotallitr = TotallitrBeznal + TotallitrNal;
			
		}*/
	}

	public Double getTotalsalesNal() {
		return TotalsalesNal;
	}
	
	public void setTotalsalesNal(Double totalsalesNal) {
		TotalsalesNal = totalsalesNal;
	}
	
	public Double getTotalsalesBeznal() {
		return TotalsalesBeznal;
	}
	
	public void setTotalsalesBeznal(Double totalsalesBeznal) {
		TotalsalesBeznal = totalsalesBeznal;
	}
	
	public Double getTotalinkass() {
		return Totalinkass;
	}
	
	public void setTotalinkass(Double totalinkass) {
		Totalinkass = totalinkass;
	}
	
	public Double getTotalspisano() {
		return Totalspisano;
	}
	
	public void setTotalspisano(Double totalspisano) {
		Totalspisano = totalspisano;
	}
	
	public Double getTotalpopolneno() {
		return Totalpopolneno;
	}
	
	public void setTotalpopolneno(Double totalpopolneno) {
		Totalpopolneno = totalpopolneno;
	}
	
	public Double getTotaltotalmoney() {
		return Totaltotalmoney;
	}
	
	public void setTotaltotalmoney(Double totaltotalmoney) {
		Totaltotalmoney = totaltotalmoney;
	}
	
	public Long getTotallitrNal() {
		return TotallitrNal;
	}
	
	public void setTotallitrNal(Long totallitrNal) {
		TotallitrNal = totallitrNal;
	}
	
	public Long getTotallitrBeznal() {
		return TotallitrBeznal;
	}
	
	public void setTotallitrBeznal(Long totallitrBeznal) {
		TotallitrBeznal = totallitrBeznal;
	}
	
	public Long getTotaltotallitr() {
		return Totaltotallitr;
	}
	
	public void setTotaltotallitr(Long totaltotallitr) {
		Totaltotallitr = totaltotallitr;
	}
		
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getTotalKopilka() {
		return totalKopilka;
	}

	public void setTotalKopilka(Double totalKopilka) {
		this.totalKopilka = totalKopilka;
	}

	public Double getTotalSumButNal() {
		return TotalSumButNal;
	}

	public void setTotalSumButNal(Double totalSumButNal) {
		TotalSumButNal = totalSumButNal;
	}

	public Double getTotalSumButBezNal() {
		return TotalSumButBezNal;
	}

	public void setTotalSumButBezNal(Double totalSumButBezNal) {
		TotalSumButBezNal = totalSumButBezNal;
	}

	public Long getTotalButNal() {
		return totalButNal;
	}

	public void setTotalButNal(Long totalButNal) {
		this.totalButNal = totalButNal;
	}

	public Long getTotalButBezNal() {
		return totalButBezNal;
	}

	public void setTotalButBezNal(Long totalButBezNal) {
		this.totalButBezNal = totalButBezNal;
	}

	public Long getTotalBut() {
		return totalBut;
	}

	public void setTotalBut(Long totalBut) {
		this.totalBut = totalBut;
	}

	public Double getTotalSumBut() {
		return totalSumBut;
	}

	public void setTotalSumBut(Double totalSumBut) {
		this.totalSumBut = totalSumBut;
	}

	public Long getTotaltotalbut() {
		return Totaltotalbut;
	}

	public void setTotaltotalbut(Long totaltotalbut) {
		Totaltotalbut = totaltotalbut;
	}

	/*public Long getTotaltotalSumBut() {
		return TotaltotalSumBut;
	}

	public void setTotaltotalSumBut(Long totaltotalSumBut) {
		TotaltotalSumBut = totaltotalSumBut;
	}*/
}
