<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
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
<style>
body {
	background-repeat: repeat;
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
	box-shadow: 0 8px 8px 4px;
}
</style>
<script>
	function navigate() {
		location.href = "ClientDetails";
	}
</script>
</head>
<body background="resources/images/bck.jpg">
	<!--div align="center">
        <h2>Welcome ${user.username}! You have logged in successfully.</h2>
        <h2>Your role is ${user.role}</h2>
    </div-->
	<div class="wrapper">
		<header>
			<img id="oracle" class="oracle" src="resources/images/img3.png" /> <img
				id="logo" class="logo" src="resources/images/img1.png" />
			<div id="page_title">Mail Storage Administration</div>
			<ul>
				<li style="display: inline-block; list-style: none"><h4>Welcome
						${ses.username}!</h4></li>
				<li
					style="display: inline-block; list-style: none; padding-left: 60%"><h4>${ses.role}</h4></li>
			</ul>
		</header>
		<nav>
			<ul>
				<li><button role="button" class="btn btn-primary btn-lg"
						onclick="navigate()">Create New Client</button></li>
			</ul>
		</nav>
		<section class="container">

			<div class="row">
				<c:forEach items="${clientList}" var="client">
					<div class="col-sm-6 col-md-3"
						style="padding-bottom: 15px; height: 400px">
						<div class="thumbnail">
							<div class="caption">
								<h3>${client.name}</h3>
								<p>
									<img src="${client.imagePath}"
										style="height: 75px; width: 150px" />
								</p>
								<p>BCC Address: ${client.bcc}</p>
								<p>Last Updated: ${client.lastUpdated}</p>
								<p>Total Mails: ${client.total_mails}</p>
								<p>Space Occupied: ${client.folderSize}</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

		</section>
	</div>
</body>
</html>