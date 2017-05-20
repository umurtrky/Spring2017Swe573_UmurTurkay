<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/messages" var="messagesUrl" />
<c:url value="/hashtags" var="hashtagUrl" />

<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<link href="<c:url value='/styles/kendo.common.min.css'/>"
	rel="stylesheet" />
<link href="<c:url value='/styles/kendo.rtl.min.css'/>" rel="stylesheet" />
<link href="<c:url value='/styles/kendo.default.min.css'/>"
	rel="stylesheet" />
<link href="<c:url value='/styles/kendo.default.mobile.min.css'/>"
	rel="stylesheet" />
<link href="<c:url value='/styles/kendo.dataviz.min.css'/>"
	rel="stylesheet" />
<link href="<c:url value='/styles/kendo.dataviz.default.min.css'/>"
	rel="stylesheet" />
<link href="<c:url value='/styles/app.css'/>" rel="stylesheet" />

<script src="<c:url value='/js/jquery.min.js' />"></script>
<script src="<c:url value='/js/kendo.all.min.js' />"></script>
<script src="<c:url value='/js/console.js'/>"></script>
<script src="<c:url value='/js/prettify.js'/>"></script>
</head>
<body>
	<div>
		<%@include file="authheader.jsp"%>
	</div>
	<div>
		<p></p>
	</div>
	<div>
		<table>
			<tr>
				<td valign="top" style="border: 1px solid;"><%@include
						file="leftMenu.jsp"%></td>
				<td valign="top" style="border: 1px solid;">
					<div id="homeDiv">
						<table>
							<tr>
								<td>
									<table width="1175" height="100" border="0" cellpadding="0"
										cellspacing="0" align="center" id="main">
										<tr>
											<td width="1160" valign="top"><table width="100%"
													border="0" cellpadding="0" cellspacing="0" id="header">
													<tr>
														<td height="42" valign="top"><table width="100%"
																border="0" cellpadding="0" cellspacing="0">
																<!--DWLayoutTable-->
																<tr>
																	<td width="280" height="42"><table width="100%"
																			border="0" cellpadding="0" cellspacing="0">
																			<!--DWLayoutTable-->
																			<tr>
																				<td width="120" class="text1 align_L">START
																					DATE</td>
																				<td width="181" valign="middle" class="ozelinput2"
																					id="startTD"><kendo:dateTimePicker
																						name="datetimepickerStart" value="${oneWeekBefore}"
																						style="width: 100%;"></kendo:dateTimePicker></td>
																			</tr>
																		</table></td>
																	<td width="37" height="42">&nbsp;</td>
																	<td width="280"><table width="100%" border="0"
																			cellpadding="0" cellspacing="0">
																			<!--DWLayoutTable-->
																			<tr>
																				<td width="75" height="42" class="text1 align_R">END
																					DATE</td>
																				<td width="1" height="42">&nbsp;</td>
																				<td width="181" border="0"><div>
																						<kendo:dateTimePicker name="datetimepickerFinish"
																							value="${today}" style="width: 100%;"></kendo:dateTimePicker>
																					</div></td>
																			</tr>
																		</table></td>
																	<td width="15" height="42">&nbsp;</td>
																</tr>
															</table></td>
														<td rowspan="2" align="right" valign="middle" border="0">
															<kendo:button name="filtreleBtn" click="onClickFilter" type="button"
																class="appButton">
																<kendo:button-content>
															        Search
															    </kendo:button-content>
															</kendo:button>
														</td>
													</tr>
													<tr>
														<td height="42" valign="top"><table width="100%"
																border="0" cellpadding="0" cellspacing="0">
																<!--DWLayoutTable-->
																<tr>
																	<td width="280" valign="top"><table width="100%"
																			border="0" cellpadding="0" cellspacing="0">
																			<tr>
																				<td width="120" valign="middle"
																					class="text1 align_L">HASHTAG</td>
																				<td width="181"><div class="demo-section k-content">
																						<kendo:multiSelect name="hashtags" placeholder="Select hashtags"
																							dataTextField="hashtagname" dataValueField="id"
																							filter="startswith">
																							<kendo:dataSource serverFiltering="true">
																								<kendo:dataSource-transport>
																									<kendo:dataSource-transport-read
																										url="${hashtagUrl}" type="POST"
																										contentType="application/json" />
																								</kendo:dataSource-transport>
																							</kendo:dataSource>
																						</kendo:multiSelect>
																					</div></td>
																			</tr>
																		</table></td>
																	<td width="37" height="42">&nbsp;</td>
																	<td width="280" valign="top"><table width="100%"
																			border="0" cellpadding="0" cellspacing="0">
																			<tr>
																				<td width="75">SENTIMENT</td>
																				<td width="1" height="42">&nbsp;</td>
																				<td width="181" id="sentimentsTD">
																			<button onClick="buttonHandlerUnselect();"
																					id="unselectButton">
																					<img src="<c:url value='/images/dyg11.jpg' />" width="25"
																						height="25" border="0" />
																				</button>
																				<button onClick="buttonHandlerNotr();"
																					id="notrButton">
																					<img src="<c:url value='/images/dyg2.jpg' />" width="25" height="25"
																						border="0" />
																				</button>

																				<button onClick="buttonHandlerNegative();"
																					id="negativeButton">
																					<img src="<c:url value='/images/dyg31.jpg' />" width="25" height="25"
																						border="0" />
																				</button>
																				<button onClick="buttonHandlerPositive();"
																					id="positiveButton">
																					<img src="<c:url value='/images/dyg4.jpg' />" width="25" height="25"
																						border="0" />
																				</button>
																				</td></tr>
																		</table></td>
																	<td width="15" valign="top">&nbsp;</td>
																</tr>
															</table></td>
													</tr>
													<tr>
														<td>
															<div class="alert alert-success">
							                                    <p>${emptyFilterHashtag}</p>
							                                </div>
														</td>
													</tr>
												</table></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td><kendo:grid name="gridSentiment"
										rowTemplate="row-template" groupable="true" sortable="true"
										resizable="true" filterable="true" style="height:650px;">
										<kendo:grid-pageable refresh="true" pageSizes="true"
											buttonCount="5">
										</kendo:grid-pageable>
										<kendo:grid-columns>
											<kendo:grid-column title="Account" field="account"
												width="150" />
											<kendo:grid-column title="Tweet" field="message" width="150" />
											<kendo:grid-column title="Hashtag" field="hashtag"
												width="150" />
											<kendo:grid-column title="Sentiment" field="sentiment"
												width="150">
													<kendo:grid-column-filterable multi="true"/>
												</kendo:grid-column>	
											<kendo:grid-column title="Share Date" field="sharedate"
												width="150" />
										</kendo:grid-columns>
										<kendo:dataSource pageSize="10" serverSorting="true" serverFiltering="true" serverGrouping="true">
											<kendo:dataSource-transport>
												<kendo:dataSource-transport-read url="${messagesUrl}" type="POST" contentType="application/json" />
												<kendo:dataSource-transport-parameterMap>
			                	<script>
				                	function parameterMap(options,type) { 
				                		if(type==="read"){
				                			return JSON.stringify(options);
				                		} else {
				                			return JSON.stringify(options.models);
				                		}
				                	}
			                	</script>
			                </kendo:dataSource-transport-parameterMap>
											</kendo:dataSource-transport>
										</kendo:dataSource>
									</kendo:grid></td>
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
               		<span class="title">#: account #</span>
            	</td>
				<td class="details">
               		<span class="description">#: message#</span>
            	</td>
				<td class="details">
               		<span class="description">#: hashtag#</span>
            	</td>
				<td class="details">
					#if(sentiment == 2) {
						#<span class="description">Neutral</span>#
					}
					else if(sentiment == 0) {
						#<span class="description">Negative</span>#
					}
					else if(sentiment == 4) {
						#<span class="description">Positive</span>#
					}
					else if(sentiment == -1) {
						#<span class="description">Not analyzed</span>#
					}
					#
            	</td>
				<td class="details">
               		<span class="description">#: new Date(sharedate).toString().substring(4,24)#</span>
            	</td>
        	</tr>
    	</script>
    	<script type="text/javascript">
	    	function buttonHandlerUnselect() {
	    		var host = document.location.host;
	    		var unslectImage = document.images[0].src;
	    		if (unslectImage == "http://" + host + "/sentwort/images/dyg11.jpg") {
	    			document.images[0].src = "<c:url value='/images/dyg1.jpg' />";
	    		} else {
	    			document.images[0].src = "<c:url value='/images/dyg11.jpg' />";
	    		}
	    	}
	
	    	function buttonHandlerNotr() {
	    		var host = document.location.host;
	    		var notrImage = document.images[1].src;
	    		if (notrImage == "http://" + host + "/sentwort/images/dyg2.jpg") {
	    			document.images[1].src = "<c:url value='/images/dyg21.jpg' />";
	    		} else {
	    			document.images[1].src = "<c:url value='/images/dyg2.jpg' />";
	    		}
	    	}
	
	    	function buttonHandlerNegative() {
	    		var host = document.location.host;
	    		var negativeImage = document.images[2].src;
	    		if (negativeImage == "http://" + host + "/sentwort/images/dyg3.jpg") {
	    			document.images[2].src = "<c:url value='/images/dyg31.jpg' />";
	    		} else {
	    			document.images[2].src = "<c:url value='/images/dyg3.jpg' />";
	    		}
	    	}
	
	    	function buttonHandlerPositive() {
	    		var host = document.location.host;
	    		var positiveImage = document.images[3].src;
	    		if (positiveImage == "http://" + host + "/sentwort/images/dyg4.jpg") {
	    			document.images[3].src = "<c:url value='/images/dyg41.jpg' />";
	    		} else {
	    			document.images[3].src = "<c:url value='/images/dyg4.jpg' />";
	    		}
	    	}
	    	
		    function onClickFilter(e) {
		    	var multiselect = $("#hashtags").data("kendoMultiSelect");
		    	var selectedHashtags = multiselect.value();
		    	var datetimepickerStart = $("#datetimepickerStart").data("kendoDateTimePicker");
		    	var startdate = datetimepickerStart.value();
		    	var datetimepickerFinish = $("#datetimepickerFinish").data("kendoDateTimePicker");
		    	var enddate = datetimepickerFinish.value();
		    	
		    	var host = document.location.host;
		    	var sentiment = [];
	    		var unselectImage = document.images[0].src;
	    		var notrImage = document.images[1].src;
	    		var negativeImage = document.images[2].src;
	    		var positiveImage = document.images[3].src;
	    		if (unselectImage == "http://" + host + "/sentwort/images/dyg11.jpg") {
	    			sentiment[0] = -1;
	    		}
	    		
	    		if (notrImage == "http://" + host + "/sentwort/images/dyg2.jpg") {
	    			sentiment[1] = 2;
	    		}
	    		
	    		if (negativeImage == "http://" + host + "/sentwort/images/dyg31.jpg") {
	    			sentiment[2] = 0;
	    		}
	    		
	    		if (positiveImage == "http://" + host + "/sentwort/images/dyg4.jpg") {
	    			sentiment[3] = 4;
	    		}
	    		
		    	if(sentiment.length != 0 && selectedHashtags.length != 0){
			    	$.ajax({
			            url : 'messages',
			            type : 'POST',
			            data: JSON.stringify({
			            	"hashtagIds": selectedHashtags,
			            	"startdate": startdate,
			            	"enddate": enddate,
			            	"sentiment": sentiment
			            	}),
			            dataType: "json",
			            contentType: "application/json",
			            success : function(data) {
			            	var grid = $('#gridSentiment').data('kendoGrid');
			            	var dataSource = new kendo.data.DataSource({
			            		  data: data,
			            		  pageSize: 10,
			            		  serverSorting : true, 
			            		  serverFiltering : true,
			            		  serverGrouping : true
	            			});
			            	grid.setDataSource(dataSource);
			            	if(data.length == 0){
			            		alert("No records found.");
			            	}
			            }
			        });
		    	}
		    	else{
		    		alert("Please do not leave any filters empty.");
		    	}
		    }
    	</script>
    	<style>
    		#unselectButton,#notrButton,#negativeButton,#positiveButton,#sentimentsTD
			{
				border: 0px;
				padding: 0px 0px 0px;
				line-height: 0em;
				background: none;
			}
    		
    	</style>

</body>
</html>