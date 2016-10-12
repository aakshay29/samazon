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
			<p>ID:<c:out value="${adminProduct.productid}"/></p><br/>
			Name: <input type="text" name="productname" value="<c:out value="${adminProduct.name}"/>"><br/>
			Description: <input type="text" name="productdescription" value="<c:out value="${adminProduct.description}"/>"><br/>
			Price: $<input type="text" name="productprice" value="<c:out value="${adminProduct.price}"/>"><br/>
			<img src="<c:out value="${adminProduct.image}"/>" alt=""><br/>
			ImageURL: <input type="text" name="productimage" value="<c:out value="${adminProduct.image}"/>"><br/>
			Seller: <input type="text" name="productseller" value="<c:out value="${adminProduct.seller}"/>"><br/>
			Category: <input type="text" name="productcategory" value="<c:out value="${adminProduct.samazoncategory.categoryid}" />"><br/><br/>		
			<input type="hidden" name="productID" value="<c:out value="${adminProduct.productid}"/>">
			<input type="hidden" name="action" value="update"> 
			<input type="submit" value="Update" id="submit">
		</form>


	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>