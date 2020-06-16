package com.hk.deader.domains;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

import com.hk.deader.service.AvtomatService;

public class AvtomatWrapper implements Serializable{

	private static final long serialVersionUID = -4579483622179444576L;
	private SensorSettings senSet;
	private Avtomat avtomat;
	private AvtomatService as;
	private String lastSession;
	private Boolean timePass;
	private Long diff;
	
	public AvtomatWrapper()	{}
	
	public AvtomatWrapper(Integer id)
	{
		as = new AvtomatService();
		avtomat = as.getById(id);
		as = null;
		if(avtomat != null)
		{
		senSet = new SensorSettings(avtomat.getRezh(),avtomat.getFlag(),avtomat.getPo(),avtomat.getNum());
		GregorianCalendar curDate = new GregorianCalendar();
		GregorianCalendar chkDate = new GregorianCalendar();
		chkDate.set(avtomat.getDat().getYear(),avtomat.getDat().getMonth(), avtomat.getDat().getDate(), avtomat.getDat().getHours(),avtomat.getDat().getMinutes(),avtomat.getDat().getSeconds());
		Date time = new Date();	
		curDate.set(time.getYear(),time.getMonth(),time.getDate(), time.getHours(), time.getMinutes(),time.getSeconds());
		diff =curDate.getTimeInMillis() - chkDate.getTimeInMillis();
		if(diff<=900000)setTimePass(false);else setTimePass(true);
		if(diff<3600000){int mins = Math.round(diff/60000);setLastSession(mins+" минут назад");}
		if(diff>3600000 && diff < 86400000)setLastSession("Больше часа назад");
		if(diff>86400000)setLastSession("Больше суток назад");
		}
	}
	
	public SensorSettings getSenSet() {
		return senSet;
	}
	public void setSenSet(SensorSettings senSet) {
		this.senSet = senSet;
	}
	public Avtomat getAvtomat() {
		return avtomat;
	}
	public void setAvtomat(Avtomat avtomat) {
		this.avtomat = avtomat;
	}

	public String getLastSession() {
		return lastSession;
	}

	public void setLastSession(String lastSession) {
		this.lastSession = lastSession;
	}

	public Boolean getTimePass() {
		return timePass;
	}

	public void setTimePass(Boolean timePass) {
		this.timePass = timePass;
	}
}
