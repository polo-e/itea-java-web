<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored = "false" %>

<div id="sidebar">
    <table border=1>
        <tr>
            <td width="252" align="left">

                <font color=white>
                    Вы авторизировались как
                    <c:if test="${empty sessionScope.user}">Guest</c:if>
                    <br/>
                    <c:if test="${not empty sessionScope.user}">${sessionScope.user.name}</c:if>
                    В вашей корзине <span id="prodCount">${sessionScope.count}</span> товаров.
                </font>
            </td>
        </tr>
    </table>
    <h2>Боковое меню</h2>
    <ul>
        <c:forEach var="category" items="${categories}">
            <li><a href="./products?category=${category.id}">${category.name}</a></li>
        </c:forEach>
        <c:if test="${empty sessionScope.user}">
            <li><a href="./registration">Регистрация</a></li>
            <li><a href="./login">Вход</a></li>
        </c:if>
        <br/>
        <li><a href="./cart">Корзина</a></li>
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
<script>
    function plus(id) {
		var it = document.getElementById(id);
		it.value = +it.value + 1;
	}

	function minus(id) {
		var it = document.getElementById(id);
		it.value = +it.value - 1;
	}

    function buy(id) {
        var productId = id;
        var qnt = document.getElementById('it' + productId).value;

        $.ajax({
            type: "POST",
            url: "./cart",
            data: "productId=" + productId + "&count=" + qnt,
            success: function(data) {
                var elem = document.getElementById("prodCount");
                elem.innerHTML = data;
            }
        });
    }

     function del(id) {
        var newCount = document.getElementById("cnt" + id).value;

        $.ajax({
            type: "POST",
            url: "./cart",
            data: "productId=-" + id + "&count=" + newCount,
            success: function(data) {
                var elem = document.getElementById("prodCount");
                elem.innerHTML = data;

                location.reload();
            }
        });
    }

    function zoomImg(id) {
        var img = document.getElementById(id);
        if(img.style.width === '' || img.style.width === "400px") {
            img.style.width = "100px";
            img.style.height = "100px";
        } else {
            img.style.width = "400px";
            img.style.height = "400px";
        }
    }

    function zoomText(id, desc) {
        var v1 = document.getElementById(id);
        var length = v1.textContent.length;

        if (length === desc.length){
            v1.innerHTML = v1.textContent.slice(0,100) + "...";
        } else {
            v1.innerHTML = desc;
        }
    }
</script>
</html>