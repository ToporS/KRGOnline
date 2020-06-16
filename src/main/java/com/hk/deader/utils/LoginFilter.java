package com.hk.deader.utils;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hk.deader.mb.CredentialsMB;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	
    
    	 private String onFailure = "/login.jsp";
    	    private FilterConfig filterConfig;
    	    
    	    public void init(FilterConfig filterConfig) throws ServletException {
    	        this.filterConfig = filterConfig;
    	        onFailure = filterConfig.getInitParameter("onFailure");
    	    }
    	   
    	    public void doFilter(ServletRequest request,
    	                       ServletResponse response,
    	                       FilterChain chain) 
    	                 throws IOException, ServletException {
    	    	
    	        HttpServletRequest req = (HttpServletRequest) request;
    	        HttpServletResponse res = (HttpServletResponse) response;
    	        
    	       if (req.getServletPath().equals(onFailure)) {
    	            chain.doFilter(request, response);
    	            return;
    	        }
    	        
    	        HttpSession session = req.getSession(); // get the session or create it
    	      //  Check the session Last Accessed time
    	        CredentialsMB CMB = (CredentialsMB) session.getAttribute("login");
    	        if(CMB == null){System.out.println("Redirecting.....");res.sendRedirect(req.getContextPath()+"/index.jsp");return;}
    	        GregorianCalendar gk = new GregorianCalendar();
    	        Long diff = gk.getTimeInMillis() - session.getLastAccessedTime();
    	        if (diff > 11760000)
    	        {
    	        	session.invalidate();
    	        	res.sendRedirect(req.getContextPath()+"/index.jsp");
    	        	return;
    	        }
    	        else {
    	        	try
    	        	{
    	        		chain.doFilter(request, response);
    	        	}catch (ServletException e) {
    	        		System.out.println("Emergency Exit...");
    	        		res.sendRedirect(req.getContextPath()+"/index.jsp");
    	        		/*ServletContext context = filterConfig.getServletContext();;    	        		
    	        		context.getRequestDispatcher("/index.jsp").forward(req, res);*/
    	        		return ;
						// TODO: handle exception
					}
    	            
    	        }
    	    }

    	    public void destroy() {
    	    }


}
