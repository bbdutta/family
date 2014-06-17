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
<h2>Behavioral Health Home Page</h2>
<p>This page is where you begin Behavioral Heath Network Program client data entry. The data collected on all the 
forms is used to create reports. All fields on all 3 forms are required and are expected to be completed upon discharge.</p>
<p>There are three forms to be completed upon discharge.
   <ul>
   <li> Intake Form, collects and creates a client profile.</li>
   <li> Case Management Form, collects information on client program accomplishments.</li>
   <li> Discharge Form, collects final information prior to leaving program.</li>
   </ul>
   </p>
<p class="quote">***Please have client's Inmate Number available</p>
<div id="index"> 
<h3>Intake Form</h3>  
<font color="red"><b>${INTAKE_AVAILABLE}</b></font>
                                                       
<h4>To enter an Intake form for a new client, please click <a href="bhnIntake?inmate-num=0&program-id=2">Add Client</a></h4>
<h4>To edit a client's Intake Form:<br>
<form method="POST" action="bhnIntake">
<input type="hidden" name="program-id" value="2"/>
Enter Inmate Number and click submit to access the form: <br>
<input name="inmate-num" id="inmateNum"/>
<input type="submit" id="inmatebutton" value="Submit">
</h4>
</form>

<h3>Case Management Form</h3>
<font color="red"><b>${CASE_AVAILABLE}</b></font>
<h4>To enter Case Management Form:<br> Enter Inmate Number and click submit to access the form: <br>
<form method="POST" action="bhnCaseManagement">
<input type="hidden" name="program-id" value="2"/>
<input name="inmate-num" id="inmateNum"/>
<input type="submit" id="inmatebutton" value="Submit">
</h4>
</form>    

<h3>Discharge Form</h3>
<font color="red"><b>${DISCHARGE_AVAILABLE}</b></font>
<h4>To enter Discharge Form:<br> Enter Inmate number and click submit to access the form: <br>
<form method="POST" action="bhnDischarge">
<input type="hidden" name="program-id" value="2"/>
<input name="inmate-num" id="inmateNum"/>
<input type="submit" id="inmatebutton" value="Submit">
</h4>
</form>

<h3>RSAT Form</h3>
<font color="red"><b>${RSAT_AVAILABLE}</b></font>
<h4>To enter RSAT Form:<br> Enter Inmate number and click submit to access the form: <br>
<form method="POST" action="bhnRsat">
<input type="hidden" name="program-id" value="2"/>
<input name="inmate-num" id="inmateNum"/>
<input type="submit" id="inmatebutton" value="Submit">
</h4>
</form>

</div>
</div>

<div class="menu"></div>

<div id="clear"></div>

</div>

<div id="main_content_bottom">
</div>

<%@include file="template/footer.jsp" %>

</body>
</html>
