<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/header.jsp"%>
<%@ page isELIgnored = "false" %>

<div id="content">
	<center>
		<label>${sessionScope.result}</label>
	</center>

	<c:choose>
		<c:when test="${empty sessionScope.user && sessionScope.disableForm != true}">
			<form action='./login' method="post">
				<center>
					<table border='0'>
						<tr>
							<td>login:</td>
							<td><input type='text' name='login'></td>
						</tr>
						<tr>
							<td>password:</td>
							<td><input type='password' name='password'></td>
						</tr>
						<tr>
							<td></td>
							<td align='right'><input type='submit' value='send'></td>
						</tr>
					</table>
				</center>
			</form>
		</c:when>
	</c:choose>
</div>
<%@ include file="includes/footer.jsp"%>
