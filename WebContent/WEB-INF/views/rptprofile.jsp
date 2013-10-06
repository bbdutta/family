
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 

<!DOCTYPE html>
<html>
<%@include file="template/head.jsp" %>

<body>
<div id="container">
<%@include file="template/header.jsp" %>
<%@include file="template/rptmenu.jsp" %>
	<div id="content-container">
		<div id="content">
			<h2>Profile Summary</h2>
		<form:form method="POST" action="addFsClient">
		<form:input type="hidden" path="clientId"/></p>
		<form:input type="hidden" path="programId" value="1"/></p> 
		  <fieldset>
			  <p>	<form:label path="lastName">Last Name</form:label>
					<form:input path="lastName" /></p>
			
              <p>	<form:label path="firstName">First Name</form:label>
              	 	<form:input path="firstName" /></p>
              	 	
              <p>	<form:label path="inmateNum">Inmate Number</form:label>
              		<form:input path="inmateNum" /></p>
              		
              		
              <p>
              		<label>Gender</label> 
              		<form:radiobutton path="gender" value="M"/>Male 
               		<form:radiobutton path="gender" value="F"/>Female</p>
               		
               		
           	 <p>
           	 		<label>Race</label>
           	 	<select name="Race">
              		<option>White/Caucasian</option>
              		<option>Hispanic</option>
             		<option>Black/African</option>
              		<option>American</option>
              		<option>Asian/Pacific Islander</option>
              		<option>Other</option>
            	</select></p>
            
            
            <p>
            		<form:label path="dob">Date Of Birth</form:label>
            		<form:input type="date" path="dob" /></p>
            		
            		
            		
            <p>	<label>TANF Eligible?</label> 
            	<form:radiobutton path="tanfEligible" value="Y"/>Yes 
              	<form:radiobutton path="tanfEligible" value="N"/>No
              
              
            <p>	<label>Referral Source</label>
            <select name="referralSource" id="referralSource">
            <option>Parole Officer</option>
            <option>TS Officer</option>
            <option>Halfway House</option>
            <option>Counselor</option>
            <option>Home</option>
            </select>
            </p>
            
            <p>
            <label>Education Level (at intake)</label> 
            <select name="education" id="education">
            <option>HS diploma</option>
            <option>GED</option>
            <option>Up to Grade 7</option>
            <option>Up to Grade 8</option>
            <option>Up to Grade 9</option>
            <option>Up to Grade 10</option>
            <option>Up to Grade 11</option>
            </select>
            <p>
            <label>Highest Grade Completed</label>
            <select name="completedGrade" id="completedGrade">
            <option>7</option>
            <option>8</option>
            <option>9</option>
            <option>10</option>
            <option>11</option>
          </select>
            </p>
           
            
            </fieldset>
        </form:form> 
		</div>
		<div id="aside">
			<h3>Report Information</h3>
			<p>"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."</p>
        </div>
		<%@include file="template/footer.jsp" %>
	</div>
</div>
<p>&nbsp;</p>
</body>
</html>
