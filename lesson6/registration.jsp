<%@ include file = "includes/header.jsp" %>
<%@ page import="ua.itea.web.*" %>

<br/><br/>

<% 
    String name = "";
    String login = "";
    String password = "";
    String retypepassword = "";
	String address = "";
	String sex = "M";
	String comment = "";
	String agree = "";
	String sendLetters = "";
	String errorMessage = "";
	String generatedLetter = "";	
	
	if (session.getAttribute("name") != null){
		name = session.getAttribute("name") + "";
	} 
	if (session.getAttribute("login") != null){
		login = session.getAttribute("login") + "";
	} 
	if (session.getAttribute("password") != null){
		password = session.getAttribute("password") + "";
	} 
	if (session.getAttribute("retypepassword") != null){
		retypepassword = session.getAttribute("retypepassword") + "";
	}
	if (session.getAttribute("address") != null){
		address = session.getAttribute("address") + "";
	}
	if (session.getAttribute("sex") != null){
		sex = session.getAttribute("sex") + "";
	}	
	if (session.getAttribute("comment") != null){
		comment = session.getAttribute("comment") + "";
	}
	if (session.getAttribute("agree") != null){
		agree = session.getAttribute("agree") + "";
	}
	if (session.getAttribute("sendLetters") != null){
		sendLetters = session.getAttribute("sendLetters") + "";
	}
	if (session.getAttribute("errorMessage") != null){
		errorMessage = session.getAttribute("errorMessage") + "";
	}
	if (session.getAttribute("generatedLetter") != null){
		generatedLetter = session.getAttribute("generatedLetter") + "";
	}

	if (session.getAttribute("isShowAntiBotForm") == null) {	
%>

<table border="0">
<tr><td>
<form action="/registration" method="post">
	<center>
		<table border="0">
		<tr><td>name:</td><td><input value="<%=(name != null ? name : "") %>" type="text" name="name"></td></tr>
		<tr><td>login:</td><td><input value="<%=(login != null ? login : "") %>" type="text" name="login"></td></tr>
		<tr><td>password:</td><td><input value="<%=(password != null ? password : "") %>" type="password" name="password"></td></tr>
		<tr><td>retype password:</td><td><input value="<%=(retypepassword != null ? retypepassword : "") %>" type="password" name="retypepassword"></td></tr>
		<tr><td>address:</td><td><select name="address">
			<option <%= (address != null && address.equals("DNR") ? "selected" : "") %> value="DNR">DNR</option>
			<option <%= (address != null && address.equals("LNR") ? "selected" : "") %> value="LNR">LNR</option>
			<option <%= (address != null && address.equals("TSD") ? "selected" : "") %> value="TSD">TSD</option>
		</select></td></tr>		
		<tr><td>sex:</td><td>M<input <%= (sex.equals("M") ? "checked" : "")%> value="M" type="radio" name="sex">
							 F<input <%= (sex.equals("F") ? "checked" : "")%> value="F" type="radio" name="sex"></td></tr>				
		<tr><td>comment:</td><td><textarea name="comment" cols="20" rows="10"><%= comment%></textarea></td></tr>
		<tr><td>I agree to install amiga browser:</td><td><input type="checkbox" <%=agree%> name="agree"></td></tr>
		<tr><td></td><td align="right"><input type="submit" value="send"></td></tr>
		</table>
	</center>
</form>
</td><td>
	
<% 
	out.write(errorMessage.toString());
%>

</td></tr>
</table>

<% 
	} else {	
%>

<form id="loginForm" action="/registration">

	<h3>Approve that you are not a bot</h3>

	<div class="field">
		<label><%=generatedLetter%></label>
		<div class="input"><input type="text" name="letters" placeholder="type the letters" value="" id="letters" /></div>
	</div>

	<div class="submit">
		<button type="submit">Send</button>	
	</div>

</form>

<%
	}
%>

<%@ include file = "includes/footer.jsp" %>