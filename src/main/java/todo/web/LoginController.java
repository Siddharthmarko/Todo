package todo.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import java.io.IOException;
import todo.dao.*;
import todo.model.*;

/**
 * @email Ramesh Fadatare
 */

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String usekaro;
	private LoginDao loginDao;

	public void init() {
		loginDao = new LoginDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In /login");
		response.sendRedirect("login/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In /login");
		// ServletContext cont = getServletContext();
		authenticate(request, response);
	}

	private void authenticate(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		System.out.println("authentication");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);

		try {
			if (loginDao.validate(loginBean)) {
				System.out.println("validate");
				usekaro = username;
				request.setAttribute("currUser", username);
				System.out.println(request.getAttribute("currUser") + "curruser print kiya hkya");

				RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
				RequestDispatcher discontrol = request.getRequestDispatcher("todo/web.TodoController.java");
				dispatcher.forward(request, response);
			} else {
				HttpSession session = request.getSession();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
