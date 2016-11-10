<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a Shopping List</title>
</head>
<body>
	<%
		if (session.getAttribute("username") != null) {
	%>
	<h1>Fill out the form below to add an item to your shopping list</h1>
	<h2>
		Note: To add more entries, you'll need to edit your list at the main
		page.<br> Invalid/empty inputs will be set to a default; an empty
		item descriptor will be ignored.<br> Prices with currency
		symbols, and weights with measurements will be ignored. Please do not
		add these to your entry.
	</h2>

	<fieldset>
		<legend>${listname}</legend>
		<form action="create" method="post">
			<div>
				<h2>
					<div id="title">Quantity:</div>
					<input type="text" name="quantity" maxlength="3" id="textbox">
				</h2>
			</div>
			<div>
				<h2>
					<div id="title">Item:</div>
					<input type="text" name="item" id="textbox">
				</h2>
			</div>
			<div>
				<h2>
					<div id="title">Weight:</div>
					<input type="text" name="weight" id="textbox">
				</h2>
			</div>
			<div>
				<h2>
					<div id="title">Price:</div>
					<input type="text" name="price" id="textbox">
				</h2>
			</div>
			<br> <input type="hidden" name="listname" value="${listname}">
			<input type="hidden" name="source" value="add"> <input
				type="submit" value="Add to your List!" id="submit">
		</form>
		</fieldset>
		<%
			} else {
				response.sendRedirect("index.html");
			}
		%>
	
</body>
</html>