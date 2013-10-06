<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<%@include file="template/head.jsp" %>
<%@include file="template/header.jsp" %>

<div id="sub_header">Behavioral Health Network Program</div>
<div id="subheader1">Signed In:  <c:out value="${username}"/></div>

<%@include file="template/menubhn.jsp" %>

<script type="text/javascript">
function validateCase() {
	
	
 	if(document.getElementById("isEmployed1").checked==false && document.getElementById("isEmployed2").checked==false)
	{
		alert("Did client obtain employment?");
		return false;
	}
 	if(document.getElementById("jobRetention301").checked==false && document.getElementById("jobRetention601").checked==false && document.getElementById("jobRetention901").checked==false && document.getElementById("jobRetention90Plus1").checked==false)
	{
		alert("Please select the Job Retention");
		return false;
	}
 	if(document.getElementById("socialReunification1").checked==false && document.getElementById("socialReunification2").checked==false)
	{
		alert("Please Select SocialReunification");
		return false;
	}
 	if(document.getElementById("isEmployed2").checked==true){
 		return true;
 	}
 	if(document.getElementById("dateOfHire").value=="")
	{
		alert("Please select the Date of Hire");
		return false;
	}
 	if(document.getElementById("isFulltime1").checked==false && document.getElementById("isParttime1").checked==false)
	{
		alert("Please select the Employment Status");
		return false;
	}
 	if(document.getElementById("employerName").value=="")
	{
		alert("Please enter Employer Name.");
		return false;
	}
 	if(document.getElementById("hourlyRate").value=="")
	{
		alert("Please enter Hourly Rate.");
		return false;
	} 

 	var value = Number(document.getElementById("hourlyRate").value);
 	if (Math.abs(value) != value) 
 	{
 		alert("Please enter valid Hourly rate : Ex(12.00)");
 		return false;
 	}	

}
function enableEmplymentDet() {
	document.getElementById("dateOfHire").disabled=false;
	document.getElementById("hourlyRate").disabled=false;
	document.getElementById("employerName").disabled=false;
	enableFullPartTime();
}
function disableEmplymentDet() {
	document.getElementById("dateOfHire").disabled=true;
	document.getElementById("hourlyRate").disabled=true;
	document.getElementById("employerName").disabled=true;
	document.getElementById("dateOfHire").value="";
	document.getElementById("hourlyRate").value="";
	document.getElementById("employerName").value="";
	disableFullPartTime();
}
function checkFullTime() {
	document.getElementById("isFulltime1").checked=true;
	document.getElementById("isParttime1").checked=false;
}
function checkPartTime() {
	document.getElementById("isFulltime1").checked=false;
	document.getElementById("isParttime1").checked=true;
}
function disableFullPartTime() {
	document.getElementById("isFulltime1").checked=false;
	document.getElementById("isParttime1").checked=false;
	document.getElementById("isFulltime1").disabled=true;
	document.getElementById("isParttime1").disabled=true;
}
function enableFullPartTime() {
	document.getElementById("isFulltime1").disabled=false;
	document.getElementById("isParttime1").disabled=false;
}
</script>

<div id="main_content">

<div class="content">
<h2>Behavioral Health Case Management Form</h2>
<p>Please answer all questions on the form that apply to this client. 
Any information that can't be filled in at this time may be filled in at a later date.  
  <br> 
  After you have completed the form click the &quot;SAVE&quot; 
  button and a new page will display a summary of completed fields. 
  You will have the option to &quot;EDIT&quot; or &quot;CONFIRM&quot; on the next page.</p>




<!--Enter the form-->
<form:form method="POST" action="confirmBhnCase">
<form:input type="hidden" path="clientId"/>
<form:input type="hidden" path="programId" value="2"/>
<form:input type="hidden" path="inmateNum" value="${inmateNum}"/>
<font color="green"><b>${CASE_SAVE_STATUS}</b></font>

<fieldset>
<div id="clientInfo">
Client: 
<label>Inmate Number</label>${inmateNum}
<label>Last Name</label>${lastName}
</div>

<div id="selfsuff">
<h3>Self Sufficiency</h3>
		<h4>Benefits/Entitlements</h4>
		  <label>Needed benefits/entitlements?</label>
          <form:checkbox path="neededBenefits" value="Y"/> 
          <label>Applied benefits/entitlements?</label>
          <form:checkbox path="appliedBenefits" value="Y"/>
          <label>Received benefits/entitlements?</label>
          <form:checkbox path="receivedBenefits" value="Y"/>
		  <br>
	  <h4>Identification</h4>
          <label>Needed ID?</label>
          <form:checkbox path="neededId" value="Y"/>
          <label>Applied ID?</label>
          <form:checkbox path="appliedId" value="Y"/>
          <label>Received ID?</label>
          <form:checkbox path="receivedId" value="Y"/>
</div>
	
</fieldset>

<input type="submit" name="submitButton" id="cmsubmit" value="Save" onclick="return validateCase()">
</form:form> 

</div>

<!--Main Menu-->
<div class="menu"></div>

<div id="clear"></div>
</div>

<div id="main_content_bottom">
</div>

<%@include file="template/footer.jsp" %>

</body>
</html>

