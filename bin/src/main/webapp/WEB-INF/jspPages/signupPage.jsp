<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
<style type="text/css">
div{
	width : 175px;
	margin-left : 45%;
	margin-right : 55%; 
}
</style>
</head>
<body>

<div>
<h2><b>Create Account</b></h2>
<form action = "/funcart/signupDetail" method="post" enctype='application/json'>
    <label><b>Username</b></label><br>
    <input type="text" placeholder="Enter Your Name" name="username" required><br><br>
    
    <label><b>Mobile number</b></label><br>
    <input type="text" placeholder="Enter Mobile" name="phoneNumber" required><br><br>
    
    <label><b>Email</b></label><br>
    <input type="text" placeholder="Enter Email" name="email" required><br><br>

    <label><b>Password</b></label><br>
    <input type="password" placeholder="Enter Password" name="passoword" required><br><br>
    
    <button type="submit" name="singupButton">Sign Up</button> or <a href = "/funcart/loginPage">Login</a>
</form>
</div>
</body>
</html>