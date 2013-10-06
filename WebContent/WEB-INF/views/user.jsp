<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<html> 
<head> 
<title>User Information</title> 
</head> 
<body> 
<h2>User Information</h2> 
<form:form method="POST" action="createUser"> 
<table> 
<tr> 
<td><form:label path="firstName">First Name</form:label></td> 
<td><form:input path="firstName" value="${command.getFirstName()}"/></td> 
</tr> 
<tr> 
<td><form:label path="middleName">Middle Name</form:label></td> 
<td><form:input path="middleName"  value="${command.getMiddleName()}"/></td> 
</tr> 
<tr> 
<td><form:label path="lastName">Last Name</form:label></td> 
<td><form:input path="lastName"  value="${command.getLastName()}"/></td> 
</tr> 
<tr> 
<td><form:label path="email">Email</form:label></td> 
<td><form:input path="email"  value="${command.getEmail()}"/></td> 
</tr> 
<tr> 
<td><form:label path="phoneNum">Phone Number</form:label></td> 
<td><form:input path="phoneNum"  value="${command.getPhoneNum()}"/></td> 
</tr> 
<tr> 
<td colspan="2"> <input type="submit" value="Submit"/> </td> 
</tr> 
</table> 
</form:form> 
</body> 
</html>