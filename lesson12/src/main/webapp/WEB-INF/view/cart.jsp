<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/header.jsp"%>
<%@ page isELIgnored="false"%>
<div id="content">
    <center>
        <c:if test="${empty sessionScope.cart}">
			<label><font color="orange">Cart is empty!</font></label>
		</c:if>
        <c:forEach var="list" items="${sessionScope.cart}">
            <table>
                <tr>
                    <td width="250px">${list.key.name}</td>
                    <td width="250px">${list.key.category}</td>
                </tr>
                <tr>
                    <td><img src="./resources/images/${list.key.id}.webp"
                             height="100px"/></td>
                    <td width="500px">${list.key.description}</td>
                </tr>
                <tr>
                    <td>${list.key.price}</td>
                    <td>
                        <input id="cnt${list.key.id}" type="text" name="count" value="${list.value}"></input>
                        <input id="del${list.key.id}" type="submit" name="productId" value="Delete"
                               onclick="del('${list.key.id}')"/>
                    </td>
                </tr>
            </table>
        </c:forEach>
    </center>
</div>
<%@ include file="includes/footer.jsp"%>