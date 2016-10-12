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
				<table class="table table-bordered table-striped table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Description</th>
							<th>Price</th>
							<th>Image</th>
							<th>Seller</th>
							<th>Category</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="productList" items="${productList}">
							<tr>
								<td><c:out value="${productList.productid}" /></td>
								<td><c:out value="${productList.name}" /></td>
								<td><c:out value="${productList.description}" /></td>
								<td><c:out value="${productList.price}" /></td>
								<td><img src="<c:out value="${productList.image}" />" alt=""></td>
								<td><c:out value="${productList.seller}" /></td>
								<td><c:out value="${productList.samazoncategory.categoryid}" /></td>
								<td>
									<form action="EditServlet" method="post">
										<input type="hidden" name="productID" value=<c:out value="${productList.productid}" />>
										<input type="hidden" name="action" value="edit">
										<input type="submit" value="Edit" id="submit">
									</form>												
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<a href="AdminAdd.jsp">Add Product</a> 
			</div>
			<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>