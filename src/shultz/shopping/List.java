package shultz.shopping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class List {
	private ArrayList<Item> listItems = new ArrayList<Item>();
	private String username, listName;

	public List(String username, String listName) {
		this.setUsername(username);
		this.setListName(listName);
		this.constructList();
	}

	private void constructList() {
		Statement listRequest = DataHandler.getNewStatement();
		ResultSet listItems;
		try {
			listItems = listRequest.executeQuery("SELECT * FROM listDetails WHERE list_ID ="
					+ DataHandler.getList_ID(DataHandler.getUserID(this.username), this.getListName()));
			while (listItems.next()) {
				addItem(new Item(listItems.getString(2), listItems.getInt(3), listItems.getDouble(4),
						listItems.getDouble(5)));
			}
		} catch (SQLException e) {
			System.out.println("Unable to get list items");
		}
	}

	public void addItem(Item item) {
		listItems.add(item);
	}

	public ArrayList<Item> getListItems() {
		 return listItems;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}
}
