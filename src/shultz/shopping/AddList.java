package shultz.shopping;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addList")
public class AddList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setStatus(500);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String listname = request.getParameter("listname");
		HttpSession session = request.getSession(false);
		int user_ID = DataHandler.getUserID(session.getAttribute("username").toString());
		boolean validName = true;
		if (listNameExists(listname, user_ID)) {
			request.setAttribute("errorMessage", "You already have a list by the name:" + listname + ".");
			validName = false;
		} else {
			request.setAttribute("listname", listname);
			addList(user_ID, listname);
		}
		directPage(validName, request, response);
	}

	private void directPage(boolean validName, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (validName) {
				request.getRequestDispatcher("addItem.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("listpage.jsp").forward(request, response);
			}
		} catch (IOException e) {
			System.out.println("Unable to forward list page.");
		} catch (ServletException e) {
			System.out.println("Servlet Error: Unable to forward list page.");
		}
	}

	private void addList(int user_ID, String listname) {
		DataHandler.executeUpdate(
				"INSERT into userLists (user_ID, listname) VALUES (" + user_ID + ",\"" + listname + "\");");
	}

	private boolean listNameExists(String listname, int user_ID) {
		ResultSet results = DataHandler
				.executeQuery("Select * FROM userLists WHERE listname = \"" + listname + "\" AND user_ID =" + user_ID);
		boolean exists = false;
		try {
			exists = results.next();
			results.close();
		} catch (SQLException e) {
		System.out.println("Error reading results");	
		}
		return exists;
	}

}
