<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c3" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored = "false" %>
            
                <div id="sidebar">
                    <table border=1>
                    <tr>
                    <td width="252" align="left">
                    
                    <font color=white>
                    Вы авторизировались как 
                    <c3:if test="${empty sessionScope.user}">Guest</c3:if><br/>
                    <c3:if test="${not empty sessionScope.user}">${sessionScope.user.name}</c3:if>
                    В вашей корзине ${sessionScope.cartSize} товаров.
                    </font>
                    </td>
                    </tr>
                    </table>
                    <h2>Боковое меню</h2>
                    <ul>
                        <c:forEach var="category" items="${categories}">
                            <li><a href="./products?category=${category.id}">${category.name}</a></li>
                        </c:forEach>
                        <c3:if test="${empty sessionScope.user}">
                            <li><a href="./registration">Регистрация</a></li>
                            <li><a href="./login">Вход</a></li>
                        </c3:if><br />
                        <li><a href="cart.php">Корзина</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="footer">
    <p>Copyright (c) by Бондаренко Антон</p>
</div>
<!-- end #footer -->
</body>
</html>