<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/header.jsp"%>
<%@ page isELIgnored="false"%>
<div id="content">
	<c:forEach var="product" items="${products}">
		<table>
			<tr>
				<td>${product.name}</td>
				<td>${product.category}</td>
			</tr>
			<tr>
				<td><img src="./images/${product.id}.webp"
					height="100px" /></td>
				<td width="200px">${product.description}</td>
			</tr>
			<tr>
				<td>${product.price}</td>
				<td><form action="./cart" method="post">
						<input type="submit" name="productId" value="${product.id}"/>Buy</form></td>
			</tr>
		</table>
	</c:forEach>
</div>
<%@ include file="includes/footer.jsp"%>
