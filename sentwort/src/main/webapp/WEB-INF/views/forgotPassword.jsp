<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

                        <div id="register" class="animate form">
                            <form:form method="POST" modelAttribute="user" autocomplete="on"> 
                            	<form:input type="hidden" path="id" id="id"/>
                            	<form:input type="hidden" path="createDate" id="createDate" />
                            	<form:errors path="username" class="help-inline"/>
                            	<form:errors path="password" class="help-inline"/>
                            	<form:errors type="hidden" path="secretquestionid" class="help-inline"/>
                            	<form:errors path="secretanswer" class="help-inline"/>
                            	<div class="alert alert-success">
                                    <p>${alertMessage}</p>
                                </div>
                                <h1> Forgot Password </h1> 
                                <p> 
                                    <label for="username" class="uname" data-icon="u">Username</label>
                                    
                                    <form:input path="username" id="username" name="username" required="required" type="text" placeholder="username" />
                                </p>
                                <p style="display:${displayQuestion}"> 
                                    <label for="secretquestion" class="uname" data-icon="a">Your Secret Question</label>
                                    
                                    <form:select path="secretquestionid" id="secretquestion" name="secretquestion" type="select">
                                    	<form:options items="${secretquestion}"></form:options>
                                    </form:select>
                                </p>
                                <p> </p>
                                <p style="display:${displayAnswer}"> 
                                    <label for="secretanswer" class="uname" data-icon="a">Enter the answer of your secret question</label>
                                    
                                    <form:input path="secretanswer" id="secretanswer" name="secretanswer" type="text"/>
                                </p>
                                <p style="display:${displayPassword}"> 
                                    <label for="password" class="youpasswd" data-icon="p">Password </label>
                                    
                                    <form:input path="password" id="password" name="password" type="password" placeholder="eg. X8df!90EO"/>
                                </p>
                                <p class="signin button"> 
									<input type="submit" value="${btnForgotPassword}"/> 
								</p>
                                <p class="change_link">  
                                	Remember your password?
									<a href="<c:url value='login' />" class="to_register">Login</a>
								</p>
                            </form:form>
                        </div>
						
                    </div>
                </div>  
            </section>
        </div>
    </body>
</html>