<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c3" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored = "false" %>
            
                <div id="sidebar">
                    <table border=1>
                    <tr>
                    <td width="252" align="left">
                    
                    <font color=white>
                    Вы авторизировались как 
                    <c3:if test="${empty sessionScope.user}">Guest</c3:if><br />
                    <c3:if test="${not empty sessionScope.user}">${sessionScope.user.name}</c3:if>
                    В вашей корзине 0 товаров.
                    </font>
                    </td>
                    </tr>
                    </table>
                    <h2>Боковое меню</h2>
                    <ul>
                        <li><a href="products.php">Категория 1</a></li>
                        <li><a href="products.php">Категория 2</a></li>
                        <li><a href="products.php">Категория 3</a></li>
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