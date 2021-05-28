<head>
    <title>TI2020: Group Design Project</title>
    <link href="../css/header.css" rel="stylesheet" type="text/css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
    <script>
    function updatemenu() {
      if (document.getElementById('responsive-menu').checked == true) {
        document.getElementById('menu').style.borderBottomRightRadius = '0';
        document.getElementById('menu').style.borderBottomLeftRadius = '0';
      }else{
        document.getElementById('menu').style.borderRadius = '0px';
      }
    }
    </script>
</head>
<body style="font-family: Arial, sans serif;">
    <header>
        <div style="float: right; color: white; margin-right: 4px; margin-top: 2px; font-size: 10px;">
        el17ak@leeds.ac.uk<br>el18j2t@leeds.ac.uk<br>el18zw@leeds.ac.uk<br>el18aaf@leeds.ac.uk</div>
        <div style="background: #293d3d; text-align: center; height: 70px; padding: 10px;">
            <h4 style="color: white; font-family: Arial, sans serif; margin-top: 10px; font-size: 20px;">Design Project 2020/2021: Group 5</h4>
        </div>
    </header>
  <div class="mx-auto" style="width: 98%" id="main-navbar">
    <nav class="navbar navbar-expand-lg navbar-light">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav" style="font-size: 15px;">
          <a class="nav-item nav-link active mx-3" href="http://77.68.95.246:9080">Home</a>
          <a class="nav-item nav-link mx-3" href="http://77.68.95.246:9080/querybook.jsp">Querybook</a>
          <a class="nav-item nav-link mx-3" href="http://77.68.95.246:9080/update.jsp">Update Manager</a>
        </div>
      </div>
    </nav>
  </div>
</body>
