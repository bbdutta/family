<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<html> 
<head> 
<title>Spring MVC Form Handling</title> 
</head> 
<body> 
<h2>Submitted Client Information</h2> 
<table> 
<tr> 
<td>First Name</td> 
<td>${firstName}</td> 
</tr> 
<tr> 
<td>Last Name</td> 
<td>${lastName}</td> 
</tr> 
<tr> 
<td>Inmate Number</td> 
<td>${inmateNumber}</td> 
</tr> 
<tr> 
<td>User Namer</td> 
<td>${userName}</td> 
</tr> 
<tr> 
<td>Password</td> 
<td>${password}</td> 
</tr> 
</table> 
</body>