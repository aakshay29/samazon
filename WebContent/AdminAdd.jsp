<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div class="container">
		<form action="EditServlet" method="post">
			Product Name: <input type="text" name="productname"><br/>
			Description: <input type="text" name="productdescription"><br/>
			Price: <input type="text" name="productprice"> <br/>
			Image URL: <input type="text" name="productimage"> <br/>
			Seller: <input type="text" name="productseller"> <br/>
			Category: <input type="text" name="productcategory"> <br/><br/>
			<input type="hidden" name="action" value="add">
			<input type="submit" value="Add Product" id="submit"><br/>
		</form>

	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>