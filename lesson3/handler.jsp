<%@ page import="java.util.Date" %>
<%! private int counter = 0;%>
<%! private long blockedTime = 0;%>
<%! private long blockedDiffTime = 10*1000;%>
<%@ include file = "includes/header.jsp" %>
<%
	String login = request.getParameter("login");
	String password = request.getParameter("password");
	boolean isShowForm = true;

	if (login != null && password != null) {
	String color = "#ff0000";
	String access = "denied";
	if(login.equals("admin") && password.equals("123")) {
		color = "#00ff00";
		access = "granted";
		isShowForm = false;
	} else {
		counter++;
	}

	if(counter >= 3) {
		isShowForm = false;
		out.write("Access blocked");
		blockedTime = new Date().getTime();
	}

	out.write(String.format("<font color='%s'>Access %s </font>", color, access));
	}

	long current = new Date().getTime();

	if(counter >= 3) {
		out.write("<br/> T:" + ((current - blockedTime) / 1000.0));
		if(current - (blockedTime + blockedDiffTime) >= 0) {
			counter = 0;
			blockedTime = 0;
			isShowForm = true;
		}
	}

	out.write("<br/>" + blockedTime);
	out.write("<br/>" + current);

	if (isShowForm) {
%>

<form id="loginForm" action="" method="post">

	<div class="field">
		<label>Enter your login:</label>
		<div class="input"><input type="text" name="login" value="" id="login" /></div>
	</div>

	<div class="field">
		<a href="#" id="forgot">Forgot your password?</a>
		<label>Enter your password:</label>
		<div class="input"><input type="password" name="password" value="" id="pass" /></div>
	</div>

	<div class="submit">
		<button type="submit">Enter</button>
		<label id="remember"><input name="" type="checkbox" value="" /> Remember me</label>
	</div>

</form>

<%}
	out.write("Count:" + counter);
%>
<%@ include file = "includes/footer.jsp" %>