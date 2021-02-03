<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Master Brand</title>
</head>
<body>
	<div align="center">
		<h1>Master Brand</h1>
		
		<table border="1">

			<th>Code</th>
			<th>Name</th>
			<th>Typ</th>
			<th>Url</th>
			<th>Action</th>

			<c:forEach var="x" items="${list}">
				<tr>

					<td>${x.vbrndcd}</td>
					<td>${x.vbrndnm}</td>
					<td>${x.vbrndtyp}</td>
					<td>${x.vurlbrnd}</td>
					<td><a href="editBrand?id=${x.vbrndcd}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="deleteBrand?id=${x.vbrndcd}">Delete</a></td>

				</tr>
			</c:forEach>
		</table>
		<h4>
			New Brand Register <a href="newBrand">here</a>
		</h4>
	</div>
</body>
</html>