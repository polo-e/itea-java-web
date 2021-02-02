<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/header.jsp"%>
<%@ page isELIgnored = "false" %>

<div id="content">
    <h2>Добро пожаловать в магазин !!!</h2>
    <p> Наши профессиональные менеджеры помогут быстро и качественно подобрать нужный Вам товар. Звоните круглосуточно!
        Втюхаем все!</p>
    <p>К сожалению, Вы можете наткнуться на схожий дизайн в интернете - это все потому,что автор лентяй и двоечник и не
        знает, как поменять шаблон.</p>
    <p>Надеемся, что это не скажется на Вашей покупательной способности.</p>
</div>
<div id="sidebar">
    <h2>Товары</h2>
    <ul>
        <li><a href="products.php">Категория 1</a></li>
        <li><a href="products.php">Категория 2</a></li>
        <li><a href="products.php">Категория 3</a></li>
        <li><a href="./registration">Регистрация</a></li>
        <li><a href="./login">Вход</a></li>
        <li><a href="cart.php">Корзина</a></li>
    </ul>
</div>
<%@ include file="includes/footer.jsp"%>
