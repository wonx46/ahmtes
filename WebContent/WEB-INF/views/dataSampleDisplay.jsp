<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>Spring MVC Hello World</title>
</head>

<body>
	<h2>All Employees in System</h2>

	<table border="1">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Descr</th>
		</tr>
		<tr>
			<td>${data.id}</td>
			<td>${data.name}</td>
			<td>${data.descr}</td>
		</tr>
	</table>

</body>
</html>