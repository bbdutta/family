<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<%@include file="template/head.jsp" %>
<%@include file="template/header.jsp" %>

<div id="sub_header">Behavioral Health Network Program</div>
<div id="subheader1">Signed In: <c:out value="${username}"/></div>

<%@include file="template/menubhn.jsp" %>

<div id="main_content">

<div class="content">
<h2>Behavioral Health Rsat Form -- Verify</h2>

<!--Enter the form-->
<form:form method="POST" action="addBhnRsat"> 
<form:input type="hidden" path="client.clientId"/>
<form:input type="hidden" path="client.inmateNum"/>
<form:input type="hidden" path="client.lastName"/>
<form:input type="hidden" path="client.programId" value="2"/>
<form:input type="hidden" path="progAddDate"/>
<form:input type="hidden" path="orientationDate"/>
<form:input type="hidden" path="orientationFacility"/> 
<form:input type="hidden" path="receivedRiskAsmt"/>
<form:input type="hidden" path="assmtDate"/>
<form:input type="hidden" path="toolNameUsed"/>
<form:input type="hidden" path="highCrimeogenicRisk"/>
<form:input type="hidden" path="completedIndTrtPlan"/>
<form:input type="hidden" path="enrolledRsatAftercare"/>
<form:input type="hidden" path="aftercareEnrollDate"/>
<form:input type="hidden" path="contCareAgmt"/>
<form:input type="hidden" path="dateOfService"/>
<form:input type="hidden" path="typeOfService"/>
<form:input type="hidden" path="otherService"/>
<form:input type="hidden" path="compAllAftercareReq"/>
<form:input type="hidden" path="dateOfCompletion"/>
<form:input type="hidden" path="reasonNonCompletion"/>
<form:input type="hidden" path="otherReason"/>
<form:input type="hidden" path="dateOfDrugTest"/>
<form:input type="hidden" path="testedPositiveSubstance"/>
<form:input type="hidden" path="noOfUrineTest"/>
<form:input type="hidden" path="healthCare"/>
<form:input type="hidden" path="enrolledInMedicaid"/>
<form:input type="hidden" path="agenciesAssistedClient"/>
<form:input type="hidden" path="progCompDate"/>
<form:input type="hidden" path="haveInsurance"/>
<form:input type="hidden" path="insuranceType"/>

<font color="green"><b>${RSAT_SAVE_STATUS}</b></font>



<div id="cmForm h3">
<fieldset>
<div id="clientInfo">
<h3>Client</h3> 
<p><label>Inmate Number : </label>${command.client.inmateNum}</p>
<p><label>Last Name : </label>${command.client.lastName}</p>
</div>
<div id="cmForm">
<h3>Rsat</h3>

		   <p>Admission Date: ${command.progAddDate}</p>
		   <p>Orientation Date: ${command.orientationDate}</p>
		   <p>Orientation Facility: ${command.orientationFacility}</p>
	       <p>Did client receive an risk assessment? ${command.receivedRiskAsmt}</p> 
           <p>Risk Assessment Date: ${command.assmtDate}</p>
           <p>Name of the tool used: ${command.toolNameUsed}</p>
           <p>High Crimeogenic risk and/or high substance abuse treatment needs? ${command.highCrimeogenicRisk}</p>
           <p>Completed individualized treatment plan? ${command.completedIndTrtPlan}</p>
           <p>Enrolled in RSAT aftercare? ${command.enrolledRsatAftercare}</p>
           <p>Enrollment Date: ${command.aftercareEnrollDate}</p>
           <p>Continuity of care agreement OR reentry OR transitional plan? ${command.contCareAgmt}</p>
           <p>Date service provided: ${command.dateOfService}</p>
           <p>Type of service provided: ${command.typeOfService}</p>
           <p>Please explain other services: ${command.otherService}</p>
           <p>Completed all requirements of aftercare portion? ${command.compAllAftercareReq}</p>
           <p>Completion Date: ${command.dateOfCompletion}</p>
           <p>Reason for non completion: ${command.reasonNonCompletion}</p>
           <p>Please explain other reason: ${command.otherReason}</p>
           <p>Date on which alcohol/drug test administered: ${command.dateOfDrugTest}</p>
           <p>Tested positive for alcohol or illegal substances? ${command.testedPositiveSubstance}</p>
           <p>No. of urine tests: ${command.noOfUrineTest}</p>
           <p>Enrolled in health care coverage: ${command.healthCare}</p>
           <p>Is enrolled in medicaid / not medicare? ${command.enrolledInMedicaid}</p>
           <p>Name of agencies that assisted client: ${command.agenciesAssistedClient}</p>
           <p>Program Completion Date: ${command.progCompDate}</p>
	       <p>Does client have insurance? ${command.haveInsurance}</p> 
           <p>What type of insurance does the client have: ${command.insuranceType}</p>
	 
	
         <br>  
    
</fieldset>
     	<h3>Please click 'Save' button if the information above is correct.If not, <br>              
     	please click 'Back' button to modify the information  </h3>

</div>   
<br>
<br>
<input type="button" name="backButton" id="backButton" value="Back" onclick="moveBack()">
<input type="submit" name="submitButton" id="submitButton" value="Save">
<script type="text/javascript">
function moveBack() 
{
	document.getElementById("command").action="./backBhnRsat";
	document.getElementById("command").submit();
}
</script>
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


