<%@ page import="shultz.shopping.List" import="shultz.shopping.Item"
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Your Shopping list!</title>
</head>
<body>
	<%
		if (session.getAttribute("username") != null) {
	%>
	<fieldset>
		<legend> ${listname} </legend>
		<%
			List list = new List(session.getAttribute("username").toString(),
						request.getAttribute("listname").toString());
				int count = 0;
				for (Item item : list.getListItems()) {
					pageContext.setAttribute("quantity", item.getQuantity());
					pageContext.setAttribute("item", item.getDescription());
					pageContext.setAttribute("weight", item.getWeight());
					pageContext.setAttribute("itemPrice", item.getPrice());
					pageContext.setAttribute("item_ID", item.getItem_ID());
		%>
		<form action=create method="post">
			<div>
				<h2>
					<div id="title">Quantity</div>
					<input type="text" name="quantity" value="${quantity}" id="textbox">
				</h2>
			</div>
			<div>
				<h2>
					<div id="title">Item</div>
					<input type="text" name="item" value="${item}" id="textbox">
				</h2>
			</div>
			<div>
				<h2>
					<div id="title">Weight</div>
					<input type="text" name="weight" value="${weight}" id="textbox">
				</h2>
			</div>
			<p>
			<div>
				<h2>
					<div id="title">Price</div>
					<input type="text" name="price" value="${itemPrice}" id="textbox">
				</h2>
			</div>
			<input type="submit" value="Edit this entry!"
				onClick="form.action='create'" id="submit"> <input
				type="submit" value="Delete this entry!"
				onClick="form.action='deleteitem'" id="submit"> <input
				type="hidden" name="listname" value="${listname}"> <input
				type="hidden" name="item_ID" value="${item_ID}"> <input
				type="hidden" name="source" value="edit">
		</form>
		<%
			}
		%>
	</fieldset>
	<%
		} else {
			response.sendRedirect("index.html");
		}
	%>
</body>
</html>