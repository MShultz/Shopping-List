package shultz.shopping;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(500);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		boolean loggedIn = false;
		try {
			ResultSet user = getUserInformation(request.getParameter("username"));
			if (user.next()) {
				if (passwordValid(request.getParameter("pword"), user)) {
					loggedIn = true;
				} else
					request.setAttribute("errorMessage", "Your password is incorrect.");
			} else {
				request.setAttribute("errorMessage", "That username does not exist.");
			}
			user.close();
		} catch (SQLException e) {
			System.out.println("Unable to login.");
			e.printStackTrace();
			response.setStatus(500);
		}
		directPage(request, response, loggedIn);
	}

	private ResultSet getUserInformation(String username) throws SQLException {
		Statement userNameRequest = DataHandler.getNewStatement();
		return userNameRequest.executeQuery("SELECT * FROM users where username = '" + username + "'");
	}

	private boolean passwordValid(String password, ResultSet user) throws SQLException {
		return DataHandler.handlePassword(password).equals(user.getString(3));
	}

	private void directPage(HttpServletRequest request, HttpServletResponse response, boolean loggedIn) {
		try {
			if (!loggedIn) {
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			} else {
				HttpSession session = request.getSession(true);
				session.setAttribute("username", request.getParameter("username"));
				request.getRequestDispatcher("/listpage.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			System.out.println("Unable to forward page.");
			response.setStatus(500);
		} catch (IOException ex) {
			System.out.println("Unable to forward page.");
			response.setStatus(500);
		}
	}

}
