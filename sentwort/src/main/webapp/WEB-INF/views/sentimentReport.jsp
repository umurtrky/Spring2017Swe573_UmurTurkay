<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/messages" var="messagesUrl" />

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
					<kendo:grid name="grid" rowTemplate="row-template" groupable="true" sortable="true" style="height:650px;">
						<kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5">
						</kendo:grid-pageable>
					    <kendo:grid-columns>
					        <kendo:grid-column title="Account" field="account" width="150" />
					        <kendo:grid-column title="Tweet" field="message" width="150" />
					        <kendo:grid-column title="Sentiment" field="sentiment" width="150" />
					        <kendo:grid-column title="Share Date" field="sharedate" width="150" />
					    </kendo:grid-columns>
					    <kendo:dataSource pageSize="10">
					        <kendo:dataSource-transport>
					            <kendo:dataSource-transport-read url="${messagesUrl}"/>
					        </kendo:dataSource-transport>
					    </kendo:dataSource>
					</kendo:grid>
					</div>
				</td>
			</tr>
		</table>

		<script id="row-template" type="text/x-kendo-template">
        	<tr data-uid="#: id #">
           	 	<td class="details">
               		<span class="title">#: account #</span>
            	</td>
				<td class="details">
               		<span class="description">#: message#</span>
            	</td>
				<td class="details">
               		<span class="description">#: sentiment# </span>
            	</td>
				<td class="details">
               		<span class="description">#: sharedate# </span>
            	</td>
        	</tr>
    	</script>

	</body>
</html>