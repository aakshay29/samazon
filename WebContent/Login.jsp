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
				SignIn<span> Form</span>
			</h3>
		</div>
		<div class="widget-shadow">
			<div class="login-top wow fadeInUp animated" data-wow-delay=".7s">
				<h4>
					Welcome to Samazon! <br> Not a Member? <a
						href="register.html"> Register Now »</a>
				</h4>
			</div>
			<div class="login-body wow fadeInUp animated" data-wow-delay=".7s">
				<form action="LoginServlet" method="post">
					<input type="text" class="user" name="username"
						placeholder="Enter your username" required=""> <input
						type="password" name="password" class="lock"
						placeholder="Password"> <input type="submit"
						value="Sign In" value="Sign In">
					<!-- <div class="forgot-grid">
						<label class="checkbox"><input type="checkbox"
							name="checkbox"><i></i>Remember me</label>
						<div class="forgot">
							<a href="#">Forgot Password?</a>
						</div>
						<div class="clearfix"></div>
					</div> -->
				</form>
			</div>
		</div>
	</div>
	<!--//login-->
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>