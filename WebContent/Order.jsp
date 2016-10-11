<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order List</title>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<!--cart-items-->
	<div class="cart-items">
		<div class="container">
			${orderHTML}
		</div>
	</div>
	<!--//cart-items-->
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>