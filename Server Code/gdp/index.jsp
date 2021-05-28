<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <link href="../css/querybook.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="header.jsp" %>
<body style="font-family: Arial, sans-serif;">
    <div class="container-fluid" style="height: 40px;">
    </div>

    <div class="container mx-auto" style="width: 80%">
        <h4>Debugging Options</h4>
        <div class="accordion accordion-flush py-4" id="debugAccordion">

            <div class="accordion-item">
                <h2 class="accordion-header" id="headingOne">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        <b>Access to Database Querybook</b>
                    </button>
                </h2>
                <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#debugAccordion">
                    <div class="accordion-body">
                        This page grants access to a wide variety of prepared queries for any data related to this project.
                        <a href="<%=request.getContextPath() %>/querybook.jsp">
                            <button class="btn btn-dark btn-sm" style="float: right; color: white;">Querybook</button>
                        </a>
                    </div>
                </div>
            </div>

            <div class="accordion-item">
                <h2 class="accordion-header" id="headingThree">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                        <b>Real-time display of testing device</b>
                    </button>
                </h2>
                <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#debugAccordion">
                    <div class="accordion-body">
                        The prototype testing device is setup for visualisation on this testing page.
                        <form method="get" action="<%=request.getContextPath() %>/live" style="float: right;">
                            <input type="hidden" name="carriage_id" value="15">
                            <button type="submit" class="btn btn-dark btn-sm" style="color: white;">Prototype Display</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="container-fluid" style="height: 40px;">
            </div>

        <div style="text-align: center;">
            <div class="container mx-auto" style="width: 50%">
            <h4 class="mx-auto"><b>Search for a train:</b></h4><br>
            <form method="get" action="<%=request.getContextPath() %>/search">
                <input type="text" placeholder="ID e.g. '17' or '15'" name="searchterm" required>
                <button type="submit" value="Search" class="btn btn-success btn-sm">Search</button>
            </form>
            </div>
        </div>

    </div>
</body>
</html>