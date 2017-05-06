<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
        <meta charset="UTF-8" />
        <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  -->
        <title>Sentwort</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="Sentwort" />
        <meta name="keywords" content="html5, css3, form, switch, animation, :target, pseudo-class" />
        <meta name="author" content="Codrops" />
        <link rel="shortcut icon" href="../favicon.ico"> 
        <link rel="stylesheet" type="text/css" href="<c:url value='/styles/demo.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/styles/style.css'/>" />
		<link rel="stylesheet" type="text/css" href="<c:url value='/styles/animate-custom.css'/>" />
    </head>
<body>

	<div class="container">
            <header>
                <h1>SENTWORT</h1>
            </header>
            <section>				
                <div id="container_demo" >
                    <!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
                    <a class="hiddenanchor" id="toregister"></a>
                    <a class="hiddenanchor" id="tologin"></a>
                    <div id="wrapper">
                    <div id="login" class="animate form">
                        <c:url var="loginUrl" value="/login" />
                        <form action="${loginUrl}" method="post" autocomplete="on">
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                    <p>Invalid username and password.</p>
                                </div>
                            </c:if>
                            <c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    <p>You have been logged out successfully.</p>
                                </div>
                            </c:if>
                                <div class="alert alert-success">
                                    <p>${success}</p>
                                </div>
                            <h1>Log in</h1> 
                                <p> 
                                    <label for="username" class="uname" data-icon="u" > Username </label>
                                    <input id="username" name="username" required="required" type="text" placeholder="username"/>
                                </p>
                                <p> 
                                    <label for="password" class="youpasswd" data-icon="p"> Password </label>
                                    <input id="password" name="password" required="required" type="password" placeholder="eg. X8df!90EO" /> 
                                </p>
                                <p class="keeplogin"> 
									<input type="checkbox" id="rememberme" name="remember-me"> 
									<label for="loginkeeping">Remember me</label>
								</p>
                            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                                 
                            <p class="login button"> 
                                    <input type="submit" value="Login" /> 
								</p>
                                <p class="change_link">
									Not a member yet ?
									<a href="<c:url value='/newuser' />" class="to_register">Register</a>
							</p>
                        </form>
                    </div>
                </div>
            </div>
            </section>
        </div>

</body>
</html>