<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a Shopping List</title>
</head>
<body>
<h1>Fill out the form below to create your shopping list</h1>
<h3>Note: To add more entries, you'll need to edit your list at the main page.</h3>
<h4>Invalid/empty inputs will be set to a default; an empty item descriptor will be ignored.</h4>
<h4>Prices with currency symbols, and weights with measurements will be ignored. Please do not add these to your entry.</h4>

<form action="create" method="post">
<h2>${listname}</h2>
<p class=ListDescriptors>Quantity: </p><input type="text" name="quantity" maxlength="3">
<p class=ListDescriptors>Item: </p><input type="text" name="item">
<p class=ListDescriptors>Weight: </p><input type="text" name="weight">
<p class=ListDescriptors>Price: </p><input type="text" name="price"><br>

<input type="hidden" name="listname" value="${listname}">
<input type="submit" value="Create Your List!">
</form>
</body>
</html>