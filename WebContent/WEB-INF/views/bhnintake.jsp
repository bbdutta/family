<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<%@include file="template/head.jsp" %>
<%@include file="template/header.jsp" %>

<div id="sub_header">Behavioral Health Network Program</div>
<div id="subheader1">Signed In: <c:out value="${username}"/></div>

<%@include file="template/menubhn.jsp" %>

<script type="text/javascript">
function validateCase() 
{
	if(document.getElementById("lastName").value=="")
	{
		alert("please enter the Last Name.");
		return false;
	} 
	if(document.getElementById("firstName").value=="")
	{
		alert("please enter the First Name.");
		return false;
	} 
	if(document.getElementById("inmateNum").value=="0" || document.getElementById("inmateNum").value=="")
	{
		alert("please enter InmateNumber.");
		return false;
	}
	if(document.getElementById("race").value=="")
	{
		alert("please select anyone on Race.");
		return false;
	} 
	if(document.getElementById("tanfEligible1").checked==false && document.getElementById("tanfEligible2").checked==false)
	{
		alert("Please Enter TANF eligible?");
		return false;
	}
	if(document.getElementById("gender1").checked==false && document.getElementById("gender2").checked==false)
	{
		alert("Please Enter Gender");
		return false;
	}
	if(document.getElementById("dob").value=="")
	{
		alert("please enter DateOfBirth.");
		return false;
	}
	if(document.getElementById("admittedOn").value=="")
	{
		alert("please enter Admission Date."); 
		return false;
	}
	
}
</script>

<div id="main_content">

<div class="content">
<h2>Behavioral Health Network Program</h2>
<p>Please answer all questions on this form. This form creates a client profile and is used to populate upcoming Case Management and Discharge forms. 
  <br> 
  After you have completed the form click the &quot;SAVE&quot; 
  button and a new page will display a summary of completed fields. 
  You will have the option to &quot;EDIT&quot; or &quot;CONFIRM&quot; on the next page.</p>

<p class="quote">Please have client's Inmate Number available</p>

<!--Enter the form-->
<form:form method="POST" action="confirmBhnIntake">
<form:input type="hidden" path="clientId"/>
<form:input type="hidden" path="programId" value="2"/>

<fieldset>
  <h3>Client Profile</h3>
<p><label>Last Name:</label>
   <form:input path="lastName" id="lastName"/></p>
   <p><label>First Name:</label>
   <form:input path="firstName" id="firstName"/></p>
   <p><label>Inmate Number:</label>
   <form:input path="inmateNum" id="inmateNum"/></p>
              
   <p><label>Birth Date:</label>            		
   <form:input type="date" path="dob" id="dob"/></p>
          
   <p><label>Gender:</label>
      		<form:radiobutton path="gender" value="M" id="gender1"/>Male 
       		<form:radiobutton path="gender" value="F" id="gender2"/>Female</p>
     
    <p><label>Race</label>
       	 	<form:select path="race" id="race">
       	 		<form:options items="${raceList}"/>
        	</form:select></p>
	 
   <p><label>Is client TANF Eligible?</label> 
         	<form:radiobutton path="tanfEligible" value="Y" id="tanfEligible1"/>Yes 
           	<form:radiobutton path="tanfEligible" value="N" id="tanfEligible2"/>No
           	
            <p><label>Referral Source</label>
            <form:select path="referralSource" id="referralSource">
       	 		<form:options items="${refSrcList}"/>
            </form:select>
            
            <p><label>Education Level (at intake)</label> 
            <form:select path="educationLevel" id="educationLevel">
       	 		<form:options items="${eduList}"/>
            </form:select>
    <p>
            <label>Admission Date</label>
            <form:input type="date" path="admittedOn"  id="admittedOn"/></p>
    
</fieldset>

<input type="submit" name="submitButton" id="cmsubmit" value="Save" onclick="return validateCase()">

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

</body>
</html>
