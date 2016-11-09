
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

@WebServlet("/signup")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(500);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String pass = request.getParameter("pword");
		String confirmPass = request.getParameter("confirm");
		boolean notAccepted = processSignUp(username, pass, confirmPass, request, response);
		directPage(request, response, notAccepted);
	}

	private boolean processSignUp(String username, String pass, String confirmPass, HttpServletRequest request,
			HttpServletResponse response) {
		boolean notAccepted = false;
		try {
			if (usernameExists(username)) {
				request.setAttribute("errorMessage", "Unfortunately, this username already exists.");
				notAccepted = true;
			} else if (passwordsAreEqual(pass, confirmPass)) {
				createUser(username, pass);
			} else {
				request.setAttribute("errorMessage", "Your confirmation password does not match; try again.");
				notAccepted = true;
			}
		} catch (SQLException e) {
			System.out.println("Unable to read from database");
			e.printStackTrace();
			response.setStatus(500);
		}
		return notAccepted;
	}

	private void directPage(HttpServletRequest request, HttpServletResponse response, boolean notAccepted) {
		try {
			if (notAccepted) {
				request.getRequestDispatcher("/index.jsp").forward(request, response);
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

	private void createUser(String username, String password) throws SQLException {
		Statement userCreationStatement = DataHandler.getNewStatement();
		userCreationStatement.executeUpdate("INSERT into users (username, `password`) VALUES (\"" + username + "\",\""
				+ DataHandler.handlePassword(password) + "\");");
	}

	private boolean usernameExists(String username) throws SQLException {
		ResultSet usernames = null;
		boolean exists = true;
		Statement userNameRequest = DataHandler.getNewStatement();
		usernames = userNameRequest.executeQuery("SELECT username FROM users where username = '" + username + "'");
		exists = usernames.next();
		usernames.close();

		return exists;
	}

	private boolean passwordsAreEqual(String password, String confirmationPass) {
		return password.equals(confirmationPass);
	}

}
