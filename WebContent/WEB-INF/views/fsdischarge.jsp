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
function validateCase()
{
// 	if(document.getElementById("dod").value=="")
// 	{
// 		alert("Please enter Discharge Date");                                   
// 		return false;
// 	}
	if(document.getElementById("educationLevel").value=="")
	{
		alert("Education Level is mandatory");                                   
		return false;
	}	
	if(document.getElementById("appointments1").checked==false && document.getElementById("appointments2").checked==false)
	{
		alert("Attended all Appointments is mandatory");                                   
		return false;
	}
	if(document.getElementById("housing").value=="")
	{
		alert("Housing is mandatory");                                   
		return false;
	}	
	if(document.getElementById("numdrugtests").value=="")
	{
		alert("Number of Drug Tests is mandatory");                                   
		return false;
	}	
	if(document.getElementById("testoncepermonth1").checked==false && document.getElementById("testoncepermonth2").checked==false)
	{
		alert("Please enter was the client test monthly");                                   
		return false;
	}	
	if(document.getElementById("numpositive").value=="")
	{
		alert("Number Positive is mandatory");                                   
		return false;
	}	
	if(document.getElementById("dischargeReason").value=="")
	{
		alert("Discharge Reason is mandatory");                                   
		return false;
	}
	if(document.getElementById("dismissReason").value=="" && document.getElementById("dischargeReason").value == 'Dismissal')
	{
		alert("Dismissal Reason is mandatory");                                   
		return false;
	}
	if(document.getElementById("dischargeplan1").checked==false && document.getElementById("dischargeplan2").checked==false)
	{
		alert("Please enter Individualized Discharge Plan developed");                                   
		return false;
	}
	if(document.getElementById("commlinkages1").checked==false && document.getElementById("commlinkages2").checked==false)
	{
		alert("Please enter Community Resources");                                   
		return false;
	}
}
function enableDismissed(){
	if(document.getElementById("dischargeReason").value == 'Dismissal') {
		document.getElementById("dismissReason").disabled=false;
	}
	else
	{
		document.getElementById("dismissReason").value="";
		document.getElementById("dismissReason").disabled=true;
	}
}
</script>

<div id="main_content">

<div class="content">
<h2>Fresh Start Discharge Form</h2>
<p>Please answer all questions on this form. This is the final form for the client. 
  <br> 
  After you have completed the form click the &quot;SAVE&quot; 
  button and a new page will display a summary of completed fields. 
  You will have the option to &quot;EDIT&quot; or &quot;CONFIRM&quot; on the next page.</p>

<!--Enter the form-->
<form:form method="POST" action="confirmFsDischarge"> 
<form:input type="hidden" path="clientId"/>
<form:input type="hidden" path="inmateNum"/>
<form:input type="hidden" path="lastName"/>
<form:input type="hidden" path="programId" value="1"/> 



<div id="cmForm h3">
<fieldset>


<div id="clientInfo">
<h3>Client</h3>
<label>Inmate Number : </label>${inmateNum}
<label>Last Name : </label>${lastName}
</div>

<div id="cmForm">
<h3>General</h3>
		<p><label>Discharge Date</label><form:input type="date" path="dod" /></p>
        
        <p><label>Atttended all appointments?</label> 
        <form:radiobutton path="appointments" value="Y"/>Yes 
	 	<form:radiobutton path="appointments" value="N"/>No
	 	</p>

        <p>
          <label>Education Level:(at Discharge)</label>
        <form:select path="educationLevel">
       	 	<form:options items="${eduList}"/>
        </form:select>
    </p>

   <h3>Housing</h3>
   
    <p>
      <label>Upon Discharge is the Client living in: </label>
        <form:select path="housing">
       	 	<form:options items="${housingList}"/>
         </form:select>
    </p>
	
	<h3>Substance Free Life Style</h3>
          
    <p><label>Number of drug tests given: </label>
         <form:select path="numdrugtests">
       	 	<form:options items="${numList}"/>
         </form:select></p>
 
    <p><label>Was client tested monthly?</label> 
          <form:radiobutton path="testoncepermonth" value="Y"/>Yes 
  		  <form:radiobutton path="testoncepermonth" value="N"/>No
  	</p>
  	
    <p>
      <label>Number of Positive urine tests: </label>
         <form:select path="numpositive">
       	 	<form:options items="${numList}"/>
         </form:select></p>
          
          <h3>Discharge</h3>
           <p><label>Discharge Reason:</label>
	         <form:select path="dischargeReason" onchange="enableDismissed()" id="dischargeReason">
       	 		<form:options items="${dischargeList}"/>
	         </form:select>
	       </p>

         <p><label>If Dismissed: (<em>select reason</em>)</label>
	         <form:select path="dismissReason" id="dismissReason">
	       	 	<form:options items="${dismissList}"/>
	         </form:select>
         </p>

         <p><label>Individualized Discharge Plan developed? </label> 
         <form:radiobutton path="dischargeplan" value="Y"/>Yes 
  		 <form:radiobutton path="dischargeplan" value="N"/>No
  		 </p>

         <p>
           <label>Pre-established links with community resources? </label> 
           <form:radiobutton path="commlinkages" value="Y"/>Yes 
		   <form:radiobutton path="commlinkages" value="N"/>No
		 </p>
	
         <br>  
    
</fieldset>
</div>   
<br>
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


