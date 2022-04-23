package com.yeditepe.acm412;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DowloadServlet
 */
@WebServlet("/DowloadServlet")
public class DowloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DowloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		OutputStream outStream = null;//responsible to write data
		final int BUFFER_SIZE = 1024 * 100;
        FileInputStream inputStream = null;//responsible to read data
		//set content type(MIME) of response object
		
		String requestFileName=request.getParameter("filename");
		//get request File
		byte[] bytes=new byte[BUFFER_SIZE];
		File requestedFile=new File(getServletContext().getRealPath("")+File.separator+"Files"+File.separator+requestFileName);
		//check that this file exist
		if(requestedFile.exists()) {
			response.setContentType("application/octet-stream");
			//in response header say that you are going to attach a file
			String headerValue = String.format("attachment; filename=\"%s\"", requestedFile.getName());
			response.setHeader("Content-Disposition",headerValue );
			inputStream=new FileInputStream(requestedFile);
			outStream=response.getOutputStream();
			int readStream=-1;
			//-1 means that you reach the end of file
			while((readStream=inputStream.read(bytes))!=-1) {
				outStream.write(bytes, 0, readStream);				
			}
			inputStream.close();
			outStream.close();
		}
		else {
			response.setContentType("text/html");
			response.getWriter().println("<html><head></head><body><p>The file "+requestFileName+
					"is not found</p></body>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
