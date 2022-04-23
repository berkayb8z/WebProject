package com.yeditepe.acm412;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet(description = "This is our welcome page", urlPatterns = { "/FirstServlet" },
initParams= {@WebInitParam(name="bgColor2",value="#33FF3C")})

@MultipartConfig(
		maxFileSize=1024*1024*5,
		fileSizeThreshold=1024*1024
		)
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		//get all header names in Http Request
		
		String accept_header=request.getHeader("accept");
		String[] accepted_file_types=accept_header.split(",");
		for(String type: accepted_file_types) {
			System.out.println(type);
		}
		
		Enumeration<String> header_names=request.getHeaderNames();
		String table="<table><tr><th>Header Name</th><th>Header Value</th></tr>";
		while(header_names.hasMoreElements()) {
			String header=header_names.nextElement();
			table+="<tr><th>"+header+"</th>"+"<th>"+request.getHeader(header)+"</th></tr>";
		}
		table+="</table>";
		
		out.println("<!DOCTYPE html>"
				+ "<html>"+"<body bgcolor=\""+getServletConfig().getInitParameter("bgColor2")+"\">"+
				"<p>We successully generate out first html content</p>"
				+"<p>Method="+request.getMethod()+"</p>"
				+"<p>URL="+request.getRequestURL()+"</p>"
				+"<p>QueryPart="+request.getQueryString()
				+"<p>URI="+request.getRequestURI()
				+"<p>Host"+request.getRemoteHost()
				+"<p>Port"+request.getLocalPort()
				+"<p>Protocol="+request.getProtocol()+"</p>"+table
				+ "</body></html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		PrintWriter out=response.getWriter();
		//to understand the part object look at the header of a given part
		Part part=request.getPart("file_uploaded");
		LinkedHashSet<String> part_headers=(LinkedHashSet<String>) part.getHeaderNames();
		for(String s: part_headers)
			System.out.println(s+"Value:"+part.getHeader(s));
		final String UPLOAD_DIRECTORY="Files";
		//Generate the file path that you want to uplaod your file
	   String uploadPath=getServletContext().getRealPath("")+File.separator+UPLOAD_DIRECTORY;
	   File uploadDir=new File(uploadPath);
	   if(!uploadDir.exists())
		   uploadDir.mkdir();
	   //we are ready to upload our file
	   for(Part p: request.getParts()){
		   String fileName=getFilename(p);
		   
		   p.write(uploadDir+File.separator+fileName);
	   }
		
		
		String table="<table border=1><tr><td>Form Data Field Name</td><td>Field Value</td>";
		//this method return all form data field names
		Enumeration<String>	paramater_names=	request.getParameterNames();
		while(paramater_names.hasMoreElements()) {
			String field_name=paramater_names.nextElement();
			table+="<tr><td>"+field_name+"</td>"+"<td>"+request.getParameter(field_name)+"</td></tr>";
		}
		table+="</table>";
		//if your form field data has more than one value, you can all selected values using request object
		
		
		response.setContentType("text/html");
		out.println("<!DOCTYPE html>"
				+ "<html>"+"<body bgcolor=\""+getServletConfig().getInitParameter("bgColor2")+"\">"+
				"<p>Hello "+request.getParameter("username")+
				"selected Languages:"+getMultipleValues(request,"language")+
				"Work Exprerince selected"+getMultipleValues(request,"deneyim")+
				"<p>We successully generate out first html content</p>"
				+"<p>Method="+request.getMethod()+"</p>"
				+"<p>URL="+request.getRequestURL()+"</p>"
				+"<p>QueryPart="+request.getQueryString()
				+"<p>URI="+request.getRequestURI()
				+"<p>Host"+request.getRemoteHost()
				+"<p>Port"+request.getLocalPort()
				+"<p>Protocol="+request.getProtocol()+"</p>"+table
				+ "</body></html>");
		
		
	}
	
	public String getMultipleValues(HttpServletRequest request,String field_name) {
		String[] values=request.getParameterValues(field_name);
		String data_list="<ul>";
		if(values.length>0) {
			for(String l: values)
			data_list+="<li>"+l+"</li>";
		}
		data_list+="</ul>";
		return data_list;
	}
	private static String getFilename(Part part) {
		for(String content: part.getHeader("content-disposition").split(";")) {
			if(content.trim().startsWith("filename")) {
			String s= content.substring(content.indexOf("=")+2,content.length());
			s=s.replace("\\", "&");
			String[] ss=s.split("&");
			System.out.println(s);
			String name=ss[ss.length-1];
			return name.substring(0, name.length()-1);
			}
		}
		return null;
	}
}
