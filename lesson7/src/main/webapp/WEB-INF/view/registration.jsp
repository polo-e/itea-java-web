<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/header.jsp"%>
<%@ page isELIgnored = "false" %>

<div id="content">
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <table border="0">
                <tr>
                    <td>
                        <form action="./registration" method="post">
                            <center>
                                <table border="0">
                                    <tr>
                                        <td>name:</td>
                                        <td><input value="${sessionScope.name}" type="text" name="name"></td>
                                    </tr>
                                    <tr>
                                        <td>login:</td>
                                        <td><input value="${sessionScope.login}" type="text" name="login"></td>
                                    </tr>
                                    <tr>
                                        <td>password:</td>
                                        <td><input value="${sessionScope.password}" type="password" name="password"></td>
                                    </tr>
                                    <tr>
                                        <td>retype password:</td>
                                        <td><input value="${sessionScope.retypepassword}" type="password" name="retypepassword"></td>
                                    </tr>
                                    <tr>
                                        <td>address:</td>
                                        <td>
                                            <select name="address">
                                                <option ${sessionScope.address == 'DNR' ? 'selected' : ''} value="DNR">DNR</option>
                                                <option ${sessionScope.address == 'LNR' ? 'selected' : ''} value="LNR">LNR</option>
                                                <option ${sessionScope.address == 'TSD' ? 'selected' : ''} value="TSD">TSD</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>sex:</td>
                                        <td>M<input ${empty sessionScope.sex || sessionScope.sex == 'M' ? 'checked' : ''} value="M" type="radio" name="sex">
                                            F<input ${sessionScope.sex == 'F' ? 'checked' : ''} value="F" type="radio" name="sex"></td>
                                    </tr>
                                    <tr>
                                        <td>comment:</td>
                                        <td><textarea name="comment" cols="20" rows="10">${sessionScope.comment}</textarea></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="right"><input type="submit" value="send"></td>
                                    </tr>
                                </table>
                            </center>
                        </form>
                    </td>
                    <td>
                        ${sessionScope.errorMessage}
                    </td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <form action="./login" method="post">
                <input type="hidden" name="logout" /> <input type="submit"
                                                             value="logout" />
            </form>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="includes/footer.jsp"%>