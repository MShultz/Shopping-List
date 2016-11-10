package shultz.shopping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		logout(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		logout(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		session.invalidate();
		try {
			response.sendRedirect("index.html");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
