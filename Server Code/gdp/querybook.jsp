<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <link href="../css/querybook.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="header.jsp" %>
<body>
    <div class="container mx-auto" style="width: 90%;">
        <h2>Database Querybook</h2>
        <div class="row row-cols-3" style="margin: 30px;">
            <div class="col">
                <div class="card text-dark bg-light mb-3" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title"><b>View collected data</b></h5>
                        <p class="card-text">Here you may see the entire database of data collected by sensing devices.</p>
                        <form method="get" action="<%=request.getContextPath() %>/query">
                            <input type="hidden" name="database_name" value="collected_data">
                            <button type="submit" value="Query" class="btn btn-outline-primary btn-sm" style="float: right;">Query</button>
                        </form>
                    </div>
                    <div class="card-footer text-muted">
                        <b>Submit data to collection</b>
                        <p style="font-size: 12px;">To be used for testing purposes only.</p>
                        <form action="<%=request.getContextPath() %>/update.jsp">
                            <button class="btn btn-sm btn-outline-warning" style="float: right;">Update Manager</button>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card text-dark bg-light mb-3" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title"><b>View train formations</b></h5>
                        <p class="card-text">Here you may see the train formations recorded via web service requests.</p>
                        <form method="get" action="<%=request.getContextPath() %>/query">
                            <input type="hidden" name="database_name" value="train_composition">
                            <button type="submit" value="Query" class="btn btn-outline-primary btn-sm" style="float: right;">Query</button>
                        </form>
                    </div>
                    <div class="card-footer text-muted">
                        <p style="font-size: 12px;">This data should only be modified by administrators.</p>
                        <form action="<%=request.getContextPath() %>/update.jsp">
                            <button class="btn btn-sm btn-outline-warning" style="float: right;">Update Manager</button>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card text-dark bg-light mb-3" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title"><b>View carriage formations</b></h5>
                        <p class="card-text">Here you may see the carriage seat formations recorded via web service requests.</p>
                        <form method="get" action="<%=request.getContextPath() %>/query">
                            <input type="hidden" name="database_name" value="coach_composition">
                            <button type="submit" value="Query" class="btn btn-outline-primary btn-sm" style="float: right;">Query</button>
                        </form>
                    </div>
                    <div class="card-footer text-muted">
                        <p style="font-size: 12px;">This data should only be modified by administrators.</p>
                        <form action="<%=request.getContextPath() %>/update.jsp">
                            <button class="btn btn-sm btn-outline-warning" style="float: right;">Update Manager</button>
                        </form>
                    </div>
                </div>
            </div>

        </div>
  </div>
</body>
</html>