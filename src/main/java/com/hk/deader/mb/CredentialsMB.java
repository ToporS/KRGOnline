package com.hk.deader.mb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.hk.deader.domains.Operator;
import com.hk.deader.domains.Polzov;
import com.hk.deader.service.OperatorService;
import com.hk.deader.utils.MD5;

import javax.faces.context.FacesContext;

@ManagedBean(name="login")
@SessionScoped
public class CredentialsMB implements Serializable {
	
	private static final long serialVersionUID = 5448402075009335980L;
	
	

	public CredentialsMB()
	{
		this.setLocale(FacesContext.getCurrentInstance().getExternalContext().getRequestLocale());
		Calendar calend = Calendar.getInstance(locale);
		this.setTimeZone(calend.getTimeZone());
				
	}
	
	private String msg;	
	private String news;
	private String operatorid;
	private String operpass;
	private String polzid;
	private String polzpass;
	private String role;
	private String uname;
	private boolean msgShow = false; 
	private boolean loggedIn = false;
	private Boolean godMode;
	private Locale locale;
	private TimeZone timeZone;
	private Boolean expandShow;
	private Boolean provider;
	private Boolean fullVersion;
	private MD5 md5 = new MD5();
	
	public Boolean getExpandShow() {
		if(this.getRole().equalsIgnoreCase("Администратор") || this.getRole().equalsIgnoreCase("провайдер") || this.getOpername().equalsIgnoreCase("Провайдер")) expandShow=true; else expandShow = false;
		return expandShow;
	}
	public void setExpandShow(Boolean expandShow) {
		this.expandShow = expandShow;
	}
	
	public Boolean getGodMode()
	{
		if(this.getPolzid().equalsIgnoreCase("Vedler3"))godMode = true; else godMode = false;
		return godMode;		
	}
	
	public Boolean getProvider() {
		if(this.getOpername().equalsIgnoreCase("Провайдер")) provider = true; else provider = false;
		return provider;
	}
	public void setProvider(Boolean provider) {
		this.provider = provider;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isMsgShow() {
		return msgShow;
	}
	public void setMsgShow(boolean msgShow) {
		this.msgShow = msgShow;
	}
	public String getOperatorid() {
		return operatorid;
	}
	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}
	public String getOperpass() {
		return operpass;
	}
	public void setOperpass(String operpass) {
		this.operpass = operpass;
	}
	public String getPolzid() {
		return polzid;
	}
	public void setPolzid(String polzid) {
		this.polzid = polzid;
	}
	public String getPolzpass() {
		return polzpass;
	}
	public void setPolzpass(String polzpass) {
		this.polzpass = polzpass;
	}	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}	
	public String getNews()
	{
		return news;
	}
	public void setNews(String news)
	{
		this.news = news;
	}
	public String getOpername() {
		return "";
	}
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public TimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}
		
	public String chkLogin2()
	{
		OperatorService os = new OperatorService();	
		String hash = new MD5().getHash(polzpass);
		String hashedpass = hash.substring(0, 15);
		Polzov polz = os.getPolz(polzid, hashedpass);
		if (polz == null)
		{
			this.setMsg("Вы ввели неверные данные");
			return "login"+"?faces-redirect=true";
		}
		this.setPolzid(polz.getUserid());
		this.setRole(polz.getArm());
		this.setUname(polz.getUsername());
		this.setLoggedIn(true);
		return "/pages/avtomats"+"?faces-redirect=true";
	}
	public String logOut()
	{
		try{
			this.setLoggedIn(false);
			HttpServletRequest hr = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			if (hr.getSession() != null)hr.getSession().invalidate();			
			return ("/index.jsp"+"?faces-redirect=true");
		}
		catch (Exception ex){ex.printStackTrace();return ("/index.jsp"+"?faces-redirect=true");}		
	}

	public Boolean getFullVersion() {
		return fullVersion;
	}
	public void setFullVersion(Boolean fullVersion) {
		this.fullVersion = fullVersion;
	}
}
