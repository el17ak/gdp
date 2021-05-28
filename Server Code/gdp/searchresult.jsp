<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<%@ include file="header.jsp" %>
<body>
    <div class='container mx-auto' style='width: 90%;'>
        <h2>Search Results for ${searchterm}</h2>
        <table>
            <c:if test="${listsize < 1}">
                No results were found for your search.
            </c:if>
            <c:forEach items="${list}" var="train">
                <tr><td>
                    <a href="<%=request.getContextPath() %>/live?carriage_id=${train.coach_id}">Result</a>
                </td></tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
