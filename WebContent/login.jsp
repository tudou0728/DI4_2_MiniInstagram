<%@ page import="java.sql.*" language="java" contentType="text/html;
charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MiniInstagram Log In</title>
</head>
<body>
	<center>
		<h1 style="color: red">Instagram login</h1>
		<form id="indexform" name="indexForm">
			<table border="0">
				<tr>
					<td>user：</td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>password：</td>
					<td><input type="password" name="password"></td>
				</tr>
			</table>
			<br> <input type="submit" value="login" style="color: #BC8F8F">
		</form>
	</center>
</body>
</html>