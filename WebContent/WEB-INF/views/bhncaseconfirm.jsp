<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<%@include file="template/head.jsp" %>
<%@include file="template/header.jsp" %>

<div id="sub_header">Behavioral Health Network Program</div>
<div id="subheader1">Signed In: <c:out value="${username}"/></div>

<%@include file="template/menubhn.jsp" %>

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
<h2>Behavioral Health CaseManagement Form--Verify</h2>

<form:form method="POST" action="addBhnCase">
<form:input type="hidden" path="clientId"/>
<form:input type="hidden" path="inmateNum"/>
<form:input type="hidden" path="programId" value="2"/>
<form:input type="hidden" path="neededBenefits"/>
<form:input type="hidden" path="appliedBenefits"/>
<form:input type="hidden" path="receivedBenefits"/>
<form:input type="hidden" path="neededId"/>
<form:input type="hidden" path="appliedId"/>
<form:input type="hidden" path="receivedId"/>


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
	document.getElementById("command").action="./backBhnCase";
	document.getElementById("command").submit();
}
</script>
</body>
</html>


