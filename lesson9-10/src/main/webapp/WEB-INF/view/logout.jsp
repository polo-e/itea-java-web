<%@ include file = "includes/header.jsp" %>
<%@ taglib prefix='c2' uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>

<center>
	<label>Hello, ${user.name}</label>
	<br/>
	<label>${sessionScope.result}</label>
	<form action="./login" method="post">
		<input type="hidden" name="logout"/>
		<input type="submit" value="logout"/>
	</form>
</center>

<%@ include file = "includes/footer.jsp" %>
