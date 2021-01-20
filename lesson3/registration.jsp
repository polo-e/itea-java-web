<%@ include file = "includes/header.jsp" %>
<%@ page import="ua.itea.web.*" %>
<%! private int counter = 0;%>
<%! private String generatedLetter;%>
<br/><br/>

<% 
	String name = request.getParameter("name");
	String login = request.getParameter("login");
	String password = request.getParameter("password");
	String retypepassword = request.getParameter("retypepassword");
	String address = request.getParameter("address");
	String sex = request.getParameter("sex");
	String comment = request.getParameter("comment");
	String agree = request.getParameter("agree");
	String sendLetters  = request.getParameter("letters");

	StringBuilder errorMessage = new StringBuilder();
	boolean isShownForm = true;
	boolean isShownAntiBotForm = false;
	boolean error = false;

	PasswordValidator passwordValidator = new PasswordValidator();
	EmailValidator emailValidator = new EmailValidator();

	if(name != null) {
		errorMessage.append("<ul>"); 

		if(name.isEmpty()) {
			error = true;
			errorMessage.append("<li>Name field is empty</li>");

		} 

		if(login.isEmpty()) {
			error = true;
			errorMessage.append("<li>Login field is empty</li>");
		} else if(!emailValidator.isValid(login)) {
			error = true;
			errorMessage.append("<li>Entered incorrect login!");
		}

		if(password.isEmpty()) {
			error = true;
			errorMessage.append("<li>Password field is empty</li>");

		} else if (!passwordValidator.isValid(password)){
			error = true;
			errorMessage.append("<li>Entered incorrect password! It must contains at least 1 digit, 1 lowercase, 1 uppercase & special character, mim 8 & max 20 characters</li>");
		}

		if(!password.equals(retypepassword)) {
			error = true;
			errorMessage.append("<li>Retype password error</li>");
		}

		if(address.isEmpty()) {
			error = true;
			errorMessage.append("<li>Address field is empty</li>");
		}

		if(sex.isEmpty()) {
			error = true;
			errorMessage.append("<li>Sex field is empty</li>");
		}

		if(comment.isEmpty()) {
			error = true;
			errorMessage.append("<li>Comment field is empty</li>");
		}

		if(agree == null) {
			error = true;
			errorMessage.append("<li>Agree field is empty</li>");
		}

		errorMessage.append("<ul>");

		if(!error) {

			isShownForm = false;
			counter++;

			if (counter >= 3){
	    
	    		isShownAntiBotForm = true;
	            if (generatedLetter != null && sendLetters != null) { 

	            	if(generatedLetter.replaceAll(" ", "").equals(sendLetters.replaceAll(" ", ""))){

						out.write("Registration successfull");
						counter = 0;
						isShownAntiBotForm = false;
	           		} 
	        	}

	        	char[] array = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        		StringBuilder sb = new StringBuilder();

		        for (int i = 0; i < 6; i++){
		            int j = (int)(Math.random()*26);
		            char s = (j > 20) ? array[j] : Character.toUpperCase(array[j]);
		            sb.append(s + " ");
		        }
     			generatedLetter = sb.toString();

			} else {
				out.write("Registration successfull");	
		}	
		}
	}

	out.write("<br/>Counter: " + counter);

	if(isShownForm) {
%>

<table border="0">
<tr><td>
<form action="">
	<center>
		<table border="0">
		<tr><td>name:</td><td><input value="<%=(name != null ? name : "") %>" type="text" name="name"></td></tr>
		<tr><td>login:</td><td><input value="<%=(login != null ? login : "") %>" type="text" name="login"></td></tr>
		<tr><td>password:</td><td><input value="<%=(password != null ? password : "") %>" type="password" name="password"></td></tr>
		<tr><td>retype password:</td><td><input value="<%=(retypepassword != null ? retypepassword : "") %>" type="password" name="retypepassword"></td></tr>
		<tr><td>address:</td><td><select name="address">
			<option <%= (address != null && address.equals("DNR") ? "selected": "") %> value="DNR">DNR</option>
			<option <%= (address != null && address.equals("LNR") ? "selected": "") %> value="LNR">LNR</option>
			<option <%= (address != null && address.equals("TSD") ? "selected": "") %> value="TSD">TSD</option>
		</select></td></tr>
		<tr><td>sex:</td><td>M<input <%= (sex == null || sex.equals("M") ? "checked" : "")%> value="M" type="radio" name="sex">
							 F<input <%= (sex != null && sex.equals("F") ? "checked" : "")%>value="F" type="radio" name="sex"></td></tr>
		<tr><td>comment:</td><td><textarea name="comment" cols="20" rows="10"><%= comment%></textarea></td></tr>
		<tr><td>I agree to install amiga browser:</td><td><input type="checkbox" name="agree"></td></tr>
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

<%} else if(isShownAntiBotForm) {
%>

<form id="loginForm" action="" method="post">

	<h3>Approve that you are not a bot</h3>

	<div class="field">
		<label><%=generatedLetter%></label>
		<div class="input"><input type="text" name="letters" placeholder="type the letters" value="" id="letters" /></div>
	</div>

	<div class="submit">
		<button type="submit">Send</button>	
	</div>

</form>

<%}%>

<%@ include file = "includes/footer.jsp" %>