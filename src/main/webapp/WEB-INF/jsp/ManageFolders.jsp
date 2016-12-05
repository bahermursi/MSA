<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage</title>
<link rel="stylesheet" type="text/css"
	href="resources/css/msacommon.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/font-awesome.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap-theme.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<!--script type="text/javascript" src="js/jquery-3.1.1.js"></script-->
<link rel="javascript" type="text/javascript"
	href="resources/js/bootstrap.js" />
<link rel="javascript" type="text/javascript" href="resources/js/npm.js" />
<script>
	$(document).ready(function(){
		var currentdir="<%=(String)request.getAttribute("currentdir")%>";
		$("#browse").click(function(){
			var fname=$("#fname").html();
			$(".content-wrapper").hide();
			currentdir=currentdir+"/"+fname;
		});
	});
</script>
<style>
body {
	background-repeat: repeat;
}
.content-wrapper{
	margin-top:5%;
}
.wrapper nav {
	margin-left: 10%;
	margin-top: 2.5%;
	padding-bottom: 2.5%;
}

nav li {
	list-style: none;
	display: inline;
}

.thumbnail {
	box-shadow: 0 1px 1px 0.5px;
}

.thumbnail .caption {
	background-color: #ccffff;
}
</style>
</head>
<body>
	<div class="wrapper">
		<header>
			<img id="oracle" class="oracle" src="resources/images/img3.png" /> <img
				id="logo" class="logo" src="resources/images/img1.png" />
			<div id="page_title">Mail Storage Administration</div>
			<ul>
				<li id="${ses.username}"
					style="display: inline-block; list-style: none"><h4>
						<span class="glyphicon glyphicon-user" style="color: white"
							aria-hidden="true"></span>&nbsp;&nbsp; ${ses.username}!
					</h4></li>
				<li id="${ses.role}"
					style="display: inline-block; list-style: none; margin-left: 60%"><h4><span class="glyphicon glyphicon-king" style="color: white"
							aria-hidden="true"></span>&nbsp;&nbsp;${ses.role}</h4></li>
			</ul>
		</header>
		<div class="content-wrapper">
			<section class="container">
				<div class="row">
					<c:forEach items="${folders}" var="folder">
						<div class="col-sm-4 col-md-2"
							style="padding-bottom: 15px; height: 100px">
							<p><a href="#" id="browse"><span class="glyphicon glyphicon-folder-open" style="text-decoration:none;color:#ffff1a;font-size:100px"
								aria-hidden="true"></span>
								</a></p>
								<p id="fname" style="text-decoration:none;font-size:22.5px;padding-left:2%">${folder}</p>
						</div>
					</c:forEach>
				</div>
			</section>
		</div>
	</div>
</body>
</html>