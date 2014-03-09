<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<%@include file="template/head.jsp" %>
<%@include file="template/header.jsp" %>

<div id="sub_header">Behavioral Health Network Program</div>
<div id="subheader1">Signed In: <c:out value="${username}"/></div>

<%@include file="template/menuadmin.jsp" %>

<div id="main_content">

<div class="content">
<h2>Admin Home Page</h2>
<p>This page is where you can perform user administration.</p>
<p>There are three functions:
   <ul>
   <li> <a href="addUser">Add User</a></li>
   <li> <a href="addUserRole">Add Role to User</a></li>
   <li> <a href="addUserProgram">Add Program to User</a></li>
   </ul>
   </p>
</div>

<div class="menu"></div>

<div id="clear"></div>

</div>

<div id="main_content_bottom">
</div>

<%@include file="template/footer.jsp" %>

</body>
</html>
