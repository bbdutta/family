<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<%@include file="template/head.jsp" %>
<%@include file="template/header.jsp" %>

<div id="sub_header">User Administration</div>
<div id="subheader1">Signed In: <c:out value="${username}"/></div>

<%@include file="template/menuadmin.jsp" %>

<div id="main_content">

<div class="content">
<h2>Assign Role to User</h2>
<p>Please enter all fields in this form. This form assigns a role to user. 
  <br> 
  After you have completed the form click the &quot;SAVE&quot; 
  button.</p>

<!--Enter the form-->
<form:form method="POST" action="createUserRole">

<fieldset>
	<p><label>Select User:</label>
   	<form:select path="userId" id="userId">
   		<form:options items="${userList}" />
	</form:select>
   	<p><label>Select Role:</label>
   	<form:select path="roleId" id="roleId">
   		<form:option value="2" label="Data Entry"/>
	</form:select>
	</p>
   
</fieldset>

<input type="submit" name="submitButton" id="cmsubmit" value="Save" />

<br>
<c:if test="${ROLE_SAVE_STATUS == 'ROLE_SAVE_STATUS'}">
	<font color="green"><b>Role added successfully</b></font>
	<p><label>Role ID:</label>${roleId}
</c:if>
<c:if test="${ROLE_SAVE_STATUS == 'ROLE_ALREADY_EXISTS'}">
	<font color="green"><b>This Role already Exists for this user.</b></font>
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
