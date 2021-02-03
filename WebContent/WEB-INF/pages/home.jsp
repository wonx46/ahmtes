<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Master Brand</title>

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
		<h1>Master Brand</h1>
		
		<!-- <form method="post" id="fileinfo" name="fileinfo" enctype="multipart/form-data">
			<a class="btn btn-success fa fa-cloud-upload" style="width: 200px">
	           <input type="file" name="file">
			</a>
		   <button type="submit" class="btn btn-primary" id="uploadBTN">Preview File</button>
							                     <a class="btn deepPink-bgcolor fa" >Exp PDF</a>
	    </form> -->
	    
	    <input type="file" name="file" id="fileLoader" /> 
		<input type="button" id="fileSubmit" value="Upload"/>
		
		<table border="1" id="records_table">

			<th>Code</th>
			<th>Name</th>
			<th>Typ</th>
			<th>Brand</th>
			<th>Action</th> 

			<%-- <c:forEach var="x" items="${list}">
				<tr>

					<td>${x.vbrndcd}</td>
					<td>${x.vbrndnm}</td>
					<td>${x.vbrndtyp}</td>
					<td>${x.vurlbrnd}</td>
					<td><a href="editBrand?id=${x.vbrndcd}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="deleteBrand?id=${x.vbrndcd}">Delete</a></td>

				</tr>
			</c:forEach> --%>
		</table>
		<h4>
			New Brand Register <a href="newBrand">here</a>
		</h4>
	</div>
	 
	 
	 <script>
	jQuery(document).ready(function($) {
		retrieveBrand();
	/* 	$('#myLink').click(function(){ MyFunction(); return false; }); */

		var files = [];
		
                
                $('#fileLoader').on('change', function(){ 
                	 files=event.target.files;
                });
		
		  $('#fileSubmit').on('click', function(){ 
			  processUpload();
         });
                
		
		function processUpload()
	    {
	        var oMyForm = new FormData();
	        oMyForm.append("file", files[0]);
	       $.ajax({dataType : 'json',
	              url : "/ahmsdnistes/uploadxlstmp?clazz=id.co.ahm.sd.nis.app000.model.AhmsdnisMstbrnd&url=-idcoahmsdnismstbrand-preview",
	              data : oMyForm,
	              type : "POST",
	              enctype: 'multipart/form-data',
	              processData: false, 
	              contentType:false,
	              success : function(result) {
	                /*  console.log("sukses"); */
	            	  window.location.href =  "previewtmpxls";
	              },
	              error : function(e) {
	            	  window.location.href =  "previewtmpxls";
	  				/* console.log("ERROR: ", e); */
	  				/* display(e); */
	  			}
	          });
	    }
	
	});
	
	
	
	function retrieveBrand() {


		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "jx/com001/retrieve",
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

	function fillTable(resp)
    {
		response =  resp.data; 
		console.log(response);
        $(function() {
        	$.each(response, function(i, item) {
        	    $('<tr>').html(
        	        "<td>" + response[i].vbrndcd + "</td><td>" + response[i].vbrndnm + "</td><td>" + response[i].vbrndtyp + "</td><td> <img src="+response[i].vurlbrnd+">  </td>   <td><a href=editBrand?id="+response[i].vbrndcd+">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp; <a id = btndel value="+response[i].vbrndcd+" href=# onclick=deleteObj('"+response[i].vbrndcd+"');return false; \">Delete</a></td>").appendTo('#records_table');
        	});
        });


    }
	
	
	function deleteObj(code) {

		console.log("deleteObj: "+code);
		 $.ajax({
			type : "GET",
			contentType : "application/json",
			url : "jx/com001/delete/"+code,
			dataType : 'json',
			timeout : 100000,
			success : function(resp) {
				console.log("SUCCESS: ");
				console.log(resp);
				 window.location.href =  "";
			},
			error : function(e) {
				console.log("ERROR: ", e);
				
			},
			done : function(e) {
				console.log("DONE");
				
			}
		}); 

	}
	
</script>
</body>
</html>