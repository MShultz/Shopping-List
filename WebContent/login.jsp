<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Login</title>
</head>
<body id="login">
	<div id="titleandinfo"><h1> Welcome to The Shopping List!</h1>
	<h2>Sign up to use. Not a member yet? <a href="signup.jsp" id="link">Sign Up now.</a></h2></div>
	<form action="login" method="post" id="userInfo">
	<h2>Username <input type="text" name="username" id="textbox"></h2><br>
	<h2>Password <input type="password" name="pword" id="textbox"></h2><br>
	<input type="submit" value="Login" id="submit">
	</form>
	<h2>${errorMessage}</h2>
</body>
</html>