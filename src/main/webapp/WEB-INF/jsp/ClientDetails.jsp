<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CreateClient</title>
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
<script type="text/javascript" src="resources/js/msacommon.js"></script>
<style>
.content-wrapper {
	height: 100%;
	width: 100%;
}
.clientform{
	margin-top:10%;
}
.form_body li:first-child{
	margin-top:0%;
}
.form_body li{
    padding-top: 10px;
    margin-top: 2.5%;
    list-style:none;
    height:25px;
    color:#003399;
    font-size:20px;
}
.form_body li label{
	width:25%;
}

.form_body li:last-child{
    margin-left:40%;
    margin-top: 30px;
}
.field{
    position: absolute;
    margin-left:2.5%;
    width: 60%;
    height: 30px;
    border-radius: 4px;
    box-shadow: 0 1px 1px 0.5px;
}
</style>
</head>
<body background="resources/images/bck.jpg">
	<div class="wrapper">
		<header>
			<img id="oracle" class="oracle" src="resources/images/img3.png" /> 
			<img id="logo" class="logo" src="resources/images/img1.png" />
			<div id="page_title">Mail Storage Administration</div>
			<ul>
				<li style="display: inline-block; list-style: none"><h4>Welcome
						${ses.username}!</h4></li>
				<li
					style="display: inline-block; list-style: none; padding-left: 45%"><h4>${ses.role}</h4></li>
			</ul>
		</header>
		<div class="content-wrapper">
			<div class="clientform">
				<div class="container">
					<div class="row">
						<div class="col-lg-9">
							<form:form id='myform' action="CreateClient" modelAttribute="client">
								<div class='form_body' id='form'>
									<ul>
										<li><label>Name</label><form:input id="name" path="name"
												placeholder="                Enter Client's Name"
												onfocus="foc(this.id)" onblur="focout(this.id)"
												cssClass="field" />
										</li>
										<li><form:errors path="name" cssClass="error" /></li>
										<li><label>BCC Address</label><form:input cssClass="field"
												id="bcc" path="bcc"
												placeholder="                Enter Client's BCC"
												onfocus="foc(this.id)" onblur="focout(this.id)" />
										</li>
										<li><form:errors path="bcc" cssClass="error" /></li>
										<li>
											<button class="btn btn-primary btn-lg" id="sub" type="submit">Create</button>
										</li>
									</ul>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>