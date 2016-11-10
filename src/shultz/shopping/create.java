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
			handleItemAddition(request, itemDescription, list_ID);

		}
	}

	private void handleItemAddition(HttpServletRequest request, String itemDescription, int list_ID) {
		boolean continueHandling = handleDescription(request, itemDescription, list_ID);
		if(continueHandling)
		handleItemDetails(request, itemDescription, list_ID);
	}

	private void handleItemDetails(HttpServletRequest request, String itemDescription, int list_ID) {
		if ((!request.getParameter("price").isEmpty()) && isDouble(request.getParameter("price"))) {
			DataHandler.executeUpdate("UPDATE listDetails SET price=" + request.getParameter("price")
					+ " WHERE list_ID =" + list_ID + " AND description = \"" + itemDescription + "\"");
		}
		if ((!request.getParameter("quantity").isEmpty()) && isInt(request.getParameter("quantity"))) {
			DataHandler.executeUpdate("UPDATE listDetails SET quantity=" + request.getParameter("quantity")
					+ " WHERE list_ID =" + list_ID + " AND description = \"" + itemDescription + "\"");
		}
		if ((!request.getParameter("weight").isEmpty()) && isDouble(request.getParameter("weight"))) {
			DataHandler.executeUpdate("UPDATE listDetails SET weight=" + request.getParameter("weight")
					+ " WHERE list_ID =" + list_ID + " AND description = \"" + itemDescription + "\"");
		}
	}

	private boolean itemExists(String item, int list_ID) {
		boolean exists = false;
		ResultSet results = DataHandler
				.executeQuery("SELECT * From listDetails WHERE description=\"" + item + "\" AND list_ID=" + list_ID);
		try {
			exists = results.next();
			results.close();
		} catch (SQLException e) {
			System.out.println("Error reading results.");
		}
		return exists;
	}

	private boolean handleDescription(HttpServletRequest request, String itemDescription, int list_ID) {
		boolean continueWithItem = true;
		if (isAdd(request)) {
			if(!itemExists(itemDescription, list_ID)){
			DataHandler.executeUpdate("INSERT into listDetails (list_ID, description) VALUES (" + list_ID + ", \""
					+ itemDescription + "\");");
			}else{
				continueWithItem = false;
			}
		} else {
			DataHandler.executeUpdate("UPDATE listDetails SET description=\"" + request.getParameter("item")
					+ "\" WHERE list_ID =" + list_ID + " AND item_ID =" + request.getParameter("item_ID"));
		}
		return continueWithItem;
	}

	private boolean isAdd(HttpServletRequest request) {
		return request.getParameter("source").equals("add");
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

}
