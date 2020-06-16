package com.hk.deader.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.model.SortOrder;

import com.hk.deader.domains.Avtomat;
import com.hk.deader.domains.AvtomatWrapper;
import com.hk.deader.domains.Operator;
import com.hk.deader.service.AvtomatService;
import com.hk.deader.service.KeyHistoryService;
import com.hk.deader.utils.AnalysysUse;

public class AvtomatDAO implements Serializable {

	private static final long serialVersionUID = -1951197947109517336L;
	private List<AvtomatWrapper> avtomats = null;
	private AvtomatService as = null; 
	
	public AvtomatDAO(){}

	public void remcheck(Integer num, Long code) 
	{
		as = new AvtomatService();
		short flg = as.getFlg(num);
		flg = (short) (flg - code);
		as.remchk(num,flg);
		as = null;
	}

	public void saveSettings(AvtomatWrapper selectedAvtomat) 
	{
		Integer base = 108192;
		Integer smsBase = 0;
		short flags=selectedAvtomat.getAvtomat().getFlag();
		Boolean oldCheck = checkFlag(flags);
		if(!selectedAvtomat.getSenSet().getParamSend())  flags =(short)(flags | (1<<1)) ;
		//if(!selectedAvtomat.getSenSet().getPoUpdate())  flags = selectedAvtomat.getAvtomat().getFlag(); else {flags = (short)(selectedAvtomat.getAvtomat().getFlag() + 4);}   
		//if(/*!poChk(flags) &&*/ selectedAvtomat.getSenSet().getPoUpdate()) flags = (short) (selectedAvtomat.getAvtomat().getFlag()+4);
		if(oldCheck && !selectedAvtomat.getSenSet().getPoUpdate()) flags =(short) (flags & ~(1<<2));
		if(!oldCheck && selectedAvtomat.getSenSet().getPoUpdate()) flags =(short) (flags |(1<<2));
		StringBuffer sb = new StringBuffer();
		sb.append("0000");
		if(selectedAvtomat.getSenSet().getTankFullCon().equalsIgnoreCase("Размыкание"))base=base+2;
		if(selectedAvtomat.getSenSet().getTrevCon().equalsIgnoreCase("Размыкание"))base=base+4;
		if(selectedAvtomat.getSenSet().getLowWaterCon().equalsIgnoreCase("Размыкание"))base=base+8;
		if(selectedAvtomat.getSenSet().getNoWaterCon().equalsIgnoreCase("Размыкание"))base=base+16;
		if(selectedAvtomat.getSenSet().getMovingCon().equalsIgnoreCase("Размыкание"))base=base+32;
		if(selectedAvtomat.getSenSet().getNoPowerCon().equalsIgnoreCase("Размыкание"))base=base+64;
		if(selectedAvtomat.getSenSet().getTempUrg().equalsIgnoreCase("Срочно"))base=base+256;
		if(selectedAvtomat.getSenSet().getTankFullUrg().equalsIgnoreCase("Срочно"))base=base+512;
		if(selectedAvtomat.getSenSet().getTrevUrg().equalsIgnoreCase("Срочно"))base=base+1024;
		if(selectedAvtomat.getSenSet().getLowWaterUrg().equalsIgnoreCase("Срочно"))base=base+2048;
		if(selectedAvtomat.getSenSet().getNoWaterUrg().equalsIgnoreCase("Срочно"))base=base+4096;
		if(selectedAvtomat.getSenSet().getNoPowerUrg().equalsIgnoreCase("Срочно"))base=base+16384;
		sb.append(base.toString());
		if(selectedAvtomat.getSenSet().getSmsLowWater())smsBase=smsBase+8;
		if(selectedAvtomat.getSenSet().getSmsNoWater())smsBase=smsBase+16;
		if(selectedAvtomat.getSenSet().getSmsNoConn())smsBase=smsBase+32;
		if(selectedAvtomat.getSenSet().getSmsnoPow())smsBase=smsBase+64;
		if(selectedAvtomat.getSenSet().getSmsTankFull())smsBase=smsBase+2;
		if(selectedAvtomat.getSenSet().getSmsTemp())smsBase=smsBase+1;
		if(selectedAvtomat.getSenSet().getSmsTrev())smsBase=smsBase+4;
		if(selectedAvtomat.getSenSet().getSmsZasor())smsBase=smsBase+128;
		int i;
		for(i=selectedAvtomat.getSenSet().getTempDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getTempDreb().toString());
		for(i=selectedAvtomat.getSenSet().getTankFullDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getTankFullDreb().toString());
		for(i=selectedAvtomat.getSenSet().getTrevDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getTrevDreb().toString());
		for(i=selectedAvtomat.getSenSet().getLowWaterDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getLowWaterDreb().toString());
		for(i=selectedAvtomat.getSenSet().getNoWaterDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getNoWaterDreb().toString());
		for(i=selectedAvtomat.getSenSet().getMovingDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getMovingDreb().toString());
		for(i=selectedAvtomat.getSenSet().getNoPowerDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getNoPowerDreb().toString());
		for(i=selectedAvtomat.getSenSet().getTempRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getTempRest().toString());
		for(i=selectedAvtomat.getSenSet().getTankFullRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getTankFullRest().toString());
		for(i=selectedAvtomat.getSenSet().getTrevRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getTrevRest().toString());
		for(i=selectedAvtomat.getSenSet().getLowWaterRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getLowWaterRest().toString());
		for(i=selectedAvtomat.getSenSet().getNoWaterRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getNoWaterRest().toString());
		for(i=selectedAvtomat.getSenSet().getMovingRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getMovingRest().toString());
		for(i=selectedAvtomat.getSenSet().getNoPowerRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getNoPowerRest().toString());
		for(i=selectedAvtomat.getSenSet().getImpLitr().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getImpLitr().toString());
		selectedAvtomat.getAvtomat().setVoda(new Double(selectedAvtomat.getSenSet().getImpLitr()));
		sb.append("   10");
		for(i=selectedAvtomat.getSenSet().getMaxImp().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getMaxImp().toString());
		Integer dbl = new Integer((int) (selectedAvtomat.getSenSet().getCenaLitrNal()*100));
		for(i=dbl.toString().length();i<5;i++)
			sb.append(" ");		
		sb.append(dbl.toString());		
		for(i=selectedAvtomat.getSenSet().getKoefMon().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getKoefMon().toString());
		for(i=selectedAvtomat.getSenSet().getKoefKup().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getKoefKup().toString());
		for(i=selectedAvtomat.getSenSet().getTimeOut().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getTimeOut().toString());
		dbl = new Integer((int) (selectedAvtomat.getSenSet().getCenaButlNal()*100));
		for(i=dbl.toString().length();i<5;i++)
			sb.append(" ");
		sb.append(dbl.toString());
		for(i=smsBase.toString().length();i<5;i++)
			sb.append("0");
		sb.append(smsBase.toString());
		dbl = null;
		sb.append(selectedAvtomat.getSenSet().getDecKey());
		sb.append("890123456789012");
		sb.append(selectedAvtomat.getSenSet().getApn());
		System.out.println(sb);
		String stringa = new String(sb);
		//if (selectedAvtomat.getSenSet().getPoUpdate()) flags=6; else flags=2;
		//if (selectedAvtomat.getSenSet().getParamSend()) flags+=2;
		as = new AvtomatService();
		as.changeSettings(stringa,selectedAvtomat,flags);
		as = null;				
	}

	private Boolean checkFlag(short flags) 
	{
		Boolean check = false; 
		
		/*for(i=0;i<10;i++)
		{
			//System.out.println(i);
			if(flags >= arr[i] )
			{
				flags=(short) (flags-arr[i]);
				switch (arr[i])
				{
					case 2048 : break;
					case 1024 : break;
					case 512 : break;
					case 128 : break;
					case 64 : break;
					case 32 : break;
					case 16 : break;
					case 8 : break;
					case 4 : check = true;break;
					case 2 : break;
				}
			}
		}*/
		if((flags & 4) == 4) check =  true; else check =  false;
		return check;
	}

	/*private Boolean poChk(short flags) 
	{
		short flag = flags;
		Boolean result = false;
		short arr[] = {2048,1024,512,128,64,32,16,8,4,2};
		for(int i=0;i<10;i++)
		{
			//System.out.println(i);
			if(flag >= arr[i] )
			{
				flag=(short) (flag-arr[i]);
				if (arr[i] == 4)result=true;
			}
		}
		return result;
	}*/

	public List<AvtomatWrapper> getAvtoList(Integer first, Integer pageSize) 
	{
		avtomats = new ArrayList<AvtomatWrapper>();
		as = new AvtomatService();
		ArrayList<Integer> idList = as.getLazyAvtomats(first,pageSize);
		as = null;
		if (idList == null) return null;
		for(Integer num : idList)
		{
			AvtomatWrapper avwr = new AvtomatWrapper(num);
			avtomats.add(avwr);
		}
		return avtomats;
	}

	public int countPlayersTotal() 
	{
		as = new AvtomatService();
		Integer rowCount = as.getRowCount("");
		as = null;
		return rowCount;
	}

	public List<AvtomatWrapper> getSortedLazyList(String sortField, SortOrder sortOrder, int first, int pageSize) 
	{
		avtomats = new ArrayList<AvtomatWrapper>();
		String order,query,qs;
		//if(opername.equalsIgnoreCase("Провайдер")) qs = "select avtomat.num from Avtomat avtomat"; else qs = "select avtomat.num from Avtomat avtomat where avtomat.operator = :oname";
		qs = "select avtomat.num from Avtomat avtomat";
		if (sortOrder.toString().equalsIgnoreCase("ASCENDING")){order = "asc";} else {order = "desc";}
		if(sortField.indexOf("senSet") != -1)
		{
			query = qs + " order by avtomat.po "+order;
		} else
		{
			query = qs + " order by "+sortField+" "+order;
		}
		as = new AvtomatService();
		ArrayList<Integer> idList = as.getSortedLazyAvtomats(query, first, pageSize);
		as = null;
		for(Integer num : idList)
		{
			AvtomatWrapper avwr = new AvtomatWrapper(num);
			avtomats.add(avwr);
		}
		return avtomats;		
	}

	public AvtomatWrapper getSelected(String opername) 
	{
		as = new AvtomatService();
		Integer idList = as.getSelected(opername);
		if (idList == null) return null;
		AvtomatWrapper avwr = new AvtomatWrapper(idList);
		as = null;
		return avwr;
	}

	public void remFlag(Short short1, int anum) 
	{
		as = new AvtomatService();
		as.remFlag(short1,anum);
		as = null;		
	}

	public String checkAvto(Integer avtonum) 
	{
		as = new AvtomatService();
		String oper = as.getOperName(avtonum);
		as = null;
		return oper;
	}

	public void saveAvto(Avtomat newAvto) 
	{
		as = new AvtomatService();
		as.saveAvtomat(newAvto);
		as = null;		
	}

	public void delAvto(Integer avtonum) 
	{
		as = new AvtomatService();
		as.delAvtom(avtonum);
		as=null;
	}

	public void editAvto(Integer avtonum) 
	{
		as = new AvtomatService();
		as.delAvtom(avtonum);
		as=null;
	}

	public void saveSettingsForAll(AvtomatWrapper selectedAvtomat) 
	{

		Integer base = 108192;
		Integer smsBase = 0;
		short flags=selectedAvtomat.getAvtomat().getFlag();
		Boolean oldCheck = checkFlag(flags);
		if(!selectedAvtomat.getSenSet().getParamSend())  flags =(short)(flags | (1<<1)) ;
		//if(!selectedAvtomat.getSenSet().getPoUpdate())  flags = selectedAvtomat.getAvtomat().getFlag(); else {flags = (short)(selectedAvtomat.getAvtomat().getFlag() + 4);}   
		//if(/*!poChk(flags) &&*/ selectedAvtomat.getSenSet().getPoUpdate()) flags = (short) (selectedAvtomat.getAvtomat().getFlag()+4);
		if(oldCheck && !selectedAvtomat.getSenSet().getPoUpdate()) flags =(short) (flags & ~(1<<2));
		if(!oldCheck && selectedAvtomat.getSenSet().getPoUpdate()) flags =(short) (flags |(1<<2));
		StringBuffer sb = new StringBuffer();
		sb.append("0000");
		if(selectedAvtomat.getSenSet().getTankFullCon().equalsIgnoreCase("Размыкание"))base=base+2;
		if(selectedAvtomat.getSenSet().getTrevCon().equalsIgnoreCase("Размыкание"))base=base+4;
		if(selectedAvtomat.getSenSet().getLowWaterCon().equalsIgnoreCase("Размыкание"))base=base+8;
		if(selectedAvtomat.getSenSet().getNoWaterCon().equalsIgnoreCase("Размыкание"))base=base+16;
		if(selectedAvtomat.getSenSet().getMovingCon().equalsIgnoreCase("Размыкание"))base=base+32;
		if(selectedAvtomat.getSenSet().getNoPowerCon().equalsIgnoreCase("Размыкание"))base=base+64;
		if(selectedAvtomat.getSenSet().getTempUrg().equalsIgnoreCase("Срочно"))base=base+256;
		if(selectedAvtomat.getSenSet().getTankFullUrg().equalsIgnoreCase("Срочно"))base=base+512;
		if(selectedAvtomat.getSenSet().getTrevUrg().equalsIgnoreCase("Срочно"))base=base+1024;
		if(selectedAvtomat.getSenSet().getLowWaterUrg().equalsIgnoreCase("Срочно"))base=base+2048;
		if(selectedAvtomat.getSenSet().getNoWaterUrg().equalsIgnoreCase("Срочно"))base=base+4096;
		if(selectedAvtomat.getSenSet().getNoPowerUrg().equalsIgnoreCase("Срочно"))base=base+16384;
		sb.append(base.toString());
		if(selectedAvtomat.getSenSet().getSmsLowWater())smsBase=smsBase+8;
		if(selectedAvtomat.getSenSet().getSmsNoWater())smsBase=smsBase+16;
		if(selectedAvtomat.getSenSet().getSmsNoConn())smsBase=smsBase+32;
		if(selectedAvtomat.getSenSet().getSmsnoPow())smsBase=smsBase+64;
		if(selectedAvtomat.getSenSet().getSmsTankFull())smsBase=smsBase+2;
		if(selectedAvtomat.getSenSet().getSmsTemp())smsBase=smsBase+1;
		if(selectedAvtomat.getSenSet().getSmsTrev())smsBase=smsBase+4;
		if(selectedAvtomat.getSenSet().getSmsZasor())smsBase=smsBase+128;
		int i;
		for(i=selectedAvtomat.getSenSet().getTempDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getTempDreb().toString());
		for(i=selectedAvtomat.getSenSet().getTankFullDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getTankFullDreb().toString());
		for(i=selectedAvtomat.getSenSet().getTrevDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getTrevDreb().toString());
		for(i=selectedAvtomat.getSenSet().getLowWaterDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getLowWaterDreb().toString());
		for(i=selectedAvtomat.getSenSet().getNoWaterDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getNoWaterDreb().toString());
		for(i=selectedAvtomat.getSenSet().getMovingDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getMovingDreb().toString());
		for(i=selectedAvtomat.getSenSet().getNoPowerDreb().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getNoPowerDreb().toString());
		for(i=selectedAvtomat.getSenSet().getTempRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getTempRest().toString());
		for(i=selectedAvtomat.getSenSet().getTankFullRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getTankFullRest().toString());
		for(i=selectedAvtomat.getSenSet().getTrevRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getTrevRest().toString());
		for(i=selectedAvtomat.getSenSet().getLowWaterRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getLowWaterRest().toString());
		for(i=selectedAvtomat.getSenSet().getNoWaterRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getNoWaterRest().toString());
		for(i=selectedAvtomat.getSenSet().getMovingRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getMovingRest().toString());
		for(i=selectedAvtomat.getSenSet().getNoPowerRest().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getNoPowerRest().toString());
		for(i=selectedAvtomat.getSenSet().getImpLitr().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getImpLitr().toString());
		selectedAvtomat.getAvtomat().setVoda(new Double(selectedAvtomat.getSenSet().getImpLitr()));
		sb.append("   10");
		for(i=selectedAvtomat.getSenSet().getMaxImp().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getMaxImp().toString());
		Integer dbl = new Integer((int) (selectedAvtomat.getSenSet().getCenaLitrNal()*100));
		for(i=dbl.toString().length();i<5;i++)
			sb.append(" ");		
		sb.append(dbl.toString());		
		for(i=selectedAvtomat.getSenSet().getKoefMon().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getKoefMon().toString());
		for(i=selectedAvtomat.getSenSet().getKoefKup().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getKoefKup().toString());
		for(i=selectedAvtomat.getSenSet().getTimeOut().toString().length();i<5;i++)
			sb.append(" ");
		sb.append(selectedAvtomat.getSenSet().getTimeOut().toString());
		dbl = new Integer((int) (selectedAvtomat.getSenSet().getCenaButlNal()*100));
		for(i=dbl.toString().length();i<5;i++)
			sb.append(" ");
		sb.append(dbl.toString());
		for(i=smsBase.toString().length();i<5;i++)
			sb.append("0");
		sb.append(smsBase.toString());
		dbl = null;
		sb.append(selectedAvtomat.getSenSet().getDecKey());
		sb.append("890123456789012");
		sb.append(selectedAvtomat.getSenSet().getApn());
		System.out.println(sb);
		String stringa = new String(sb);
		//if (selectedAvtomat.getSenSet().getPoUpdate()) flags=6; else flags=2;
		//if (selectedAvtomat.getSenSet().getParamSend()) flags+=2;
		as = new AvtomatService();		
		as.changeSettingsForAll(stringa,selectedAvtomat,flags);
		as = null;				

	}

	public List<AnalysysUse> getAnalysys(List<String> list, Integer param, Integer monthNum, Integer yearNum) 
	{
		List<AnalysysUse> alList = new ArrayList<AnalysysUse>();
		Integer countNum = 0;
		Date fromDate = new Date();
		fromDate.setYear(yearNum-1900);
		fromDate.setDate(1);
		fromDate.setMonth(monthNum);
		fromDate.setHours(0);
		Date toDate = new Date();
		toDate.setYear(yearNum-1900);
		toDate.setDate(28);
		toDate.setMonth(monthNum);
		toDate.setHours(23);
		as = new AvtomatService();
		for(String listEntry: list)
		{
			if(!listEntry.equalsIgnoreCase("Провайдер")&&!listEntry.equalsIgnoreCase("ООО \"Ключ Здоровья\"(склад)"))
			{
			List<Integer> aList = as.getOperAutomats();
			if(aList!=null && !aList.isEmpty())
			for(Integer aaa: aList)
			{
				Integer litrSale = as.getNalLitrSale(aaa, fromDate,toDate);
				if(litrSale > param)
				{
					countNum++;
					//alList.add(new AnalysysUse(listEntry, countNum));
				}
				else
				{
					Integer bnLitrSale = as.getBeznalLitrSale(aaa, fromDate,toDate);
					if((bnLitrSale+litrSale)>param)
					{
						countNum++;
						//alList.add(new AnalysysUse(listEntry, countNum));
					}
				}
			}
			if(countNum >0) alList.add(new AnalysysUse(listEntry, countNum));
			countNum=0;
		}
		}
		as = null;
		return alList;
	}

	public List<AvtomatWrapper> getAvtoListById(Integer aNum) 
	{
		avtomats = new ArrayList<AvtomatWrapper>();
		AvtomatWrapper avwr = new AvtomatWrapper(aNum);
		if(avwr.getAvtomat() != null)avtomats.add(avwr); else return null;
		return avtomats;
	}
}
