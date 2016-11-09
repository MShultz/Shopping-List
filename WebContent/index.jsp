<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>The Shopping List</title>
</head>
<body>
	<h1> Welcome to The Shopping List!</h1>
	<h2>Sign up to use. Already a member? Login now!</h2>
	<form action="" method="post">
	Username:<br> <input type="text" name="username"><br>
	Password:<br> <input type="password" name="pword"><br>
	Confirm Password:<br><input type="password" name="confirm"><br>
	(You only need to enter a confirmation if you're signing up.)
	<input type="submit" value="Sign Up" onClick="form.action='signup'">
	<input type="submit" value="Login" onClick="form.action='login'">
	</form>
	<div id="errormsg">${errorMessage}</div>
</body>
</html>

