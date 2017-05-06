<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/statistics" var="messagesUrl" />

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
		<link href="<c:url value='/styles/app.css'/>" rel="stylesheet" />
		
		<script src="<c:url value='/js/jquery.min.js' />"></script>
		<script src="<c:url value='/js/kendo.all.min.js' />"></script>
		<script src="<c:url value='/js/kendo.timezones.min.js' />"></script>
		<script src="<c:url value='/js/console.js'/>"></script>
		<script src="<c:url value='/js/prettify.js'/>"></script>
    </head>
    <body>
		<div>
		<%@include file="authheader.jsp" %>
		</div>
		<table>
			<tr>
				<td valign="top" style="border:1px solid;">
					<%@include file="leftMenu.jsp" %>
				</td>
				<td valign="top" style="border:1px solid;">
					<div id="homeDiv">
						<table style="height:650px; width:1300px;">
							<tr>
								<td>
									<table style="border:1px solid; height:450px; width:300px; text-align:center;">
										<tr>
											<td style="">Hashtag</td>
											<td></td>
										</tr>
										<tr>
											<td>Total</td>
											<td>${numofhashtags}</td>
										</tr>
										<tr>
											<td>Active</td>
											<td>${numofactivehashtags}</td>
										</tr>
										<tr>
											<td>Passive</td>
											<td>${numofpassivehashtags}</td>
										</tr>
									</table>
								</td>
								<td>
									<table style="border:1px solid; height:450px; width:300px; text-align:center;">
										<tr>
											<td>Tweet</td>
											<td></td>
										</tr>
										<tr>
											<td>Total</td>
											<td>${numOfMessages}</td>
										</tr>
										<tr>
											<td>Analyzed</td>
											<td>${numOfAnalyzedMessages} - </td>
										</tr>
									</table>
								</td>
								<td>
									<table style="border:1px solid; height:450px; width:300px; text-align:center;">
										<tr>
											<td>Sentiment</td>
											<td></td>
										</tr>
										<tr>
											<td>Positive</td>
											<td>${numOfPositiveMessages}</td>
										</tr>
										<tr>
											<td>Negative</td>
											<td>${numOfNegativeMessages} - </td>
										</tr>
										<tr>
											<td>Neutral</td>
											<td>${numOfNeutralMessages} - </td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>

	</body>
</html>