package com.entor.jdbc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		try {
			
			String sql="SELECT * FROM n_user u WHERE u.username = ? AND u.password = ?";
			JDBCCustomer jdbcCustomer=new JDBCCustomer();
			int row=jdbcCustomer.execute(sql, username,password);
			if (row>0) {
				response.sendRedirect("add_user.jsp");
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
