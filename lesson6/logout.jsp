<%@ include file = "includes/header.jsp" %>
<% 
	String name = "Guest";
	String registrationResult = "";

	if(session.getAttribute("name") != null) {
		name = session.getAttribute("name").toString();
	}
	if(session.getAttribute("registrationResult") != null) {
		registrationResult = session.getAttribute("registrationResult").toString();
	}
%>


<center>
	<label>Hello, <%=name %></label>
	<br/>
	<label><%=registrationResult %></label>
	<form action="/login" method="post">
		<input type="hidden" name="logout"/>
		<input type="submit" value="logout"/>
	</form>
</center>

<%@ include file = "includes/footer.jsp" %>
