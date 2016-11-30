<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
		<link rel="stylesheet" type="text/css" href="resources/css/msacommon.css"/>
		<link rel="stylesheet" type="text/css" href="resources/css/font-awesome.css"/>
        <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="resources/css/bootstrap-theme.css"/>
        <link rel="stylesheet" type="text/css" href="resources/css/bootstrap-theme.min.css"/>
        <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <!--script type="text/javascript" src="js/jquery-3.1.1.js"></script-->
        <link rel="javascript" type="text/javascript" href="resources/js/bootstrap.js"/>
        <link rel="javascript" type="text/javascript" href="resources/js/npm.js"/>
        <script>
        	function navigate(){
        		location.href="ClientDetails";
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
           <img id="oracle" class="oracle"  src="resources/images/img3.png"/>
           <img id="logo" class="logo" src="resources/images/img1.png"/>
           <div id="page_title">
                 Mail Storage Administration
           </div>
           <ul>
	           <li style="display:inline-block;list-style:none"><h4>Welcome ${ses.username}!</h4></li>
	           <li style="display:inline-block;list-style:none;padding-left:45%"><h4>${ses.role}</h4></li>
           </ul>
        </header>
    </div>
    <button role="button"  class="btn btn-primary btn-lg" onclick="navigate()">Create Client</a>
</body>
</html>