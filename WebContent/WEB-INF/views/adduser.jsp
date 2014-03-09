<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<%@include file="template/head.jsp" %>
<%@include file="template/header.jsp" %>

<div id="sub_header">User Administration</div>
<div id="subheader1">Signed In: <c:out value="${username}"/></div>

<%@include file="template/menuadmin.jsp" %>

<script type="text/javascript">
function validateCase() 
{
	if(document.getElementById("lastName").value=="")
	{
		alert("please enter the Last Name.");
		return false;
	} 
	if(document.getElementById("firstName").value=="")
	{
		alert("please enter the First Name.");
		return false;
	} 
	if(document.getElementById("username").value=="")
	{
		alert("please enter the Username.");
		return false;
	} 
	if(document.getElementById("password").value=="")
	{
		alert("please enter the Password.");
		return false;
	} 
	
}
</script>

<div id="main_content">

<div class="content">
<h2>Add User</h2>
<p>Please enter all fields in this form. This form creates a user. 
  <br> 
  After you have completed the form click the &quot;SAVE&quot; 
  button.</p>

<!--Enter the form-->
<form:form method="POST" action="createUser">

<fieldset>
	<p><label>First Name:</label>
   	<form:input path="firstName" id="firstName"/></p>
   	<p><label>Last Name:</label>
   	<form:input path="lastName" id="lastName"/></p>
   
</fieldset>

<input type="submit" name="submitButton" id="cmsubmit" value="Save" onclick="return validateCase()">

<br>
<c:if test="${USER_SAVE_STATUS == 'USER_SAVE_STATUS'}">
	<font color="green"><b>User added successfully</b></font>
	<p><label>Username:</label>${userName}
   	<p><label>Password:</label>${password}
</c:if>
<br>

</form:form> 
</div>
<!--Main Menu-->
<div class="menu">
</div>

<div id="clear"></div>
</div>

<div id="main_content_bottom">
</div>

<%@include file="template/footer.jsp" %>

</body>
</html>
