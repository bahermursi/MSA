<%-- 
    Document   : home
    Created on : Nov 17, 2016, 6:05:49 PM
    Author     : souvik.p
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" type="text/css" href="css/msa.css"/>
        <link rel="stylesheet" type="text/css" href="css/font-awesome.css"/>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.css"/>
        <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css"/>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <!--script type="text/javascript" src="js/jquery-3.1.1.js"></script-->
        <link rel="javascript" type="text/javascript" href="js/bootstrap.js"/>
        <link rel="javascript" type="text/javascript" href="js/npm.js"/>
        <script type="text/javascript" src="js/msa.js"></script>
    </head>
    <body style="background-image:url('images/img2.jpg')">
        <div class="wrapper" id='wrap'>
            <img id="oracle" src="images/img3.png"/>
            <img id="logo" src="images/img1.png"/>
            <header>
                <div id="page_title">
                    Mail Storage Administration
                </div>
            </header>
            <div class="container" id="login_block">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-3"  id="login_form">
                        <form id='myform' action="welcome.jsp" onsubmit="return validate()">
                            <div class='form_body' id='form' method="post">
                                <ul>
                                <li><h1>Getting Started</h1></li>    
                                <li>Username   
                                    <input class="field" id="ip1" type="text" name="uname" placeholder="        Enter your Username" onfocus="foc(this.id)" onblur="focout(this.id)" required/>
                                </li>
                                <li>Password&nbsp;
                                    <input class="field" id="ip2" type="password" name="pwd" placeholder="        Enter your password" onfocus="foc(this.id)" onblur="focout(this.id)" required/>
                                </li>
                                <li>
                                    <button class="btn btn-primary btn-lg" id="sub"  type="submit" >Sign in</button>
                                </li>
                                </ul>
                             </div>   
                        </form>     
                    </div>
            </div>
        </div>
    </body>
</html>
