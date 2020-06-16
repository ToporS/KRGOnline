package com.hk.deader.domains;

import java.io.Serializable;

import com.hk.deader.dao.AvtomatDAO;
import com.hk.deader.service.AvtomatService;

public class SensorSettings implements Serializable {
	
	private static final long serialVersionUID = 1218804946797392712L;
	private final Integer base = 108192;
	private String decKey;
	private Long trevDreb;
	private Long trevRest;
	private Long lowWaterDreb;
	private Long lowWaterRest;
	private Long noWaterDreb;
	private Long noWaterRest;
	private Long noPowerDreb;
	private Long noPowerRest;
	private Long tempDreb;
	private Long tempRest;
	private Long zasorDreb;
	private Long zasorRest;
	private Long tankFullDreb;
	private Long tankFullRest;
	private Long movingDreb;
	private Long movingRest;
	private Boolean trevAct = false;
	private Boolean lowWaterAct = false;
	private Boolean noWaterAct = false;
	private Boolean noPowerAct = false;
	private Boolean tempAct = false;
	private Boolean zasorAct = false;
	private Boolean tankFullAct = false;
	private Boolean movinAct = false;
	private Boolean poUpdate = false;
	private Boolean paramSend = false;
	private String apn;
	private Integer impLitr;
	private Integer maxImp;
	private Double kopilka;
	private Integer po;
	private Integer rezh;
	private Boolean trevCon = true;
	private Boolean trevUrg = false;
	private Boolean lowWaterCon = true;
	private Boolean lowWaterUrg = false;
	private Boolean noWaterCon = true;
	private Boolean noWaterUrg = false;
	private Boolean noPowerCon = true;
	private Boolean noPowerUrg = false;
	private Boolean tankFullCon = true;
	private Boolean tankFullUrg = false;
	private Boolean movingCon = true;
	private Boolean tempUrg = false;
	private Double cenaLitrNal;
	private Double cenaButlNal;
	private Integer koefMon;
	private Integer koefKup;
	private Integer timeOut;
	private Boolean smsTrev = false;
	private Boolean smsLowWater = false;
	private Boolean smsNoWater = false;
	private Boolean smsnoPow = false;
	private Boolean smsTemp = false;
	private Boolean smsZasor = false;
	private Boolean smsNoConn = false;
	private Boolean smsTankFull = false;
	
public SensorSettings(){}
	
	public SensorSettings(String str, short flag, Integer pof, int avtonum)
	{
		AvtomatDAO adao = new AvtomatDAO();
		short arr[] = {2048,1024,512,128,64,32,16,8,4,2};
		Integer conUrg[] = {16384,4096,2048,1024,512,256,64,32,16,8,4,2};
		Integer ssmmss[] = {128,64,32,16,8,4,2,1};
		String tmpStr;
		tempDreb=Long.parseLong(str.substring(10,15).trim());
		tankFullDreb=Long.parseLong(str.substring(15,20).trim());
		trevDreb=Long.parseLong(str.substring(20,25).trim());
		lowWaterDreb=Long.parseLong(str.substring(25,30).trim());
		noWaterDreb=Long.parseLong(str.substring(30,35).trim());
		movingDreb=Long.parseLong(str.substring(35,40).trim());
		noPowerDreb=Long.parseLong(str.substring(40,45).trim());
		tempRest=Long.parseLong(str.substring(45,50).trim());
		tankFullRest=Long.parseLong(str.substring(50,55).trim());
		trevRest=Long.parseLong(str.substring(55,60).trim());
		lowWaterRest=Long.parseLong(str.substring(60,65).trim());
		noWaterRest=Long.parseLong(str.substring(65,70).trim());
		movingRest=Long.parseLong(str.substring(70,75).trim());
		noPowerRest=Long.parseLong(str.substring(75,80).trim());
		impLitr=Integer.parseInt(str.substring(80,85).trim());
		maxImp = Integer.parseInt(str.substring(90,95).trim());
		cenaLitrNal = Double.parseDouble(str.substring(95,100).trim())/100;
		koefMon = Integer.parseInt(str.substring(100,105).trim());
		koefKup = Integer.parseInt(str.substring(105,110).trim());
		timeOut = Integer.parseInt(str.substring(110,115).trim());
		cenaButlNal = Double.parseDouble(str.substring(115,120).trim())/100;
		decKey = str.substring(125, 145).trim();
		apn = str.substring(160).trim();
		Integer smss = Integer.parseInt(str.substring(120, 125).trim());
		int i;
		for(i=0;i<8;i++)
		{
			//System.out.println(i);
			if(smss >= ssmmss[i] )
			{
				smss=smss-ssmmss[i];
				switch (ssmmss[i])
				{
					case 128 : smsZasor=true;break;
					case 64 : smsnoPow=true;break;
					case 32 : smsNoConn=true;break;
					case 16 : smsNoWater=true;break;
					case 8 : smsLowWater=true;break;
					case 4 : smsTrev = true;break;
					case 2 : smsTankFull = true;break;
					case 1 : smsTemp = true;break;
				}
			}
		}
	/*	for(i=0;i<10;i++)
		{
			//System.out.println(i);
			if(flag >= arr[i] )
			{
				flag=(short) (flag-arr[i]);
				switch (arr[i])
				{
					case 2048 : zasorAct=true; break;
					case 1024 : noPowerAct=true;break;     zasorAct=true; break;
					case 512: break;noPowerAct=true;break;
					case 128 : noWaterAct=true;break;
					case 64 : lowWaterAct=true;break;
					case 32 : trevAct=true;break;
					case 16 : tankFullAct=true;break;
					case 8 : tempAct=true;break;
					case 4 : poUpdate = true;/*remPOUpdate(avtonum)*///;break;
		/*			case 2 : paramSend = true;break;
				}
			}
		}*/
		if((flag & 2) == 2) paramSend = true; else paramSend = false;
		if((flag & 4) == 4) poUpdate = true; else poUpdate = false;
		if((flag & 8) == 8) tempAct = true; else tempAct = false;
		if((flag & 16) == 16) tankFullAct = true; else tankFullAct = false;
		if((flag & 32) == 32) trevAct = true; else trevAct = false;
		if((flag & 64) == 64) lowWaterAct = true; else lowWaterAct = false;
		if((flag & 128) == 128) noWaterAct = true; else noWaterAct = false;
	//	if((flag & 256) == 256) tempAct = true; else tempAct = false;
		if((flag & 512) == 512) noPowerAct = true; else noPowerAct = false;
		if((flag & 1024) == 1024) zasorAct = true; else zasorAct = false;
		po = pof%100;
		kopilka =(double)(pof - po)/10000;
		rezh = Integer.parseInt(str.substring(4,10)) - base;
		for( i=0;i<12;i++)
		{
			if(rezh >= conUrg[i])
			{
				rezh-=conUrg[i];
				switch(conUrg[i])
				{
				case 16384:noPowerUrg = true;break;
				case 4096: noWaterUrg = true;break;
				case 2048: lowWaterUrg = true;break;
				case 1024: trevUrg = true;break;
				case 512: tankFullUrg = true;break;
				case 256: tempUrg = true;break;
				case 64: noPowerCon = false;break;
				case 32: movingCon = false;break;
				case 16: noWaterCon = false;break;
				case 8: lowWaterCon = false;break;
				case 4: trevCon = false;break;
				case 2:tankFullCon = false;break;
				}
			}
		}
	}
	
	private void remPOUpdate(int avtonum) 
	{
		// TODO Auto-generated method stub
		AvtomatService as = new AvtomatService();
		as.remUpdt(avtonum);
		as = null;
		
	}

	public SensorSettings(String str)
	{
		Integer conUrg[] = {16384,4096,2048,1024,512,256,64,32,16,8,4,2};
		Integer ssmmss[] = {128,64,32,16,8,4,2,1};
		String tmpStr;
		tempDreb=Long.parseLong(str.substring(10,15).trim());
		tankFullDreb=Long.parseLong(str.substring(15,20).trim());
		trevDreb=Long.parseLong(str.substring(20,25).trim());
		lowWaterDreb=Long.parseLong(str.substring(25,30).trim());
		noWaterDreb=Long.parseLong(str.substring(30,35).trim());
		movingDreb=Long.parseLong(str.substring(35,40).trim());
		noPowerDreb=Long.parseLong(str.substring(40,45).trim());
		tempRest=Long.parseLong(str.substring(45,50).trim());
		tankFullRest=Long.parseLong(str.substring(50,55).trim());
		trevRest=Long.parseLong(str.substring(55,60).trim());
		lowWaterRest=Long.parseLong(str.substring(60,65).trim());
		noWaterRest=Long.parseLong(str.substring(65,70).trim());
		movingRest=Long.parseLong(str.substring(70,75).trim());
		noPowerRest=Long.parseLong(str.substring(75,80).trim());
		impLitr=Integer.parseInt(str.substring(80,85).trim());
		maxImp = Integer.parseInt(str.substring(90,95).trim());
		cenaLitrNal = Double.parseDouble(str.substring(95,100).trim())/100;
		koefMon = Integer.parseInt(str.substring(100,105).trim());
		koefKup = Integer.parseInt(str.substring(105,110).trim());
		timeOut = Integer.parseInt(str.substring(110,115).trim());
		cenaButlNal = Double.parseDouble(str.substring(115,120).trim())/100;
		decKey = str.substring(125, 145).trim();
		apn = str.substring(160).trim();
		Integer smss = Integer.parseInt(str.substring(120, 125).trim());
		int i;
		for(i=0;i<8;i++)
		{
			//System.out.println(i);
			if(smss >= ssmmss[i] )
			{
				smss=smss-ssmmss[i];
				switch (ssmmss[i])
				{
					case 128 : smsZasor=true;break;
					case 64 : smsnoPow=true;break;
					case 32 : smsNoConn=true;break;
					case 16 : smsNoWater=true;break;
					case 8 : smsLowWater=true;break;
					case 4 : smsTrev = true;break;
					case 2 : smsTankFull = true;break;
					case 1 : smsTemp = true;break;
				}
			}
		}		
		
		rezh = Integer.parseInt(str.substring(4,10)) - base;
		for( i=0;i<12;i++)
		{
			if(rezh >= conUrg[i])
			{
				rezh-=conUrg[i];
				switch(conUrg[i])
				{
				case 16384:noPowerUrg = true;break;
				case 4096: noWaterUrg = true;break;
				case 2048: lowWaterUrg = true;break;
				case 1024: trevUrg = true;break;
				case 512: tankFullUrg = true;break;
				case 256: tempUrg = true;break;
				case 64: noPowerCon = false;break;
				case 32: movingCon = false;break;
				case 16: noWaterCon = false;break;
				case 8: lowWaterCon = false;break;
				case 4: trevCon = false;break;
				case 2:tankFullCon = false;break;
				}
			}
		}
	}
	
	public String getTrevCon() {
		return trevCon?"Замыкание":"Размыкание";
	}

	public void setTrevCon(String trevCon) {
		if(trevCon.equalsIgnoreCase("Замыкание"))this.trevCon = true; else this.trevCon = false;
	}

	public String getTrevUrg() {
		return trevUrg?"Срочно":"Не срочно";
	}

	public void setTrevUrg(String trevUrg) {
		this.trevUrg = trevUrg.equalsIgnoreCase("Срочно")?true:false;
	}

	public String getLowWaterCon() {
		return lowWaterCon?"Замыкание":"Размыкание";
	}

	public void setLowWaterCon(String lowWaterCon) {
		this.lowWaterCon = lowWaterCon.equalsIgnoreCase("Замыкание")?true:false;
	}

	public String getLowWaterUrg() {
		return lowWaterUrg?"Срочно":"Не срочно";
	}

	public void setLowWaterUrg(String lowWaterUrg) {
		this.lowWaterUrg = lowWaterUrg.equalsIgnoreCase("Срочно")?true:false;;
	}

	public String getNoWaterCon() {
		return noWaterCon?"Замыкание":"Размыкание";
	}

	public void setNoWaterCon(String noWaterCon) {
		this.noWaterCon = noWaterCon.equalsIgnoreCase("Замыкание")?true:false;;
	}

	public String getNoWaterUrg() {
		return noWaterUrg?"Срочно":"Не срочно";
	}

	public void setNoWaterUrg(String noWaterUrg) {
		this.noWaterUrg = noWaterUrg.equalsIgnoreCase("Срочно")?true:false;;
	}

	public String getNoPowerCon() {
		return noPowerCon?"Замыкание":"Размыкание";
	}

	public void setNoPowerCon(String noPowerCon) {
		this.noPowerCon = noPowerCon.equalsIgnoreCase("Замыкание")?true:false;;
	}

	public String getNoPowerUrg() {
		return noPowerUrg?"Срочно":"Не срочно";
	}

	public void setNoPowerUrg(String noPowerUrg) {
		this.noPowerUrg = noPowerUrg.equalsIgnoreCase("Срочно")?true:false;;
	}

	public String getTankFullCon() {
		return tankFullCon?"Замыкание":"Размыкание";
	}

	public void setTankFullCon(String tankFullCon) {
		this.tankFullCon = tankFullCon.equalsIgnoreCase("Замыкание")?true:false;;
	}

	public String getTankFullUrg() {
		return tankFullUrg?"Срочно":"Не срочно";
	}

	public void setTankFullUrg(String tankFullUrg) {
		this.tankFullUrg = tankFullUrg.equalsIgnoreCase("Срочно")?true:false;;
	}

	public String getMovingCon() {
		return movingCon?"Замыкание":"Размыкание";
	}

	public void setMovingCon(String movingCon) {
		this.movingCon = movingCon.equalsIgnoreCase("Замыкание")?true:false;;
	}

	public String getTempUrg() {
		return tempUrg?"Срочно":"Не срочно";
	}

	public void setTempUrg(String tempUrg) {
		this.tempUrg = tempUrg.equalsIgnoreCase("Срочно")?true:false;;
	}
	
	public Long getMovingDreb() {
		return movingDreb;
	}

	public void setMovingDreb(Long movingDreb) {
		this.movingDreb = movingDreb;
	}

	public Long getMovingRest() {
		return movingRest;
	}

	public void setMovingRest(Long movingRest) {
		this.movingRest = movingRest;
	}

	public Boolean getMovinAct() {
		return movinAct;
	}

	public void setMovinAct(Boolean movinAct) {
		this.movinAct = movinAct;
	}
	public Long getTrevDreb() {
		return trevDreb;
	}
	public void setTrevDreb(Long trevDreb) {
		this.trevDreb = trevDreb;
	}
	public Long getTrevRest() {
		return trevRest;
	}
	public void setTrevRest(Long trevRest) {
		this.trevRest = trevRest;
	}
	public Long getLowWaterDreb() {
		return lowWaterDreb;
	}
	public void setLowWaterDreb(Long lowWaterDreb) {
		this.lowWaterDreb = lowWaterDreb;
	}
	public Long getLowWaterRest() {
		return lowWaterRest;
	}
	public void setLowWaterRest(Long lowWaterRest) {
		this.lowWaterRest = lowWaterRest;
	}
	public Long getNoWaterDreb() {
		return noWaterDreb;
	}
	public void setNoWaterDreb(Long noWaterDreb) {
		this.noWaterDreb = noWaterDreb;
	}
	public Long getNoWaterRest() {
		return noWaterRest;
	}
	public void setNoWaterRest(Long noWaterRest) {
		this.noWaterRest = noWaterRest;
	}
	public Long getNoPowerDreb() {
		return noPowerDreb;
	}
	public void setNoPowerDreb(Long noPowerDreb) {
		this.noPowerDreb = noPowerDreb;
	}
	public Long getNoPowerRest() {
		return noPowerRest;
	}
	public void setNoPowerRest(Long noPowerRest) {
		this.noPowerRest = noPowerRest;
	}
	public Long getTempDreb() {
		return tempDreb;
	}
	public void setTempDreb(Long tempDreb) {
		this.tempDreb = tempDreb;
	}
	public Long getTempRest() {
		return tempRest;
	}
	public void setTempRest(Long tempRest) {
		this.tempRest = tempRest;
	}
	public Long getZasorDreb() {
		return zasorDreb;
	}
	public void setZasorDreb(Long zasorDreb) {
		this.zasorDreb = zasorDreb;
	}
	public Long getZasorRest() {
		return zasorRest;
	}
	public void setZasorRest(Long zasorRest) {
		this.zasorRest = zasorRest;
	}
	public Long getTankFullDreb() {
		return tankFullDreb;
	}
	public void setTankFullDreb(Long tankFullDreb) {
		this.tankFullDreb = tankFullDreb;
	}
	public Long getTankFullRest() {
		return tankFullRest;
	}
	public void setTankFullRest(Long tankFullRest) {
		this.tankFullRest = tankFullRest;
	}
	public Boolean getTrevAct() {
		return trevAct;
	}
	public void setTrevAct(Boolean trevAct) {
		this.trevAct = trevAct;
	}
	public Boolean getLowWaterAct() {
		return lowWaterAct;
	}
	public void setLowWaterAct(Boolean lowWaterAct) {
		this.lowWaterAct = lowWaterAct;
	}
	public Boolean getNoWaterAct() {
		return noWaterAct;
	}
	public void setNoWaterAct(Boolean noWaterAct) {
		this.noWaterAct = noWaterAct;
	}
	public Boolean getNoPowerAct() {
		return noPowerAct;
	}
	public void setNoPowerAct(Boolean noPowerAct) {
		this.noPowerAct = noPowerAct;
	}
	public Boolean getTempAct() {
		return tempAct;
	}
	public void setTempAct(Boolean tempAct) {
		this.tempAct = tempAct;
	}
	public Boolean getZasorAct() {
		return zasorAct;
	}
	public void setZasorAct(Boolean zasorAct) {
		this.zasorAct = zasorAct;
	}
	public Boolean getTankFullAct() {
		return tankFullAct;
	}
	public void setTankFullAct(Boolean tankFullAct) {
		this.tankFullAct = tankFullAct;
	}

	public String getApn() {
		return apn;
	}

	public void setApn(String apn) {
		this.apn = apn;
	}

	public Integer getImpLitr() {
		return impLitr;
	}

	public void setImpLitr(Integer impLitr) {
		this.impLitr = impLitr;
	}

	public Integer getMaxImp() {
		return maxImp;
	}

	public void setMaxImp(Integer maxImp) {
		this.maxImp = maxImp;
	}

	public Boolean getPoUpdate() {
		return poUpdate;
	}

	public void setPoUpdate(Boolean poUpdate) {
		this.poUpdate = poUpdate;
	}

	public Boolean getParamSend() {
		return paramSend;
	}

	public void setParamSend(Boolean paramSend) {
		this.paramSend = paramSend;
	}

	public Double getKopilka() {
		return kopilka;
	}

	public void setKopilka(Double kopilka) {
		this.kopilka = kopilka;
	}

	public Integer getPo() {
		return po;
	}

	public void setPo(Integer po) {
		this.po = po;
	}

	public Double getCenaLitrNal() {
		return cenaLitrNal;
	}

	public void setCenaLitrNal(Double cenaLitrNal) {
		this.cenaLitrNal = cenaLitrNal;
	}

	public Double getCenaButlNal() {
		return cenaButlNal;
	}

	public void setCenaButlNal(Double cenaButlNal) {
		this.cenaButlNal = cenaButlNal;
	}

	/**
	 * @return the koefMon
	 */
	public Integer getKoefMon() {
		return koefMon;
	}

	/**
	 * @param koefMon the koefMon to set
	 */
	public void setKoefMon(Integer koefMon) {
		this.koefMon = koefMon;
	}

	/**
	 * @return the koefKup
	 */
	public Integer getKoefKup() {
		return koefKup;
	}

	/**
	 * @param koefKup the koefKup to set
	 */
	public void setKoefKup(Integer koefKup) {
		this.koefKup = koefKup;
	}

	/**
	 * @return the timeOut
	 */
	public Integer getTimeOut() {
		return timeOut;
	}

	/**
	 * @param timeOut the timeOut to set
	 */
	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}

	/**
	 * @return the smsTrev
	 */
	public Boolean getSmsTrev() {
		return smsTrev;
	}

	/**
	 * @param smsTrev the smsTrev to set
	 */
	public void setSmsTrev(Boolean smsTrev) {
		this.smsTrev = smsTrev;
	}

	/**
	 * @return the smsLowWater
	 */
	public Boolean getSmsLowWater() {
		return smsLowWater;
	}

	/**
	 * @param smsLowWater the smsLowWater to set
	 */
	public void setSmsLowWater(Boolean smsLowWater) {
		this.smsLowWater = smsLowWater;
	}

	/**
	 * @return the smsNoWater
	 */
	public Boolean getSmsNoWater() {
		return smsNoWater;
	}

	/**
	 * @param smsNoWater the smsNoWater to set
	 */
	public void setSmsNoWater(Boolean smsNoWater) {
		this.smsNoWater = smsNoWater;
	}

	/**
	 * @return the smsnoPow
	 */
	public Boolean getSmsnoPow() {
		return smsnoPow;
	}

	/**
	 * @param smsnoPow the smsnoPow to set
	 */
	public void setSmsnoPow(Boolean smsnoPow) {
		this.smsnoPow = smsnoPow;
	}

	/**
	 * @return the smsTemp
	 */
	public Boolean getSmsTemp() {
		return smsTemp;
	}

	/**
	 * @param smsTemp the smsTemp to set
	 */
	public void setSmsTemp(Boolean smsTemp) {
		this.smsTemp = smsTemp;
	}

	/**
	 * @return the smsZasor
	 */
	public Boolean getSmsZasor() {
		return smsZasor;
	}

	/**
	 * @param smsZasor the smsZasor to set
	 */
	public void setSmsZasor(Boolean smsZasor) {
		this.smsZasor = smsZasor;
	}

	/**
	 * @return the smsNoConn
	 */
	public Boolean getSmsNoConn() {
		return smsNoConn;
	}

	/**
	 * @param smsNoConn the smsNoConn to set
	 */
	public void setSmsNoConn(Boolean smsNoConn) {
		this.smsNoConn = smsNoConn;
	}

	/**
	 * @return the smsTankFull
	 */
	public Boolean getSmsTankFull() {
		return smsTankFull;
	}

	/**
	 * @param smsTankFull the smsTankFull to set
	 */
	public void setSmsTankFull(Boolean smsTankFull) {
		this.smsTankFull = smsTankFull;
	}

	public String getDecKey() {
		return decKey;
	}

	public void setDecKey(String decKey) {
		this.decKey = decKey;
	}

}
