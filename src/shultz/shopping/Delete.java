package shultz.shopping;

import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(500);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		String username = session.getAttribute("username").toString();
		String listName = request.getParameter("listChoice");
		int user_ID = DataHandler.getUserID(username);
		int list_ID = DataHandler.getList_ID(user_ID, listName);
		deleteFromListDetails(list_ID);
		deleteFromUserLists(list_ID, user_ID);
	}

	private void deleteFromListDetails(int list_ID) {
		try {
			DataHandler.getNewStatement().executeUpdate("DELETE FROM listdetails WHERE list_ID=" + list_ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteFromUserLists(int list_ID, int user_ID) {
try {
	DataHandler.getNewStatement().executeUpdate("DELETE FROM userLists WHERE list_ID=" + list_ID + " AND user_ID=" + user_ID);
} catch (SQLException e) {
	e.printStackTrace();
}
	}
}
