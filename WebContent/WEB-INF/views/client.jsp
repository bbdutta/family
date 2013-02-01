<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<html> 
<head> 
<title>Client Information</title> 
</head> 
<body> 
<h2>Client Information</h2> 
<form:form method="POST" action="addClient"> 
<table> 
<tr> 
<td><form:label path="firstName">First Name</form:label></td> 
<td><form:input path="firstName" /></td> 
</tr> 
<tr> 
<td><form:label path="lastName">Last Name</form:label></td> 
<td><form:input path="lastName" /></td> 
</tr> 
<tr> 
<td><form:label path="inmateNum">Inmate Number</form:label></td> 
<td><form:input path="inmateNum" /></td> 
</tr> 
<tr> 
<td><form:label path="gender">Gender (M/F)</form:label></td> 
<td><form:input path="gender" /></td> 
</tr> 
<tr> 
<td><form:label path="dob">Date Of Birth</form:label></td> 
<td><form:input type="date" path="dob" /></td> 
</tr> 
<tr> 
<td><form:label path="educationLevel">Education Level</form:label></td> 
<td><form:input path="educationLevel" /></td> 
</tr> 
<tr> 
<td colspan="2"> <input type="submit" value="Submit"/> </td> 
</tr> 
</table> 
</form:form> 
</body> 
</html>