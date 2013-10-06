<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<%@include file="template/head.jsp" %>
<%@include file="template/header.jsp" %>

<div id="sub_header">Fresh Start Program</div>
<div id="subheader1">Signed In: <c:out value="${username}"/></div>

<%@include file="template/menu.jsp" %>

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
<h2>Fresh Start Case Management Form</h2>
<p>Please answer all questions on the form that apply to this client. 
Any information that can't be filled in at this time may be filled in at a later date.  
  <br> 
  After you have completed the form click the &quot;SAVE&quot; 
  button and a new page will display a summary of completed fields. 
  You will have the option to &quot;EDIT&quot; or &quot;CONFIRM&quot; on the next page.</p>




<!--Enter the form-->
<form:form method="POST" action="confirmCase">
<form:input type="hidden" path="clientId"/>
<form:input type="hidden" path="programId" value="1"/>
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
	
<div id="cmForm">
<h3>Employment</h3>
   <p> <label>Did client obtain employment?</label> 
            <form:radiobutton path="isEmployed" value="Y" onclick="enableEmplymentDet()"/>Yes
            <form:radiobutton path="isEmployed" value="N" onclick="disableEmplymentDet()"/>No
   </p>           
   <p><label>Date of hire:</label><form:input type="date" path="dateOfHire" id="dateOfHire"/></p>
          
   <p><label>Employment Status:</label>
          <form:radiobutton path="isFulltime" value="Y" onclick="checkFullTime()"/>Full-Time
          <form:radiobutton path="isParttime" value="Y" onclick="checkPartTime()"/>Part-Time
   </p>
	 
   <p><label>Hourly Rate:</label>
   		<form:input path="hourlyRate"/>
   </p>
   
   <p><label>Employer Name:</label>
   		<form:input path="employerName"/>
   </p>
   
   <p><label>Job Retention:</label>
          <form:checkbox path="jobRetention30" value="Y"/> 30 days 
          <form:checkbox path="jobRetention60" value="Y"/> 60 days  
          <form:checkbox path="jobRetention90" value="Y"/> 90 days
          <form:checkbox path="jobRetention90Plus" value="Y"/> >90 days
   </p>
   
   <h3>Social Preparation</h3>
   
    <p><label>Social/Family Reunification?</label> 
           <form:radiobutton path="socialReunification" value="Y"/>Yes
           <form:radiobutton path="socialReunification" value="N"/>No
	</p>              
    <p><label>Did client enroll in job readiness program?</label> 
           <form:radiobutton path="enrolledJobReadiness" value="Y"/>Yes
           <form:radiobutton path="enrolledJobReadiness" value="N"/>No
	</p>       
              
    <p> <label>If enrolled did they complete it?</label> 
           <form:radiobutton path="completedJobRediness" value="Y"/>Yes
           <form:radiobutton path="completedJobRediness" value="N"/>No
	</p>	
	<h3>Goals and Services</h3>
    <div id="goalsandser">
    <p><label>Goals:</label>
		<select name="allGoals" onchange="populateServices()" id="allGoals">
        		<option value=""></option>
           		<c:forEach items="${goalList}" var="goal">
           			<option value="${goal.getGoalId()}">${goal.getGoalName()}</option>
           		</c:forEach>
        </select>
    </p>
    <p><label>Services:</label>
		<select name="allServices" id="allServices"></select><br/><br/>
		<input type="button" name="addGoalService" value="Add" onclick="addGoalServices()"/>
	</p>
	</div>
   
	<div id="clientgoalsandser">
    <p><label>Client Goals:</label>
     	<table id="goalServicesTab" style="border: 1px solid black;">
     		<tr>
     			<td  width="45%">Goal</td><td width="45%">Service</td><td width="10%"></td>
     		</tr>
	      	<c:set var="i" value="0"/>
	      	<c:forEach items="${command.selectedGoalService}" var="goalService">
	      		<c:set var="i" value="${i + 1}"/>
		       	<tr id="gsrow${i-1}">
		       		<td>${goalService.getGoalName()}</td>
		       		<td>${goalService.getServiceName()}</td>
					<td>
						<input type='hidden' name='selectedGoalService[${i-1}].goalServiceId' value='${goalService.getGoalServiceId()}'/>
						<input type='hidden' name='selectedGoalService[${i-1}].goalName' value='${goalService.getGoalName()}'/>
						<input type='hidden' name='selectedGoalService[${i-1}].serviceName' value='${goalService.getServiceName()}'/>
						<input type='button' name='deleteBtn' value='Delete' onclick='deleteGoalServiceRow(${i-1})'/>
					</td>
		       	</tr>
	    	</c:forEach>
    	</table>
    </p>
    </div>
</div>          
</fieldset>

<input type="submit" name="submitButton" id="cmsubmit" value="Save" onclick="return validateCase()">
</form:form> 

</div>

    <div id="serviceHidden" style="display:none">
    <c:forEach items="${goalList}" var="goal">
    	<select name="goalServices${goal.getGoalId()}">
    		<option value=""></option>
	    	<c:forEach items="${serviceList}" var="service">
	    		<c:if test="${service.getGoalId() == goal.getGoalId()}">
	    			<option value="${service.getGoalServiceId()}">${service.getServiceName()}</option>
	    		</c:if>
	    	</c:forEach>
    	</select>
    </c:forEach>
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

<script type="text/javascript">
function populateServices() {
	var goalsObj = document.getElementById("allGoals");
	if(goalsObj.options[goalsObj.options.selectedIndex].value == '') {
		document.getElementById("allServices").innerHTML = "";
	}
	document.getElementById("allServices").innerHTML = document.all["goalServices"+goalsObj.options[goalsObj.options.selectedIndex].value].innerHTML;
}
function addGoalServices() {
	var goalsObj = document.getElementById("allGoals");
	var servicesObj = document.getElementById("allServices");
	if(goalsObj.options[goalsObj.options.selectedIndex].value == '') {
		alert("Please select at least on Goal.");
		return;
	}
	if(servicesObj.options[servicesObj.options.selectedIndex].value == '') {
		alert("Please select at least on Service.");
		return;
	}
	addGoalServiceRow(goalsObj.options[goalsObj.options.selectedIndex].innerHTML, servicesObj.options[servicesObj.options.selectedIndex].innerHTML, goalsObj.options[goalsObj.options.selectedIndex].value, servicesObj.options[servicesObj.options.selectedIndex].value);
}
function addGoalServiceRow(selectedGoal, selectedService, goalId, goalServiceId) {
	var goalServicesTab = document.getElementById("goalServicesTab");
	var rowIndex = goalServicesTab.rows.length;
	var row=goalServicesTab.insertRow(rowIndex);
	row.id="gsrow"+rowIndex;
	var cell1=row.insertCell(0);
	var cell2=row.insertCell(1);
	var cell3=row.insertCell(2);
	
	var cellHiddenText = "<input type='hidden' name='selectedGoalService[" + (rowIndex-1) +"].goalServiceId' value='" + goalServiceId + "'/>";
	cellHiddenText = cellHiddenText + "<input type='hidden' name='selectedGoalService[" + (rowIndex-1) +"].goalName' value='" + selectedGoal + "'/>";
	cellHiddenText = cellHiddenText + "<input type='hidden' name='selectedGoalService[" + (rowIndex-1) +"].serviceName' value='" + selectedService + "'/>";

	cell1.innerHTML=selectedGoal;
	cell2.innerHTML=selectedService;
	cell3.innerHTML=cellHiddenText+"<input type='button' name='deleteBtn' value='Delete' onclick='deleteGoalServiceRow("+rowIndex+")'/>";
	
	if(document.getElementById("goalServicesTab").rows.length > 1) {
    	document.getElementById("noRecordsMsg").innerHTML='';
    }
}
function deleteGoalServiceRow(rowIndex) {
	var rows = document.getElementById("goalServicesTab").rows;
	var currentIndex=-1;
    for(var i = 1; i < rows.length; i++) {
        if(rows[i].id=="gsrow"+rowIndex) {
        	currentIndex = i;
        	break;
        }
    }
    if(currentIndex != -1) {
    	document.getElementById("goalServicesTab").deleteRow(currentIndex);
    }
    if(document.getElementById("goalServicesTab").rows.length == 1) {
    	document.getElementById("noRecordsMsg").innerHTML='No Goals and Services are selected.';
    }
}

</script>

