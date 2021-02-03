<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Brand</title>
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
        <h1>Edit Brand</h1>
       <%--  <form:form action="updateBrand" method="post" modelAttribute="obj"> --%>
       <form class="form-horizontal" id="save-form">
        <table>
            <%-- <form:hidden path="vbrndcd"/>
             <form:hidden path="vcrea"/>
              <form:hidden path="dcrea"/>
               <form:hidden path="vmodi"/>
                <form:hidden path="dmodi"/> --%>
                
          <input type="hidden" class="form-control" id="vbrndcd" value="${idnya}" >
            
            <tr>
                <td>Name:</td>
                <td>
         <%--        <form:input path="vbrndnm" /> --%>
                  <input type=text class="form-control" id="vbrndnm">
                </td>
            </tr>
          
            <tr>
                <td>TYP:</td>
                <td>
                <%-- <form:input path="vbrndtyp" /> --%>
                  <input type=text class="form-control" id="vbrndtyp">
                </td>
            </tr>
            
             <tr>
                <td>URL:</td>
                <td>
               <%--  <form:input path="vurlbrnd" /> --%>
                <input type=text class="form-control" id="vurlbrnd">
                </td>
            </tr>
            
            
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save" id="btn-save"></td>
            </tr>
        </table>
       <%--  </form:form> --%>
       </form>
    </div>
    
     <script>
	jQuery(document).ready(function($) {
		retrieveBrand($("#vbrndcd").val()); 

		 $("#save-form").submit(function(event) {

			// Disble the search button
			enableButton(false);

			// Prevent the form from submitting via the browser.
			event.preventDefault();

			saveForm();

		}); 

	});
	
	function retrieveBrand(code) {


		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "http://localhost:9988/brand/findbycode?code="+code,
			dataType : 'json',
			timeout : 100000,
			success : function(resp) {
				console.log("SUCCESS: ");
				console.log(resp);
				fillForm(resp.data[0]);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				/* display(e); */
			},
			done : function(e) {
				console.log("DONE");
				/* enableSearchButton(true); */
			}
		});

	}
	
	function fillForm(data) {
		console.log("fill data "+data);
		$("#vbrndcd").val(data.vbrndcd);
		$("#vbrndnm").val(data.vbrndnm);
		$("#vbrndtyp").val(data.vbrndtyp);
		$("#vurlbrnd").val(data.vurlbrnd);
	}

	
	function saveForm() {

		var form = {}
		form["vbrndcd"] = $("#vbrndcd").val();
		form["vbrndnm"] = $("#vbrndnm").val();
		form["vbrndtyp"] = $("#vbrndtyp").val();
		form["vurlbrnd"] = $("#vurlbrnd").val();
		

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "http://localhost:9988/brand/update",
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