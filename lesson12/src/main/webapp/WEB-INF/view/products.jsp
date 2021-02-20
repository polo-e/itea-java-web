<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="includes/header.jsp"%>
<%@ page isELIgnored="false"%>

<div id="content" xmlns="http://www.w3.org/1999/html">
    <center>
        <c:forEach var="product" items="${products}">
            <table>
                <tr>
                    <td width="250px">${product.name}</td>
                    <td width="250px">${product.category}</td>
                </tr>
                <tr>
                    <td><img src="./resources/images/${product.id}.webp"
                             height="100px" id="img${product.id}" onclick="zoomImg('img${product.id}')"/></td>
                    <td width="500px" id="desc${product.id}"
                        onclick="zoomText('desc${product.id}','${product.description}')">
                        ${fn:substring(product.description, 0, 100)}...
                    </td>
                </tr>
                <tr>
                    <td>${product.price}</td>
                    <td>
                        <img src="./resources/images/minus.png" width="20px" height="20px"
                             onclick="minus('it${product.id}')">
                        <input id="it${product.id}" type="text" size="2" value="1">
                        <img src="./resources/images/plus.png" width="20px" height="20px"
                             onclick="plus('it${product.id}')">
                        <input type="submit" name="productId" value="Buy" onclick="buy('${product.id}')"/>
                    </td>
                </tr>
            </table>
        </c:forEach>
    </center>
</div>
<%@ include file="includes/footer.jsp"%>
