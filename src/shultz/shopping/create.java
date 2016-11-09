package shultz.shopping;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/create")
public class create extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setStatus(500);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int user_ID = DataHandler.getUserID(getUsername(request));
		createList(user_ID, request);
		request.getRequestDispatcher("listpage.jsp").forward(request, response);

	}

	private void createList(int user_ID, HttpServletRequest request) {
		int list_ID = DataHandler.getList_ID(user_ID, request.getParameter("listname"));
		insertDetails(request, list_ID);
	}

	private void insertDetails(HttpServletRequest request, int list_ID) {
		if (!request.getParameter("item").isEmpty()) {
			String itemDescription = request.getParameter("item");
			executeUpdateQuery("INSERT into listDetails (list_ID, description) VALUES (" + list_ID + ", \""
					+ itemDescription + "\");");
			if ((!request.getParameter("price").isEmpty()) && isDouble(request.getParameter("price"))) {
				executeUpdateQuery("UPDATE listDetails SET price=" + request.getParameter("price") + " WHERE list_ID ="
						+ list_ID + " AND description = \"" + itemDescription + "\"");
			}
			if ((!request.getParameter("quantity").isEmpty()) && isInt(request.getParameter("quantity"))) {
				executeUpdateQuery("UPDATE listDetails SET quantity=" + request.getParameter("quantity")
						+ " WHERE list_ID =" + list_ID + " AND description = \"" + itemDescription + "\"");
			}
			if ((!request.getParameter("weight").isEmpty()) && isDouble(request.getParameter("weight"))) {
				executeUpdateQuery("UPDATE listDetails SET weight=" + request.getParameter("weight")
						+ " WHERE list_ID =" + list_ID + " AND description = \"" + itemDescription + "\"");
			}
		}
	}

	private String getUsername(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session.getAttribute("username").toString();
	}

	private boolean isDouble(String input) {
		return (isInt(input) || input.matches("[0-9]*\\.[0-9]+"));
	}

	private boolean isInt(String input) {
		return input.matches("[0-9]+");
	}

	private void executeUpdateQuery(String query) {
		Statement detailStatement = DataHandler.getNewStatement();
		try {
			detailStatement.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Unable to insert list details.");
			e.printStackTrace();
		}
	}

}
