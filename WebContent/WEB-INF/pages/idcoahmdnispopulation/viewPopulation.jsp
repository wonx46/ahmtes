<%-- 
    Document   : viewPopulation
    Created on : Feb 3, 2021, 9:53:28 AM
    Author     : admin
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Population</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/10.14.0/sweetalert2.css">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/10.14.0/sweetalert2.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    </head>
    <body>
        <br>
        <div class="container">
            <!--<h2>VIew Population</h2>-->
            <br>
            <!-- Nav tabs -->
            <ul class="nav nav-tabs " role="tablist">
                <li class="nav-item">
                    <a class="nav-link active " data-toggle="tab" href="#maintainAgeRangeTab" onclick="retrieveAgeRange();">Maintain Age Range</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#maintainPopulationTab" onclick="retrievePopulation();">Maintain Population</a>
                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content " >
                <div id="maintainAgeRangeTab" class=" tab-pane active "><br>
                    <div id="maintainAgeRange">
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <form id="formSearchAgeRange">
                                            <div class="form-group">
                                                <label for="search">Search: </label>
                                                <input type="text" class="form-control" required="true" id="searchAgeRange" name="search">
                                            </div>
                                            <button type="button" id="ahmsdnis000BtnResetAge" class="btn btn-secondary float-right "><i class="fa fa-sync-alt"> </i> Reset</button>
                                            <button type="submit"  class="btn btn-danger float-right mr-2 "><i class="fa fa-search "> </i>Search</button>
                                        </form>
                                    </div>
                                </div>
                            </div> 
                        </div>
                        <br/>
                        <div class="clearfix" >
                            <button id="ahmsdnis000BtnAdd" class="btn btn-danger float-right" ><i class="fa fa-plus"> </i> Add New</button>
                        </div>
                        <br/>
                        <table class="table" id="tableRangeAge" >
                            <thead>
                                <tr class="bg-danger text-white">
                                    <th>Code</th>
                                    <th>Start</th>
                                    <th>End</th>
                                    <th>Description</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody id="tableBodyAgeRange">
                            </tbody>
                        </table>
                        <ul class="pagination pagination-default h-100 justify-content-center align-items-center">
                            <li class="page-item"><a class="page-link text-danger" href="#">Previous</a></li>
                            <li class="page-item"><a class="page-link text-danger" href="#">1</a></li>
                            <li class="page-item"><a class="page-link text-danger" href="#">2</a></li>
                            <li class="page-item"><a class="page-link text-danger" href="#">3</a></li>
                            <li class="page-item"><a class="page-link text-danger" href="#">Next</a></li>
                        </ul>
                    </div>

                    <div  id="maintainAgeRangeForm" style="visibility: hidden">
                        <div class="col-md-6">
                            <form id="ahmsdnis000p01FormAgeRange">
                                <div class="form-group required" >
                                    <label >Code</label>
                                    <input type="text"  class="form-control" id="vcdagerng" required="true" maxlength="2" >
                                </div>
                                <div class="form-group">
                                    <label >Start</label>
                                    <input type="text"  class="form-control " maxlength="3" required="true"  id="nstart"  >
                                </div>
                                <div class="form-group">
                                    <label >End</label>
                                    <input type="text" class="form-control" maxlength="3" id="nend"  required="true">
                                </div>
                                <div class="form-group ">
                                    <label >Begin Effective Date</label>
                                    <input class="form-control" type="date"  id="dbegineff" required="true">
                                </div>
                                <div class="form-group ">
                                    <label >End Effective Date</label>
                                    <input class="form-control" type="date"  id="dendeff" required="true">
                                </div>
                                <button type="button" class="btn btn-secondary" id="ahmsdnis000BtnCancelAgeRange" >Cancel</button>
                                <button type="submit" class="btn btn-danger" id="ahmsdnis000BtnSaveAgeRange" ><i class="fa fa-save"> </i> Submit</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div id="maintainPopulationTab" class=" tab-pane fade"><br>
                    <div  id="maintainPopulation">
                        <div class="card">
                            <form class="form" id="formSearchPopulation" action="#">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <div class="form-group" >
                                                <label >Year</label>
                                                <select class="form-control" id="yearSearch" >\
                                                    <option>2017</option>
                                                    <option>2018</option>
                                                    <option>2019</option>
                                                    <option>2020</option>
                                                    <option>2021</option>
                                                    <option>2022</option>
                                                    <option>2023</option>
                                                    <option>2024</option>
                                                    <option>2025</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label >Province: </label>
                                                <input type="text" class="form-control" id="provinceSearch">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label >District: </label>
                                                <input type="text" class="form-control" id="districtSearch">
                                            </div>
                                        </div>

                                    </div>
                                    <button  class="btn btn-secondary float-right ml-2 mb-3" id="buttonResetPopulation"><i class="fa fa-sync-alt"> </i> Reset</button>
                                    <button type="submit" class="btn btn-danger float-right mb-3"><i class="fa fa-search"> </i>Search</button>
                                </div>
                            </form>
                        </div> 
                        <br/>
                        <div class="clearfix">
                            <button  id="ahmsdnis000p01Upload" class="btn btn-danger float-right">Upload Data</button>
                        </div>
                        <br/>
                        <table class="table " id="tablePopulation">
                            <thead>
                                <tr class="bg-danger text-white">
                                    <th>No</th>
                                    <th>Year</th>
                                    <th>Province</th>
                                    <th>District</th>
                                    <th>Age Range</th>
                                    <th>Male</th>
                                    <th>Female</th>
                                    <th>Begin Effective</th>
                                    <th>End Effective</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody id="bodyPopulation">

                            </tbody>
                        </table>
                        <ul class="pagination pagination-default h-100 justify-content-center align-items-center">
                            <li class="page-item"><a class="page-link text-danger" href="#">Previous</a></li>
                            <li class="page-item"><a class="page-link text-danger" href="#">1</a></li>
                            <li class="page-item"><a class="page-link text-danger" href="#">2</a></li>
                            <li class="page-item"><a class="page-link text-danger" href="#">3</a></li>
                            <li class="page-item"><a class="page-link text-danger" href="#">Next</a></li>
                        </ul>
                    </div>
                    <br/>
                    <div  id="uploadAgeForm" style="visibility: hidden" >
                        <div class="col-md-6">
                            <form action="/action_page.php">
                                <div class="custom-file mb-3">
                                    <input type="file" class="custom-file-input" id="fileLoader" name="filename">
                                    <label class="custom-file-label" for="customFile">Choose file</label>
                                </div>
                                <button type="button" class="btn btn-secondary" id="ahmsdnis000p01BtncancelDownload">Cancel</button>
                                <button type="button" class="btn btn-secondary" id="ahmsdnis000p01BtnDownloadPopulation"><i class="fa fa-download"> </i> Download Template</button>
                                <button type="button"  class="btn btn-danger" id="fileSubmit" ><i class="fa fa-save"> </i> Submit</button>
                            </form>
                        </div>
                    </div>

                    <div class="container" id="editAgeForm" style="visibility: hidden" >
                        <div class="col-md-6">
                            <form action="/action_page.php" id="formPopulation">
                                <input type="hidden" class="form-control" id="vbpsprvcd">
                                <input type="hidden" class="form-control" id="vbpsdstrcd">
                                <div class="form-group">
                                    <label >Year </label>
                                    <input type="text" class="form-control" id="nyear" required="true">
                                </div>
                                <div class="form-group" >
                                    <label >Province</label>
                                    <input type="text" class="form-control" id="vbpsprvnm" required="true"/>
                                </div>
                                <div class="form-group">
                                    <label >District</label>
                                    <input type="text" class="form-control" id="vbpsdstrnm" required="true"/>
                                </div>
                                <div class="form-group">
                                    <label >Age Range</label>
                                    <input type="text" class="form-control" id="vagerng" />
                                </div>
                                <div class="form-group" >
                                    <label >Female</label>
                                    <input type="text" class="form-control" id="nmale">
                                </div>
                                <div class="form-group">
                                    <label >Male</label>
                                    <input type="text" class="form-control" id="nfemale">
                                </div>
                                <div class="form-group ">
                                    <label >Begin Effective Date</label>
                                    <input class="form-control" type="date" id="dbegineffpop">
                                </div>
                                <div class="form-group ">
                                    <label >End Effective Date</label>
                                    <input class="form-control" type="date" id="dendeffpop">
                                </div>
                                <button type="button" class="btn btn-primary" id="ahmsdnis000p01BtnCancelPopulation" >Cancel</button>
                                <button type="submit" class="btn btn-danger" id="ahmsdnis000p01BtnSavePopulation">Save</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
        var isUpdate = false;
        var idage = null;

        $("#ahmsdnis000BtnAdd").click(function () {
            $("#maintainAgeRange").css("display", "none");
            $("#maintainAgeRange").css("visibility", "hidden");

            $("#maintainAgeRangeForm").css("visibility", "visible");
            $("#maintainAgeRangeForm").css("display", "block");

            resetFormAge()
        });



        $("#ahmsdnis000BtnCancelAgeRange").click(function () {
            $("#maintainAgeRange").css("display", "block");
            $("#maintainAgeRange").css("visibility", "visible");

            $("#maintainAgeRangeForm").css("visibility", "hidden");
            $("#maintainAgeRangeForm").css("display", "none");
        });

        $("#ahmsdnis000p01Edit").click(function () {
            $("#maintainAgeRange").css("display", "none");
            $("#maintainAgeRange").css("visibility", "hidden");

            $("#maintainAgeRangeForm").css("visibility", "visible");
            $("#maintainAgeRangeForm").css("display", "block");
        });


        $("#ahmsdnis000p01Upload").click(function () {
            $("#maintainPopulation").css("display", "none");
            $("#maintainPopulation").css("visibility", "hidden");

            $("#uploadAgeForm").css("display", "block");
            $("#uploadAgeForm").css("visibility", "visible");
        });

        $("#ahmsdnis000p01BtnSubmitPopulation").click(function () {
            $("#maintainPopulation").css("display", "block");
            $("#maintainPopulation").css("visibility", "visible");

            $("#uploadAgeForm").css("display", "none");
            $("#uploadAgeForm").css("visibility", "hidden");
        });

//        $("#ahmsdnis000p01editPopulation").click(function () {
//            
//        });

        $("#ahmsdnis000p01BtnCancelPopulation").click(function () {
            $("#maintainPopulation").css("display", "block");
            $("#maintainPopulation").css("visibility", "visible");

            $("#editAgeForm").css("visibility", "hidden");
            $("#editAgeForm").css("display", "block");
            window.scrollTo(0, 0);
        });

        jQuery(document).ready(function ($) {
            retrieveAgeRange();
            $("#ahmsdnis000p01FormAgeRange").submit(function (event) {
                // Disble the search button
                enableButton(false);
                // Prevent the form from submitting via the browser.
                event.preventDefault();
                saveForm();
            });

            $("#formPopulation").submit(function (event) {
                // Disble the search button
//                enableButton(false);
                // Prevent the form from submitting via the browser.
                event.preventDefault();
                saveFormPopulation();
            });
        });
        //CRUD
        function retrieveAgeRange() {
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/ahmsdnistes/jx/ahmsdnis000/retrieve-mstagerngs",
                dataType: 'json',
                timeout: 100000,
                success: function (resp) {
                    console.log("SUCCESS: ");
                    fillTableAgeRange(resp);
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                    /* display(e); */
                },
                done: function (e) {
                    console.log("DONE");
                    /* enableSearchButton(true); */
                }
            });
        }

        function fillTableAgeRange(resp) {
            content = "";
            response = resp.data;
            console.log(response);
            $(function () {
                $.each(response, function (i, item) {
                    content += "<tr>\n\
                            <td>" + response[i].vcdagerng + "</td>\n\
                            <td>" + response[i].nstart + "</td>\n\
                            <td>" + response[i].nend + "</td>\n\
                            <td> " + response[i].vdscrptn + "</td>\n\
                            <td><a href='#' onclick=getById('" + response[i].vcdagerng + "')>\n\
                                    <i class='fa fa-edit text-danger'></i>\n\
                                </a></td>";
                });
                $('#tableBodyAgeRange').html(content);
            });
        }

        function saveForm() {
            var form = {}
            var api = "/ahmsdnistes/jx/ahmsdnis000/save-mstagerngs";
            if (isUpdate) {
                api = "/ahmsdnistes/jx/ahmsdnis000/update-mstagerngs";
            }
            form["vcdagerng"] = $("#vcdagerng").val();
            form["nstart"] = $("#nstart").val();
            form["nend"] = $("#nend").val();
            form["dbegineff"] = $("#dbegineff").val();
            form["dendeff"] = $("#dendeff").val();

            if (!isValidN($("#nstart").val(), $("#nend").val())) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Start harus lebih kecil dari end'
                });
                return;
            }
            if (!isvalidDate($("#dbegineff").val(), $("#dendeff").val())) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Date tidak valid'
                });
                return;
            }
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: api,
                data: JSON.stringify(form),
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    console.log(data.message.message);
                    if (data.message.code == '02') {
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Kode Age Range sudah tersedia !'
                        });
                        return;
                    }
                    if (data.message.code == '01') {
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Start Age tidak valid'
                        });
                        return;
                    }
//                    window.location.href = "/ahmsdnistes/view-population";
                    $("#maintainAgeRange").css("display", "block");
                    $("#maintainAgeRange").css("visibility", "visible");

                    $("#maintainAgeRangeForm").css("visibility", "hidden");
                    $("#maintainAgeRangeForm").css("display", "none");
                    retrieveAgeRange();
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                    /* display(e); */
                },
                done: function (e) {
                    console.log("DONE");
                    enableButton(true);
                }
            });
        }

        function isValidN(start, end) {
            if (start >= end) {
                return false;
            } else {
                return true;
            }
        }
        function isvalidDate(start, end) {
            if (new Date(start) >= new Date(end)) {
                return false;
            } else {
                return true;
            }
        }


        function enableButton(flag) {
            $("#ahmsdnis000p01FormAgeRange").prop("disabled", flag);
        }

        function retrieveAgeById(id) {
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/ahmsdnistes/jx/ahmsdnis000/findbycode-mstagerngs?code=" + id,
                dataType: 'json',
                timeout: 100000,
                success: function (resp) {
                    console.log("SUCCESS get by id: " + resp.data[0].vcdagerng);
                    idage = resp.data[0].vcdagerng;
                    fillFormAge(resp);
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                    /* display(e); */
                },
                done: function (e) {
                    console.log("DONE");
                    /* enableSearchButton(true); */
                }
            });
        }

        function fillFormAge(resp) {
            isUpdate = true;
//            $("#idage").val(resp.data[0].id);
            $("#vcdagerng").val(resp.data[0].vcdagerng);
            $("#nstart").val(resp.data[0].nstart);
            $("#nend").val(resp.data[0].nend);
            $("#dbegineff").val(resp.data[0].dbegineff);
            $("#dendeff").val(resp.data[0].dendeff);
        }

        function resetFormAge() {
            $("#vcdagerng").val("");
            $("#nstart").val("");
            $("#nend").val("");
            $("#dbegineff").val("");
            $("#dendeff").val("");
        }


        function getById(id) {
            $("#maintainAgeRange").css("display", "none");
            $("#maintainAgeRange").css("visibility", "hidden");

            $("#maintainAgeRangeForm").css("visibility", "visible");
            $("#maintainAgeRangeForm").css("display", "block");

            retrieveAgeById(id);
            $("#vcdagerng").attr("disabled", "true");
        }


        //PUPULATION
        function retrievePopulation() {
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/ahmsdnistes/jx/ahmsdnis000/retrieve-txnppltns",
                dataType: 'json',
                timeout: 100000,
                success: function (resp) {
                    console.log("SUCCESS: ");
                    console.log(resp);
                    fillPopulation(resp);
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                    /* display(e); */
                },
                done: function (e) {
                    console.log("DONE");
                    /* enableSearchButton(true); */
                }
            });

        }

        function fillPopulation(resp)
        {
            content = "";
            response = resp.data;
            $(function () {
                $.each(response, function (i, item) {
                    content += "<tr>\n\
                            <td>" + (i + 1) + "</td>\n\
                            <td>" + response[i].ahmsdnisTxnppltnsPK.nyear + "</td>\n\
                            <td>" + response[i].vbpsprvnm + "</td>\n\
                            <td>" + response[i].vbpsdstrnm + "</td>\n\
                            <td>" + response[i].ahmsdnisTxnppltnsPK.vagerng + "</td>\n\
                            <td> " + response[i].nmale + "</td>\n\
                            <td> " + response[i].nfemale + "</td>\n\
                            <td> " + response[i].dbgneff + "\n\
                            <td> " + response[i].dlasteff + "</td>\n\
                            <td> <a href='#' >\n\
                                    <a href='#' onclick=getPopulationById('" + response[i].ahmsdnisTxnppltnsPK.nyear + "','" + response[i].ahmsdnisTxnppltnsPK.vbpsprvcd + "','" + response[i].ahmsdnisTxnppltnsPK.vbpsdstrcd + "','" + response[i].ahmsdnisTxnppltnsPK.vagerng + "')>\n\
                                    <i class='fa fa-edit text-danger'></i>\n\
                                </a>\n\
                                </a></td>";
                });
                $("#bodyPopulation").html(content);
            });
        }

        function getPopulationById(nyear, vbpsprvcd, vbpsdstrcd, vagerng) {
            $("#maintainPopulation").css("display", "none");
            $("#uploadAgeForm").css("display", "none");
            $("#maintainPopulation").css("visibility", "hidden");
            $("#editAgeForm").css("visibility", "visible");

            retrievePopulationById(nyear, vbpsprvcd, vbpsdstrcd, vagerng);
        }
        function retrievePopulationById(nyear, vbpsprvcd, vbpsdstrcd, vagerng) {
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/ahmsdnistes/jx/ahmsdnis000/findbycode-txnppltns?nyear="
                        + nyear + "&vbpsprvcd=" + vbpsprvcd + "&vbpsdstrcd=" + vbpsdstrcd + "&vagerng=" + vagerng + "",
                dataType: 'json',
                timeout: 100000,
                success: function (resp) {
                    fillFormPopulation(resp);
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                    /* display(e); */
                },
                done: function (e) {
                    console.log("DONE");
                    /* enableSearchButton(true); */
                }
            });
        }

        function fillFormPopulation(resp) {
            $("#nyear").val(resp.data[0].ahmsdnisTxnppltnsPK.nyear);
            $("#vbpsprvnm").val(resp.data[0].vbpsprvnm);
            $("#nmale").val(resp.data[0].nmale);
            $("#nfemale").val(resp.data[0].nfemale);
            $("#dbegineffpop").val(resp.data[0].dbgneff);
            $("#dendeffpop").val(resp.data[0].dlasteff);
            $("#vbpsdstrnm").val(resp.data[0].vbpsdstrnm);
            $("#vagerng").val(resp.data[0].ahmsdnisTxnppltnsPK.vagerng);
            $("#vbpsprvcd").val(resp.data[0].ahmsdnisTxnppltnsPK.vbpsprvcd);
            $("#vbpsdstrcd").val(resp.data[0].ahmsdnisTxnppltnsPK.vbpsdstrcd);
            
            $("#nyear").attr("disabled","true");
            $("#vagerng").attr("disabled","true");

        }

        function saveFormPopulation() {
            var form = {}
            var pk = {}
            var api = "/ahmsdnistes/jx/ahmsdnis000/update-txnppltns";

            pk["nyear"] = $("#nyear").val();
            pk["vagerng"] = $("#vagerng").val();
            pk["vbpsprvcd"] = $("#vbpsprvcd").val();
            pk["vbpsdstrcd"] = $("#vbpsdstrcd").val();

            form["ahmsdnisTxnppltnsPK"] = pk;
            form["vbpsprvnm"] = $("#vbpsprvnm").val();
            form["nmale"] = $("#nmale").val();
            form["nfemale"] = $("#nfemale").val();
            form["dbgneff"] = $("#dbegineffpop").val();
            form["dlasteff"] = $("#dendeffpop").val();
            form["vbpsdstrnm"] = $("#vbpsdstrnm").val();

            console.log("TEST :" + JSON.stringify(form));
            if (!isvalidDate($("#dbegineffpop").val(), $("#dendeffpop").val())) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Begin Effective harus lebih kecil dari End Effective'
                });
                return;
            }
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: api,
                data: JSON.stringify(form),
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    $("#maintainPopulation").css("display", "block");
                    $("#maintainPopulation").css("visibility", "visible");

                    $("#editAgeForm").css("visibility", "hidden");
                    $("#editAgeForm").css("display", "block");
                    window.scrollTo(0, 0);
                    retrievePopulation();
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                },
                done: function (e) {
                    console.log("DONE");
                    enableButton(true);
                }
            });
        }

        //Search Age Range
        $("#formSearchAgeRange").submit(function (event) {
            event.preventDefault();

            searchAgeRangeById($("#searchAgeRange").val());
        });

        function searchAgeRangeById(search) {
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/ahmsdnistes/jx/ahmsdnis000/search-mstagerngs?search=" + search,
                dataType: 'json',
                timeout: 100000,
                success: function (resp) {
                    if (resp.data.length > 0) {
                        fillTableAgeRangeSearch(resp);
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Data Dengan keyword ' + search + ' tidak di temukan!'
                        });
                    }
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                },
                done: function (e) {
                    console.log("DONE");
                    /* enableSearchButton(true); */
                }
            });
        }

        function fillTableAgeRangeSearch(resp) {
            response = resp.data;
            console.log(response);
            var content = "";
            $(function () {
                $.each(response, function (i, item) {
                    content += "<tr>\n\
                            <td>" + response[i].vcdagerng + "</td>\n\
                            <td>" + response[i].nstart + "</td>\n\
                            <td>" + response[i].nend + "</td>\n\
                            <td> " + response[i].vdscrptn + "</td>\n\
                            <td><a href='#' onclick=getById('" + response[i].vcdagerng + "')>\n\
                                    <i class='fa fa-edit text-danger'></i>\n\
                                </a></td>";
                });
                $('#tableBodyAgeRange').html(content);
            });
        }
        $("#ahmsdnis000BtnResetAge").click(function () {
            $("#searchAgeRange").val('');
            retrieveAgeRange();
        })

//        Search population
        $("#formSearchPopulation").submit(function (event) {
            if ($("#provinceSearch").val() == '') {

            }
            event.preventDefault();
            searchPopulation();
        });
        $("#buttonResetPopulation").click(function () {
            $("#provinceSearch").val('');
            $("#districtSearch").val('');
            $("#yearSearch").val('');
            retrievePopulation();
        })

        function searchPopulation() {
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/ahmsdnistes/jx/ahmsdnis000/search-txnppltns?year=" + $("#yearSearch").val() + "&province=" + $("#provinceSearch").val() + "&district=" + $("#districtSearch").val() + "",
                dataType: 'json',
                timeout: 100000,
                success: function (resp) {
                    if (resp.data.length > 0) {
                        fillPopulation(resp);
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Data Dengan keyword  tidak di temukan!'
                        });
                    }
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                },
                done: function (e) {
                    console.log("DONE");
                    /* enableSearchButton(true); */
                }
            });
        }


        function deleteAgeRange(id) {
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/ahmsdnistes/jx/ahmsdnis000/delete-mstagerngs/" + id,
                dataType: 'json',
                timeout: 100000,
                success: function (resp) {
                    console.log("SUCCESS: ");
                    window.location.href = "";
                    retrieveAgeRange();
                },
                error: function (e) {
                    console.log("ERROR: ", e);

                },
                done: function (e) {
                    console.log("DONE");

                }
            });
        }



        //upload file
        var files = [];
        $('#fileLoader').on('change', function () {
            files = event.target.files;
        });

        $('#fileSubmit').on('click', function () {
            processUpload();
        });
        function processUpload() {
            if (files.length === 0) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Pilih file terlebih dahulu!'
                });
                return;
            }
            var oMyForm = new FormData();
            oMyForm.append("file", files[0]);
            $.ajax({dataType: 'json',
                url: "/ahmsdnistes/uploadxlstmppopulation?clazz=id.co.ahm.sd.nis.app000.model.AhmsdnisTxnppltns&url=-idcoahmdnispopulation-preview",
                data: oMyForm,
                type: "POST",
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                success: function (result) {
                    console.log("sukses");
//                    window.location.href = "previewtmpxlspopulation";
                },
                error: function (e) {
                    window.location.href = "previewtmpxlspopulation";
                    console.log("ERROR: ", e);
                    /* display(e); */
                }
            });
        }

        $("#ahmsdnis000p01BtnDownloadPopulation").click(function () {
            window.location.href = "writexls-population?filetemplate=template_population.xlsx&outputfilename=Template_Upload_POPULATION_DATA.xlsx"
        });

        $(".custom-file-input").on("change", function () {
            var fileName = $(this).val().split("\\").pop();
            $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
        });
    </script>
    <style>
        .nav-tabs .nav-item .nav-link {
            background-color: #F3F0F3;
            color: black;
        }

        .nav-tabs .nav-item .nav-link.active {
            color: red;
        }

    </style>
</html>
