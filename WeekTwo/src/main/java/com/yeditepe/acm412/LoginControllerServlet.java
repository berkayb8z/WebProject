package com.yeditepe.acm412;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginControllerServlet
 */
@WebServlet("/LoginController")
public class LoginControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String eposta = request.getParameter("email");
		String sifre = request.getParameter("password");

		UserDAO dao = new UserDAO();
		
		if(eposta!=null && sifre!=null) {

			User client = dao.checkUser(eposta, sifre);
			
			request.setAttribute("user", client);
			
			//kt�phane ekledim bb
			RequestDispatcher rd = request.getRequestDispatcher("/MainServlet");
			rd.forward(request, response);			
		}
		
		
		else {
			response.sendRedirect("index.html");
		}		
	}
}
