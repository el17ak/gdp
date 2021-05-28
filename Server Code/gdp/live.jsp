<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="../css/live.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="header.jsp" %>
<body>
    <div align="center">
        <div>
        ${train.departureStation} -> ${train.arrivalStation}
        ${train.departureTime} -> ${train.arrivalTime}
        </div>
        <table style="border: 1px solid black; width: 80px; height: 80px;">
            <tbody>
            <div>
                <c:forEach items="${seat-list}" var="entry">
                    <tr>
                        <td align="center" style="height: 40px; width: 40px;">
                            <div style="background: #ebebeb; border: 1px solid; border-radius: 6px; height: 80%; width: 80%; align-items:center; justify-content: center; display: flex;">
                                <div class="updatedLive" id="updatedLive">
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </div>
            </tbody>
        </table>
    </div>

        <script>
            $(document).ready(function() {
                setInterval(function(){
                $("#updatedLive").load('live.jsp #updatedLive', function() {
                    $.get('api/live', {carriage_id:${carriage_id}}, function(list){
                        if(list != null){
                            $.each(list, function(id, element){
                                if(element.status == 0){
                                    if(!($('#updatedLive').hasClass('freeClass'))){
                                        $('#updatedLive').removeClass();
                                        $('#updatedLive').addClass('freeClass');
                                    }
                                }
                                else if(element.status == 1){
                                    if(!($('#updatedLive').hasClass('occupiedClass'))){
                                        $('#updatedLive').removeClass();
                                        $('#updatedLive').addClass('occupiedClass');
                                    }
                                }
                                else{
                                    if(!($('#updatedLive').hasClass('unavailableClass'))){
                                        $('#updatedLive').removeClass();
                                        $('#updatedLive').addClass('unavailableClass');
                                    }
                                }
                            });
                        }
                        else{

                        }
                    });
                });
                }, 1000);
            });

        </script>
</body>
</html>