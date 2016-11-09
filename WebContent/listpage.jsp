<%@ page import="shultz.shopping.UserLists"
	import="shultz.shopping.List" import="shultz.shopping.Item"
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>The shopping list.</title>
</head>
<body>
	<h1>Welcome, ${sessionScope.username}!</h1>
	<%
		UserLists lists = new UserLists(session.getAttribute("username").toString());
		if (!lists.getUserLists().isEmpty()) {
	%>
	<h2>Here are your current lists</h2>
	<%
		} else {
	%>
	<h2>You have no lists. Please create a new list to continue.</h2>
	<%
		}
		for (List list : lists.getUserLists()) {
			if (!list.getListItems().isEmpty()) {
				pageContext.setAttribute("listTitle", list.getListName());
	%>
	<fieldset>
		<legend> ${listTitle} </legend>
		<table>
			<tr>
				<th>Quantity</th>
				<th>Item</th>
				<th>Weight</th>
				<th>Price</th>
			</tr>
			<%
				double price = 0.0;
						for (Item item : list.getListItems()) {
							pageContext.setAttribute("quantity", item.getQuantity());
							pageContext.setAttribute("item", item.getDescription());
							pageContext.setAttribute("weight", item.getWeight());
							pageContext.setAttribute("itemPrice", item.getPrice());
			%>
			<tr>
				<td>${quantity}</td>
				<td>${item}</td>
				<td>${weight}</td>
				<td>$${itemPrice}</td>
				<%
					price += item.getPrice();
							}
				%>
			
			<tr>
				<td colspan="4">Total Price: $${price}</td>
			</tr>
		</table>
	</fieldset>
	<%
		}
		}
		if (!lists.getUserLists().isEmpty()) {
	%>
	<p id="edit">Need to edit or delete a list?</p>
	<p id="note">Note: To add more entries to a list, you'll need to
		edit it.</p>
	<form method="post">
		<select name="listChoice">
			<%
				for (List list : lists.getUserLists()) {
						pageContext.setAttribute("listname", list.getListName());
			%>
			<option value="${listname}">${listname}</option>
			<%
				}
			%>
		</select> <br>
		<p>Deleting cannot be undone!!!</p>
		<input type="submit" value="DeleteList" onClick="form.action='delete'">
		<input type="submit" value="Edit" onClick="form.action='edit'">
		<input type="submit" value="addItem" onClick="form.action='listchoiceforward'">
	</form>
	<%
		}
	%>
	<form action="addList" method="post">
		<p>List Name</p>
		<input type="text" name="listname"> <input type="submit"
			value="Create a new List!" name="addList">
	</form>
	<p>${errorMessage}</p>
</body>
</html>
