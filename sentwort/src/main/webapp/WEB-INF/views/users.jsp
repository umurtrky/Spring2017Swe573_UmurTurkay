<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:url value="/users" var="transportReadUrl" />

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Users List</title>
    <link href="styles/kendo.common.min.css" rel="stylesheet" type="text/css" />
    <link href="styles/kendo.default.min.css" rel="stylesheet" type="text/css" />
    <link href="styles/kendo.bootstrap.min.css" rel="stylesheet" type="text/css" />
    
    <script src="js/jquery.min.js"></script>
    <script src="js/kendo.all.min.js"></script>
</head>
 
<body>
    <kendo:grid name="grid" groupable="true" sortable="true" style="height:550px;">
		<kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5">
		</kendo:grid-pageable>
	    <kendo:grid-columns>
	        <kendo:grid-column title="Name" field="username" />
	        <kendo:grid-column title="Question" field="secretquestion" />
	    </kendo:grid-columns>
	    <kendo:dataSource pageSize="10">
	         <kendo:dataSource-schema>
	            <kendo:dataSource-schema-model>
	                <kendo:dataSource-schema-model-fields>
	                    <kendo:dataSource-schema-model-field name="username" type="string" />
	                    <kendo:dataSource-schema-model-field name="secretquestion" type="string" />
	                </kendo:dataSource-schema-model-fields>
	            </kendo:dataSource-schema-model>
	        </kendo:dataSource-schema>
	        <kendo:dataSource-transport>
	            <kendo:dataSource-transport-read url="${transportReadUrl}"/>
	        </kendo:dataSource-transport>
	    </kendo:dataSource>
	</kendo:grid>
</body>
</html>