<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Preview XLS</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

        <spring:url value="/resources/core/js/jquery.1.10.2.min.js"
                    var="jqueryJs" />
        <script src="${jqueryJs}"></script>
    </head>
    <body>
        <div class="container">
            <h1>Preview Population from XLS</h1>
            <table border="1" class="table table-bordered" >
                <thead class="bg-danger text-white">
                    <tr>
                        <th>Province</th>
                        <th>District</th>
                        <th>Male</th>
                        <th>Female</th>
                        <th>Age Range</th>
                    </tr>
                </thead>

                <c:forEach var="x" items="${objs}">
                    <tr>
                        <td>${x.vbpsprvnm}</td>
                        <td>${x.vbpsdstrnm}</td>
                        <td>${x.nmale}</td>
                        <td>${x.nfemale}</td>
                        <td>${x.ahmsdnisTxnppltnsPK.vagerng}</td>
                    </tr>
                </c:forEach> 
            </table>
            
            <h4>
                <button class="btn bg-danger text-white" id="btnSaveToDb">Save to Database</button>
                <button class="btn bg-danger text-white" id="cancelSaveToDb">Cancel</button>
                <!--<a href="savexlstodbpopulation?key=population&urlview=home">here</a>-->
            </h4>
        </div>
        <script>

            $("#btnSaveToDb").click(function () {
                window.location.href = "savexlstodbpopulation?key=population&urlview=idcoahmdnispopulation/viewPopulation";
            });
            $("#cancelSaveToDb").click(function (){
                window.location.href="/ahmsdnistes/view-population";
            });


        </script>
</body>
</html>