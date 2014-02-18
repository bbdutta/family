<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<%@include file="template/head.jsp" %>
<%@include file="template/header.jsp" %>

<div id="sub_header">Reports</div>
<div id="subheader1">Signed In: <c:out value="${username}"/></div>

<%@include file="template/rptmenu.jsp" %>

<script type="text/javascript">
function validate() 
{
	if(document.getElementById("rep-name").value=="")
	{
		alert("Please select a Report.");
		return false;
	} 
	if(document.getElementById("start-date").value=="")
	{
		alert("Please enter the Start Date.");
		return false;
	} 
	if(document.getElementById("end-date").value=="")
	{
		alert("Please enter the End Date.");
		return false;
	} 
	
}
</script>
<div id="main_content">

<div class="content">
<h2>Select Report</h2>
<p>&nbsp;</p>
<div id="index"> 
	                                                             
	  <form method="POST" action="genRpt">
	  <h3>Fresh Start Reports</h3><br>
	    <input type="radio" name="rep-name" id="rep-name" value="FS_CLIENT_LIST" checked>
	    <label>Fresh Start Client List</label>
	    <br>
	    <input type="radio" name="rep-name" id="rep-name" value="FS_SUB_FREE">
	    <label>Fresh Start substance free lifestyle</label>
	    <br>
	    <input type="radio" name="rep-name" id="rep-name" value="FS_DIS_PLN">
	    <label>Fresh Start discharge plan</label>
	    <br>
	    <input type="radio" name="rep-name" id="rep-name" value="FS_TRT_COMP">
	    <label>Fresh Start Treatment Compliance</label>
	    <br>
	    <input type="radio" name="rep-name" id="rep-name" value="FS_PROG_COMP">
	    <label>Fresh Start Program Completion</label>
	    <br>
	    <h3>Behavioral Health Network Reports</h3><br>
	    <input type="radio" name="rep-name" id="rep-name" value="BHN_CLIENT_LIST">
	    <label>Behavioral Health Client List</label>
	    <br>
	    <input type="radio" name="rep-name" id="rep-name" value="BHN_SUB_FREE">
	    <label>Behavioral Health substance free lifestyle</label>
	    <br>
	    <input type="radio" name="rep-name" id="rep-name" value="BHN_DIS_PLN">
	    <label>Behavioral Health discharge plan</label>
	    <br>
	    <input type="radio" name="rep-name" id="rep-name" value="BHN_TRT_COMP">
	    <label>Behavioral Health Treatment Compliance</label>
	    <br>
	    <input type="radio" name="rep-name" id="rep-name" value="BHN_PROG_COMP">
	    <label>Behavioral Health Program Completion</label>
  
	<h3>Select Parameters</h3>

   <div class="params"> 
		<p><label>Start Date</label>
		<input type="date" id="start-date" name="start-date">
		<label> End Date</label>
		<input type="date" id="end-date" name="end-date"></p>

		<p>
		<label> Sort By</label>
		<select name="order-by" size="1">
		  <option value="FIRST">First Name</option>
		  <option value="LAST">Last Name</option>
		</select></p>

		<p>
		<label> Report Format</label>
		<select name="rep-fmt" size="1">
		  <option value="PDF">Pdf</option>
		  <option value="XLS">Excel</option>
		  <!-- <option value="CSV">Csv</option>
		  <option value="HTML">Html</option> -->
		</select></p>
		</div>
		<br>

	  	<input name="gen-report" type="submit" id="inmatebutton" value="Generate Report" onclick="return validate()">
	  </form>
  </div>
</div>

<div id="clear"></div>

</div>

<div id="main_content_bottom">
</div>

<%@include file="template/footer.jsp" %>

</body>
</html>
