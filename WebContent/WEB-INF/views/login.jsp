<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<%@include file="template/head.jsp" %>
<%@include file="template/header.jsp" %>

<div id="sub_header">Welcome to FRE Web Application System</div>
  
<div id="main_content">

<div class="content">
<h2>Log In</h2>
<p>Please enter your username and password in the fields below and click submit.<br> If you forgot
your password click the link under the 'Submit' button. </p>
<br>
<br>
<br>
<div id="loginForm">
<form:form method="POST" action="validateLogin">
<table>
<tr>
<td >USER NAME</td>
<td><form:input path="userName" /></td>

</tr>

<tr>
<td>PASSWORD</td>
<td><form:input type="password" path="password" /></td>

</tr>
</table>

<input type="submit" name="loginButton" id="loginButton" value="Submit">

</form:form>

<div id="getPassword"><a href="#" title="Please contact the administrator to retrieve your password.">Forgot Password</a></div>
<br>
<br>
<br>
</div>
</div>
<div class="menu"></div>
<br>
<br>
<br>
<div id="clear"></div>

</div>

<div id="main_content_bottom"></div>
 
<!-- end .content -->
<%@include file="template/footer.jsp" %>


</body>
</html>


