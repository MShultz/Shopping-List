<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Creation</title>
</head>
<body id="Login">
	<div id="titleandinfo"><h1 id="welcome"> Welcome New User!</h1>
	<h2>Sign up to use. Already a member? <a href="login.jsp" id="link">Login now!</a></h2></div>
	<form action="signup" method="post" id="userInfo">
	<h2>Username <input type="text" name="username" id="textbox"></h2><br>
	<h2>Password <input type="password" name="pword" id="textbox"></h2><br>
	<h2>Confirm  <input type="password" name="confirm" id="textbox"></h2><br>
	<input type="submit" value="Sign Up" id="submit">
	</form>
	<h2>${errorMessage}</h2>
</body>
</html>