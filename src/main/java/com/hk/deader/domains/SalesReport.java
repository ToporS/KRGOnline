package com.hk.deader.domains;

import java.io.Serializable;

import org.primefaces.model.chart.CartesianChartModel;

public class SalesReport implements Serializable {

	private static final long serialVersionUID = 2263493570962633630L;
	private Integer avtid;//
	private String adres;
	private Double salesNal;
	private Double salesBeznal;//
	private Double inkass;//
	private Double spisano;
	private Double popolneno;//
	private Double totalmoney;
	private Long litrNal;
	private Long litrBeznal;//
	private Long totallitr;
	private Long totalbut;
	private String opername;
	private Integer butNal;
	private Integer butBezNal;
	private Double sumButNal;
	private Double sumButBezNal;
	private Double sumLitrNal;
	private Double sumLitrBezNal;
	//private CartesianChartModel ccm;	
	
	public Double getSumLitrNal() {
		return sumLitrNal;
	}

	public void setSumLitrNal(Double sumLitrNal) {
		this.sumLitrNal = sumLitrNal;
	}

	public Double getSumLitrBezNal() {
		return sumLitrBezNal;
	}

	public void setSumLitrBezNal(Double sumLitrBezNal) {
		this.sumLitrBezNal = sumLitrBezNal;
	}

	public SalesReport(){}

	public Integer getAvtid() {
		return avtid;
	}
	public void setAvtid(Integer avtid) {
		this.avtid = avtid;
	}
	public Double getSalesNal() {
		return salesNal;
	}
	public void setSalesNal(Double salesNal) {
		this.salesNal = salesNal;
	}
	public Double getSalesBeznal() {
		return salesBeznal;
	}
	public void setSalesBeznal(Double salesBeznal) {
		this.salesBeznal = salesBeznal;
	}
	public Double getInkass() {
		return inkass;
	}
	public void setInkass(Double inkass) {
		this.inkass = inkass;
	}
	public Double getSpisano() {
		return spisano;
	}
	public void setSpisano(Double spisano) {
		this.spisano = spisano;
	}
	public Double getPopolneno() {
		return popolneno;
	}
	public void setPopolneno(Double popolneno) {
		this.popolneno = popolneno;
	}
	public Long getLitrNal() {
		return litrNal;
	}

	public void setLitrNal(Long litrNal) {
		this.litrNal = litrNal;
	}

	public Long getLitrBeznal() {
		return litrBeznal;
	}

	public void setLitrBeznal(Long litrBeznal) {
		this.litrBeznal = litrBeznal;
	}

	public Double getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(Double totalmoney) {
		this.totalmoney = totalmoney;
	}
	public Long getTotallitr() {
		return totallitr;
	}
	public void setTotallitr(Long totallitr) {
		this.totallitr = totallitr;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getOpername() {
		return opername;
	}

	public void setOpername(String opername) {
		this.opername = opername;
	}

	public Integer getButNal() {
		return butNal;
	}

	public void setButNal(Integer butNal) {
		this.butNal = butNal;
	}

	public Integer getButBezNal() {
		return butBezNal;
	}

	public void setButBezNal(Integer butBezNal) {
		this.butBezNal = butBezNal;
	}

	public Double getSumButNal() {
		return sumButNal;
	}

	public void setSumButNal(Double sumButNal) {
		this.sumButNal = sumButNal;
	}

	public Double getSumButBezNal() {
		return sumButBezNal;
	}

	public void setSumButBezNal(Double sumButBezNal) {
		this.sumButBezNal = sumButBezNal;
	}

	public Long getTotalbut() {
		return totalbut;
	}

	public void setTotalbut(Long totalbut) {
		this.totalbut = totalbut;
	}

	/*public CartesianChartModel getCcm() {
		return ccm;
	}

	public void setCcm(CartesianChartModel ccm) {
		this.ccm = ccm;
	}*/
	
}
