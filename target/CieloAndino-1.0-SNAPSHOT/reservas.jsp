<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>

<html>
<head>    
    <title>Reservas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">

</head>
<body>
    <%@ include file="/Layout/header.jsp" %>

    <div class="container">
        <div class="row">
            <h2 class="text-center">Reservar</h2>
            <div class="col-md-4 col-sm-12 col-xs-12">
                <label class="form-label">Fecha ingreso</label>
                <input class="form-control" type="date" name="fecha-ingreso" id="fecha-ingreso" required>
            </div>
            <div class="col-md-4 col-sm-12 col-xs-12">
                <label class="form-label">Fecha salida</label>
                <input class="form-control" type="date" name="fecha-salida" id="fecha-salida" required>
            </div>
            <div class="col-md-4 col-sm-12 col-xs-12 d-flex flex-column justify-content-end">
                <button class="btn btn-outline-success" type="button" id="btn-insert">Reservar</button>
            </div>
        </div>
        <div class="row mt-3">
            <div id="lista-reservas"></div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
    <script src="js/reservation.js"></script>
</body>
</html>
