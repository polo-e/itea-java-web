<%@ include file = "includes/header.jsp" %>
<% 
    String result = "";

    if(session.getAttribute("result") != null) {
        result = session.getAttribute("result").toString();
    }
%>

<body>
<label><br/><br/><%= result %><br/></label>
<br/>

<% 
    if (session.getAttribute("disableForm") == null) { 
%>

<form id="loginForm" action="/login" method="post">
    		<div class="field">
        		<label>Enter your login:</label>
        		<div class="input"><input type="text" name="login" value="" id="login"/></div>
    		</div>
		    <div class="field">
        		<a href="#" id="forgot">Forgot your password?</a>
        		<label>Enter your password:</label>
        		<div class="input"><input type="password" name="password" value="" id="pass"/></div>
    		</div>
			<div class="submit">
        		<button type="submit">Enter</button>
        		<label id="remember"><input name="" type="checkbox" value=""/> Remember me</label>
    		</div>
    	</form>
<hr/>

<% 
    } else { 
%>
    <center>        
        <form action="/login" method="post">
            <input type="hidden" name="logout"/>
            <input type="submit" value="Reload"/>
        </form>
    </center>
<% } %>

<%@ include file = "includes/footer.jsp" %>