<%@ page import="shultz.shopping.UserLists"
	import="shultz.shopping.List" import="shultz.shopping.Item"
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>The shopping list.</title>
</head>
<body>
	<%
		if (session.getAttribute("username") != null) {
	%>
	<h1 id="welcome">Welcome, ${sessionScope.username}!</h1>
	<form action="logout" id="logout">
		<input type="submit" value="Logout" id="submit">
	</form>
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
	<fieldset id="currentlists">
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
				<td class="price">$${itemPrice}</td>
				<%
					price += item.getPrice();
								}
				%>
			
			<tr>
				<td colspan="4" id="total">Total Price: $  <%out.print(price);
				%></td>
			</tr>
		</table>
	</fieldset><br />
	<%
		}
			}
			if (!lists.getUserLists().isEmpty()) {
	%>
	<h2>Need to edit or delete a list?</h2>
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
		<h2>Deleting cannot be undone!!!</h2>
		<input type="submit" value="Delete List"
			onClick="form.action='deletelist'" id="submit"> <input type="submit"
			value="Edit" onClick="form.action='editforward'" id="submit"> <input
			type="submit" value="Add Item" onClick="form.action='addforward';" id="submit">
	</form>
	<%
		}
	%>
	<form action="addList" method="post">
		<h2>List Name</h2>
		<input type="text" name="listname" id="textbox"> <input type="submit"
			value="Create a new List!" name="addList" id="submit">
	</form>
	<h2>${errorMessage}</h2>
	<%
		} else {
			response.sendRedirect("index.html");
		}
	%>
</body>
</html>
