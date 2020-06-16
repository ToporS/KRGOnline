package com.hk.deader.mb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import com.hk.deader.dao.AvtomatDAO;
import com.hk.deader.domains.Avtomat;
import com.hk.deader.domains.Operator;
import com.hk.deader.domains.SensorSettings;
import com.hk.deader.service.OperatorService;
import com.hk.deader.utils.AnalysysUse;
@ManagedBean(name="provider")
@ViewScoped
public class ProviderMB implements Serializable {

	private static final long serialVersionUID = -7213884415189673017L;
	@ManagedProperty(value="#{login}")
	CredentialsMB CMB;
	private Integer avtonum;
	private SensorSettings senSet;
	private AvtomatDAO adao;
	private List<String> operList = new ArrayList<String>();
	private List<AnalysysUse> anList = new ArrayList<AnalysysUse>();
	private String autoType;
	private String typeCode = null;
	private Operator selectedOperator;
	private Boolean showOperTable = false;
	private Operator newOper = new Operator();
	private Integer param;
	private Integer monthNum;
	private Integer yearNum;
	private Boolean renderatable = false;
	private Properties props;
	private List<Long> drebinst = new ArrayList<Long>();
	private List<Long> restinst = new ArrayList<Long>();
	private List<String> urginst = new ArrayList<String>();
	private List<String> coninst = new ArrayList<String>();
	private List<String> appType = new ArrayList<String>();	
	private String selectedType;
	private String newsText;
	
	public ProviderMB()
	{
		props = new Properties();
		try
		{
			props.load(new FileInputStream("D:\\KRG_Touch\\properts.properties"));
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		urginst.add("Срочно");
		urginst.add("Не срочно");
		coninst.add("Замыкание");
		coninst.add("Размыкание");
		drebinst.add((long) 10);
		drebinst.add((long) 50);
		drebinst.add((long) 100);
		drebinst.add((long) 500);
		drebinst.add((long) 1000);
		drebinst.add((long) 5000);
		drebinst.add((long) 10000);
		drebinst.add((long) 30000);
		drebinst.add((long) 60000);
		restinst.add((long) 10);
		restinst.add((long) 50);
		restinst.add((long) 100);
		restinst.add((long) 500);
		restinst.add((long) 1000);
		restinst.add((long) 5000);
		restinst.add((long) 10000);
		restinst.add((long) 21600);
		restinst.add((long) 30000);
		restinst.add((long) 60000);
		appType.add("ИЧВ");
		appType.add("Киоск");
		appType.add("Колонка");
		appType.add("Бутыль");
		
	}
	
	public void addAvtomat()
	{
		adao = new AvtomatDAO();
		String oName = adao.checkAvto(avtonum);
		if(typeCode == null || typeCode.isEmpty())
		{
			adao=null;
			FacesContext context = FacesContext.getCurrentInstance();  	          
	        context.addMessage(null, new FacesMessage("Ошибка", "Выберите тип аппарата!"));
		}else
		if(oName!=null)
		{
			adao=null;
			FacesContext context = FacesContext.getCurrentInstance();  	          
	        context.addMessage(null, new FacesMessage("Ошибка", "Аппарат уже зарегестрирован на пользователя "+oName));
		}
		else
		{
			Avtomat newAvto = new Avtomat();
			newAvto.setAdr("");
			newAvto.setCena(new Float(2));
			newAvto.setCenabut(new Double(10));
			newAvto.setDat(new Date());
			newAvto.setFlag((short) 6);
			newAvto.setNovodadat(new Date());
			newAvto.setNum(avtonum);
			newAvto.setOperator("1");
			newAvto.setPitdat(new Date());
			newAvto.setPo(0);
			newAvto.setPrim("");
			newAvto.setRezdat(new Date());
			newAvto.setRezerv(new Double(0));
			newAvto.setStat("");
			newAvto.setTel("");
			newAvto.setTemdat(new Date());
			newAvto.setTrevdat(new Date());
			newAvto.setVoda(new Double(40));
			newAvto.setVodadat(new Date());
			newAvto.setZasordat(new Date());
			switch (typeCode)
			{
				case "ИЧВ" : newAvto.setRezh(props.getProperty("ichv")); break;
				case "Киоск" : newAvto.setRezh(props.getProperty("kiosk"));break;
				case "Бутыль" : newAvto.setRezh(props.getProperty("butil"));break;
				case "Колонка" : newAvto.setRezh(props.getProperty("kolonka"));break;
			}
			adao.saveAvto(newAvto);
			FacesContext context = FacesContext.getCurrentInstance();  	          
	        context.addMessage(null, new FacesMessage("Успешно", "Аппарат "+avtonum.toString()+ " зарегестрирован "));
		}
	}
	
	public void delAvtomat()
	{
		adao = new AvtomatDAO();
		adao.delAvto(avtonum);
		adao=null;
		FacesContext context = FacesContext.getCurrentInstance();  	          
        context.addMessage(null, new FacesMessage("Успешно", "Аппарат "+avtonum+" удален"));
	}
	
	public void editAvtomat()
	{
		adao = new AvtomatDAO();
		adao.editAvto(avtonum);
		adao=null;
		FacesContext context = FacesContext.getCurrentInstance();  	          
        context.addMessage(null, new FacesMessage("Успешно", "Свойства аппарата отредактированы "));
	}

	
	public Integer getAvtonum() {
		return avtonum;
	}
	public void setAvtonum(Integer avtonum) {
		this.avtonum = avtonum;
	}

	public String getAutoType() {
		return autoType;
	}

	public void setAutoType(String autoType) {
		this.autoType = autoType;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	public Operator getSelectedOperator() {
		return selectedOperator;
	}

	public void setSelectedOperator(Operator selectedOperator) {
		this.selectedOperator = selectedOperator;
	}

	public Boolean getShowOperTable() {
		return showOperTable;
	}

	public void setShowOperTable(Boolean showOperTable) {
		this.showOperTable = showOperTable;
	}

	public Operator getNewOper() {
		return newOper;
	}

	public void setNewOper(Operator newOper) {
		this.newOper = newOper;
	}

	public Integer getParam() {
		return param;
	}

	public void setParam(Integer param) {
		this.param = param;
	}

	public Integer getMonthNum() {
		return monthNum;
	}

	public void setMonthNum(Integer monthNum) {
		this.monthNum = monthNum;
	}

	public Integer getYearNum() {
		return yearNum;
	}

	public void setYearNum(Integer yearNum) {
		this.yearNum = yearNum;
	}

	public Boolean getRenderatable() {
		return renderatable;
	}

	public void setRenderatable(Boolean renderatable) {
		this.renderatable = renderatable;
	}

	public List<AnalysysUse> getAnList() {
		return anList;
	}

	public void setAnList(List<AnalysysUse> anList) {
		this.anList = anList;
	}

	public SensorSettings getSenSet() {
		if(senSet == null)
		{
			senSet= new SensorSettings(props.getProperty("ichv"));
		}
		return senSet;
	}

	public void setSenSet(SensorSettings senSet) {
		this.senSet = senSet;
	}

	public List<Long> getDrebinst() {
		return drebinst;
	}

	public void setDrebinst(List<Long> drebinst) {
		this.drebinst = drebinst;
	}

	public List<Long> getRestinst() {
		return restinst;
	}

	public void setRestinst(List<Long> restinst) {
		this.restinst = restinst;
	}

	public List<String> getUrginst() {
		return urginst;
	}

	public void setUrginst(List<String> urginst) {
		this.urginst = urginst;
	}

	public List<String> getConinst() {
		return coninst;
	}

	public void setConinst(List<String> coninst) {
		this.coninst = coninst;
	}

	public List<String> getAppType() {
		return appType;
	}

	public void setAppType(List<String> appType) {
		this.appType = appType;
	}

	public String getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}
	
	public void cblisten(ValueChangeEvent vcEvent)
	{
		String key="";
		System.out.println(vcEvent.getNewValue().toString());
		switch (vcEvent.getNewValue().toString())
		
		{
			case "ИЧВ": key="ichv";break;
			case "Киоск": key="kiosk";break;
			case "Колонка": key="kolonka";break;
			case "Бутыль": key="butil";break;
		} 
		this.setSenSet(new SensorSettings(props.getProperty(key)));
	}
	
	public void saveSettings()
	{
		Integer base = 108192;
		Integer smsBase = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("0000");
		if(getSenSet().getTankFullCon().equalsIgnoreCase("Размыкание"))base=base+2;
		if(getSenSet().getTrevCon().equalsIgnoreCase("Размыкание"))base=base+4;
		if(getSenSet().getLowWaterCon().equalsIgnoreCase("Размыкание"))base=base+8;
		if(getSenSet().getNoWaterCon().equalsIgnoreCase("Размыкание"))base=base+16;
		if(getSenSet().getMovingCon().equalsIgnoreCase("Размыкание"))base=base+32;
		if(getSenSet().getNoPowerCon().equalsIgnoreCase("Размыкание"))base=base+64;
		if(getSenSet().getTempUrg().equalsIgnoreCase("Срочно"))base=base+256;
		if(getSenSet().getTankFullUrg().equalsIgnoreCase("Срочно"))base=base+512;
		if(getSenSet().getTrevUrg().equalsIgnoreCase("Срочно"))base=base+1024;
		if(getSenSet().getLowWaterUrg().equalsIgnoreCase("Срочно"))base=base+2048;
		if(getSenSet().getNoWaterUrg().equalsIgnoreCase("Срочно"))base=base+4096;
		if(getSenSet().getNoPowerUrg().equalsIgnoreCase("Срочно"))base=base+16384;
		sb.append(base.toString());
		if(getSenSet().getSmsLowWater())smsBase=smsBase+8;
		if(getSenSet().getSmsNoWater())smsBase=smsBase+16;
		if(getSenSet().getSmsNoConn())smsBase=smsBase+32;
		if(getSenSet().getSmsnoPow())smsBase=smsBase+64;
		if(getSenSet().getSmsTankFull())smsBase=smsBase+2;
		if(getSenSet().getSmsTemp())smsBase=smsBase+1;
		if(getSenSet().getSmsTrev())smsBase=smsBase+4;
		if(getSenSet().getSmsZasor())smsBase=smsBase+128;
		int i;
		for(i=getSenSet().getTempDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getTempDreb().toString());
		for(i=getSenSet().getTankFullDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getTankFullDreb().toString());
		for(i=getSenSet().getTrevDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getTrevDreb().toString());
		for(i=getSenSet().getLowWaterDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getLowWaterDreb().toString());
		for(i=getSenSet().getNoWaterDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getNoWaterDreb().toString());
		for(i=getSenSet().getMovingDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getMovingDreb().toString());
		for(i=getSenSet().getNoPowerDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getNoPowerDreb().toString());
		for(i=getSenSet().getTempRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getTempRest().toString());
		for(i=getSenSet().getTankFullRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getTankFullRest().toString());
		for(i=getSenSet().getTrevRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getTrevRest().toString());
		for(i=getSenSet().getLowWaterRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getLowWaterRest().toString());
		for(i=getSenSet().getNoWaterRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getNoWaterRest().toString());
		for(i=getSenSet().getMovingRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getMovingRest().toString());
		for(i=getSenSet().getNoPowerRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getNoPowerRest().toString());
		for(i=getSenSet().getImpLitr().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getImpLitr().toString());
			sb.append("   10");
		for(i=getSenSet().getMaxImp().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getMaxImp().toString());
		Integer dbl = new Integer((int) (getSenSet().getCenaLitrNal()*100));
		for(i=dbl.toString().length();i<5;i++)
			sb.append(" ");		
		sb.append(dbl.toString());		
		for(i=getSenSet().getKoefMon().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getKoefMon().toString());
		for(i=getSenSet().getKoefKup().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getKoefKup().toString());
		for(i=getSenSet().getTimeOut().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(getSenSet().getTimeOut().toString());
		dbl = new Integer((int) (getSenSet().getCenaButlNal()*100));
		for(i=dbl.toString().length();i<5;i++)
			sb.append(" ");
		sb.append(dbl.toString());
		for(i=smsBase.toString().length();i<5;i++)
			sb.append("0");
		sb.append(smsBase.toString());
		dbl = null;
		
		sb.append("45678901234567834567890123456789012");
		sb.append(getSenSet().getApn());
		//System.out.println(sb);
		String stringa = new String(sb);		
		String key="";
		switch (selectedType)
		{
			case "ИЧВ": key="ichv";break;
			case "Киоск": key="kiosk";break;
			case "Колонка": key="kolonka";break;
			case "Бутыль": key="butil";break;
		} 
		props.setProperty(key, stringa);
		try
		{String fileName ="D:\\KRG_Touch\\properts.properties";
		FileOutputStream fis = new FileOutputStream(new File(fileName));
		//rb = new RB(fis);
		props.store(fis, null);
		//props.save(fis);		
        fis.close();
        
    }catch(Exception e){e.getStackTrace();}		
	}


	public String getNewsText()
	{
		return newsText;
	}


	public void setNewsText(String newsText)
	{
		this.newsText = newsText;
	}

	public CredentialsMB getCMB()
	{
		return CMB;
	}

	public void setCMB(CredentialsMB cMB)
	{
		CMB = cMB;
	}
}
