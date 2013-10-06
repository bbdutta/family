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

<h2>Behavioral Health Intake Form--Verify</h2>

<form:form method="POST" action="addBhnClient">
<form:input type="hidden" path="clientId"/>
<form:input type="hidden" path="programId" value="2"/>


<form:input type="hidden" path="lastName"/>
<form:input type="hidden" path="firstName"/>
<form:input type="hidden" path="inmateNum"/>
<form:input type="hidden" path="gender"/>
<form:input type="hidden" path="dob"/>
<form:input type="hidden" path="race"/>
<form:input type="hidden" path="tanfEligible"/>
<form:input type="hidden" path="referralSource"/>
<form:input type="hidden" path="educationLevel"/>
<form:input type="hidden" path="admittedOn"/>


<c:if test="${INTAKE_SAVE_STATUS == 'INTAKE_SAVE_STATUS'}">
<font color="green"><b>Intake information saved successfully</b></font>
</c:if>

<fieldset>
  <h3>Client Profile</h3>
        
        <p><label>Last Name  : </label>${command.lastName}</p>
        <p><label>First Name : </label>${command.firstName}</p>
        <p><label>Inmate Number : </label>${command.inmateNum}</p>
        <p><label>Date of Birth : ${command.dob}</p><br>
		<p><label>Gender:</label> 
           <c:if test="${command.gender == 'M'}">Male</c:if>
   		   <c:if test="${command.gender == 'F'}">FeMale</c:if>
	    </p>
        <p><label>Race : </label> 
           <c:if test="${command.race == 'White/Caucasian'}">White/Caucasian</c:if>
   		   <c:if test="${command.race == 'Hispanic'}">Hispanic</c:if>
   		   <c:if test="${command.race == 'Black/African'}">Black/African</c:if>
   		   <c:if test="${command.race == 'American'}">American</c:if>
   		   <c:if test="${command.race == 'Asian/Pacific Islander'}">Asian/Pacific Islander</c:if>
   		   <c:if test="${command.race == 'Other'}">Other</c:if>
   		   
   		   
	    </p>
   
        <p><label>Is client TANF Eligible : </label> 
           <c:if test="${command.tanfEligible == 'Y'}">Yes</c:if>
   		   <c:if test="${command.tanfEligible == 'N'}">No</c:if>
	    </p>
	    <p><label>Referral Source : </label> 
           <c:if test="${command.referralSource == 'Parole Officer'}">Parole Officer</c:if>
   		   <c:if test="${command.referralSource == 'TS Officer'}">TS Officer</c:if>
   		   <c:if test="${command.referralSource == 'Halfway House'}">Halfway House</c:if>
   		   <c:if test="${command.referralSource == 'Counselor'}">Counselor</c:if>
   		   <c:if test="${command.referralSource == 'Home'}">Home</c:if>
	 		   
	    </p> 
	    
	    <p><label>Education Level (at intake) : </label> 
           <c:if test="${command.educationLevel == 'HS diploma'}">HS diploma</c:if>
   		   <c:if test="${command.educationLevel == 'GED'}">GED</c:if>
   		   <c:if test="${command.educationLevel == 'Up to Grade 7'}">Up to Grade 7</c:if>
   		   <c:if test="${command.educationLevel == 'Up to Grade 8'}">Up to Grade 8</c:if>
   		   <c:if test="${command.educationLevel == 'Up to Grade 9'}">Up to Grade 9</c:if>
	 	   <c:if test="${command.educationLevel == 'Up to Grade 10'}">Up to Grade 10</c:if>
   		   <c:if test="${command.educationLevel == 'Up to Grade 11'}">Up to Grade 11</c:if>
	 		 	   
	    </p>     
     
     	        <p><label>Admission Date : ${command.admittedOn}</p><br>
     	        
     	<h3>"Please click save button if the information above is correct.If not, <br>              please click 'Back' button to modify the information."  </h3>
     	   
   
</fieldset>
<input type="button" name="backButton" id="backButton" value="Back" onclick="moveBack()">

<c:if test="${INTAKE_SAVE_STATUS == 'INTAKE_SAVE_STATUS'}">
<input type="submit" name="submitButton" id="save" value="Save" disabled>
</c:if>
<c:if test="${INTAKE_SAVE_STATUS != 'INTAKE_SAVE_STATUS'}">
<input type="submit" name="submitButton" id="save" value="Save">
</c:if>

<br>
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
<script type="text/javascript">
function moveBack() 
{
	document.getElementById("command").action="./backBhnIntake";
	document.getElementById("command").submit();
}
</script>

</body>
</html>
