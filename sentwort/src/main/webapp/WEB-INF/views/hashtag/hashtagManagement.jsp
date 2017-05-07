<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/hashtag/read" var="readUrl" />
<c:url value="/hashtag/destroy" var="destroyUrl" />

<!DOCTYPE html>
<html>
    <head>
        <title>Hashtag Management</title>
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
		<%@include file="../authheader.jsp" %>
		</div>
		<div><p></p></div>
		<div>
			<table>
				<tr>
					<td valign="top" style="border:1px solid;">
						<%@include file="../leftMenu.jsp" %>
					</td>
					<td valign="top" style="border:1px solid;">
						<div id="hashtagGridDiv">
							<table>
								<tr>
									<td>
										<label>Hashtag</label>
										<input id="txtHashtag" type="text"></input>
										<kendo:button name="btnAddHashtag" type="button" click="onClickAdd" class="appButton">
										    <kendo:button-content>
										        Add
										    </kendo:button-content>
										</kendo:button>
										<p>${hashtagAlreadyExists}</p>
									</td>
								</tr>
									<tr><td></td></tr>
								<tr>
									<td>
										<kendo:grid name="gridHashtag" rowTemplate="row-template" groupable="true" selectable="single" sortable="true"
										 	style="height:650px;">
											<kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5">
											</kendo:grid-pageable>
										    <kendo:grid-columns>
										        <kendo:grid-column title="Name" field="hashtagname" width="200" />
										        <kendo:grid-column title="Active" field="isactive" width="75" />
										        <kendo:grid-column title="Create Date" field="createdate" width="200" />
										        <kendo:grid-column title="Start Date" field="startdate" width="200" />
										        <kendo:grid-column title="Stop Date" field="stopdate" width="200" />
										    </kendo:grid-columns>
										    <kendo:dataSource pageSize="10">
										        <kendo:dataSource-transport>
									                <kendo:dataSource-transport-read url="${readUrl}" dataType="json" type="POST" contentType="application/json"/>
									                <kendo:dataSource-transport-destroy url="${destroyUrl}" dataType="json" type="POST" contentType="application/json" />
										        </kendo:dataSource-transport>
										    </kendo:dataSource>
										</kendo:grid>
									</td>
								</tr>
								<tr>
									<td style="text-align:right">
										<kendo:button name="btnStart" type="button" click="onClickStart" class="appButton">
										    <kendo:button-content>
										        Start Listening
										    </kendo:button-content>
										</kendo:button>
										<kendo:button name="btnStop" type="button" click="onClickStop" class="appButton">
										    <kendo:button-content>
										        Stop Listening
										    </kendo:button-content>
										</kendo:button>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<script id="row-template" type="text/x-kendo-template">
        <tr data-uid="#: id #">
            <td class="details">
               <span class="title">#: hashtagname #</span>
            </td>
			<td class="details">
				# if(isactive == 1) {
					#<span class="description">Active</span>#
				}
				else {
					#<span class="description">Passive</span>#
				}
				#
            </td>
			<td class="details">
               <span class="description">#: new Date(createdate).toString().substring(4,24)# </span>
            </td>
			<td class="details">
               <span class="description">#: new Date(startdate).toString().substring(4,24)# </span>
            </td>
			<td class="details">
				# if(stopdate != null) {
					#<span class="description">#: new Date(stopdate).toString().substring(4,24)# </span>#
				}
				#
            </td>
        </tr>
    	</script>
		<script>
		    function onClickAdd(e) {
		        $.ajax({
		            url : 'create',
		            type : 'POST',
		            data: JSON.stringify({
		            	"hashtagname": $('#txtHashtag').val(),
		            	"isactive": 1,
		            	"createdate": new Date(),
		            	"startdate": new Date(),
		            	"stopdate": null
		            	}),
		            dataType: "json",
		            contentType: "application/json",
		            success : function() {
		            	$('#gridHashtag').data('kendoGrid').dataSource.read();
		            	$('#gridHashtag').data('kendoGrid').refresh();
		            }
		        });
		    }
		    function onClickStart(e) {
		    	var grid = $("#gridHashtag").data("kendoGrid");
		    	var selectedItem = grid.dataItem(grid.select());
		    	console.log(selectedItem.id);
		    	$.ajax({
		            url : 'startListening',
		            type : 'POST',
		            data: JSON.stringify({
		            	"id": selectedItem.id
		            	}),
		            dataType: "json",
		            contentType: "application/json",
		            success : function() {
		            	$('#gridHashtag').data('kendoGrid').dataSource.read();
		            	$('#gridHashtag').data('kendoGrid').refresh();
		            }
		        });
		    }
		    function onClickStop(e) {
		    	var grid = $("#gridHashtag").data("kendoGrid");
		    	var selectedItem = grid.dataItem(grid.select());
		        $.ajax({
		            url : 'stopListening',
		            type : 'POST',
		            data: JSON.stringify({
		            	"id": selectedItem.id
		            	}),
		            dataType: "json",
		            contentType: "application/json",
		            success : function() {
		            	$('#gridHashtag').data('kendoGrid').dataSource.read();
		            	$('#gridHashtag').data('kendoGrid').refresh();
		            }
		        });
		    }
		</script>
	</body>
</html>