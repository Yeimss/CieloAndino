    <%@page import="com.models.Usuario"%>
    <%@ page session="true" %>
    <%
      Usuario usuario = (Usuario) session.getAttribute("usuario");
      if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
      }
    %>

    <nav class="navbar bg-dark border-bottom border-body" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="dashboard.jsp">Cielo Andino</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="dashboard.jsp">Home</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="reservas.jsp">Reservar</a>
              </li>
            </ul>
           <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link"><%= usuario.getNombre() %></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="LogoutServlet">Cerrar sesión</a>
                </li>
            </ul>
        </div>
      </div>
    </nav>