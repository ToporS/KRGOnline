package com.hk.deader.mb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="news")
@ViewScoped
public class NewsMB implements Serializable
{

	private static final long serialVersionUID = 7657123985014134558L;
	
	private String news = null;
	private List<String> newsList = null;
	private String updNews = null;
	
	public NewsMB()
	{
		try 
		{           
			File f = new File("D:\\KRG_Touch\\news.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f),Charset.forName("UTF-8")));
			String line = in.readLine();
			if(line != null )setNews(line); else setNews("");
			in.close(); in = null; f = null;
        }catch(Exception e){e.getStackTrace(); setNews("");System.out.println("Fucking shit...");}
	}
	
	public void saveNews()
	{
		String line;
		synchronized(this){
		try
		{
			File f = new File("D:\\KRG_Touch\\news.txt");
			File tf = new File("D:\\KRG_Touch\\news.tmp");
			f.renameTo(tf);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, false), Charset.forName("UTF-8")));
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(tf),Charset.forName("UTF-8")));
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			StringBuffer sb = new StringBuffer();
			sb.append(sdf.format(new Date()));
			sb.append(" ");
			sb.append(getUpdNews());
			line = new String(sb);
			out.append(line);
			out.newLine();
			while ((line = in.readLine()) != null) 
			{
			    out.append(line);
			    out.newLine();
			}
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
			tf.delete();	
			tf = null;
		} catch (UnsupportedEncodingException | FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		FacesContext context = FacesContext.getCurrentInstance();  	          
        context.addMessage(null, new FacesMessage("Успех", "Новость обновлена"));
	}

	public String getNews()
	{
		return news;
	}

	public void setNews(String news)
	{
		this.news = news;
	}

	public List<String> getNewsList()
	{
		if(newsList == null )
		{
			newsList = new ArrayList<String>();
			int lineCount = 0;
			BufferedReader br = null;
			String line;			
			try
			{
				br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\KRG_Touch\\news.txt"), Charset.forName("UTF-8")));
				while ((line = br.readLine()) != null && lineCount < 10) 
				{
				    newsList.add(line);
				    lineCount++;
				}
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			finally
			{
				try
				{
					if(br != null) br.close();
				} 
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				br = null;
			}
		}
		return newsList;
	}

	public void setNewsList(List<String> newsList)
	{
		this.newsList = newsList;
	}

	public String getUpdNews()
	{
		return updNews;
	}

	public void setUpdNews(String updNews)
	{
		this.updNews = updNews;
	}
	
	
	
}
