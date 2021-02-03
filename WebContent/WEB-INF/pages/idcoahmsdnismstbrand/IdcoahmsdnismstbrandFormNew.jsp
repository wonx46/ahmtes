<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Brand</title>
</head>
<body>
    <div align="center">
        <h1>New Brand</h1>
        <form:form action="saveBrand" method="post" modelAttribute="obj">
        <table>
            
             <tr>
                <td>Code:</td>
                <td><form:input path="vbrndcd" /></td>
            </tr>
                
            <tr>
                <td>Name:</td>
                <td><form:input path="vbrndnm" /></td>
            </tr>
          
            <tr>
                <td>TYP:</td>
                <td><form:input path="vbrndtyp" /></td>
            </tr>
            
             <tr>
                <td>URL:</td>
                <td><form:input path="vurlbrnd" /></td>
            </tr>
            
            
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>