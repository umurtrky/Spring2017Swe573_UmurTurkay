<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Home</title>
		<link href="<c:url value='/styles/kendo.common.min.css'/>" rel="stylesheet" />
		<link href="<c:url value='/styles/kendo.rtl.min.css'/>" rel="stylesheet" />
		<link href="<c:url value='/styles/kendo.default.min.css'/>" rel="stylesheet" />
		<link href="<c:url value='/styles/kendo.default.mobile.min.css'/>" rel="stylesheet" />
		<link href="<c:url value='/styles/kendo.dataviz.min.css'/>" rel="stylesheet" />
		<link href="<c:url value='/styles/kendo.dataviz.default.min.css'/>" rel="stylesheet" />
		
		<script src="<c:url value='/js/jquery.min.js' />"></script>
		<script src="<c:url value='/js/kendo.all.min.js' />"></script>
		<script src="<c:url value='/js/kendo.timezones.min.js' />"></script>
		<script src="<c:url value='/js/console.js'/>"></script>
		<script src="<c:url value='/js/prettify.js'/>"></script>
    </head>
    <body>
    	<div class="page">
    		<a class="offline-button" href="<c:url value='../' />">Back to all examples</a>
    		<div id="example" class="k-content">