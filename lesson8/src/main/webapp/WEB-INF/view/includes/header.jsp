<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--
Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License

Name       : Photoshoot 
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20110926

-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Photoshoot by FCT</title>
<link href="./css/style.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="./resources/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="./resources/js/jquery.poptrox-0.1.js"></script>
</head>
<body>
<div id="header" class="container">
    <div id="logo">
        <h1><a href="#">Мой магазин </a></h1>
        <p>я - двоечник и лентяй (<a href="http://www.freecsstemplates.org"></a></p>
    </div>
    <div id="menu">
        <ul>
            <li class="current_page_item"><a href="index.php">Главная</a></li>
            <li><a href="./products">Товары</a></li>
            <c:if test="${empty sessionScope.user}">
                <li><a href="./registration">Регистрация</a></li>
                <li><a href="./login">Вход</a></li>
            </c:if>
            <c:if test="${not empty sessionScope.user}">
                <label>${user.name}</label>
                <form action="./login" method="post">
                    <input type="hidden" name="logout"/>
                    <input type="submit" value="logout"/>
                </form>
            </c:if>
            <li><a href="cart.lsp">Корзина</a></li>
        </ul>
    </div>
</div>
<!-- end #header -->
<div id="poptrox">
    <!-- start -->
    <ul id="gallery">
        <li><a href="./images/img01_big.jpg"><img src="./images/img01.jpg" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
        <li><a href="./images/img02_big.jpg"><img src="./images/img02.jpg" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
        <li><a href="./images/img03_big.jpg"><img src="./images/img03.jpg" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
        <li><a href="./images/img04_big.jpg"><img src="./images/img04.jpg" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
        <li><a href="./images/img02_big.jpg"><img src="./images/img02.jpg" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
        <li><a href="./images/img01_big.jpg"><img src="./images/img01.jpg" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
        <li><a href="./images/img04_big.jpg"><img src="./images/img04.jpg" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
        <li><a href="./images/img03_big.jpg"><img src="./images/img03.jpg" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
    </ul>
    <br class="clear" />
    <script type="text/javascript">
        $('#gallery').poptrox();
        </script>
    <!-- end -->
</div>
<div id="page">
    <div id="bg1">
        <div id="bg2">
            <div id="bg3">