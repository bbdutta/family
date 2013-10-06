<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<%@include file="template/head.jsp" %>
<%@include file="template/header.jsp" %>

<div id="sub_header">Fresh Start Program</div>
<div id="subheader1">Signed In: <c:out value="${username}"/></div>

<%@include file="template/menu.jsp" %>

<style>
table
{
border-collapse:collapse;
}
table, td, th
{
border:1px solid black;
}
</style>
<body>

<div id="main_content">

<div class="content">
<h2>Fresh Start CaseManagement Form--Verify</h2>

<form:form method="POST" action="addCase">
<form:input type="hidden" path="clientId"/>
<form:input type="hidden" path="inmateNum"/>
<form:input type="hidden" path="programId" value="1"/>



<form:input type="hidden" path="neededBenefits"/>
<form:input type="hidden" path="appliedBenefits"/>
<form:input type="hidden" path="receivedBenefits"/>
<form:input type="hidden" path="neededId"/>
<form:input type="hidden" path="appliedId"/>
<form:input type="hidden" path="receivedId"/>
<form:input type="hidden" path="isEmployed"/>
<form:input type="hidden" path="dateOfHire"/>
<form:input type="hidden" path="isFulltime"/>
<form:input type="hidden" path="isParttime"/>
<form:input type="hidden" path="hourlyRate"/>
<form:input type="hidden" path="employerName"/>
<form:input type="hidden" path="jobRetention30"/>
<form:input type="hidden" path="jobRetention60"/>
<form:input type="hidden" path="jobRetention90"/>
<form:input type="hidden" path="jobRetention90Plus"/>
<form:input type="hidden" path="socialReunification"/>
<form:input type="hidden" path="enrolledJobReadiness"/>
<form:input type="hidden" path="completedJobRediness"/>

<font color="green"><b>${CASE_SAVE_STATUS}</b></font>

<fieldset>
<div id="clientInfo">
Client: 
<p><label>Inmate Number</label>${inmateNum}</p>
<p><label>Last Name</label>${lastName}</p>
</div>

<div id="selfsuff">
<h3>Self Sufficiency</h3>
		<h4>Benefits/Entitlements</h4>
          <c:if test="${command.neededBenefits == 'Y'}">
          	<label>Needed benefits/entitlements?</label>Yes
          </c:if>
          <c:if test="${command.appliedBenefits == 'Y'}">
          	<label>Applied benefits/entitlements?: </label>Yes
          </c:if>
          <c:if test="${command.receivedBenefits == 'Y'}">
          	<label>Received benefits/entitlements?: </label>Yes
          </c:if>
		  <br>
	  <h4>Identification</h4>
	  	  <c:if test="${command.neededId == 'Y'}">
          	<label>Needed ID?</label>Yes
          </c:if>
          <c:if test="${command.appliedId == 'Y'}">
          	<label>Applied ID?</label>Yes
          </c:if>
          <c:if test="${command.receivedId == 'Y'}">
          	<label>Received ID?</label>Yes
          </c:if>
</div>
	
<div id="cmForm">
<h3>Employment</h3>
   <p> <label>Did client obtain employment?</label> 
   			<c:if test="${command.isEmployed == 'Y'}">Yes</c:if>
   			<c:if test="${command.isEmployed == 'N'}">No</c:if>
   </p>           
   <p><label>Date of hire: ${command.dateOfHire}</p> <br>
          
   <p><label>Employment Status:</label>
        	<c:if test="${command.isFulltime == 'Y'}">Full-Time</c:if>
   			<c:if test="${command.isParttime == 'Y'}">Part-Time</c:if>
   </p>
	 
   <p><label>Hourly Rate:</label>${command.hourlyRate}</p>
   
   <p><label>Employer Name:</label>${command.employerName}</p>
   
   <p><label>Job Retention:</label>
   			<c:if test="${command.jobRetention30 == 'Y'}">30 days</c:if>
   			<c:if test="${command.jobRetention60 == 'Y'}">60 days</c:if>
   			<c:if test="${command.jobRetention90 == 'Y'}">90 days</c:if>
   			<c:if test="${command.jobRetention90Plus == 'Y'}"><90 days</c:if>
   </p>
   
   <h3>Social Preparation</h3>
   
    <p><label>Social/Family Reunification?</label> 
           <c:if test="${command.socialReunification == 'Y'}">Yes</c:if>
   		   <c:if test="${command.socialReunification == 'N'}">No</c:if>
	</p>
    <p><label>Did client enroll in job readiness program?</label> 
    		<c:if test="${command.enrolledJobReadiness == 'Y'}">Yes</c:if>
   		   	<c:if test="${command.enrolledJobReadiness == 'N'}">No</c:if>
	</p>       
              
    <p> <label>If enrolled did they complete it?</label> 
    		<c:if test="${command.completedJobRediness == 'Y'}">Yes</c:if>
   		   	<c:if test="${command.completedJobRediness == 'N'}">No</c:if>
	</p>	
	<h3>Goals and Services</h3>
          
    <p><label>Client Goals:</label>
     	<table id="goalServicesTab" style="border: 1px solid black;">
     		<tr>
     			<td  width="45%">Goal</td><td width="45%">Service</td></td>
     		</tr>
	      	<c:set var="i" value="0"/>
	      	<c:forEach items="${command.selectedGoalService}" var="goalService">
	      		<c:set var="i" value="${i + 1}"/>
		       	<tr id="gsrow${i}">
		       		<td>${goalService.getGoalName()}</td>
		       		<td>${goalService.getServiceName()}
						<input type='hidden' name='selectedGoalService[${i-1}].goalServiceId' value='${goalService.getGoalServiceId()}'/>
						<input type='hidden' name='selectedGoalService[${i-1}].goalName' value='${goalService.getGoalName()}'/>
						<input type='hidden' name='selectedGoalService[${i-1}].serviceName' value='${goalService.getServiceName()}'/>
					</td>
		       	</tr>
	    	</c:forEach>
    	</table>
    </p>
</div>          
</fieldset>
  	<h3>"Please click save button if the information above is correct.If not, <br>              please click 'Back' button to modify the information."  </h3>

<input type="button" name="backButton" id="backButton" value="Back" onclick="moveBack()">
<input type="submit" name="submitButton" id="submitButton" value="Save">

</form:form> 
</div>


<!--Main Menu-->
<div class="menu"></div>

<div id="clear"></div>
</div>

<div id="main_content_bottom">
</div>

<%@include file="template/footer.jsp" %>

<script type="text/javascript">
function moveBack() {
	document.getElementById("command").action="./backCase";
	document.getElementById("command").submit();
}
</script>
</body>
</html>


