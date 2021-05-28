<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <link href="../css/querybook.css" rel="stylesheet" type="text/css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page import="java.util.List,com.mongodb.DBObject,java.lang.String" %>
</head>
<%@ include file="header.jsp" %>
<body>
    <div class='container mx-auto' style='width: 90%;'>
        <h2>Query Results for Database ${databasename}</h2>
            <table style="border-width: 1px; border-style: solid; border-collapse: collapse;">

                <c:if test="${querytype eq '0'}">

                    <tr><th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">
                        Sensor Collected Data</th></tr>

                    <tr><th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Mongo ID</th>
                        <th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Train ID</th>
                        <th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Carriage ID</th>
                        <th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Seat ID</th>
                        <th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Status</th>
                        <th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Timestamp</th></tr>

                    <c:forEach items="${list}" var="entry">
                        <tr><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry._id}"/>
                        </td><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry.train_id}"/>
                        </td><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry.coach_id}"/>
                        </td><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry.seat_id}"/>
                        </td><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry.status}"/>
                        </td><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry.timestamp}"/>
                        </td></tr>
                    </c:forEach>
                </c:if>

                <c:if test="${querytype eq '1'}">
                    <tr><th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Mongo ID</th>
                        <th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Train ID</th>
                        <th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Carriage ID</th>
                        <th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Carriage Position</th>
                        <th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Carriage Type</th>
                        <th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Timestamp</th></tr>

                    <c:forEach items="${list}" var="entry">
                        <tr><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry._id}"/>
                        </td><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry.train_id}"/>
                        </td><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry.coach_id}"/>
                        </td><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry.coach_position}"/>
                        </td><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry.coach_type}"/>
                        </td><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry.timestamp}"/>
                        </td></tr>
                    </c:forEach>
                </c:if>

                <c:if test="${querytype eq '2'}">
                    <tr><th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Mongo ID</th>
                        <th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Carriage ID</th>
                        <th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Seat ID</th>
                        <th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Priority</th>
                        <th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Table</th>
                        <th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Window</th>
                        <th style="border-style: solid; border-width: 1px; padding: 5px; background-color: #DFDFED;">Timestamp</th></tr>

                    <c:forEach items="${list}" var="entry">
                        <tr><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry._id}"/>
                        </td><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry.coach_id}"/>
                        </td><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry.seat_id}"/>
                        </td><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry.priority}"/>
                        </td><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry.table}"/>
                        </td><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry.window}"/>
                        </td><td style="border-style: solid; border-width: 1px; padding: 5px;">
                            <c:out value="${entry.timestamp}"/>
                        </td></tr>
                    </c:forEach>
                </c:if>
            </table>
    </div>
</body>
</html>