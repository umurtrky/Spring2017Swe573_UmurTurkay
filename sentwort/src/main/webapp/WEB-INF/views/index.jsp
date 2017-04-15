<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>
<%@taglib prefix="demo" tagdir="/WEB-INF/tags"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/messages" var="messagesUrl" />

<demo:header />

<kendo:grid name="grid" rowTemplate="row-template" groupable="true" sortable="true" style="height:550px;">
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

<demo:footer />