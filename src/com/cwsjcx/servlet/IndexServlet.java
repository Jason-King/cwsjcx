package com.cwsjcx.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/Index" })
public class IndexServlet  extends HttpServlet {
	private static final long serialVersionUID = -8608229251804310705L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String page = request.getParameter("page");
		request.setAttribute("type",page);
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.include(request, response);
	}
}
