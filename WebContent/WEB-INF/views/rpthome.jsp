<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<%@include file="template/head.jsp" %>
<%@include file="template/header.jsp" %>

<div id="sub_header">Reports</div>
<div id="subheader1">Signed In: <c:out value="${username}"/></div>

<%@include file="template/rptmenu.jsp" %>

<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
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
 <script>
$( document ).ready(function() {
	$( "#RPT_FORM" ).change(function( event ) {
		var repName = $( "input:radio[name=rep-name]:checked" ).val();
		
		if (repName == 'BHN_RSAT') {
			$( "#rsat" ).show();
			$( "#sort" ).hide();
		} else {
			$( "#rsat" ).hide();
			$( "#sort" ).show();
		}
	});
});
</script>
<div id="main_content">

<div class="content">
<h2>Select Report</h2>
<p>&nbsp;</p>
<div id="index"> 
	                                                             
	  <form id="RPT_FORM" method="POST" action="genRpt">
<!-- 	  
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
-->	    
	    <h3>Behavioral Health Network Reports</h3><br>
	    <input type="radio" name="rep-name" id="BHN_CLIENT" value="BHN_CLIENT_LIST">
	    <label>Behavioral Health Client List</label>
	    <br>
	    <input type="radio" name="rep-name" id="RSAT_CLIENT" value="RSAT_CLIENT_LIST">
	    <label>RSAT Client List</label>
	    <br>
	    <input type="radio" name="rep-name" id="BHN_RSAT" value="BHN_RSAT">
	    <label>RSAT Report</label>
	    <br>
	    <input type="radio" name="rep-name" id="BHN_SUB" value="BHN_SUB_FREE">
	    <label>Behavioral Health substance free lifestyle</label>
	    <br>
	    <input type="radio" name="rep-name" id="BHN_DIS" value="BHN_DIS_PLN">
	    <label>Behavioral Health discharge plan</label>
	    <br>
	    <input type="radio" name="rep-name" id="BHN_TRT" value="BHN_TRT_COMP">
	    <label>Behavioral Health Treatment Compliance</label>
	    <br>
	    <input type="radio" name="rep-name" id="BHN_PROG" value="BHN_PROG_COMP">
	    <label>Behavioral Health Program Completion</label>
  
	<h3>Select Parameters</h3>

   <div class="params">
    
		<div id="dates">
		<label>Start Date</label>
		<input type="date" id="start-date" name="start-date">
		<label> End Date</label>
		<input type="date" id="end-date" name="end-date">
		</div>
		<p/>
		<div id="sort">
		<label> Sort By</label>
		<select name="order-by" size="1">
		  <option value="FIRST">First Name</option>
		  <option value="LAST">Last Name</option>
		</select>
		</div>
 		<br/>
 		<div id="rsat" style="display : none">
    		<label>Does your RSAT program use evidence based treatment services?</label><br/> 
			<input type="radio" name="evidence" value="Y">Yes<br/>
			<input type="radio" name="evidence" value="N">No<br/>
			<label>Describe evidence based treatment services : </label><br/>
			<textarea name="evidenceTreatment" rows="2" cols="80">&nbsp;</textarea><br/>
			<label>How many staff who directly work with the participants in RSAT program : </label><br/>
			<input type="text" name="noOfDirectStaff"  value="0"><br/>
			<label>No. of newly available mental health service facilities : </label><br/>
			<input type="text" name="noOfMentalHealthFacility"  value="0"><br/>
			<label>No. of newly available substance abuse service facilities : </label><br/>
			<input type="text" name="noOfSunstanceAbuseFacility"  value="0"><br/>
			<label>No. of newly available primary care service facilities : </label><br/>
			<input type="text" name="noOfPrimaryCareFacility" value="0">
			<br/>
		</div>
		<br/>
		<label> Report Format</label>
		<select name="rep-fmt" size="1">
		  <option value="PDF">Pdf</option>
		  <option value="XLS">Excel</option>
		  <!-- <option value="CSV">Csv</option>
		  <option value="HTML">Html</option> -->
		</select>
		</div>
		<br/>

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
