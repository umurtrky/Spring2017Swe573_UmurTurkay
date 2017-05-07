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
		<div><p></p></div>
		<div>
			<table>
				<tr>
					<td valign="top" style="border:1px solid;">
						<%@include file="leftMenu.jsp" %>
					</td>
					<td style="border:1px solid; width:1300px; height:100%;">
						<table>
							<tr>
								<td style="width:390px;">
									<div class="demo-section k-content wide" style="height:650px;">
									    <kendo:chart name="hashtagChart" style="height:100%;">
									        <kendo:chart-title text="Hashtags" />
									        <kendo:chart-legend position="top" />
									        <kendo:chart-series>
									           <kendo:chart-seriesItem type="pie" data="${pieChartDataHashtag}">
									               <kendo:chart-seriesItem-labels visible="true" template="#= category # \n #= kendo.format('{0:P}', percentage)#" />
									           </kendo:chart-seriesItem>
									        </kendo:chart-series>
									        <kendo:chart-tooltip visible="true" template="#= category # - #= value#" />
									    </kendo:chart>
									</div>
								</td>
								<td style="width:430px;">
									<div class="demo-section k-content wide" style="height:650px;">
									    <kendo:chart name="messageChart" style="height:100%;">
									        <kendo:chart-title text="Messages" />
									        <kendo:chart-legend position="top" />
									        <kendo:chart-series>
									           <kendo:chart-seriesItem type="pie" data="${pieChartDataMessage}">
									               <kendo:chart-seriesItem-labels visible="true" template="#= category # \n #= kendo.format('{0:P}', percentage)#" />
									           </kendo:chart-seriesItem>
									        </kendo:chart-series>
									        <kendo:chart-tooltip visible="true" template="#= category # - #= value#" />
									    </kendo:chart>
									</div>
								</td>
								<td style="width:470px;">
									<div class="demo-section k-content wide" style="height:650px;">
									    <kendo:chart name="sentimentChart" style="height:100%;">
									        <kendo:chart-title text="Sentiment" />
									        <kendo:chart-legend position="top" />
									        <kendo:chart-series>
									           <kendo:chart-seriesItem type="pie" data="${pieChartDataSentiment}">
									               <kendo:chart-seriesItem-labels visible="true" template="#= category # \n #= kendo.format('{0:P}', percentage)#" />
									           </kendo:chart-seriesItem>
									        </kendo:chart-series>
									        <kendo:chart-tooltip visible="true" template="#= category # - #= value#" />
									    </kendo:chart>
									</div>
								</td>
						 	</tr>
						 </table>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>