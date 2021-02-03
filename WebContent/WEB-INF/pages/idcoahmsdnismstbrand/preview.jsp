<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Preview XLS</title>

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
		<h1>Preview XLS</h1>
		

		
		<table border="1" id="records_table">

			<th>Code</th>
			<th>Name</th>
			<th>Typ</th>
			<th>Brand</th>
		
		<c:forEach var="x" items="${objs}">
				<tr>

					<td>${x.vbrndcd}</td>
					<td>${x.vbrndnm}</td>
					<td>${x.vbrndtyp}</td>
					<td>${x.vurlbrnd}</td>
				<td><a href="xlseditnew?id=${x.vbrndcd}&clazzname=AhmsdnisMstbrnd&urlpageedit=idcoahmsdnismstbrand-IdcoahmsdnismstbrandFormUpdateXls">Edit</a></td> 

				</tr>
			</c:forEach> 

		</table>
	   <h4>
			Save to database <a href="savexlstodb?key=brand&urlview=home">here</a>
		</h4>
	</div>
	 
	 
	 <script>
	jQuery(document).ready(function($) {
		/* fillTable(objs) */
	
	});
	
	
	
	function retrieve() {


		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/ahmsdnistes/jx/com001/retrieve",
			dataType : 'json',
			timeout : 100000,
			success : function(resp) {
				console.log("SUCCESS: ");
				console.log(resp);
				fillTable(resp);
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

	function fillTable(response)
    {
		
		console.log(resp);
        $(function() {
        	$.each(response, function(i, item) {
        	    $('<tr>').html(
        	        "<td>" + response[i].vbrndcd + "</td><td>" + response[i].vbrndnm + "</td><td>" + response[i].vbrndtyp + "</td><td> <img src="+response[i].vurlbrnd+">  </td> ").appendTo('#records_table');
        	});
        });


    }
	
	
	
</script>
</body>
</html>