<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
</head>
<body class="d-flex align-items-center">
  <div class="container d-flex justify-content-center">
    <form class="row w-50 p-4 border border-success rounded" action="LoginServlet" method="post">
        <div class="col-12">
          <h2 class="text-center">Iniciar Sesión</h2>
        </div>
        <div class="col-12">
            <label class="form-label">Correo</label>
            <input class="form-control" type="text" name="correo" required>
        </div>
        <div class="col-12">
            <label class="form-label">Contraseña</label>
            <input class="form-control" type="password" name="clave" required>
        </div>  
        <div class="col-12 text-end">
            <p><a href="register.jsp" class="link-success link-offset-2 link-underline-opacity-100-hover">¿No tienes cuenta? Regístrate aquí</a></p>
        </div>
        <div class="col-12">
            <input class="w-100 btn btn-outline-dark" type="submit" value="Entrar">
        </div>
        <p style="color:red">${error}</p>      
    </form>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
</body>
</html>
