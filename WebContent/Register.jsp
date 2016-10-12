<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Samazon Login</title>
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<!--login-->
	<div class="login-page">
		<div class="title-info wow fadeInUp animated" data-wow-delay=".5s">
			<h3 class="title">
				Register
			</h3>
		</div>
		<div class="widget-shadow">
			<div class="login-body wow fadeInUp animated" data-wow-delay=".7s">
				<form action="RegisterServlet" method="post">
					<input type="text" class="user" name="name" placeholder="Enter your name" required=""> 
					<input type="text" class="user" name="username" placeholder="Enter your username" required=""> 
					<input type="text" class="user" name="email" placeholder="Enter your email" required=""> 
					<input type="password" name="password" class="lock" placeholder="Password"> 
					<input type="text" class="user" name="phone" placeholder="Enter your phone" required=""> 
					<input type="text" class="user" name="address" placeholder="Enter your address" required=""> 
					<input type="submit" value="Register">
				</form>
			</div>
		</div>
	</div>
	<!--//login-->
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>