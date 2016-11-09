package shultz.shopping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserLists {
	private String username;
	private ArrayList<List> userLists = new ArrayList<List>();

	public UserLists(String username){
		this.setUsername(username);
		this.constructUsersLists();
		
	}
	private void constructUsersLists(){
		int user_ID = DataHandler.getUserID(username);
		Statement request = DataHandler.getNewStatement();
		try {
			ResultSet lists = request.executeQuery("SELECT listname FROM userLists WHERE user_ID = " + user_ID);
			while(lists.next()){
				this.addList(new List(username, lists.getString(1)));
			}
		} catch (SQLException e) {
			System.out.println("Unable to get lists.");
		}
	}
	private void addList(List list){
		userLists.add(list);
	}
	public ArrayList<List> getUserLists() {
		return userLists;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
