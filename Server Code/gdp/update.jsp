<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <link href="../css/querybook.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="header.jsp" %>
<body>
    <div class="container mx-auto" style="width: 90%;">
        <h2>Database Update Manager</h2>
        <div class="row row-cols-3" style="margin: 30px;">
            <div class="col">
                <div class="card border-warning mb-3" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title"><b>Modify collected data</b></h5>
                        <p class="card-text">Enter valid parameters for entry in database.</p>
                        <form method="post" action="<%=request.getContextPath() %>/api/live">
                            <input class="form-control form-control-sm my-2" type="text" name="train_id" placeholder="Train ID">
                            <input class="form-control form-control-sm my-2" type="text" name="coach_id" placeholder="Carriage ID">
                            <input class="form-control form-control-sm my-2" type="text" name="seat_id" placeholder="Seat ID">
                            <div class="form-check from-check-inline my-2">
                                <input class="form-check-input" type="radio" name="radioStatus" id="radio1" value="availableStatus">
                                <label class="form-check-label" for="radio1">Available</label>
                            </div>
                            <div class="form-check from-check-inline my-2">
                                <input class="form-check-input" type="radio" name="radioStatus" id="radio2" value="occupiedStatus">
                                <label class="form-check-label" for="radio2">Occupied</label>
                            </div>
                            <div class="form-check from-check-inline my-2">
                                <input class="form-check-input" type="radio" name="radioStatus" id="radio3" value="reservedStatus">
                                <label class="form-check-label" for="radio3">Reserved</label>
                            </div>
                            <button type="submit" value="Query" class="btn btn-warning btn-sm" style="float: right;">Submit data</button>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card border-danger mb-3" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title"><b>Update train formations</b></h5>
                        <p class="card-text">Enter valid parameters for train formation entry.</p>
                        <form method="post" action="<%=request.getContextPath() %>/api/composition/train">
                            <input class="form-control form-control-sm my-2" type="text" name="train_id" placeholder="Train ID">
                            <input class="form-control form-control-sm my-2" type="text" name="coach_id" placeholder="Carriage ID">
                            <input class="form-control form-control-sm my-2" type="text" name="coach_position" placeholder="Carriage Position">
                            <button type="submit" value="Query" class="btn btn-warning btn-sm" style="float: right;">Submit data</button>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card border-danger mb-3" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title"><b>Update carriage formations</b></h5>
                        <p class="card-text">Enter valid parameters for carriage formation entry.</p>
                        <form method="post" action="<%=request.getContextPath() %>/api/composition/coach">
                            <input class="form-control form-control-sm my-2" type="text" name="coach_id" placeholder="Carriage ID">
                            <input class="form-control form-control-sm my-2" type="text" name="seat_id" placeholder="Seat ID">
                            <button type="submit" value="Query" class="btn btn-warning btn-sm" style="float: right;">Submit data</button>
                        </form>
                    </div>
                </div>
            </div>

        </div>
  </div>
</body>
</html>