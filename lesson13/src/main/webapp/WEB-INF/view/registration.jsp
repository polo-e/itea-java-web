<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/header.jsp"%>
<%@ page isELIgnored = "false" %>

<div id="content">
    <center>
        <c:if test="${empty sessionScope.user && sessionScope.isShowAntiBotForm != true}">
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
                                        <td><input value="${sessionScope.password}" type="password" name="password">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>retype password:</td>
                                        <td><input value="${sessionScope.retypepassword}" type="password"
                                                   name="retypepassword"></td>
                                    </tr>
                                    <tr>
                                        <td>address:</td>
                                        <td>
                                            <select name="address">
                                                <option ${sessionScope.address==
                                                'UA' ? 'selected' : ''} value="UA">UA</option>
                                                <option ${sessionScope.address==
                                                'EN' ? 'selected' : ''} value="EN">EN</option>
                                                <option ${sessionScope.address==
                                                'RU' ? 'selected' : ''} value="RU">RU</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>sex:</td>
                                        <td>M<input ${empty sessionScope.sex || sessionScope.sex== 'M' ? 'checked' :
                                            ''} value="M" type="radio" name="sex">
                                            F<input ${sessionScope.sex== 'F' ? 'checked' : ''} value="F"
                                            type="radio" name="sex">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>comment:</td>
                                        <td><textarea name="comment" cols="20"
                                                      rows="10">${sessionScope.comment}</textarea></td>
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
                        <label><font color="orange">${sessionScope.errorMessage}</font></label>
                    </td>
                </tr>
            </table>
        </c:if>

        <c:if test="${not empty sessionScope.user}">
            <form action="./logout" method="post"><input type="submit" value="logout"/></form>
        </c:if>

        <c:if test="${sessionScope.isShowAntiBotForm == true}">
            <form action="./registration">
                <label><font color="brown">Approve that you are not a bot!</font></label>
                <br/>
                <div class="field">
                    <label><font color="orange">${sessionScope.generatedLetter}</font></label>
                    <div class="input"><input type="text" name="letters" placeholder="type the letters" value=""
                                              id="letters"/></div>
                </div>
                <div class="submit">
                    <button type="submit" formmethod="post">Send</button>
                </div>
            </form>
        </c:if>
    </center>
</div>
<%@ include file="includes/footer.jsp"%>