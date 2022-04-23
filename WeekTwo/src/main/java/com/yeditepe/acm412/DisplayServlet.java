package com.yeditepe.acm412;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayServlet
 */
@WebServlet("/DisplayServlet")
public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayServlet() {
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
		
		String file_directory=getServletContext().getRealPath("")+File.separator+"Files";
		//get the directory where your upload files are stored
		File file_list=new File(file_directory);
		//get all files in this directory
		File[] files=file_list.listFiles();
		String file_table="<table border=\"1\"><tr><td>Filename</td><td>Link</td>";
		if(files!=null) {
			for(File f : files) {
				String download_link=getServletContext().getContextPath()+"/DowloadServlet"+"?"+"filename="+f.getName();
				file_table+="<tr><td>"+f.getName()+"</td><td><a href=\""+download_link+"\">Dowload File</a>";
			}
		file_table+="</table>";
		}
		out.println("<html><head></head><body>"+file_table+"</body></html>");

		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
