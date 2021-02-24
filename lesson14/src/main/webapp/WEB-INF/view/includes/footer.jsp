<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored = "false" %>

<c:if test="${empty sessionScope.count}">
    <c:set var="count" value="0" scope="session"/>
</c:if>

<div id="sidebar">
    <table border=1>
        <tr>
            <td width="270" align="left">

                <font color=white>
                    Вы авторизировались как
                    <c:if test="${empty sessionScope.user}">
                        <label><font color="orange">Гость</font></label>
                    </c:if>
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
            <li><a href="./registration">Регистрация</a></li>
            <li><a href="./login">Вход</a></li>
            <li><a href="./cart">Корзина</a></li>
    </ul>
</div>
</div>
</div>
</div>
</div>
<div id="footer">
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

        var request = new Object();
        request.productId = id;
        request.count = document.getElementById('it' + id).value;

        $.ajax({
            type: "POST",
            url: "./api/v1/cart/add",
            dataType : "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(request),
            success: function(data) {
                var elem = document.getElementById("prodCount");
                elem.innerHTML = data.count;
            }
        });
    }

    function del(id) {

        var curCount = document.getElementById('cnt' + id).value;
        var request = new Object();
        request.productId = id;
        request.count = curCount;

        $.ajax({
            type: "DELETE",
            url: "./api/v1/cart/del",
            dataType : "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(request),
            success: function(data) {
                var elem = document.getElementById("prodCount");
                elem.innerHTML = data.total;
                var elem2 = document.getElementById('cnt' + id);
                elem2.value = data.count;

                if (data.reloadPage == true){
                    location.reload();
                }
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