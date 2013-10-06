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
<h2>Behavioral Health Discharge Form -- Verify</h2>

<!--Enter the form-->
<form:form method="POST" action="addBhnDischarge"> 
<form:input type="hidden" path="clientId"/>
<form:input type="hidden" path="inmateNum"/>
<form:input type="hidden" path="lastName"/>
<form:input type="hidden" path="programId" value="2"/> 
<form:input type="hidden" path="dod"/>
<form:input type="hidden" path="appointments"/>
<form:input type="hidden" path="educationLevel"/>
<form:input type="hidden" path="housing"/>
<form:input type="hidden" path="numdrugtests"/>
<form:input type="hidden" path="testoncepermonth"/>
<form:input type="hidden" path="numpositive"/>
<form:input type="hidden" path="dischargeReason"/>
<form:input type="hidden" path="dismissReason"/>
<form:input type="hidden" path="dischargeplan"/>
<form:input type="hidden" path="commlinkages"/>
<form:input type="hidden" path="trtModality"/>
<form:input type="hidden" path="medicationAssisted"/>
<form:input type="hidden" path="validatedTrt"/>




<font color="green"><b>${DISCHARGE_SAVE_STATUS}</b></font>



<div id="cmForm h3">
<fieldset>
<div id="clientInfo">
<h3>Client</h3> 
<p><label>Inmate Number : </label>${command.inmateNum}</p>
<p><label>Last Name : </label>${command.lastName}</p>
</div>
<div id="cmForm">
<h3>General</h3>


	       <p><label> Discharge Date: ${command.dod}</label></p> <br>
	    
           <p><label>Atttended all appointments? </label>
        	<c:if test="${command.appointments == 'Y'}">Yes</c:if>
   			<c:if test="${command.appointments == 'N'}">No</c:if>
           </p>
	 
           <p><label>Education Level:(at Discharge) :  </label> 
              <c:if test="${command.educationLevel == 'HS diploma'}">HS diploma</c:if>
   		      <c:if test="${command.educationLevel == 'GED'}">GED</c:if>
   		      <c:if test="${command.educationLevel == 'Up to Grade 7'}">Up to Grade 7</c:if>
   		      <c:if test="${command.educationLevel == 'Up to Grade 8'}">Up to Grade 8</c:if>
   		      <c:if test="${command.educationLevel == 'Up to Grade 9'}">Up to Grade 9</c:if>
	 	      <c:if test="${command.educationLevel == 'Up to Grade 10'}">Up to Grade 10</c:if>
   		      <c:if test="${command.educationLevel == 'Up to Grade 11'}">Up to Grade 11</c:if>
	 		 	   
	       </p>     
     
       

   <h3>Housing</h3>
   
           <p><label>Upon Discharge is the Client living in : </label>
         	<c:if test="${command.housing == 'Home'}">Home</c:if>
   			<c:if test="${command.housing == 'Half-way house'}">Half-way house</c:if>
           </p>
	 
   <h3>Treatment</h3>
   
           <p><label>Treatment Modality : </label>
        	<c:if test="${command.trtModality == 'Individual counseling'}">Individual counseling</c:if>
   			<c:if test="${command.trtModality == 'Family counseling'}">Family counseling</c:if>
   			<c:if test="${command.trtModality == 'Couples counseling'}">Couples counseling</c:if>
   			<c:if test="${command.trtModality == 'Group counseling'}">Group counseling</c:if>
           </p><br>
	 
           <p><label>Medication Assisted Treatment : </label>
        	<c:if test="${command.medicationAssisted == 'Methadone'}">Methadone</c:if>
   			<c:if test="${command.medicationAssisted == 'Anti-buse'}">Anti-buse</c:if>
   			<c:if test="${command.medicationAssisted == 'Vitriol'}">Vitriol</c:if>
   			<c:if test="${command.medicationAssisted == 'Psychotropic medication'}">Psychotropic medication</c:if>
           </p><br>
   
           <p><label>Validated Treatment? </label>
        	<c:if test="${command.validatedTrt == 'Y'}">Yes</c:if>
   			<c:if test="${command.validatedTrt == 'N'}">No</c:if>
           </p><br>
    	
	<h3>Substance Free Life Style</h3>
	
           <p><label>Number of drug tests given: ${command.numdrugtests} </label>
                	           
           </p><br>
	  <p><label>Was client tested monthly? </label>
        	<c:if test="${command.testoncepermonth == 'Y'}">Yes</c:if>
   			<c:if test="${command.testoncepermonth == 'N'}">No</c:if>
           </p>
       <p><label>Number of Positive urine tests : ${command.numpositive} </label>
   
          </p><br>
          <h3>Discharge</h3>
          
          <p><label>Discharge Reason : </label>
        	<c:if test="${command.dischargeReason == 'Program completion'}">Program completion</c:if>
   			<c:if test="${command.dischargeReason == 'Remand'}">Remand</c:if>
   			<c:if test="${command.dischargeReason == 'Dismissal'}">Dismissal</c:if>
   			
           </p>
          
          
          
         
         <c:if test="${command.dischargeReason == 'Dismissal'}">
        <p><label>If Dismissed : </label>
        	<c:if test="${command.dismissReason == 'loss of contact'}">loss of contact</c:if>
   			<c:if test="${command.dismissReason == 'program rule violation'}">program rule violation</c:if>
   			<c:if test="${command.dismissReason == 'EOS'}">EOS</c:if></p>
   			<c:if test="${command.dismissReason == 'Illness/Hospitalization'}">Illness/Hospitalization</c:if>
   			<c:if test="${command.dismissReason == 'Death'}">Death</c:if>
   			<c:if test="${command.dismissReason == 'Transfer'}">Transfer</c:if>
   			</c:if>
   		
         <p><label>Individualized Discharge Plan developed? </label>
        	<c:if test="${command.dischargeplan == 'Y'}">Yes</c:if>
   			<c:if test="${command.dischargeplan == 'N'}">No</c:if>
           </p>
      
             <p><label>Pre-established links with Community resources?  </label>
        	<c:if test="${command.commlinkages == 'Y'}">Yes</c:if>
   			<c:if test="${command.commlinkages == 'N'}">No</c:if>
           </p>
         
        
	
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
	document.getElementById("command").action="./backBhnDischarge";
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


