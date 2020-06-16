package com.hk.deader.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.model.chart.CartesianChartModel;
import com.hk.deader.dao.SalesReportDAO;
import com.hk.deader.domains.SalesReport;
import com.hk.deader.utils.Totals;
@ManagedBean(name="salesreport")
@ViewScoped
public class SalesReportMB implements Serializable {

	private static final long serialVersionUID = 1703090395486034362L;
/*	@ManagedProperty(value="#{login}")
	private CredentialsMB CMB;*/
	private Boolean isFormRendered = false; 
	private Date fromDate = null;
	private Date toDate = null;
	private CartesianChartModel categoryModel = new CartesianChartModel();
	private SalesReportDAO srDAO = null;
	private List<SalesReport> reportList;
	private SalesReport selectedReport = new SalesReport();
	private JasperPrint jasperPrint;
	private Totals total;
	
	
	
	public SalesReportMB(){};
	
	@SuppressWarnings("deprecation")
	public Date getFromDate() {
		if (fromDate == null)
		{
			fromDate = new Date();//By default setting fromDay to beginning of current day
			fromDate.setHours(0);
			fromDate.setMinutes(0);
			fromDate.setSeconds(0);
		} 
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	@SuppressWarnings("deprecation")
	public Date getToDate() {
		if (toDate == null)
		{
			toDate =  new Date(); //By default setting toDay to the end of current day
			toDate.setHours(23);
			toDate.setMinutes(59);
			toDate.setSeconds(59);
		} 
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public List<SalesReport> getReportList() {
		//if(reportList == null || reportList.isEmpty())byDate();
		return reportList;
	}
	public void setReportList(List<SalesReport> reportList) {
		this.reportList = reportList;
	}

	public CartesianChartModel getCategoryModel() {
		return categoryModel;
	}

	public void setCategoryModel(CartesianChartModel categoryModel) {
		this.categoryModel = categoryModel;
	}

	public SalesReport getSelectedReport() {
		return selectedReport;
	}

	public void setSelectedReport(SalesReport selectedReport) {
		this.selectedReport = selectedReport;
	}

	@SuppressWarnings("deprecation")
	public void byDate()
	{
		if(reportList != null)reportList.clear();
		this.isFormRendered = true;
		if(toDate == null)
		{	
			toDate = new Date();
			toDate.setHours(23);
			toDate.setMinutes(59);
			toDate.setSeconds(59);
		}
		if(fromDate == null)
		{
			fromDate = new Date();
			fromDate.setHours(0);
			fromDate.setMinutes(0);
			fromDate.setSeconds(0);
		}
		srDAO = new SalesReportDAO();		
		this.reportList = srDAO.getReportList(fromDate,toDate);
		this.total = new Totals(fromDate, toDate);
		srDAO = null;
	}	
	
	public void init() throws JRException
	{
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(reportList);
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath("/pages/templates/sr.jasper");
		jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(),datasource);  
	}
	/*
	public void XLSX() throws IOException, JRException
	{
		System.out.println("Poehali...");
		init();  
		
	       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();  
	      httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.xlsx");  
	       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();  
	       JRXlsxExporter docxExporter=new JRXlsxExporter();  
	       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);  
	       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);  
	       docxExporter.exportReport();  
	       FacesContext.getCurrentInstance().responseComplete();
	}*/

	public Totals getTotal() {
	/*	if(total == null)
		{
			total = new Totals(getOperatorName(), fromDate, toDate);
		}*/
		return total;
	}

	public void setTotal(Totals total) {
		this.total = total;
	}

	public Boolean getIsFormRendered() {
		return isFormRendered;
	}

	public void setIsFormRendered(Boolean isFormRendered) {
		this.isFormRendered = isFormRendered;
	}	
}
