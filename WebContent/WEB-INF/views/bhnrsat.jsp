<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<%@include file="template/head.jsp" %>
<%@include file="template/header.jsp" %>

<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	var receivedRiskAsmtY = $("#receivedRiskAsmtY");
	if (receivedRiskAsmtY.attr("checked") != "undefined" && receivedRiskAsmtY.attr("checked") == "checked"){
		$("#assmtDate").prop('disabled', false);
		$("#toolNameUsed").prop('disabled', false);
	} else {
		$("#assmtDate").prop('disabled', true);
		$("#toolNameUsed").prop('disabled', true);
	}
	
	var typeOfService = $("#typeOfService").value;
	if (typeOfService == "Other services") {
		$("#otherService").prop('disabled', false);
	} else {
		$("#otherService").prop('disabled', true);
	}

	var reasonNonCompletion = $("#reasonNonCompletion").value;
	if (reasonNonCompletion == "Other reason") {
		$("#otherReason").prop('disabled', false);
	} else {
		$("#otherReason").prop('disabled', true);
	}
});
</script>
</head>

<div id="sub_header">Behavioral Health Network Program</div>
<div id="subheader1">Signed In: <c:out value="${username}"/></div>

<%@include file="template/menubhn.jsp" %>

<script type="text/javascript">
function validateCase()
{
}
function enableAssmtDate(){
	if(document.getElementById("receivedRiskAsmtY").checked){
		document.getElementById("assmtDate").disabled=false;
		document.getElementById("toolNameUsed").disabled=false;
	}
	else
	{
		document.getElementById("assmtDate").value="";
		document.getElementById("toolNameUsed").value="";
		document.getElementById("assmtDate").disabled=true;
		document.getElementById("toolNameUsed").disabled=true;
	}
}
function enableEnrollDate(){
	if(document.getElementById("enrolledRsatAftercareY").checked){
		document.getElementById("aftercareEnrollDate").disabled=false;
	}
	else
	{
		document.getElementById("aftercareEnrollDate").value="";
		document.getElementById("aftercareEnrollDate").disabled=true;
	}
}
function enableOtherService(){
	if(document.getElementById("typeOfService").value == "Other services"){
		document.getElementById("otherService").disabled=false;
	}
	else
	{
		document.getElementById("otherService").value="";
		document.getElementById("otherService").disabled=true;
	}
}
function enableDOC(){
	if(document.getElementById("compAllAftercareReqY").checked){
		document.getElementById("dateOfCompletion").disabled=false;
	}
	else
	{
		document.getElementById("dateOfCompletion").value="";
		document.getElementById("dateOfCompletion").disabled=true;
	}
}
function enableOtherReason(){
	if(document.getElementById("reasonNonCompletion").value == "Other reason"){
		document.getElementById("otherReason").disabled=false;
	}
	else
	{
		document.getElementById("otherReason").value="";
		document.getElementById("otherReason").disabled=true;
	}
}

</script>

<div id="main_content">

<div class="content">
<h2>Behavioral Health RSAT Form</h2>
<p>Please answer all questions on this form. 
  <br> 
  After you have completed the form click the &quot;SAVE&quot; 
  button and a new page will display a summary of completed fields. 
  You will have the option to come&quot;BACK&quot; or &quot;SAVE&quot; on the next page.</p>

<!--Enter the form-->
<form:form method="POST" action="confirmBhnRsat"> 
<form:input type="hidden" path="client.clientId"/>
<form:input type="hidden" path="client.inmateNum"/>
<form:input type="hidden" path="client.lastName"/>
<form:input type="hidden" path="client.programId"/> 



<div id="cmForm h3">
<fieldset>


<div id="clientInfo">
<h3>Client</h3>
<label>Inmate Number : </label>${inmateNum}
<label>Last Name : </label>${lastName}
</div>

<div id="cmForm">

<h3>RSAT</h3>
    <p>
    	<label>Did client receive an risk assessment?</label> 
        <form:radiobutton id="receivedRiskAsmtY" path="receivedRiskAsmt" value="Y" onchange="enableAssmtDate()"/>Yes 
	 	<form:radiobutton id="receivedRiskAsmtN" path="receivedRiskAsmt" value="N" onchange="enableAssmtDate()"/>No
	</p>
    <p>
    	<label>Risk Assessment Date:</label><form:input id="assmtDate" type="date" path="assmtDate" />
    </p>
    <p>
		<label>Name of the tool used:</label>
		<form:input path="toolNameUsed" id="toolNameUsed"/>
	</p>
    <p>
    	<label>High Crimeogenic risk and/or high substance abuse treatment needs?</label> 
        <form:radiobutton path="highCrimeogenicRisk" value="Y"/>Yes 
	 	<form:radiobutton path="highCrimeogenicRisk" value="N"/>No
	</p>
	<br/>
    <p>
    	<label>Completed individualized treatment plan?</label> 
        <form:radiobutton path="completedIndTrtPlan" value="Y"/>Yes 
	 	<form:radiobutton path="completedIndTrtPlan" value="N"/>No
	</p>
    <p>
    	<label>Enrolled in RSAT aftercare?</label> 
        <form:radiobutton id="enrolledRsatAftercareY"  path="enrolledRsatAftercare" value="Y" onchange="enableEnrollDate()"/>Yes 
	 	<form:radiobutton id="enrolledRsatAftercareN"  path="enrolledRsatAftercare" value="N" onchange="enableEnrollDate()"/>No
	</p>
    <p>
		<label>Enrollment Date:</label><form:input type="date" id="aftercareEnrollDate" path="aftercareEnrollDate" /></br>
	</p>
    <p>
    	<label>Continuity of care agreement OR reentry OR transitional plan?</label> 
        <form:radiobutton path="contCareAgmt" value="Y"/>Yes 
	 	<form:radiobutton path="contCareAgmt" value="N"/>No</br>
	</p>
	<br/>
    <p>
    	<label>Date service provided:</label> 
		<form:input type="date" path="dateOfService" />
	</p>
   
    <p>
      <label>Type of service provided: </label>
      <form:select path="typeOfService" id="typeOfService" onchange="enableOtherService()">
 	 		<form:options items="${serviceList}"/>
      </form:select>
    </p>
    <p>
		<label>Please explain other services:</label>
		<form:input path="otherService" id="otherService"/>
	</p>
    <p>
    	<label>Completed all requirements of aftercare portion?</label> 
        <form:radiobutton id="compAllAftercareReqY" path="compAllAftercareReq" value="Y" onchange="enableDOC()"/>Yes 
	 	<form:radiobutton id="compAllAftercareReqN" path="compAllAftercareReq" value="N" onchange="enableDOC()"/>No
	</p>
    <p>
		<label>Completion Date:</label><form:input type="date" id="dateOfCompletion" path="dateOfCompletion" /></br>
	</p>
    <p>
      	<label>Reason for non completion: </label>
	      <form:select path="reasonNonCompletion" id="reasonNonCompletion" onchange="enableOtherReason()">
	 	 		<form:options items="${nonCompletionList}"/>
	      </form:select>
    </p>
    <p>
		<label>Please explain other reason:</label>
		<form:input path="otherReason" id="otherReason"/>
	</p>
    <p>
    	<label>Date on which alcohol/drug test administered?</label> 
		<form:input type="date" path="dateOfDrugTest" />
	</p>
    <p>
    	<label>Tested positive for alcohol or illegal substances?</label> 
        <form:radiobutton path="testedPositiveSubstance" value="Y"/>Yes 
	 	<form:radiobutton path="testedPositiveSubstance" value="N"/>No
	</p>
    <p>
      <label>Enrolled in health care coverage: </label>
	      <form:select path="healthCare" id="healthCare">
	 	 		<form:options items="${healthCareList}"/>
	      </form:select>
    </p>
    <p>
    	<label>Is enrolled in medicaid / not medicare?</label> 
        <form:radiobutton path="enrolledInMedicaid" value="Y"/>Yes 
	 	<form:radiobutton path="enrolledInMedicaid" value="N"/>No
	</p>
	
    <br>  
    
</fieldset>
</div>   
<br>
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


