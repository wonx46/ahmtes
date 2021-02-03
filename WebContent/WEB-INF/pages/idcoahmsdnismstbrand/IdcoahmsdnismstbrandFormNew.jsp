<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Brand</title>
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />

<spring:url value="/resources/core/js/jquery.1.10.2.min.js"
	var="jqueryJs" />
<script src="${jqueryJs}"></script>
</head>
<body>
    <div align="center">
        <h1>New Brand</h1>
       <%--  <form:form action="saveBrand" method="post" modelAttribute="obj"> --%>
       	<form class="form-horizontal" id="save-form">
        <table>
            
             <tr>
                <td>Code:</td>
                <td>
                <%-- <form:input path="vbrndcd" /> --%>
                <input type=text class="form-control" id="vbrndcd">
                </td>
            </tr>
                
            <tr>
                <td>Name:</td>
                <td>
              <%--   <form:input path="vbrndnm" /> --%>
               <input type=text class="form-control" id="vbrndnm">
                </td>
            </tr>
          
            <tr>
                <td>TYP:</td>
                <td>
               <%--  <form:input path="vbrndtyp" /> --%>
                <input type=text class="form-control" id="vbrndtyp">
                </td>
            </tr>
            
               <tr>
                <td>Brand:</td>
                <td>
        
                <input type=text class="form-control" id="vurlbrnd">
                </td>
            </tr>
        
        
            
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save" id="btn-save" ></td>
            </tr>
        </table>
        <%-- </form:form> --%>
        </form>
    </div>
    
     <script>
	jQuery(document).ready(function($) {
		

		 $("#save-form").submit(function(event) {

			// Disble the search button
			enableButton(false);

			// Prevent the form from submitting via the browser.
			event.preventDefault();

			saveForm();

		}); 

	});
	

	
	function saveForm() {

		var form = {}
		form["vbrndcd"] = $("#vbrndcd").val();
		form["vbrndnm"] = $("#vbrndnm").val();
		form["vbrndtyp"] = $("#vbrndtyp").val();
		form["vurlbrnd"] = $("#vurlbrnd").val();
		

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "http://localhost:9988/brand/save",
			data : JSON.stringify(form),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				 window.location.href =  "/ahmsdnis001-pst/";
				/* display(data); */
			},
			error : function(e) {
				console.log("ERROR: ", e);
				/* display(e); */
			},
			done : function(e) {
				console.log("DONE");
				enableButton(true);
			}
		});

	}
	
	

	function enableButton(flag) {
		$("#btn-save").prop("disabled", flag);
	}


</script>
</body>
</html>